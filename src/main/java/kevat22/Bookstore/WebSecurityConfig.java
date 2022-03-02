package kevat22.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kevat22.Bookstore.web.UserDetailServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	  @Autowired
	    private UserDetailServiceImpl userDetailsService;	
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        //.authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
	       // .and()
	        .authorizeRequests()
	          .anyRequest().authenticated() //kaikkiin tarvitaan autentikointi
	          .and()
	      .formLogin()
	    //  .loginPage("/login")//tähän voi laittaa oman loginpagen, ota käyttöön poista koommenteista
	          .defaultSuccessUrl("/booklist", true)
	          .permitAll() //kaikki pääsee
	          .and()
	      .logout()
	          .permitAll();//kaikki pääsee login ja logout
	    }
	    
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	    }
	}
