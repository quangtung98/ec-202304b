package jp.co.example.ecommerce_b.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	private static final String ORDER_SELECT_SQL = "SELECT a.id , a.user_id , a.status , a.total_price , a.order_date , a.destination_name , a.destination_email , a.destination_zipcode , destination_pref , destination_municipalities , a.destination_address , a.destination_tel , a.delivery_time , a.payment_method , b.id AS order_item_id , b.item_id , b.quantity , b.size , b.name AS item_name , b.description , b.price_m AS item_price_m , b.price_l AS item_price_l , b.image_path , b.deleted , c.id AS order_topping_id , c.topping_id , c.name AS topping_name , c.price_m AS topping_price_m , c.price_l AS topping_price_l FROM orders AS a JOIN (SELECT d.id , item_id , quantity , order_id , size , name , description , price_m , price_l , image_path , deleted FROM order_items AS d JOIN items ON d.item_id = items.id) AS b ON a.id = b.order_id JOIN (SELECT e.id ,order_item_id ,topping_id , name , price_m , price_l FROM order_toppings AS e JOIN toppings ON e.topping_id = toppings.id) AS c ON b.id = c.order_item_id ";

	/**
	 * 主キーから注文情報を１件取得.
	 * 
	 * @param id 主キー
	 * @return 注文商品と注文トッピングのリストを持った１件の注文情報
	 */
	public Order load(Integer id) {
		String sql = ORDER_SELECT_SQL + "WHERE a.id =:id ORDER BY order_item_id;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Order order = template.query(sql, param, ORDER_ITEM_TOPPING_RESULTSET);
		return order;
	}

	public Order findByUserIdAndStatus(int userId, int status) {
		String sql = ORDER_SELECT_SQL + "WHERE a.user_id =:userId AND a.status = :status ORDER BY order_item_id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		Order order = template.query(sql, param, ORDER_ITEM_TOPPING_RESULTSET);
		return order;
	}

	public Order insert(Order order) {
		String sql = "insert into orders(status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_pref, destination_municipalities, destination_address, destination_tel, delivery_time, payment_method) values(:status, :totalPrice, :orderDate, :destinationName, :destinationEmail, :destinationZipcode, :destinationPref, :destinationMunicipalities, :destinationAddress, :destinationTel, :deliveryTime, :paymentMethod);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = { "id" };
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(sql, param, keyHolder, keyColumnNames);
		order.setId(keyHolder.getKey().intValue());

		return order;
	}
}
