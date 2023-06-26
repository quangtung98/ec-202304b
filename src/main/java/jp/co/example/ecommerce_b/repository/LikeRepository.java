package jp.co.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.Like;

@Repository
public class LikeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * いいね情報を挿入.
	 * 
	 * @param like いいね情報
	 */
	public void insert(Like like) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(like);
		String sql = "INSERT INTO likes(user_id,item_id) VALUES(:userId,:itemId);";
		template.update(sql, param);
	}

	/**
	 * 商品IDよりいいねの数を取得.
	 * 
	 * @param itemId 商品ID
	 * @return いいねの数
	 */
	public Integer findByItemId(Integer itemId) {
		String sql = "SELECT count(user_id) AS count_likes FROM likes GROUP BY :itemId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
		Integer count = template.queryForObject(sql, param, Integer.class);
		return count;
	}

	/**
	 * ユーザーIDと商品IDによるレコードの数を取得.
	 * 
	 * @param userId ユーザーID
	 * @param itemId 商品ID
	 * @return レコード数
	 */
	public Integer findByUserIdAndItemId(Integer userId, Integer itemId) {
		String sql = "SELECT count(*) FROM likes WHERE user_id =:userId AND item_id=:itemId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("itemId", itemId);
		Integer count = template.queryForObject(sql, param, Integer.class);
		return count;
	}

}
