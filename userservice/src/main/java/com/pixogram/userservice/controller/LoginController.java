package com.pixogram.userservice.controller;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.pixogram.userservice.entity.Users;
import com.pixogram.userservice.model.RegisteredData;
import com.pixogram.userservice.model.ResponseData;
import com.pixogram.userservice.model.UserInput;
import com.pixogram.userservice.model.Usernames;
import com.pixogram.userservice.repository.UsersRepository;
import com.pixogram.userservice.service.IUserServices;
import com.pixogram.userservice.service.StorageService;

@CrossOrigin("*")
@RestController
public class LoginController {
	
	// dependency
	@Autowired
	private IUserServices userService;
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private StorageService store;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// testing end-point
	@GetMapping("/login")
	public ResponseEntity<ResponseData> login(HttpServletRequest request) {
		// if called then credentials are valid
		
		String authorization = request.getHeader("Authorization");
		String[] values = null;
		
		if (authorization != null && authorization.startsWith("Basic")) {
		    // Authorization: Basic base64credentials
		    String base64Credentials = authorization.substring("Basic".length()).trim();
		    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
		    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
		    // credentials = username:password
		    values = credentials.split(":", 2);
		}
		
		
		logger.info("Logged In...");
		logger.info(values[0]);
		logger.info(values[1]);
        
        Users user = this.userRepository.findByUserName(values[0]).get(0);
        logger.info("User : " + user);
        // add any additional information like firstname, lastname, profilepic etc
		ResponseData data = new ResponseData("Welcome!!!", System.currentTimeMillis(), user.getProfile(),user.getFirstName(),user.getLastName(),user.getId());

		ResponseEntity<ResponseData> response = 
					new ResponseEntity<ResponseData>(data, HttpStatus.OK);
		
		return response;
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<RegisteredData> register(@RequestParam("file") MultipartFile file,@RequestParam("fname") String fname,@RequestParam("lname") String lname,
			@RequestParam("uname") String username,@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("dob") String dob,
			@RequestParam("profile") String profile) {
		// if called then credentials are valid
		logger.info("Registration...");
		UserInput userInput = new UserInput(username,fname,lname,email,password,LocalDate.parse(dob),profile);
		logger.info("email= "+userInput.getUemail());
		
		this.userService.saveuser(userInput);
		this.store.store(file);
		RegisteredData data = new RegisteredData("Welcome!!!", System.currentTimeMillis(),"Registered");

		ResponseEntity<RegisteredData> response = 

					new ResponseEntity<RegisteredData>(data, HttpStatus.OK);
		return response;

}
	@GetMapping("/usernames")
	public ResponseEntity<Usernames> getUserNames(){
		List<String> names = new ArrayList<String>();
		names = this.userService.getUsernames();
		Usernames data = new Usernames(names);
		ResponseEntity<Usernames> result = new ResponseEntity<Usernames>(data,HttpStatus.OK);
		return result;
		
	}
	/*
	@DeleteMapping("/authority/{username}")
	public boolean dleteauth(@PathVariable String username) {
		this.userService.deleteauth(username);
		return true;
	}
	*/
}