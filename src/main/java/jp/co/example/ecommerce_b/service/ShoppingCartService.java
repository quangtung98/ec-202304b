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

	public Order showShoppingCart(int userId, int status) {

		return orderRepository.findByUserIdAndStatus(userId, status);
	}

	public Order insertShoppingCart(InsertShoppingCartForm form) {
		return null;
	}

	public void deleteOrderItemFromShoppingCart(int orderItemId) {

	}
}
