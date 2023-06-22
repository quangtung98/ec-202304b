package jp.co.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Item;
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
	public String showItemDetail(Integer id, Model model) {
		Item item = showItemDetailService.showItemDetail(id);
		model.addAttribute("item", item);
		return "item_detail";
	}

}
