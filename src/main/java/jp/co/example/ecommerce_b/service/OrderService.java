package jp.co.example.ecommerce_b.service;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
	public void order(OrderForm form) {
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
		String message = "<!DOCTYPE html>\n" + "<html lang=\"ja\">\n" + "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Document</title>\n" + "</head>\n" + "<body>\n" + "    <h3>ご注文ありがとうございます！</h3>\n"
				+ "    <h4>以下のご注文を承りました！</h4>\n" + "<hr>";
		for (OrderItem orderItem : orderItemList) {
			List<OrderTopping> orderToppingList = orderItem.getOrderToppingList();
			String item_message = "    <p>注文商品  " + orderItem.getItem().getName() + "</p>" + "    <p>個数"
					+ orderItem.getQuantity() + "</p>\n" + "    <p>トッピング  ";
			message += item_message;
			for (OrderTopping orderTopping : orderToppingList) {
				String topping_message = orderTopping.getTopping().getName() + "</p>";
				message += topping_message;
			}
			message += "<hr>";
		}
		String foot_message = "<h4>お届け時刻" + order.getDeliveryTime() + "</h4>" + "</body>\n" + "</html>";
		message += foot_message;
		return message;
	}
	
	public String checkCreditCard() {
		String url = "http://153.127.48.168:8080/sample-credit-card-web-api/credit-card/payment";
		HttpHeaders httpHeaders = new HttpHeaders();
		//こっからサンプル
//		String userId = "0";
//		String orderNumber = "12345678901234";
//		String amount = "1234567890";
//		String cardNumber = "1234567890";
//		String cardExpYear = "2025";
//		String cardExpMonth = "02";
//		String cardName = "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvb";
//		String cardCvv = "123";
		
		String json = "{\r\n"
				+ "  \"user_id\":2,\r\n"
				+ "  \"order_number\":12345678901234,\r\n"
				+ "  \"amount\":1234567890,\r\n"
				+ "  \"card_number\":12345678901234,\r\n"
				+ "  \"card_exp_year\":2000,\r\n"
				+ "  \"card_exp_month\":12,\r\n"
				+ "  \"card_name\":\"ishikawa kazuhiro\",\r\n"
				+ "  \"card_cvv\":321\r\n"
				+ "}";
		
		RequestEntity.BodyBuilder builder = RequestEntity.post( uri( url ) );
		RestTemplate rest = new RestTemplate();
		 @SuppressWarnings("deprecation")
		RequestEntity<String> request = builder
		            .contentType( MediaType.APPLICATION_JSON )
		            .body( json );

		    ResponseEntity<String> response = rest.exchange( 
		            request, 
		            String.class );
		
		

		//リクエストの送信
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(httpHeaders), String.class, userId, orderNumber, amount, cardNumber, cardExpYear, cardExpMonth, cardName, cardCvv);

		//結果の取得
		HttpStatusCode status = response.getStatusCode();
		String body = response.getBody();
		return body;
	}
	
	private static final URI uri( String url ) {
	    try {
	        return new URI( url );
	    }
	    // 検査例外はうざいのでランタイム例外でラップして再スロー。
	    catch ( Exception ex ) {
	        throw new RuntimeException( ex );
	    }
	}

}
