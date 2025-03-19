package com.sparrow.handler;

import com.alibaba.fastjson.JSON;
import com.sparrow.utils.ResponseResult;
import com.sparrow.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/19 11:06
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
		String json = JSON.toJSONString(result);
		//处理异常
		WebUtils.renderString(httpServletResponse, json);

	}
}
