package jp.co.example.ecommerce_b.controller;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.form.OrderForm;
import jp.co.example.ecommerce_b.service.OrderService;
import jp.co.example.ecommerce_b.service.ShowOrderConfirmService;

/**
 * 注文情報を操作するコントローラー.
 * 
 * @author kanae.osaki
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShowOrderConfirmController showOrderConfirmController;

	@Autowired
	private ShowOrderConfirmService showOrderConfirmService;

	/**
	 * 注文情報をを登録する.
	 * 
	 * @param form    注文情報を保持するフォーム
	 * @param result  エラー情報を格納用オブジェクト
	 * @param model   モデル
	 * @param orderId 注文ID
	 * @return 注文完了画面へリダイレクト
	 */
	@PostMapping("")
	public String order(@Validated OrderForm form, BindingResult result, Model model, Integer orderId) {
		if (form.getPaymentMethod() == 2 && form.getCreditCardId().length() >= 14
				&& form.getCreditCardId().length() <= 16 && form.getCardHolder().length() >= 1
				&& form.getCardHolder().length() <= 50 && form.getSecurityCode().length() >= 3
				&& form.getSecurityCode().length() <= 4 && checkTextType(form.getCreditCardId()) && checkTextType(form.getSecurityCode())) {
			Map<String, String> map = orderService.checkCreditCard(form,
					showOrderConfirmService.showOrderConfirm(orderId));
			if (map.get("error_code").equals("E-01")) {
				FieldError fieldError = new FieldError(result.getObjectName(), "monthOfExpiry", "カードの有効期限が無効です");
				result.addError(fieldError);
			} else if (map.get("error_code").equals("E-02")) {
				FieldError fieldError = new FieldError(result.getObjectName(), "securityCode", "セキュリティコードが正しくありません");
				result.addError(fieldError);
			} else if (map.get("error_code").equals("E-03")) {
				FieldError fieldError = new FieldError(result.getObjectName(), "securityCode", "数値を入力してください");
				result.addError(fieldError);
			}
		}
		boolean checkAfter3Hours = form.getDeliveryTime() < LocalTime.now().getHour() + 4
				&& form.getDeliveryDate().toString().equals(new Date(System.currentTimeMillis()).toString());
		boolean checkAfterToday = form.getDeliveryDate().toLocalDate()
				.isBefore(new Date(System.currentTimeMillis()).toLocalDate());
		if (checkAfter3Hours || checkAfterToday) {
			FieldError fieldError = new FieldError(result.getObjectName(), "deliveryDate", "今から3時間後の日時をご入力ください");
			result.addError(fieldError);
		}
		if (form.getDestinationPref().equals("") || form.getDestinationMunicipalities().equals("")
				|| form.getDestinationAddress().equals("")) {
			result.rejectValue("destinationPref", "", "住所を入力してください");
		}
		if (result.hasErrors()) {
			return showOrderConfirmController.showOrderConfirm(form.getOrderId(), model, form);
		}
		Order order = orderService.order(form);
		orderService.sentMessageMail(order);
		return "redirect:/order/finished";
	}

	/**
	 * 注文完了画面を表示する.
	 * 
	 * @return 注文完了画面
	 */
	@GetMapping("/finished")
	public String finished() {
		return "order_finished";
	}

	@GetMapping("/testcredit")
	public String testCradit() {

		return "order_finished";
	}

	/**
	 * String型のテキストが数値か判定するメソッド.
	 * @param text 判定の対象の文
	 * @return　結果（boolean）
	 */
	public static boolean checkTextType(String text) {
		boolean result = true;
		for (int i = 0; i < text.length(); i++) {
			// i文字めの文字についてCharacter.isDigitメソッドで判定する
			if (Character.isDigit(text.charAt(i))) {
				// 数字の場合は次の文字の判定へ
				continue;
			} else {
				// 数字でない場合は検証結果をfalseに上書きする
				result = false;
				break;
			}
		}
		return result;
	}
}
