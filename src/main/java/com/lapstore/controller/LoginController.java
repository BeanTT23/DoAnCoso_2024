package com.lapstore.controller;

import com.lapstore.entity.User;
import com.lapstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String welcomePage(Model model) {
		User user = UserSession.getCurrentUser(userService);

		if (user != null && user.getRole().equalsIgnoreCase("admin")) {
			UserSession.getLoggedUserInfo(userService, model);
 
			model.addAttribute("pageTitle", "G16 - Quản lí đơn hàng");
			return "redirect:/admin/all";
		}

		UserSession.getLoggedUserInfo(userService, model);

		model.addAttribute("pageTitle", "G16 Lap Store");

		return "view_customer/index";
	} 

	@GetMapping("/login")
	public String loginPage() {
		if(UserSession.getCurrentUser(userService) != null)
			return "redirect:/";
		
		return "view_customer/login";
	}
}