package com.manulife.studentlibrary.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.manulife.studentlibrary.Security.Jwt.JWTEntryPoint;
import com.manulife.studentlibrary.Security.Jwt.JWTFilter;
import com.manulife.studentlibrary.Service.StudentService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	  private JWTEntryPoint unauthorizedHandler;
	  @Bean
	  public JWTFilter authenticationJwtTokenFilter() {
	    return new JWTFilter();
	  }
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
			/*
			 * http .csrf().disable() .authorizeRequests() .antMatchers(HttpMethod.GET,
			 * "/api/**").permitAll() .antMatchers("/**").permitAll();
			 */
		 http.cors().and().csrf().disable()
	      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	      .authorizeRequests().antMatchers("/api/auth/**").permitAll()
	      .antMatchers("/**").permitAll()
	      .anyRequest().authenticated();
	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	        
	    }

	    @Bean
	    PasswordEncoder passwordEncoder(){
	    	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	    }
	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
}
