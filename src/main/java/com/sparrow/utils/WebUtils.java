package com.sparrow.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/17 22:04
 **/
public class WebUtils {
	public static String renderString(HttpServletResponse response, String string) {
		try {
			response.setStatus(200);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
