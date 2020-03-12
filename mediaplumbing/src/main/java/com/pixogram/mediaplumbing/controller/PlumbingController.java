package com.pixogram.mediaplumbing.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.mediaplumbing.feignproxy.ActionServiceProxy;
import com.pixogram.mediaplumbing.feignproxy.CommentServiceProxy;
import com.pixogram.mediaplumbing.feignproxy.MediaServiceProxy;
import com.pixogram.mediaplumbing.feignproxy.NewsFeedProxy;
import com.pixogram.mediaplumbing.feignproxy.UserServiceproxy;
import com.pixogram.mediaplumbing.model.ActionCountAndStatus;
import com.pixogram.mediaplumbing.model.ActionsCountModel;
import com.pixogram.mediaplumbing.model.Comments;
import com.pixogram.mediaplumbing.model.CommentsCountModel;
import com.pixogram.mediaplumbing.model.CommentsList;
import com.pixogram.mediaplumbing.model.CommentsResponse;
import com.pixogram.mediaplumbing.model.CompleteProfileData;
import com.pixogram.mediaplumbing.model.GalleryResponse;
import com.pixogram.mediaplumbing.model.MediaData;
import com.pixogram.mediaplumbing.model.MediaDataModel;
import com.pixogram.mediaplumbing.model.MediaDetailOfUser;
import com.pixogram.mediaplumbing.model.MediaDetails;
import com.pixogram.mediaplumbing.model.MediaModel;
import com.pixogram.mediaplumbing.model.NewsFeedModel;
import com.pixogram.mediaplumbing.model.ProfileData;
import com.pixogram.mediaplumbing.model.ProfileMediaResponce;


