package com.siasisten1.security.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import com.siasisten1.SecurityHandler;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/assets/**").permitAll()
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.successHandler(new SecurityHandler())
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.permitAll();
	}
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentification(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery("select username, password, enabled from users where username = ?")
		.authoritiesByUsernameQuery("select username, role from user_roles where username = ?");
	}
	
	@SuppressWarnings("unchecked")
	public PasswordEncoder passwordEncoder() {
	    // This is the ID we use for encoding.
		String id = "bcrypt";
		@SuppressWarnings("rawtypes")
		Map encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());
	   
	    return new DelegatingPasswordEncoder(id, encoders);
	}

}
