package by.home.project.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDataSource;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.jdbcAuthentication().dataSource(securityDataSource);

		UserBuilder users = User.withDefaultPasswordEncoder(); 

		authentication.inMemoryAuthentication().withUser(users.username("user").
		password("password").roles("USER"));


		authentication.jdbcAuthentication().dataSource(securityDataSource)
				.usersByUsernameQuery("select username, password, enabled from users where username=?")
				.authoritiesByUsernameQuery(
						"Select u.username, r.type as user_role from users u join role r on u.role_id = r.id  where u.username=?");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/")
					.hasAnyRole("USER", "ADMIN", "ANONYMOUS")
				.antMatchers("/basketpage/**")
					.hasAnyRole("USER", "ADMIN")
				.and()
				.formLogin()
					.loginPage("/signin")
					.loginProcessingUrl("/signin")
				.failureUrl(
						"/signin?signin=error")
				.permitAll()
				.and()
				.logout().logoutUrl("/signout")
				.permitAll()
				.and()
				.exceptionHandling();

	}

}
