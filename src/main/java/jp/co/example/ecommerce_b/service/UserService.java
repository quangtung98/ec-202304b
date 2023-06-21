package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.User;
import jp.co.example.ecommerce_b.repository.UserRepository;

/**
 * ユーザー情報を操作するサービス.
 * 
 * @author mami.horioka
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;

	/**
	 * ユーザー情報を登録する.
	 * 
	 * @param user
	 */
	public void insert(User user) {

		String encodePassword = encoder.encode(user.getPassword());
		user.setPassword(encodePassword);

		repository.insert(user);
	}

	/**
	 * メールアドレスからユーザー情報を取得する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報 存在しない場合はnullを返す
	 */
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}
}
