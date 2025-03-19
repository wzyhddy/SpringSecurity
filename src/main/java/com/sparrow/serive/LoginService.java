package com.sparrow.serive;

import com.sparrow.entity.User;
import com.sparrow.utils.ResponseResult;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/18 19:51
 **/
public interface LoginService {
	ResponseResult login(User user);

	ResponseResult logut();
}
