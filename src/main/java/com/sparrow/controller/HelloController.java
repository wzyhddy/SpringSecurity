package com.sparrow.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/17 20:31
 **/
@RestController
public class HelloController {
	@RequestMapping("/hello")
	@PreAuthorize("hasAuthority('system:dept:list')")
	public String sayHello() {
		return "Hello World";
	}
}
