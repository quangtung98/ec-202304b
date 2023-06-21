package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.repository.OrderRepository;

/**
 * 注文確認画面における業務処理を操作するサービス.
 * 
 * @author kanae.osaki
 *
 */
@Service
@Transactional
public class ShowOrderConfirmService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 注文IDより注文情報を１件取得.
	 * 
	 * @param orderId 注文ID
	 * @return 取得した注文情報
	 */
	public Order showOrderConfirm(Integer orderId) {
		return orderRepository.load(orderId);
	}
}
