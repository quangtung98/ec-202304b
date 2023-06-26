package jp.co.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.OrderItem;

/**
 * order_itemsテーブルの情報を操作するリポジトリ.
 * 
 * @author kanae.osaki
 *
 */
@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 注文商品を挿入する.
	 * 
	 * @param orderItem 注文商品
	 */
	public OrderItem insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		String sql = "INSERT INTO order_items(item_id, order_id, quantity, size) VALUES(:itemId, :orderId, :quantity, :size);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = { "id" };
		template.update(sql, param, keyHolder, keyColumnNames);
		orderItem.setId(keyHolder.getKey().intValue());

		return orderItem;
	}

	/**
	 * 注文商品を削除する.
	 * 
	 * @param id ID
	 */
	public void delete(int id) {
		String sql = "delete from order_items where id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

	/**
	 * 注文IDを更新する.
	 * 
	 * @param orderId
	 */
	public void updateOrderId(int beforeOrderId, int newOrderId) {
		String sql = "UPDATE order_items SET order_id=:newOrderId WHERE order_id=:beforeOrderId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("beforeOrderId", beforeOrderId)
				.addValue("newOrderId", newOrderId);
		template.update(sql, param);
	}
}
