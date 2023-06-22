package jp.co.example.ecommerce_b.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * ログイン認証用設定.
 * 
 * @author mami.horioka
 *
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	/**
	 * 認可の設定やログイン/ログアウトに関する設定.
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				authorize -> authorize.requestMatchers("/user/**", "/", "/showItemDetail/**", "/shoppingCart/**")
						.permitAll().anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/user/toLogin").loginProcessingUrl("/user/login")
						.failureUrl("/user/toLogin?error=true").defaultSuccessUrl("/", true).usernameParameter("email")
						.passwordParameter("password"))
				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout**"))
						.logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true));
		return http.build();
	}

	/**
	 * 静的リソースに対してセキュリティの設定を無効にする。
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers("/img/**", "/css/**", "/img_coffee/**");
	}

	/**
	 * <pre>
	 * bcryptアルゴリズムでハッシュ化する実装を返す.
	 * これを指定することでパスワードハッシュ化やマッチ確認する際に
	 * &#64;Autowired
	 * private PasswordEncoder passwordEncoder;
	 * と記載するとDIされるようになる。
	 * </pre>
	 * 
	 * @return bcryptアルゴリズムでハッシュ化する実装オブジェクト
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
