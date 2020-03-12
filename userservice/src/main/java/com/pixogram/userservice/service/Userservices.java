package com.pixogram.userservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.userservice.entity.Authorities;
import com.pixogram.userservice.entity.Users;
import com.pixogram.userservice.exception.UserNotFoundException;
import com.pixogram.userservice.model.SearchedUserModel;
import com.pixogram.userservice.model.SearchedUserModelList;
import com.pixogram.userservice.model.UserInput;
import com.pixogram.userservice.model.UserOutput;
import com.pixogram.userservice.model.Userid;
import com.pixogram.userservice.repository.AuthorityRepository;
import com.pixogram.userservice.repository.IUsersCustomRepo;
import com.pixogram.userservice.repository.UsersRepository;

@Service
public class Userservices implements IUserServices{

	@Autowired
	private UsersRepository userRepository;
	
	
	
	@Autowired
	private IUsersCustomRepo customRepoImp;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public List<Users> getall(){
		List<Users> records=this.userRepository.findAll();
		return records;
		
	}
	public Optional<Users> getWithId(Integer id){
		Optional<Users> record= this.userRepository.findById(id);
		return record;
		
	}
	
	public void saveuser(UserInput user) {
		Users data = new Users();
		//Authorities auth = new Authorities();
		//auth.setUsername(user.getUsername());
		//auth.setAuthority("ROLE_USER");
		// data.setId(user.getId());
		data.setUserName(user.getUsername());
		data.setFirstName(user.getFname());
		data.setLastName(user.getLname());
		data.setEmail(user.getUemail());
		data.setDob(user.getDob());
		data.setPassword("{noop}" + user.getPassword());
		data.setProfile(user.getProfile());
		data.setEnabled(true);
		this.userRepository.save(data);
		
		// add authority
		Authorities role = new Authorities(user.getUsername(), "ROLE_USER");
		this.authorityRepository.save(role);
	}
	
	public void updateuser(UserOutput user) {
		Users data = new Users();
		//Authorities auth = new Authorities();
		//auth.setUsername(user.getUsername());
		//auth.setAuthority("ROLE_USER");
		data.setId(user.getId());
		data.setUserName(user.getUsername());
		data.setFirstName(user.getFname());
		data.setLastName(user.getLname());
		data.setEmail(user.getUemail());
		data.setDob(user.getDob());
		data.setPassword("{noop}"+user.getPassword());
		data.setProfile(user.getProfile());
		data.setEnabled(true);
		this.userRepository.save(data);
		//Authorities auth =new Authorities(user.getUsername(),"ROLE_USER");
		//this.authorityRepository.save(auth);
	}
	@Override
	public Userid getUserId(String username) {
		// TODO Auto-generated method stub
		try {
		//return this.customRepoImp.getUserId(username);
		List<Users> user = this.userRepository.findByUserName(username);
		Users data = user.get(0);
		Userid result = new Userid(data.getId(),data.getFirstName(),data.getLastName(),data.getProfile());
		return result;
		}catch(Exception e) {
			throw new UserNotFoundException("id not found in service");
		}
	}
	@Override
	public List<String> getUsernames() {
		// TODO Auto-generated method stub
		return this.customRepoImp.getUsernames();
	}
	@Override
	public void deleteauth(String username) {
		// TODO Auto-generated method stub
		Authorities auth = new Authorities(username,"ROLE_USER");
		this.authorityRepository.delete(auth);
	}
	@Override
	public Integer getLikesByMediaId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SearchedUserModelList searchUsers(String searchText) {
		// TODO Auto-generated method stub
		// retrieved matching name list from db
		List<Users> users = this.userRepository.findByUserNameContaining(searchText);
		// transforming into model object
		List<SearchedUserModel> searchedUsers =
				users.stream()
					.map(user -> {
						SearchedUserModel searchedUser = 
								new SearchedUserModel(user.getId(), 
													 user.getFirstName() + " " + user.getLastName(), 
													 user.getProfile(), 
													 false);
						return searchedUser;		
					}).collect(Collectors.toList());
		// wrapping it into object
		SearchedUserModelList list = new SearchedUserModelList();
		list.setUserList(searchedUsers);
		return list;
	}
	@Override
	public String getCommentedUsername(Integer userId) {
		// TODO Auto-generated method stub
		
		return this.customRepoImp.getUsernameById(userId);
	}
	
	
}
