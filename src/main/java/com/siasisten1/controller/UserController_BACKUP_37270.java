<<<<<<< HEAD
package com.siasisten1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {
	 	@RequestMapping("/")
	    public String index ()
	    {
	        return "";
	    }
	    
		@RequestMapping("login")
		public String login() {
			return "login";
		}
		
		@RequestMapping("admin")
		public String admin() {
			return "admin";
		}
}
=======
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
>>>>>>> a728cbf2b574a861dccd264fa2d186b7f5d33f50
