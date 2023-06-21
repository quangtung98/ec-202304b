package jp.co.example.ecommerce_b.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.form.InsertShoppingCartForm;

/**
 * ショッピングカート情報を操作するコントローラー.
 * 
 * @author mami.horioka
 *
 */
@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@GetMapping("/show")
	public String showShoppingCart(int userId, int status) {
		return null;
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
