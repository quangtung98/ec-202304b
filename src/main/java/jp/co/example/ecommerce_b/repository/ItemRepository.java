package jp.co.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.Item;

/**
 * 
 * itemsテーブルの情報を操作するリポジトリ.
 * 
 * @author kanae.osaki
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

	/**
	 * 商品一覧を検索するメソッド.
	 * 
	 * @return 全商品一覧情報
	 */
	public List<Item> findAll() {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items ORDER BY price_m;";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
}
