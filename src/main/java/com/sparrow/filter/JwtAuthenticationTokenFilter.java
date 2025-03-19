package com.sparrow.filter;

import com.sparrow.domin.LoginUser;
import com.sparrow.utils.JwtUtil;
import com.sparrow.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: Sparrow
 * @Description: ☝应该保证每个请求只执行一次
 * @DateTime: 2025/3/18 22:44
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private RedisCache redisCache;

     //获取token
	//解析token 获取userId
	//从redis中获取用户信息
	//存入SecurityContextHolder
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		String token = httpServletRequest.getHeader("token");
		if (StringUtils.hasText(token)) {
			try {
				// 解析 Token 中的用户 ID
				String userId = JwtUtil.parseJWT(token).getSubject();

				// 从 Redis 中获取用户信息
				LoginUser loginUser = redisCache.getCacheObject("login:" + userId);
				if (Objects.nonNull(loginUser)) {
					// 创建 Authentication 对象
					UsernamePasswordAuthenticationToken authenticationToken =
							new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());

					// 设置到 SecurityContextHolder 中
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			} catch (Exception e) {
				// Token 解析失败，可能是无效 Token
				e.printStackTrace();
				throw new RuntimeException("Authentication Failed");
			}
		}


		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
