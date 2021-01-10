package com.tapo.auto.controller.login;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    /**
     * 管理者権限のロール一覧.
     */
    private static final String[] ADMIN = {"ROLE_ADMIN"};

    /**
     * ログインフォーム.
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String loginForm(Model model) {
        return "login";
    }

    /**
     * ログイン後遷移トップページ.
     * @param model
     * @return
     */
    @RequestMapping(value = "/top")
    public String top(@Validated @ModelAttribute LoginForm model) {
	    // HttpSessionに情報格納している
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String userName = auth.getName();
        var authority = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Boolean authorityFlag = Arrays.asList(ADMIN).containsAll(authority);
        model.setName(userName);
        model.setAdministrator(authorityFlag);
        return "top";
    }
}
