package com.tapo.auto.service.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.tapo.auto.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImple implements UserDetailsService {
    /**
     * パスワードエンコーダ.
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * ユーザ取得用リポジトリ.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * ユーザ情報を取得・成型.
     * @param userName ユーザ名
     * @return ユーザ情報
     */
    @Override
    @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        if (Objects.isNull(userName)) {
			throw new UsernameNotFoundException("username was not set");
        }

        var user = userRepository.findByName(userName);

        if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("userName" + userName + "was not found in the database");
        }

		List<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority(user.getUserRole().getName()));
 
		return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                authorityList);
    }
}