package com.uas.humanresource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig {
    //	// [...]
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("user").password(passwordEncoder().encode("password")).roles("USER")
				.and()
				.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
	}
//
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").hasAnyRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/students", true)
                        .permitAll()
                )

                .logout((logout) -> logout.logoutSuccessUrl("/login")
                        .permitAll()).sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.builder()
                        .username("user")
                        .password(this.passwordEncoder().encode("password"))
                        .roles("USER")
                        .build();


        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    @Primary
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
