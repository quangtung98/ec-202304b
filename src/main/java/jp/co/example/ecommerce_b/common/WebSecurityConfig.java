package jp.co.example.ecommerce_b.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/user/**", "/", "/detail", "/shoppingCart",
				"/insertShoppingCart", "/deleteShoppingCart").permitAll().anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/user/toLogin").loginProcessingUrl("/user/login")
						.failureUrl("/?error=true").defaultSuccessUrl("/order", true).usernameParameter("email")
						.passwordParameter("password"))
				.logout(logout -> logout.logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true));
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers("/img/**", "/css/**", "/img_coffee/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
