package WebConfing;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
*/
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//import co.edu.unbosque.service.UsuarioDetailsService;
/*
@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UsuarioDetailsService usuarioDetailsService;
	
	 @Autowired
	 private DataSource dataSource;
	     
	    @Bean
	    public UserDetailsService userDetailsService() {
	        return usuarioDetailsService;
	    }
	     
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	     
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	         
	        return authProvider;
	    }
	 
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }
	    
	
		public void configure(WebSecurity web) throws Exception {
			super.configure(web);
		}
	 
	    
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	            .antMatchers("/users").authenticated()
	            .anyRequest().permitAll()
	            .and()
	            .formLogin()
	                .usernameParameter("usuario")
	                .defaultSuccessUrl("/users")
	                .permitAll()
	            .and()
	            .logout().logoutSuccessUrl("/").permitAll();
	    }
	     

}
*/