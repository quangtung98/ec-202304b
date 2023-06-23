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

	/**
	 * ショッピングカートを表示する.
	 * 
	 * @param userId ユーザーID
	 * @param status 状態
	 * @param model  モデル
	 * @return ショッピングカート画面
	 */
	@GetMapping("/show")
	public String showShoppingCart(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		Order order = service.showShoppingCart(loginUser.getUser().getId(), 0);
		model.addAttribute("order", order);
		return "cart_list";
	}

	/**
	 * ショッピングカートに商品を追加する.
	 * 
	 * @param form      フォーム
	 * @param loginUser ログインユーザー
	 * @param model     モデル
	 * @return ショッピングカート画面
	 */
	@PostMapping("/insert")
	public String InsertShoppingCart(InsertShoppingCartForm form, @AuthenticationPrincipal LoginUser loginUser) {
		service.insertShoppingCart(form, loginUser.getUser().getId());
		return "redirect:/shoppingCart/show";
	}

	/**
	 * ショッピングカートから商品を削除する.
	 * 
	 * @param orderItemId 削除する注文商品ID
	 * @param loginUser   ログインユーザー
	 * @param model       モデル
	 * @return ショッピングカート画面
	 */
	@PostMapping("/delete")
	public String deleteShoppingCart(String orderItemId, @AuthenticationPrincipal LoginUser loginUser) {
		service.deleteOrderItemFromShoppingCart(Integer.parseInt(orderItemId));
		return "redirect: /shoppingCart/show";

	}
}
