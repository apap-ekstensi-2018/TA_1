package com.siasisten1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {
	 	@RequestMapping("/")
	    public String index ()
	    {
	        return "login";
	    }

		@RequestMapping("login")
		public String login(){
			return "login";
		}

		@RequestMapping("admin")
		public String admin() {
			return "admin";
		}
}
