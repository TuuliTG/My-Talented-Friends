package projekti.securityConfiguration;

import projekti.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("default")
@Configuration
@EnableWebSecurity
public class DevelopmentSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Pyyntöjä ei tarkasteta
        
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .antMatchers("/h2-console", "/h2-console/**").permitAll()
                .antMatchers("/frontpage").permitAll()
                .antMatchers(HttpMethod.GET, "static/css","static/css/*").permitAll()
                .antMatchers("/signup").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .permitAll().loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/userHomePage", true)
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutUrl("/perform_logout");
                
                        
    }
    
     @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*
    protected String determineTargetUrl() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            return username;
    }
*/
}
