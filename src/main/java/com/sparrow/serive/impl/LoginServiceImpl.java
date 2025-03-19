package com.sparrow.serive.impl;

import com.sparrow.domin.LoginUser;
import com.sparrow.entity.User;
import com.sparrow.serive.LoginService;
import com.sparrow.utils.JwtUtil;
import com.sparrow.utils.RedisCache;
import com.sparrow.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/18 19:51
 **/
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RedisCache redisCache;


	@Override
	public ResponseResult login(User user) {
		//AuthenticationManager authenticate进行用户认证
		UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

		Authentication authenticate = authenticationManager.authenticate(passwordAuthenticationToken);
		//如果认证没通过，给出提示
		if (Objects.isNull(authenticate)) {
			throw new RuntimeException("Authentication Failed");
		}

		//如果认证通过了使用userId生成一个jwt存入ResponseResult
		LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
		Long id = loginUser.getUser().getId();
		String jwt = JwtUtil.createJWT(id.toString());
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("token", jwt);
		redisCache.setCacheObject("login:" + id, loginUser);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		//把完整的用户信息存入Redis，userid作为key
		return new ResponseResult(200, "登录成功", hashMap);
	}

	@Override
	public ResponseResult logut() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		long id = loginUser.getUser().getId();
		redisCache.deleteObject("login:" + id);
		return new ResponseResult(200, "注销成功");
	}
}
