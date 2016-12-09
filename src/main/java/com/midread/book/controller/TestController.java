package com.midread.book.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("versionController")
public class TestController {
	@Value("#{config.test}")
	protected String test;
	
	@RequestMapping(value="/test")
	public @ResponseBody Object version(HttpServletRequest request){
		System.out.println(test);
		return "test";
	}
}
