package jp.co.example.ecommerce_b.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.domain.OrderTopping;
import jp.co.example.ecommerce_b.domain.Topping;

/**
 * 
 * ordersテーブルの情報を操作するリポジトリ.
 * 
 * @author kanae.osaki
 *
 */
@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ordersとorder_itemsとorder_toppingsを結合したテーブルから取得したデータをOrder型のオブジェクトにセットするための定数
	 * order_itemsはitemsと、order_toppingsはtoppingsと結合されている
	 */
	private static final ResultSetExtractor<Order> ORDER_ITEM_TOPPING_RESULTSET = (rs) -> {
		Order order = new Order();
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		List<OrderTopping> orderToppingList = null;
		int beforeIdNum = 0;
		while (rs.next()) {
			order.setId(rs.getInt("id"));
			order.setUserId(rs.getInt("user_id"));
			order.setStatus(rs.getInt("status"));
			order.setTotalPrice(rs.getInt("total_price"));
			order.setOrderDate(rs.getDate("order_date"));
			order.setDestinationName(rs.getString("destination_name"));
			order.setDestinationEmail(rs.getString("destination_email"));
			order.setDestinationZipcode(rs.getString("destination_zipcode"));
			order.setDestinationAddress(rs.getString("destination_address"));
			order.setDestinationTel(rs.getString("destination_tel"));
			order.setDeliveryTime(rs.getTimestamp("delivery_time"));
			order.setPaymentMethod(rs.getInt("paymethod_method"));
			orderItemList = new ArrayList<OrderItem>();
			int nowIdNum = rs.getInt("order_item_id");
			if (nowIdNum != beforeIdNum) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(nowIdNum);
				orderItem.setItemId(rs.getInt("item_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setSize(rs.getString("size").charAt(0));
				Item item = new Item();
				item.setId(rs.getInt("item_id"));
				item.setName(rs.getString("item_name"));
				item.setDescription(rs.getString("discription"));
				item.setPriceM(rs.getInt("item_price_m"));
				item.setPriceL(rs.getInt("item_price_l"));
				item.setImagePath(rs.getString("image_path"));
				orderItem.setItem(item);
				orderToppingList = new ArrayList<OrderTopping>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);
			}
			if (rs.getInt("order_topping_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("order_topping_id"));
				orderTopping.setToppingId(rs.getInt("topping_id"));
				Topping topping = new Topping();
				topping.setId(rs.getInt("topping_id"));
				topping.setName(rs.getString("topping_name"));
				topping.setPriceM(rs.getInt("topping_price_m"));
				topping.setPriceL(rs.getInt("topping_price_l"));
				orderTopping.setTopping(topping);
				orderToppingList.add(orderTopping);
			}
			beforeIdNum = nowIdNum;
		}

		return order;
	};

	/**
	 * 主キーから注文情報を１件取得.
	 * 
	 * @param id 主キー
	 * @return 注文商品と注文トッピングのリストを持った１件の注文情報
	 */
	public Order load(Integer id) {
		String sql = "SELECT \r\n" + "  a.id\r\n" + "  , a.user_id \r\n" + "  , a.status \r\n"
				+ "  , a.total_price \r\n" + "  , a.order_date \r\n" + "  , a.destination_name \r\n"
				+ "  , a.destination_email \r\n" + "  , a.destination_zipcode\r\n" + "  , destination_pref\r\n"
				+ "  , destination_municipalities\r\n" + "  , a.destination_address\r\n" + "  , a.destination_tel\r\n"
				+ "  , a.delivery_time\r\n" + "  , a.payment_method\r\n" + "  , b.id AS order_item_id\r\n"
				+ "  , b.item_id \r\n" + "  , b.quantity \r\n" + "  , b.size \r\n" + "  , b.name AS item_name\r\n"
				+ "  , b.description \r\n" + "  , b.price_m AS item_price_m\r\n" + "  , b.price_l AS item_price_l\r\n"
				+ "  , b.image_path \r\n" + "  , b.deleted\r\n" + "  , c.id AS order_topping_id\r\n"
				+ "  , c.topping_id\r\n" + "  , c.name AS topping_name\r\n" + "  , c.price_m AS topping_price_m\r\n"
				+ "  , c.price_l AS topping_price_l\r\n" + " FROM\r\n" + " orders AS a\r\n" + " JOIN\r\n"
				+ " (SELECT \r\n" + "  d.id \r\n" + "  , item_id \r\n" + "  , quantity \r\n" + "  , order_id\r\n"
				+ "  , size \r\n" + "  , name \r\n" + "  , description \r\n" + "  , price_m \r\n" + "  , price_l \r\n"
				+ "  , image_path \r\n" + "  , deleted \r\n" + "  FROM \r\n" + "  order_items AS d\r\n" + "  JOIN\r\n"
				+ "  items\r\n" + "  ON d.item_id = items.id) AS b\r\n" + " ON a.id = b.order_id\r\n" + " JOIN\r\n"
				+ " (SELECT\r\n" + "   e.id \r\n" + "  ,order_item_id\r\n" + "  ,topping_id\r\n" + "  , name \r\n"
				+ "  , price_m \r\n" + "  , price_l \r\n" + "  FROM \r\n" + "  order_toppings AS e\r\n" + "  JOIN\r\n"
				+ "  toppings\r\n" + "  ON e.topping_id = toppings.id) AS c\r\n" + " ON b.id = c.order_item_id\r\n"
				+ "WHERE a.id =:id\r\n" + "ORDER BY order_item_id;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Order order = template.query(sql, param, ORDER_ITEM_TOPPING_RESULTSET);
		return order;
	}
}
