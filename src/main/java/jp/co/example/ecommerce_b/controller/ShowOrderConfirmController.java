package jp.co.example.ecommerce_b.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.form.OrderForm;
import jp.co.example.ecommerce_b.service.ShowOrderConfirmService;

/**
 * 注文確認画面を操作するコントローラー.
 * 
 * @author kanae.osaki
 *
 */
@Controller
@RequestMapping("/showOrderConfirm")
public class ShowOrderConfirmController {

	@Autowired
	private ShowOrderConfirmService showOrderConfirmService;

	/**
	 * 注文確認画面の表示.
	 * 
	 * @param orderId 注文ID
	 * @param model   モデル
	 * @return 注文確認画面
	 */
	@PostMapping("/")
	public String showOrderConfirm(Integer orderId, Model model, OrderForm form) {
		if (form.getDeliveryDate() == null) {
			form.setDeliveryDate(new Date(System.currentTimeMillis()));
		}
		if (form.getDeliveryTime() == 0) {
			form.setDeliveryTime(10);
		}
		if (form.getPaymentMethod() == null) {
			form.setPaymentMethod(1);
		}
		model.addAttribute("order", showOrderConfirmService.showOrderConfirm(orderId));
		model.addAttribute("orderId", orderId);
		return "order_confirm";
	}
}
