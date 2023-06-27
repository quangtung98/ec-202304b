package jp.co.example.ecommerce_b.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.domain.User;
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
		Order order = showOrderConfirmService.showOrderConfirm(orderId);
		User user = showOrderConfirmService.setUserInForm(order.getUserId());
		if (form.getDestinationName() == null) {
			form.setDestinationName(user.getName());
		}
		if (form.getDestinationEmail() == null) {
			form.setDestinationEmail(user.getEmail());
		}
		if (form.getDestinationZipcode() == null) {
			form.setDestinationZipcode(user.getZipcode());
		}
		if (form.getDestinationPref() == null) {
			form.setDestinationPref(user.getPref());
		}
		if (form.getDestinationMunicipalities() == null) {
			form.setDestinationMunicipalities(user.getMunicipalities());
		}
		if (form.getDestinationAddress() == null) {
			form.setDestinationAddress(user.getAddress());
		}
		if (form.getDestinationTel() == null) {
			form.setDestinationTel(user.getTelephone());
		}
		if (form.getDeliveryDate() == null) {
			model.addAttribute("inputDate", new Date(System.currentTimeMillis()));
		} else {
			model.addAttribute("inputDate", form.getDeliveryDate());
		}

		if (form.getDeliveryTime() == 0) {
			form.setDeliveryTime(10);
		}
		if (form.getPaymentMethod() == null) {
			form.setPaymentMethod(1);
		}

		model.addAttribute("order", order);
		model.addAttribute("orderId", orderId);

		return "order_confirm";
	}

}
