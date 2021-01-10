package com.tapo.auto.controller.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping(value = "/success")
    public String success(Model model) {
	// HttpSessionに情報格納している
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userName = auth.getName();
        model.addAttribute("userName", userName);
        return "success.html";
    }
}
