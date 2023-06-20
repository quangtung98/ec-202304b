package jp.co.example.ecommerce_b.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

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

	/**
	 * 通常の管理者情報に加え、認可用ロールを設定する。
	 * 
	 * @param user          ユーザ情報
	 * @param authorityList 権限情報が入ったリスト
	 */
	public LoginUser(User user, Collection<GrantedAuthority> authorityList) {
		super(user.getEmail(), user.getPassword(), authorityList);
		this.user = user;
	}

	/**
	 * ユーザ情報を返す.
	 * 
	 * @return ユーザ情報
	 */
	public User getAdministrator() {
		return user;
	}

}
