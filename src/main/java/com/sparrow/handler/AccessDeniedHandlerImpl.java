package com.sparrow.handler;

import com.alibaba.fastjson.JSON;
import com.sparrow.utils.ResponseResult;
import com.sparrow.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/19 11:11
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足");
		String json = JSON.toJSONString(result);
		//处理异常
		WebUtils.renderString(httpServletResponse, json);
	}
}
