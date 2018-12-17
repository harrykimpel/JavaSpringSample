package com.newrelic.momentum.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.newrelic.momentum.security.MyAccessDeniedHandler;
import com.newrelic.momentum.security.MyAuthenticationFailureHandler;
import com.newrelic.momentum.security.MyLogoutSuccessHandler;
import com.newrelic.momentum.security.RestAuthenticationEntryPoint;
import com.newrelic.momentum.security.RestAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String USERS_BY_USERNAME_QUERY = "select USERNAME as j_username , PASSWORD as j_password , true as enabled from USER where USERNAME = ?";
	private static final String AUTHORITIES_BY_USERNAME_QUERY = "select USERNAME as j_username, ROLES from USER_ROLES where USERNAME = ?";

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(USERS_BY_USERNAME_QUERY)
				.authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()			
			.antMatchers("/momentum**").hasAnyRole("ADMIN", "USER")
			.antMatchers("/css/**").permitAll()
			.antMatchers("/i18n/**").permitAll()
			.antMatchers("/images/**").permitAll()
			.antMatchers("/components/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/home.html").permitAll()
			.anyRequest().authenticated();
        
        http
        	.formLogin()
        	.defaultSuccessUrl("/home.html")
        	.loginProcessingUrl("/j_spring_security_check")
        	.successHandler(restAuthenticationSuccessHandler())
        	.loginPage("/js/partials/login.html")
        	.successHandler(restAuthenticationSuccessHandler())
        	.failureHandler(myAuthenticationFailureHandler())
        	.usernameParameter("j_username")
        	.passwordParameter("j_password");
       
        http
        	.httpBasic()
        	.authenticationEntryPoint(restAuthenticationEntryPoint());

		http
			.logout()
			.logoutSuccessUrl("/js/partials/login.html")
			.logoutUrl("/j_spring_security_logout")
			.logoutSuccessHandler(logoutSuccessHandler())
			.invalidateHttpSession(true).deleteCookies("JSESSIONID");

	}

	@Bean
	public RestAuthenticationSuccessHandler restAuthenticationSuccessHandler() {
		return new RestAuthenticationSuccessHandler();
	}

	@Bean
	public MyAuthenticationFailureHandler myAuthenticationFailureHandler() {
		return new MyAuthenticationFailureHandler();
	}

	@Bean
	public MyLogoutSuccessHandler logoutSuccessHandler() {
		return new MyLogoutSuccessHandler();
	}

	@Bean
	public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public MyAccessDeniedHandler myAccessDeniedHandler() {
		return new MyAccessDeniedHandler();
	}
}
