package jp.co.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.RecommendTopping;

/**
 * recommendToppingテーブルを操作するリポジトリ.
 * 
 * @author kanae.osaki
 *
 */
@Repository
public class RecommendToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * おすすめトッピング情報を挿入する.
	 * 
	 * @param recommendTopping おすすめトッピング情報
	 */
	public void insert(RecommendTopping recommendTopping) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(recommendTopping);
		String sql = "INSERT INTO recommend_toppings(topping_id, topping_article_id) VALUES(:toppingId, :toppingArticleId);";
		template.update(sql, param);
	}
}
