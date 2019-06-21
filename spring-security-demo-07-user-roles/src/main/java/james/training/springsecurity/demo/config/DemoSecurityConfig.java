package james.training.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// add users for in memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("baxter").password("test123").roles("MANAGER", "EMPLOYEE"))
			.withUser(users.username("james").password("test123").roles("MANAGER", "EMPLOYEE", "CEO"))
			.withUser(users.username("gilby").password("test123").roles("ADMIN", "EMPLOYEE"));
		
	}
	
	//this will make use of our custom login form
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http.authorizeRequests()
         .antMatchers("/").permitAll()  // allow public access to home page
         .antMatchers("/employees").hasRole("EMPLOYEE")
         .antMatchers("/leaders/**").hasAnyRole("MANAGER","CEO", "EMPLOYEE")
         .antMatchers("/systems/**").hasRole("ADMIN")
         .and()
         	.formLogin()
         	.loginPage("/showMyLoginPage")
         	.loginProcessingUrl("/authenticateTheUser")
         	.permitAll()
         .and()
         	.logout()
         	.logoutSuccessUrl("/")  // after logout then redirect to landing page (root)
         	.permitAll()
         .and()
         	.exceptionHandling().accessDeniedPage("/access-denied");
	}

	
	
}
