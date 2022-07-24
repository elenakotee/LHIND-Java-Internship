package com.jwtwithrolesspringsecurity.configuration;

import com.jwtwithrolesspringsecurity.service.JwtService;
import com.jwtwithrolesspringsecurity.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//class responsible for retrieving authorization header from which it retrieves bearer token and then tries to check
//username and token and validate token, once token validated it allows particular request
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//whenever we send a request, we will be sending the jwt token as an authorization token
		//with format Authorization Bearer token

		final String header = request.getHeader("Authorization");

		String jwtToken = null;
		String username = null;

		//check header is valid/ in correct format
		if (header != null && header.startsWith("Bearer ")) {
			jwtToken = header.substring(7);

			try {

				username = jwtUtil.getUsernameFromToken(jwtToken);

			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT token");
			} catch (ExpiredJwtException e) {
				System.out.print("JWT token is expired");
			}

		} else {
			System.out.println("JWT token doesnt start with 'Bearer'");
		}

		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = jwtService.loadUserByUsername(username);

			if(jwtUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}

		}

		filterChain.doFilter(request, response);
	}


}
