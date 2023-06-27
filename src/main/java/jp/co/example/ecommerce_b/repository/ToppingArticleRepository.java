package jp.co.example.ecommerce_b.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.RecommendTopping;
import jp.co.example.ecommerce_b.domain.ToppingArticle;
import jp.co.example.ecommerce_b.domain.User;

/**
 * topping_articlesテーブルを操作するリポジトリ.s
 * 
 * @author kanae.osaki
 *
 */
@Repository
public class ToppingArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * articlesとrecommendToppingsの結合テーブルから取得したでーたをList<recommendtoppingArticle>型のオブジェクトにセットするための定数
	 */
	private static final ResultSetExtractor<List<ToppingArticle>> TOPPING_ARTICLE_RESULTSET = (rs) -> {
		List<ToppingArticle> toppingArticleList = new ArrayList<>();
		List<RecommendTopping> recommendToppingList = null;
		int beforeIdNum = 0;
		while (rs.next()) {
			int nowIdNum = rs.getInt("id");
			if (nowIdNum != beforeIdNum) {
				ToppingArticle toppingArticle = new ToppingArticle();
				toppingArticle.setId(nowIdNum);
				toppingArticle.setUserId(rs.getInt("user_id"));
				toppingArticle.setItemId(rs.getInt("item_id"));
				toppingArticle.setContent(rs.getString("content"));
				toppingArticle.setImage(rs.getString("image"));
				Item item = new Item();
				item.setId(rs.getInt("item_id"));
				item.setName(rs.getString("item_name"));
				toppingArticle.setItem(item);
				User user = new User();
				user.setName(rs.getString("user_name"));
				recommendToppingList = new ArrayList<RecommendTopping>();
				toppingArticle.setRecommendToppingList(recommendToppingList);
				toppingArticleList.add(toppingArticle);
			}
			if (rs.getInt("recommendtopping_article_id") != 0) {
				RecommendTopping recommendTopping = new RecommendTopping();
				recommendTopping.setId(rs.getInt("topping_id"));
				recommendTopping.setToppingName(rs.getString("topping_name"));
				recommendToppingList.add(recommendTopping);
			}

			beforeIdNum = nowIdNum;
		}
		return toppingArticleList;
	};

	/**
	 * トッピング投稿情報を挿入する.
	 * 
	 * @param recommendtoppingArticle トッピング投稿情報
	 */
	public void insert(ToppingArticle toppingArticle) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(toppingArticle);
		String sql = "INSERT INTO recommendtopping_articles(item_id, user_id,content, image) VALUES(:itemId, :userId, :content, :image);";
		template.update(sql, param);
	}

	/**
	 * トッピング投稿情報を全件取得.
	 * 
	 * @return トッピング投稿情報全件 idの昇順
	 */
	public List<ToppingArticle> findAll() {
		String sql = "SELECT a.id , a.user_id , a.item_id , a.content , a.image , a.item_name , a.user_name , b.id AS topping_article_id, b.topping_id,b.topping_name FROM (SELECT ta.id ,ta.item_id,ta.user_id,ta.content,ta.image,u.name AS user_name,i.name AS item_name FROM topping_articles AS ta JOIN users AS u ON ta.user_id = u.id JOIN items AS i ON item_id = i.id) AS a JOIN (SELECT rt.id,rt.topping_id,rt.topping_article_id,t.name AS topping_name FROM recommend_toppings AS rt JOIN toppings AS t ON rt.topping_id = t.id) As b ON a.id = b.topping_article_id ORDER BY a.id;";
		List<ToppingArticle> toppingArticleList = template.query(sql, TOPPING_ARTICLE_RESULTSET);
		return toppingArticleList;
	}
}
