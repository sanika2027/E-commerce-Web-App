package com.FashionFlickApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FashionFlickApp.model.LoginRequest;
import com.FashionFlickApp.model.User;
import com.FashionFlickApp.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	
	public String registerUser(User user) {
		Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
		if (existingUser.isPresent()) {
			return "User already exists";
		}
		userRepo.save(user);
		return "User registered successfully";
	}

	public List<User> getAllUser() {
		return userRepo.findAll();
	}
	
	public String loginUser(LoginRequest loginRequest) {
        // Extract email and password from the LoginRequest object
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Check if the user exists by email and password
        Optional<User> userOpt = userRepo.findByEmailAndPassword(email, password);

        if (!userOpt.isPresent()) {
            return "Invalid email or password";  // Return message if credentials are wrong
        }

        return "Login successful";  // Return success message if both email and password match
    }
}
