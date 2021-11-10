package co.edu.unbosque.WebConfing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import co.edu.unbosque.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioService userDatailService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Bean
	public BCryptPasswordEncoder paswordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
		
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
		
		auth.userDetailsService(userDatailService).passwordEncoder(bcrypt);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	
        http
          .authorizeRequests()          
          .anyRequest()          
          .authenticated()
          .and()
          .httpBasic();
          
    }
    	

}
