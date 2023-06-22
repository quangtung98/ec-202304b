package jp.co.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.form.InsertShoppingCartForm;
import jp.co.example.ecommerce_b.service.ShoppingCartService;

/**
 * ショッピングカート情報を操作するコントローラー.
 * 
 * @author mami.horioka
 *
 */
@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService service;

	@GetMapping("/show")
	public String showShoppingCart(int userId, int status, Model model) {
		Order order = service.showShoppingCart(userId, status);
		model.addAttribute("order", order);
		return "cart_list";
	}

	@PostMapping("/insert")
	public String InsertShoppingCart(InsertShoppingCartForm form) {
		return null;
	}

	@PostMapping("/delete")
	public String deleteShoppingCart(int orderItemId) {
		return null;
	}
}
