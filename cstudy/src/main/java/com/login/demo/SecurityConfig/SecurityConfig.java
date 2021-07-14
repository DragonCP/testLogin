package com.login.demo.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
    /**
     * @author jy Hwang
     * @param : http
     * @exception
     */
	@Override	
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable()  //csrf로 기본적인 보안 설정입니다
		    .authorizeRequests() //csrf를 통해 인증 요청을 매핑시킵니다
		        .antMatchers("/", "/home", "/index", "/about").permitAll() //로그인 없이 접근가능한 페이지 입니다.
		        .antMatchers("/admin/**").hasAnyRole("ADMIN") //어드민 접근권한만 가지고 있는 사람(root 이하 폴더 )접근가
		        .antMatchers("/user/**").hasAnyRole("USER")//보통의 유저들
		        .anyRequest().authenticated() //어떠한 요청도 spring security를 거칩니다
		    .and()
		    .formLogin()
		       .loginPage("login").failureUrl("/login?error").permitAll()//로그인 페이지 설정(접근권한은 permitAll과 상동합니다)
		       .permitAll()
		       .and()
		   .logout()
		       .permitAll()
		       .and()
		   .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		        
	}
	/**
	 * db연동전 임시비밀번호 설정 (user/admin)
	 * @author jyHwang
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		       .withUser("user").password("USER")
		       .and()
		       .withUser("admin").password("password").roles("ADMIN");
	}
	

}