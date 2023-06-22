package jp.co.example.ecommerce_b.controller;

import java.sql.Date;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.form.OrderForm;
import jp.co.example.ecommerce_b.service.OrderService;

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

	/**
	 * 注文情報をを登録する.
	 * 
	 * @param form   注文情報を保持するフォーム
	 * @param result エラー情報を格納用オブジェクト
	 * @param model  モデル
	 * @return 注文完了画面へリダイレクト
	 */
	@PostMapping("")
	public String order(@Validated OrderForm form, BindingResult result, Model model) {
		if(form.getDeliveryTime()<LocalTime.now().getHour()+3 && form.getDeliveryDate()==new Date(System.currentTimeMillis())) {
			FieldError fieldError = new FieldError(result.getObjectName(), "deliveryTime", "今から3時間後の日時をご入力ください");
			result.addError(fieldError);
		}
		if (result.hasErrors()) {
			return showOrderConfirmController.showOrderConfirm(form.getOrderId(), model, form);
		}
		orderService.order(form);
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
}
