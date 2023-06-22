package jp.co.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.Topping;

/**
 * 
 * toppingsテーブルの情報を操作するリポジトリ.
 * 
 * @author kanae.osaki
 *
 */
@Repository
public class ToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = new BeanPropertyRowMapper<>(Topping.class);

	/**
	 * toppingsテーブルで全件検索するメソッド.
	 * 
	 * @return 全トッピング一覧
	 */
	public List<Topping> findAll() {
		String sql = "SELECT id,name,price_m,price_l FROM toppings;";
		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);
		return toppingList;
	}
}
