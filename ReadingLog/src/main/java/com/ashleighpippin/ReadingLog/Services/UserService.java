package com.ashleighpippin.ReadingLog.Services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ashleighpippin.ReadingLog.Models.LoginUser;
import com.ashleighpippin.ReadingLog.Models.User;
import com.ashleighpippin.ReadingLog.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public User registerUser(User u, BindingResult result) {
		//Reject if email already present in the database
		Optional<User> exists = userRepo.findByEmail(u.getEmail());
		if(exists.isPresent()) {
			result.rejectValue("email", "Matches", "Email already in use.");
		}
		//Reject if password and confirm don't match
		if(!u.getConfirm().equals(u.getPassword())) {
			result.rejectValue("confirm", "Matches", "Passwords do not match.");
		}
		
		if(!isValidEmailFormatReg(u.getEmail())) {
			result.rejectValue("email", "Matches", "Please use proper email format.");
		}
		
		if(!isValidPassword(u.getPassword())) {
			result.rejectValue("password", "Matches", "Passwords must contain at least one special character and one number.");
		}
		
		if(result.hasErrors()) {
			return null;
		}
		
		//hash & set password
		String hashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
		u.setPassword(hashed);

		return userRepo.save(u);
	}
	
	private boolean isValidEmailFormatReg(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return email != null && email.matches(emailRegex);
	}
	
	private boolean isValidPassword(String password) {
	    // Password must contain at least one special character and one number
	    return password != null && password.matches(".*[!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*") && password.matches(".*\\d.*");
	}
	
	
	public User login(LoginUser login, BindingResult result) {
	
		//Find user in DB by email
		//Reject if NOT present
		Optional<User> exists = userRepo.findByEmail(login.getEmail());
//		User user = null;
		if(exists.isEmpty()) {
			result.rejectValue("email", "Matches", "Email does not exist.");
			return null;
		}
		User user = exists.get();
		//Reject if BCrypt pw match fails
		if(!BCrypt.checkpw(login.getPassword(), exists.get().getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Password");
			return null;
		}
		
		return user;
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public User getUserById(Long id) {
		Optional<User> optUser = userRepo.findById(id);
		return optUser.orElse(null);
	}	
}

