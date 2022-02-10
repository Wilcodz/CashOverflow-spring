package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.UserAccountDto;
import com.revature.model.UserAccount;
import com.revature.service.LoginService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://3.92.176.100"})
public class LoginController {
	LoginService serv;

	@Autowired
	public LoginController(LoginService serv) {
		this.serv = serv;
	}

	/**
	 * Checks if the User name & password matches credential in the database
	 * 
	 * @param username
	 * @param password
	 * @return login User
	 * 
	 * @author Emmanuel Sosa
	 */
	@PostMapping(value = "/login")
	public UserAccountDto login(@RequestBody UserAccount req) {
		System.out.println(req.getUsername());
		UserAccount user = serv.login(req.getUsername(), req.getPassword());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Credentials");
		}
		return new UserAccountDto(user);

	}
}


