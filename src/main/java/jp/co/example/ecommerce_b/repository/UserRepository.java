package jp.co.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.User;

/**
 * 
 * usersテーブルの情報を操作するレポジトリ.
 * 
 * @author kanae.osaki
 *
 */
@Repository
public class UserRepository {

	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * メールアドレスからユーザー情報を取得する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報 存在しない場合はnullを返す
	 */
	public User findByEmail(String email) {
		String sql = "select id, name, email, password, zipcode, pref, municipalities, address, telephone from users where email=:email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}

		return userList.get(0);
	}

	/**
	 * ユーザー情報を挿入する.
	 * 
	 * @param user ユーザー情報
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "insert into users(name, email, password, zipcode, pref, municipalities, address, telephone)values(:name, :email, :password, :zipcode, :pref, :municipalities, :address, :telephone)";
		template.update(sql, param);
	}
}
