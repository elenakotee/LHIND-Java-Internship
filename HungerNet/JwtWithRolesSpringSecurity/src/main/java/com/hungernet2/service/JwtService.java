package com.hungernet2.service;

import com.hungernet2.model.entity.JwtRequest;
import com.hungernet2.model.entity.JwtResponse;
import com.hungernet2.repository.UserRepository;
import com.hungernet2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.hungernet2.model.entity.*;

import java.util.HashSet;
import java.util.Set;

//could also be names customuserdetailsservice
@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("Username is not valid")
		);
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getUserPassword(),
				getAuthorities(user)
		);

	}

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String username = jwtRequest.getUsername();
		String userPassword = jwtRequest.getUserPassword();
		authenticate(username, userPassword);

		final UserDetails userDetails = loadUserByUsername(username);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		User user = userRepository.findByUsername(username).get();
		return new JwtResponse(user, newGeneratedToken);

	}

	private void authenticate(String username, String userPassword) throws Exception {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userPassword));
	}

	private Set getAuthorities(User user) {
		Set authorities = new HashSet();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}

	public static String getCurrentUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
