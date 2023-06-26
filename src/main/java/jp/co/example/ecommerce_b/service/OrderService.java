package jp.co.example.ecommerce_b.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.domain.OrderTopping;
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

	@Autowired
	private JavaMailSender sender;

	/**
	 * 注文情報を更新する.
	 * 
	 * @param form フォームより送られてきた注文情報
	 */
	public Order order(OrderForm form) {
		Order order = orderRepository.load(form.getOrderId());
		BeanUtils.copyProperties(form, order);
		LocalDate localDate = form.getDeliveryDate().toLocalDate();
		LocalTime localTime = LocalTime.of(form.getDeliveryTime(), 0);
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		order.setDeliveryTime(Timestamp.valueOf(localDateTime));
		if (form.getPaymentMethod() == 0) {
			order.setStatus(1);
		} else {
			order.setStatus(2);
		}
		orderRepository.update(order);

		return order;
	}

	/**
	 * 注文完了メールの送信.
	 * 
	 * @param order 注文情報
	 */
	@Async
	public void sentMessageMail(Order order) {
		MimeMessage message = sender.createMimeMessage();
		try {
			// 送信情報設定
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("okanae310@gmail.com");
			helper.setTo(order.getDestinationEmail());
			helper.setSubject("注文完了メール");
			String sentMessage = sentMessage(order);
			helper.setText(sentMessage, true);

			// メール送信
			sender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * メール文を作成する.
	 * 
	 * @param order 注文情報
	 * @return メール文
	 */
	private String sentMessage(Order order) {
		List<OrderItem> orderItemList = order.getOrderItemList();
		String message = "<!DOCTYPE html>\r\n" + "<html lang=\"ja\">\r\n" + "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "    <h3>ご注文ありがとうございます！</h3>\r\n"
				+ "    <h4>以下のご注文を承りました！</h4>\r\n" + "<hr>";
		for (OrderItem orderItem : orderItemList) {
			List<OrderTopping> orderToppingList = orderItem.getOrderToppingList();
			String item_message = "    <p>注文商品  " + orderItem.getItem().getName() + "</p>" + "    <p>個数"
					+ orderItem.getQuantity() + "</p>\r\n" + "    <p>トッピング  ";
			message += item_message;
			for (OrderTopping orderTopping : orderToppingList) {
				String topping_message = orderTopping.getTopping().getName() + "</p>";
				message += topping_message;
			}
			message += "<hr>";
		}
		String foot_message = "<h4>お届け時刻" + order.getDeliveryTime() + "</h4>" + "</body>\r\n" + "</html>";
		message += foot_message;
		return message;
	}

}
