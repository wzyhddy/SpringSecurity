package com.sparrow.controller;

import com.sparrow.entity.User;
import com.sparrow.serive.LoginService;
import com.sparrow.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/18 19:47
 **/
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/user/login")
	public ResponseResult login(@RequestBody User user) {
		ResponseResult result = loginService.login(user);
		return result;
	}

	@PostMapping("/user/logout")
	public ResponseResult logout() {
		return loginService.logut();
	}
}
