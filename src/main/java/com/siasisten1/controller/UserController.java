package com.siasisten1.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {
	 	@RequestMapping("/")
	 	 public String index (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException 
	    {
	 		if (authentication != null) {
				response.sendRedirect("/pengajuan/viewall");
			}
				
			return "login";
	    }

	 	@RequestMapping("login")
		public String login(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
			if (authentication != null) {
				response.sendRedirect("/pengajuan/viewall");
			}
			
			return "login";
		}

		@RequestMapping("admin")
		public String admin() {
			return "admin";
		}
}
