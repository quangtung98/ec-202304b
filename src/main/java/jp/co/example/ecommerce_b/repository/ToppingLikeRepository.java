package jp.co.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import jp.co.example.ecommerce_b.domain.ToppingLike;

public class ToppingLikeRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * いいね情報を挿入.
	 * 
	 * @param toppingLike いいね情報
	 */
	public void insert(ToppingLike toppingLike) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(toppingLike);
		String sql = "INSERT INTO topping_likes(user_id,topping_article_id) VALUES(:userId,:toppingArticleId);";
		template.update(sql, param);
	}

	/**
	 * トッピング投稿IDよりいいねの数を取得.
	 * 
	 * @param toppingArticleId トッピング投稿ID
	 * @return いいねの数
	 */
	public Integer findByToppingArticleId(Integer toppingArticleId) {
		String sql = "SELECT count(*) AS count_likes FROM topping_likes WHERE topping_article_id=:toppingArticleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("toppingArticleId", toppingArticleId);
		Integer count = template.queryForObject(sql, param, Integer.class);
		return count;
	}

	/**
	 * ユーザーIDとトッピング投稿IDによるレコードの数を取得.
	 * 
	 * @param userId           ユーザーID
	 * @param toppingArticleId トッピング投稿ID
	 * @return レコード数
	 */
	public Integer findByUserIdAndToppingArticleId(Integer userId, Integer toppingArticleId) {
		String sql = "SELECT count(*) FROM likes WHERE user_id =:userId AND  topping_article_id=:toppingArticleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("itemId",
				toppingArticleId);
		Integer count = template.queryForObject(sql, param, Integer.class);
		return count;
	}

}
