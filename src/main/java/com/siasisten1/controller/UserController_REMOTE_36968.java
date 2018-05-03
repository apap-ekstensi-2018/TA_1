package com.siasisten1.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class UserController {

	@RequestMapping("/login")
	public String login()
	{
		return "";
	}
	
	@RequestMapping("/logout")
	public String logout()
	{
		return "";
	}
	
	@RequestMapping("/user")
	public String user()
	{
		return "";
	}
}
