package jp.co.example.ecommerce_b.domain;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import jakarta.servlet.http.HttpSession;
import jp.co.example.ecommerce_b.service.ShoppingCartService;

/**
 * ユーザのログイン情報を格納するエンティティ.
 * 
 * @author mami.horioka
 *
 */
public class LoginUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	/** ユーザ情報 */
	private final User user;

	@Autowired
	private HttpSession session;

	private ShoppingCartService service;

	/**
	 * 通常の管理者情報に加え、認可用ロールを設定する。
	 * 
	 * @param user          ユーザ情報
	 * @param authorityList 権限情報が入ったリスト
	 */
	public LoginUser(User user, Collection<GrantedAuthority> authorityList) {
		super(user.getEmail(), user.getPassword(), authorityList);
		this.user = user;
		if (session != null && session.getAttribute("userId") != null) {
			service.integrateShoppingCart((int) session.getAttribute("userId"), user.getId());
		}

	}

	/**
	 * ユーザ情報を返す.
	 * 
	 * @return ユーザ情報
	 */
	public User getUser() {
		return user;
	}

}
