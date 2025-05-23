package com.appsdeveloperblog.app.ws.userservice;

import java.util.Map;

import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;

public interface UserService {

	UserRest createUser(UserDetailsRequestModel userDetails);

	Map<String, UserRest> getUsers();

}