@RestController
public class PlumbingController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MediaServiceProxy mediaProxy;
	
	@Autowired
	private CommentServiceProxy commentProxy;
	
	@Autowired
	private ActionServiceProxy actonProxy;
	
	@Autowired
	private UserServiceproxy userProxy;
	
	@Autowired
	private NewsFeedProxy newsProxy;
	
	@PostMapping("/media")
	public void post(@RequestParam("file") MultipartFile file,@RequestParam("url") String url,@RequestParam("title") String title,
			@RequestParam("description") String description,@RequestParam("tags") String tags,@RequestParam("userid") String userid,
			@RequestParam("type") String type) {
		logger.info("title : "+title);
		logger.info("userid: "+userid);
		logger.info("url : "+url);
		logger.info("desc : "+description);
		logger.info("tags : "+tags );
		logger.info("type : "+type);
		logger.info(file.getOriginalFilename() + " : "  +file.getSize());
		// create a model using other info
		MediaDataModel model = new MediaDataModel(Integer.parseInt(userid),url,title,description,tags,type);
		//this.restTemplate.getForObject(MEDIA_URL, MediaDataModel.class);
		// 1. call media microservice to save data in db
		this.mediaProxy.saveData(model);
		// 2. call media microservice to upload file
		this.mediaProxy.save(file);//,model);
		// 3. Add Entry in newsfeed
		String feed = "Media file uploaded";
		NewsFeedModel newsFeed = new NewsFeedModel(Integer.parseInt(userid), feed);
		this.newsProxy.save(newsFeed);
		
	}
	//getting all media by userid
	@GetMapping("/media/{userId}")
	public ResponseEntity<MediaDetailOfUser> getAllById(@PathVariable Integer userId){
		logger.info("user id : => : "+userId);
		ResponseEntity<MediaModel> media = this.mediaProxy.getall(userId);
		List<GalleryResponse> filelist = new ArrayList<GalleryResponse>();
		logger.info("yet to start to for loop");
		logger.info("sample---->"+media.getBody());
		logger.info("sample2--->"+media.getBody().getClass());
		logger.info("this is info =============>>>"+media.getBody().getMedialist());
		logger.info("this is info =============>>>"+media.getBody().getMedialist());
		for(MediaData data :media.getBody().getMedialist()) {
			logger.info("loop started");
			Integer comments = this.commentProxy.getCountById(data.getId()).getBody().getComments();
			Integer likes = this.actonProxy.getLikes(data.getId()).getBody().getLikes();
			Integer unlikes = this.actonProxy.getLikes(data.getId()).getBody().getUnlikes();
			
			GalleryResponse response = new GalleryResponse(data.getId(), data.getUserId(), data.getTitle(), data.getDescription(), data.getTags(), data.getType(), data.getUrl(), likes, unlikes, comments);
			
			filelist.add(response);
		}
		
		MediaDetailOfUser resultlist = new MediaDetailOfUser();
		resultlist.setFilelist(filelist);
		ResponseEntity<MediaDetailOfUser> result = new ResponseEntity<MediaDetailOfUser>(resultlist,HttpStatus.OK);	
		
		return result;
	}
	/*
	 @GetMapping("/media/{userId}")
	public ResponseEntity<MediaModel> getAllById(@PathVariable Integer userId){
		
		
		return this.mediaProxy.getall(userId);
	}
	 */
	//getting details of media by mediaId
	@GetMapping("/mediadetails/{mediaId}")
	public ResponseEntity<MediaDetails> getMedialDetailsById(@PathVariable Integer mediaId){
		MediaData media = this.mediaProxy.getDetailsBymediaId(mediaId).getBody();
		Integer likes = this.actonProxy.getLikes(media.getId()).getBody().getLikes();
		Integer unlikes = this.actonProxy.getLikes(media.getId()).getBody().getUnlikes();
		Integer count = this.commentProxy.getCountById(media.getId()).getBody().getComments();
		logger.info("Count ======>>>>>>    "+count);
		CommentsList comments = new CommentsList();
		if(count == 0) {
			comments.setCommentsList(null);
		}	
		else {
			comments = this.commentProxy.getCommentsById(media.getId()).getBody();
		}
		List<CommentsResponse> response = new ArrayList<CommentsResponse>();
		for(Comments comment : comments.getCommentsList()) {
			String name = this.userProxy.getCommentedUsername(comment.getUserId()).getBody();
			CommentsResponse data = new CommentsResponse(name, comment.getComments(), ""+comment.getCreatedOn());
			response.add(data);
		}
		MediaDetails data = new MediaDetails(media.getId(), media.getUserId(), media.getTitle(), media.getDescription(), media.getTags(), media.getType(), media.getUrl(), likes, unlikes, response);
		ResponseEntity<MediaDetails> result = new ResponseEntity<MediaDetails>(data,HttpStatus.OK);	
		
		return result;
	}
	
	//getting the count of likes and dislike
	@GetMapping("/action/{id}")
	public ResponseEntity<ActionsCountModel> getalllikes(@PathVariable Integer id) {
		ResponseEntity<ActionsCountModel> result = this.actonProxy.getLikes(id);
		return result;
	}
	
	//getting comments count
	@GetMapping("/comment/{id}")
	public ResponseEntity<CommentsCountModel> getCommentsCount(@PathVariable Integer id){
		ResponseEntity<CommentsCountModel> count = this.commentProxy.getCountById(id);
		return count;
	}
	
	@GetMapping("/profile/{userId}/mine/{mineId}")
	public ResponseEntity<CompleteProfileData> getProfileByUserId(@PathVariable Integer userId,@PathVariable Integer mineId) {
		CompleteProfileData completeProfile = new CompleteProfileData();
		ProfileData profiledata = this.userProxy.getProfileById(userId).getBody();
		ResponseEntity<MediaModel> media = this.mediaProxy.getall(userId);
		List<ProfileMediaResponce> filelist = new ArrayList<ProfileMediaResponce>();
		for(MediaData data :media.getBody().getMedialist()) {
			logger.info("loop started");
			Integer comments = this.commentProxy.getCountById(data.getId()).getBody().getComments();
			ActionCountAndStatus actions = this.actonProxy.getLikesAndStatus(data.getId(), mineId).getBody();
			Integer likes = actions.getLikes();
			Integer unlikes = actions.getUnlikes();
			Boolean status = actions.getStatus();
			ProfileMediaResponce response = new ProfileMediaResponce(data.getId(), data.getUserId(), data.getTitle(), data.getDescription(), data.getTags(), data.getType(), data.getUrl(), likes, unlikes, status, comments);
			
			filelist.add(response);
		}
		//setting complete profile
		completeProfile.setUserid(profiledata.getUserid());
		completeProfile.setUsername(profiledata.getUsername());
		completeProfile.setName(profiledata.getName());
		completeProfile.setProfile(profiledata.getProfile());
		completeProfile.setMediaList(filelist);
		
		ResponseEntity<CompleteProfileData> result = new ResponseEntity<CompleteProfileData>(completeProfile,HttpStatus.OK);	
		
		return result;
		
	}
	
}
