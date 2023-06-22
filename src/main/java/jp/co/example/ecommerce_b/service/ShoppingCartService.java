package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.form.InsertShoppingCartForm;
import jp.co.example.ecommerce_b.repository.OrderItemRepository;
import jp.co.example.ecommerce_b.repository.OrderRepository;
import jp.co.example.ecommerce_b.repository.OrderToppingRepository;

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

	/**
	 * ショッピングカートを表示するサービス.
	 * 
	 * @param userId ユーザーID
	 * @param status 状態
	 * @return 注文
	 */
	public Order showShoppingCart(int userId, int status) {

		return orderRepository.findByUserIdAndStatus(userId, status);
	}

	/**
	 * ショッピングカートに商品を追加するサービス.
	 * 
	 * @param form フォーム
	 * @return 注文
	 */
	public Order insertShoppingCart(InsertShoppingCartForm form) {
		// TODO
		return null;
	}

	/**
	 * ショッピングカートから商品を削除するサービス.
	 * 
	 * @param orderItemId 削除する商品のID
	 */
	public void deleteOrderItemFromShoppingCart(int orderItemId) {
		// TODO
	}
}
