package edu.ifam.aranoua.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;
	
	
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**",
			"/categoria/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
			http.csrf().disable();
		}
		
		http.authorizeRequests()
		.antMatchers(PUBLIC_MATCHERS).permitAll()
		.anyRequest().authenticated();
	}
}
		
		
	