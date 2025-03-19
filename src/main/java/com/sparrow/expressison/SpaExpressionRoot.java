package com.sparrow.expressison;

import com.sparrow.domin.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Sparrow
 * @Description: 自定义权限校验方法
 * @DateTime: 2025/3/19 12:40
 **/
@Component
public class SpaExpressionRoot {

	public boolean hasAuthority(String authority) {
		//取消当前用户的权限
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		List<String> permissions = loginUser.getPermissions();
		return permissions.contains(authority);
	}
}
