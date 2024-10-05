package com.FashionFlickApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FashionFlickApp.model.LoginRequest;
import com.FashionFlickApp.model.User;
import com.FashionFlickApp.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User newUser) {
		String response = userService.registerUser(newUser);
		if ("User already exists".equals(response)) {
			return new ResponseEntity<>(response, HttpStatus.CONFLICT); // 409 Conflict if user exists
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> getUser = userService.getAllUser();
		return new ResponseEntity<>(getUser, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        // Extract email and password from the request object
        String response = userService.loginUser(loginRequest);

        // Return response based on login success or failure
        if ("Login successful".equals(response)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);  // 401 if login fails
    }
	
}
