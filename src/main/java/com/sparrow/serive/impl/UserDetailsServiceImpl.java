package com.sparrow.serive.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sparrow.domin.LoginUser;
import com.sparrow.entity.User;
import com.sparrow.mapper.MenuMapper;
import com.sparrow.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/18 19:16
 **/
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
		queryWrapper.eq(User::getUserName, username);
		User user = userMapper.selectOne(queryWrapper);
		if (Objects.isNull(user)) {
			throw new RuntimeException("用户名未找到");
		}
		List<String> list = menuMapper.selectPermsByUserId(user.getId());
		//把数据封装成UserDetails
		return new LoginUser(user,list);
	}
}
