package com.example.share.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import com.example.share.Services.UniversityService;

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	   private UniversityService userDetailsService;
		@Bean
		public static BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
			
		}
		@Bean
		public DaoAuthenticationProvider authenticationprovider() {
			DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
			auth.setUserDetailsService(userDetailsService);
			auth.setPasswordEncoder(passwordEncoder());
			return auth;
			
		}
		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationprovider());
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.antMatchers(
					"/adminRegister"
					).hasAuthority("ADMIN")
			.antMatchers(
					"/signup/**",
					"/home"
					).permitAll()
			.antMatchers(
					"/css/**",
					"/css1/**",
					"/icon/**",
					"/images/**",
					"/img1/**",
					"/js/**",
					"/js1/**",
					"/pages/**",
					"/vendor/**"
					).permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			    .loginPage("/signin")
			    .usernameParameter("username")
			    .permitAll() 
			    .defaultSuccessUrl("/",true)
			.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/signin?logout")
			.permitAll()
			
			.and()
			.rememberMe().key("12345678dfghjklkjhcchjhf")
			.tokenValiditySeconds(60)
			.userDetailsService(userDetailsService);
			
		}
}
