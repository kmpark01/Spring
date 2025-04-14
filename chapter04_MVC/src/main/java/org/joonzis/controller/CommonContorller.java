package org.joonzis.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CommonContorller {
	
	@GetMapping("/accessError")
	public String accessDeined(Authentication auth, Model model) {
		log.info("Access Denied : " + auth);
		model.addAttribute("msg", "AccessDenied");
		return "/accessError";
	}
	
	@GetMapping("/customLogin")
	public String loginInput(String err, String logout, Model model) {
		log.info("error : " + err);
		log.info("logout : " + logout);
		
		if(err != null) {
			model.addAttribute("error", "Login Error Check your Account");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "Logout!!!");
		}
		
		return "/customLogin";
	}
	
	@GetMapping("/customLogout")
	public void logoutGET() {
		log.info("custom logout");
	}
	
	@ResponseBody
	@GetMapping("/api/currentUser")
	public Authentication getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
