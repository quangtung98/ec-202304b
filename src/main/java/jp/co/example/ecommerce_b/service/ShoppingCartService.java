package jp.co.example.ecommerce_b.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.domain.OrderTopping;
import jp.co.example.ecommerce_b.form.InsertShoppingCartForm;
import jp.co.example.ecommerce_b.repository.ItemRepository;
import jp.co.example.ecommerce_b.repository.OrderItemRepository;
import jp.co.example.ecommerce_b.repository.OrderRepository;
import jp.co.example.ecommerce_b.repository.OrderToppingRepository;
import jp.co.example.ecommerce_b.repository.ToppingRepository;

/**
 * ショッピングカートを操作するサービス.
 * 
 * @author mami.horioka
 *
 */
@Service
@Transactional
public class ShoppingCartService {
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ToppingRepository toppingRepository;
	@Autowired
	private ItemRepository itemRepository;

	/**
	 * ショッピングカートを表示するサービス.
	 * 
	 * @param userId ユーザーID
	 * @param status 状態
	 * @return 注文
	 */
	public Order showShoppingCart(int userId, int status) {
		Order order = orderRepository.findByUserIdAndStatus(userId, status);
		if (order != null) {
			order.setTotalPrice(order.getCalcTotalPrice());
			orderRepository.update(order);
		}
		return order;
	}

	/**
	 * ショッピングカートに商品を追加するサービス.
	 * 
	 * @param form フォーム
	 * @return 注文
	 */
	public void insertShoppingCart(InsertShoppingCartForm form, int userId) {

		List<OrderTopping> orderToppingList = new ArrayList<>();
		if (form.getOrderToppingList() != null) {
			for (Integer orderToppingId : form.getOrderToppingList()) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setToppingId(orderToppingId);
				orderTopping.setTopping(toppingRepository.load(orderToppingId));
				orderToppingList.add(orderTopping);
			}
		}

		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(form.getItemId());
		orderItem.setSize(form.getSize().toCharArray()[0]);
		orderItem.setQuantity(form.getQuantity());
		orderItem.setOrderToppingList(orderToppingList);
		orderItem.setItem(itemRepository.load(form.getItemId()));

		Order myOrder = orderRepository.findByUserIdAndStatus(userId, 0);

		if (myOrder.getId() == null) {
			myOrder.setUserId(userId);
			myOrder.setStatus(0);
			myOrder.setOrderDate(new Date());
			// 住所を追加する
			myOrder.setTotalPrice(0);
			myOrder = orderRepository.insert(myOrder);
		}
		orderItem.setOrderId(myOrder.getId());
		orderItem = orderItemRepository.insert(orderItem);

		for (OrderTopping orderTopping : orderToppingList) {
			orderTopping.setOrderItemId(orderItem.getId());
			orderToppingRepository.insert(orderTopping);
		}

	}

	/**
	 * ショッピングカートから商品を削除するサービス.
	 * 
	 * @param orderItemId 削除する商品のID
	 */
	public void deleteOrderItemFromShoppingCart(int orderItemId) {
		orderItemRepository.delete(orderItemId);
	}
}
