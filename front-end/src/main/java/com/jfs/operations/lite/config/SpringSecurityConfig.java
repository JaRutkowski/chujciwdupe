//package com.jfs.operations.lite.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//    // Uncomment if the in-memory auth should be applied
//    //	@Override
//    //	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    //		User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//    //		auth.inMemoryAuthentication().
//    //				withUser(userBuilder.username("Test").password("Test123").roles("ADMIN"));
//    //	}
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new MessageDigestPasswordEncoder("SHA-512");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/").permitAll()
//                //TODO: togglez
//                //.antMatchers("/togglz-console/**").permitAll()
//                .antMatchers("/web/app/**").authenticated()
//                .and()
//                .formLogin().loginPage("/web/login/login-page")
//                .loginProcessingUrl("/authenticateUser")
//                .defaultSuccessUrl("/web/app/dashboard")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/web/login/login-page?logout")
//                .permitAll();
//    }
//
//    //TODO: Spring Boot 3.0
//    // https://javatechonline.com/spring-security-userdetailsservice-using-spring-boot-3/?utm_content=cmp-true
//
//    //TODO: Observability
//    // https://www.baeldung.com/spring-boot-3-observability
//
////	@Bean
////	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////				return http.csrf().disable().authorizeHttpRequests()
////				.requestMatchers("/").permitAll()
////				.requestMatchers("/web/app/**").authenticated()
////				.and()
////				.formLogin().loginPage("/web/login/login-page")
////				.loginProcessingUrl("/authenticateUser")
////				.defaultSuccessUrl("/web/app/dashboard")
////				.permitAll()
////				.and()
////				.logout()
////				.logoutSuccessUrl("/web/login/login-page?logout")
////				.permitAll().and().build();
////	}
//}
