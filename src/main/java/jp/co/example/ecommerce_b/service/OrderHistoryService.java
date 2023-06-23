package jp.co.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.repository.OrderRepository;

/**
 * 注文履歴を操作するサービス.
 * 
 * @author mami.horioka
 *
 */
@Service
@Transactional
public class OrderHistoryService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 注文履歴を表示する.
	 * 
	 * @param userId ユーザーID
	 * @return 注文履歴のリスト
	 */
	public List<Order> showOrderHistory(int userId) {
		return orderRepository.findAllByUserId(userId);
	}

	/**
	 * 注文詳細を表示する.
	 * 
	 * @param id 注文ID
	 * @return 注文詳細画面
	 */
	public Order showOrderDetail(int id) {
		return orderRepository.load(id);
	}

}
