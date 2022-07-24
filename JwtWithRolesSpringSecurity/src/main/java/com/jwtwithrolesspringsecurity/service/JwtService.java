package com.jwtwithrolesspringsecurity.service;

import com.jwtwithrolesspringsecurity.entity.JwtRequest;
import com.jwtwithrolesspringsecurity.entity.JwtResponse;
import com.jwtwithrolesspringsecurity.entity.User;
import com.jwtwithrolesspringsecurity.repository.UserRepository;
import com.jwtwithrolesspringsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
		User user = userRepository.findById(username).get();

		if(user != null) {
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(),
					user.getUserPassword(),
					getAuthorities(user)
			);
		} else {
			throw new UsernameNotFoundException("Username is not valid");
		}
	}

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String username = jwtRequest.getUsername();
		String userPassword = jwtRequest.getUserPassword();
		authenticate(username, userPassword);

		final UserDetails userDetails = loadUserByUsername(username);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		User user = userRepository.findById(username).get();
		return new JwtResponse(user, newGeneratedToken);

	}

	private void authenticate(String username, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userPassword));
		} catch (DisabledException e) {
			throw new Exception("User is disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad credentials from user");
		}

	}

	private Set getAuthorities(User user) {
		Set authorities = new HashSet();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}
}
