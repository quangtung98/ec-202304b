package jp.co.example.ecommerce_b.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.form.OrderForm;
import jp.co.example.ecommerce_b.repository.OrderRepository;

/**
 * 注文する業務処理を操作するサービス.
 * 
 * @author kanae.osaki
 *
 */
@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 注文情報を更新する.
	 * 
	 * @param form フォームより送られてきた注文情報
	 */
	public void order(OrderForm form) {
		Order order = orderRepository.load(form.getOrderId());
		BeanUtils.copyProperties(form, order);
		LocalDate localDate =form.getDeliveryDate().toLocalDate();
		LocalTime localTime=LocalTime.of(form.getDeliveryTime(), 0);
		LocalDateTime localDateTime = LocalDateTime.of(localDate,localTime);
		order.setDeliveryTime(Timestamp.valueOf(localDateTime));
		System.out.println(order.getDeliveryTime());
		if (form.getPaymentMethod() == 0) {
			order.setStatus(1);
		} else {
			order.setStatus(2);
		}
		orderRepository.update(order);
	}
}
