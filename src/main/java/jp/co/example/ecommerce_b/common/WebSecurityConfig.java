package jp.co.example.ecommerce_b.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/toLogin", "/toRegister", "/login", "/register", "/", "/detail", "/shoppingCart",
						"/insertShoppingCart", "/deleteShoppingCart")
				.permitAll().requestMatchers("/order/**").hasRole("USER").anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/toLogin").loginProcessingUrl("/login").failureUrl("/?error=true")
						.defaultSuccessUrl("/", true).usernameParameter("mailAddress").passwordParameter("password"))
				.logout(logout -> logout.logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true));
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers("/img/**", "/js/**", "/css/**", "/img_coffee**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
