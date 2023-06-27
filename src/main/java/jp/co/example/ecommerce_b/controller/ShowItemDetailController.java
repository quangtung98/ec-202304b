package jp.co.example.ecommerce_b.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.form.InsertShoppingCartForm;
import jp.co.example.ecommerce_b.service.ShowItemDetailService;

/**
 * 商品詳細情報を表示する処理を行うコントローラー.
 * 
 * @author kazuhiro.ishikawa
 *
 */
@Controller
@RequestMapping("/showItemDetail")
public class ShowItemDetailController {

	@Autowired
	private ShowItemDetailService showItemDetailService;

	/**
	 * 商品詳細画面を表示するメソッド.
	 * 
	 * @param id    ID
	 * @param model 商品詳細情報を格納するリクエストスコープ
	 * @return 商品詳細画面
	 */
	@GetMapping("/")
	public String showItemDetail(Integer id, Model model, InsertShoppingCartForm form,
			@AuthenticationPrincipal LoginUser loginUser) {

		Item item = showItemDetailService.showItemDetail(id);
		model.addAttribute("item", item);

		Map<Integer, String> toppingMap = showItemDetailService.showToppings();
		model.addAttribute("toppingMap", toppingMap);

		model.addAttribute("likeCount", showItemDetailService.countLike(id));
		if (loginUser == null) {
			model.addAttribute("checkLike", 0);
		} else {
			model.addAttribute("checkLike", showItemDetailService.checkLike(loginUser.getUser().getId(), id));
		}
		return "item_detail";
	}

	/**
	 * いいね情報えを登録するメソッド.
	 * 
	 * @param loginUser ログインユーザーID
	 * @param itemId    商品ID
	 * @return 商品詳細画面へリダイレクト
	 */
	@PostMapping("/like")
	public String like(@AuthenticationPrincipal LoginUser loginUser, Integer itemId) {
		showItemDetailService.insert(loginUser.getUser().getId(), itemId);
		return "redirect:/showItemDetail/?id=" + itemId;
	}

}
