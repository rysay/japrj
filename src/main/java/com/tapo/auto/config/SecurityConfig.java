package com.tapo.auto.config;

import javax.sql.DataSource;

import com.tapo.auto.service.authentication.UserDetailsServiceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsServiceImple userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 静的ファイルには認証をかけない
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/favicon.ico",
                "/css/**",
                "/js/**",
                "/images/**",
                "/fonts/**",
                "/shutdown");
    }

    /**
     * UserDetailsServiceインターフェースを実装した独自の認証レルムを使用する設定
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 認証無しの場合アクセス不許可
        http.authorizeRequests()
                .anyRequest().authenticated();
        // ログイン制御
        http.formLogin()
                //.loginProcessingUrl("/signin")//ログイン処理をするURL
                //.loginPage("/loginForm")//ログイン画面のURL
                //.failureUrl("/login?error")//認証失敗時のURL
                .successForwardUrl("/success")//認証成功時のURL
                .usernameParameter("name")//ユーザのパラメータ名
                .passwordParameter("password")//パスワードのパラメータ名
                .permitAll();
        // ログアウト
        http.logout()
                .logoutUrl("/logout**")//ログアウト時のURL（今回は未実装）
                .logoutSuccessUrl("/login")//ログアウト成功時のURL
                .permitAll();
    }
}