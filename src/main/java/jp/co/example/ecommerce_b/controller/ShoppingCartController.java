package jp.co.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.LoginUser;
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
	public String InsertShoppingCart(InsertShoppingCartForm form, @AuthenticationPrincipal LoginUser loginUser,
			Model model) {
		service.insertShoppingCart(form, loginUser.getUser().getId());
		return showShoppingCart(loginUser.getUser().getId(), 0, model);
	}

	@PostMapping("/delete")
	public String deleteShoppingCart(int orderItemId) {
		return null;
	}
}
