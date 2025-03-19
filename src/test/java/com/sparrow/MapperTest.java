package com.sparrow;

import com.sparrow.entity.User;
import com.sparrow.mapper.MenuMapper;
import com.sparrow.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/17 23:03
 **/
@SpringBootTest
public class MapperTest {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private MenuMapper menuMapper;

	@Test
	public void testUserMapper() {
		List<User> users = mapper.selectList(null);
		System.out.println(users);
	}

	@Test
	public void testBCyptPasswordEncoder() {
//		String encode = encoder.encode("123456");
		System.out.println(encoder.matches("123456", "$2a$10$eJhb8V0TdkIEeoqWPwVOgu5KprVGkZIklTzT7z2R58FOMg6quQqsa"));
	}

	@Test
	public void testSelectPermsByUserId() {
		List<String> stringList = menuMapper.selectPermsByUserId(1000L);
		System.out.println(stringList);
	}
}
