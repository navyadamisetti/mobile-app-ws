package com.appsdeveloperblog.app.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import com.appsdeveloperblog.app.ws.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {

	Map<String, UserRest> users;
	Utils utils;

	public UserServiceImpl() {
		// if we remove default constructor, @Autowired is not required for the below constructor
		// Spring auto-wires it automatically
	}

	// Construction Injection
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}

	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {

		UserRest returnValue = new UserRest();
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		returnValue.setEmail(userDetails.getEmail());

		String userId = utils.generateUserId();
		returnValue.setUserId(userId);

		if (users == null)
			users = new HashMap<>();
		users.put(userId, returnValue);

		return returnValue;
	}
	
	public Map<String, UserRest> getUsers(){
		return users;
	}
}
