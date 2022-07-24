package com.demospringsecurity.configuration;

import com.demospringsecurity.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

	//use when creating custom user details service
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	//used once we do custom authentication provider
	@Autowired
	@Lazy
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //do this when using different authorisation token, not recommended when browser clients
				.authorizeRequests()
				.antMatchers("/register", "/login").permitAll()
				.anyRequest().authenticated()
				//.antMatchers("/dashboard").hasAuthority("READ")
				//.antMatchers("/profile").hasAuthority("WRITE")
//				.antMatchers("/dashboard").hasAuthority("ROLE_USER")
//				.antMatchers("/profile").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
				//.antMatchers("/profile").hasAnyRole("ADMIN", "USER", "ROOT", "SUPERADMIN")
				.and()
				.formLogin()
				.and()
				.httpBasic();
	}

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").authorities("admin")
				.and()
				.withUser("user").password("12345").authorities("read")
				.and()
				.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}*/

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		UserDetails firstUser = User.withUsername("admin").password("12345").build();
		UserDetails secondUser = User.withUsername("user").password("12345").build();
		userDetailsManager.createUser(firstUser);
		userDetailsManager.createUser(secondUser);
		auth.userDetailsService(userDetailsManager);
	}*/


	//use when creating custom user details service
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

		//used once we do a custom authentication provider
		auth.authenticationProvider(customAuthenticationProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
