package jp.co.example.ecommerce_b.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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
	
	@Autowired
	private JavaMailSender sender;

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
		if (form.getPaymentMethod() == 0) {
			order.setStatus(1);
		} else {
			order.setStatus(2);
		}
		orderRepository.update(order);
		MimeMessage message = sender.createMimeMessage();
	    try {
	      //送信情報設定
	      MimeMessageHelper helper = new MimeMessageHelper(message, true);
	      helper.setFrom("okanae310@gmail.com");
	      helper.setTo(order.getDestinationEmail());
	      helper.setSubject("注文完了メール");
	      helper.setText("ご注文を承りました。"+order.getDeliveryTime().toString()+"にお届け予定です！");
	      
	      //メール送信
	      sender.send(message);
	    } catch(MessagingException e) {
	      e.printStackTrace();
	    }
	}
}
