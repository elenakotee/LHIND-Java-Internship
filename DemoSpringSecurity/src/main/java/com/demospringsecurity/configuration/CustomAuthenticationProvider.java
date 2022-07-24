package com.demospringsecurity.configuration;

import com.demospringsecurity.entity.Authority;
import com.demospringsecurity.entity.User;
import com.demospringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String email = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = userRepository.findByEmail(email).orElseThrow(() ->
				new UsernameNotFoundException("User not found"));

		if(passwordEncoder.matches(password,user.getPassword())) {
			//use when configuring authorities
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getAuthority().getAuthority()));
			return new UsernamePasswordAuthenticationToken(email, password, authorities);

			//do when configuring roles
			//return new UsernamePasswordAuthenticationToken(email, password, getAuthorities(user.getAuthorities()));
		} else {
			throw new BadCredentialsException("Invalid credentials");
		}
	}

	//declare when configuring roles
	/*private Set<SimpleGrantedAuthority> getAuthorities(Set<Authority> authorities) {
		Set<SimpleGrantedAuthority> list = new HashSet<>();
		for(Authority auth: authorities) {
			list.add(new SimpleGrantedAuthority(auth.getAuthority()));
		}
		return list;
	}*/

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
