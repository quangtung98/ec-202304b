package jp.co.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	 * @param sortkey  ソートする列の名前
	 * @param sorttype ソートの仕様（昇順OR降順）
	 * @return 全商品一覧情報
	 */
	public List<Item> findAll(String sortkey, String sorttype) {
		StringBuilder sql = new StringBuilder(
				"SELECT id,name,description,price_m,price_l,image_path,deleted FROM items ORDER BY ");
		sql.append(sortkey);
		sql.append(" ");
		sql.append(sorttype);
		sql.append(";");
		List<Item> itemList = template.query(sql.toString(), ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 入力値より、商品で名前のあいまい検索を行うメソッド.
	 * 
	 * @param fuzzyName 入力されたあいまい検索の名前
	 * @param sortkey   ソートする列の名前
	 * @param sorttype  ソートの仕様（昇順OR降順）
	 * @return あいまい検索された商品一覧
	 */
	public List<Item> findByNameContaining(String fuzzyName, String sortkey, String sorttype) {
		StringBuilder sql = new StringBuilder(
				"SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE name LIKE :fuzzyName ORDER BY ");
		sql.append(sortkey);
		sql.append(" ");
		sql.append(sorttype);
		sql.append(";");
		SqlParameterSource param = new MapSqlParameterSource().addValue("fuzzyName", "%" + fuzzyName + "%");
		List<Item> itemList = template.query(sql.toString(), param, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 主キー検索を行うメソッド.
	 * 
	 * @param id ID
	 * @return 主キーによって検索された商品情報
	 */
	public Item load(Integer id) {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}
}
