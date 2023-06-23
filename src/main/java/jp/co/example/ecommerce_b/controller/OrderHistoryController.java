package jp.co.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.service.OrderHistoryService;

/**
 * 注文履歴を操作するコントローラー.
 * 
 * @author mami.horioka
 *
 */
@Controller
@RequestMapping("/orderHistory")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService service;

	/**
	 * 注文履歴を表示する.
	 * 
	 * @param loginUser ログインユーザー
	 * @param model     モデル
	 * @return 注文履歴画面
	 */
	@GetMapping("/show")
	public String showOrderHistory(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		List<Order> orderList = service.showOrderHistory(loginUser.getUser().getId());
		model.addAttribute("orderList", orderList);
		return "order_history";
	}

	/**
	 * 注文履歴詳細画面を表示する.
	 * 
	 * @param loginUser ログインユーザー
	 * @param model     モデル
	 * @param id        注文ID
	 * @return 注文履歴詳細画面
	 */
	@GetMapping("/detail")
	public String showOrderHistoryDetail(@AuthenticationPrincipal LoginUser loginUser, Model model, int id) {
		Order order = service.showOrderDetail(id);
		if (order.getUserId() != loginUser.getUser().getId()) {
			return "order_history";
		}
		model.addAttribute("order", order);
		return "order_history_detail";
	}
}
