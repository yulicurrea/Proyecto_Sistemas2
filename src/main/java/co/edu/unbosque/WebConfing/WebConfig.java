package co.edu.unbosque.WebConfing;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import co.edu.unbosque.service.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UsuarioDetailsService usuarioDetailsService;
	

	 @Bean
	    public UserDetailsService userDetailsService() {
	        return usuarioDetailsService;
	    }
	     
	    @Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	 auth.inMemoryAuthentication().withUser("usuario").password("{noop}password").roles("USER");
		}
	    
		public void configure(WebSecurity web) throws Exception {
			super.configure(web);
		}
	 
		@Bean
		public PasswordEncoder passwordEncoder() {
		    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}
	    
		/*
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	            .antMatchers("/api/user/login").authenticated()
	            .anyRequest().permitAll()
	            .and()
	            .formLogin()
	                .usernameParameter("usuario")
	                .defaultSuccessUrl("/api/user/login")
	                .permitAll()
	            .and()
	            .logout().logoutSuccessUrl("/").permitAll();
	    }
	     */
		
		
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and()
			.authorizeRequests()
			.antMatchers("/api/user/login","/logout","not-found").permitAll()
			.anyRequest().fullyAuthenticated().and()
			.logout()
	        .permitAll()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
	        .and()
		
			.httpBasic().and()
			
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
		
			.csrf().disable();
			
			http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
		}

}
