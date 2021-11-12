package co.edu.unbosque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import co.edu.unbosque.auth.filter.JWTAuthenticationFilter;
import co.edu.unbosque.auth.filter.JWTAuthorizationFilter;
import co.edu.unbosque.auth.service.JWTService;
import co.edu.unbosque.service.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private JpaUserDetailsService userDetailsService;

	/**
	 * Configure web security to ignore some routes for which validation is not
	 * required.<br>
	 * This is done in order to prevent the memory from filling up unnecessarily
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// web.ignoring().antMatchers("/api/schedule/es-record/save-remote-work");
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger.json");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/css/**", "/js/**", "/images/**", "/locale", "/api/**", "/login**", "/files/**",
						"/secured/**")
				.permitAll().anyRequest().authenticated()

				.and().addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))

				.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))

				.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

}
