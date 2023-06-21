package jp.co.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.service.ShowItemListService;

/**
 * 商品一覧を表示する処理をするコントローラー.
 * 
 * @author kazuhiro.ishikawa
 *
 */
@Controller
@RequestMapping("/")
public class ShowItemListController {

	@Autowired
	private ShowItemListService showItemListService;

	/**
	 * 商品一覧画面を表示する処理をするメソッド.
	 * 
	 * @param model 全商品一覧情報を格納するスコープ
	 * @return 商品一覧画面
	 */
	@GetMapping("/")
	public String showItemList(Model model) {
		List<Item> itemList = showItemListService.showItemList();
		if(itemList.size()==0) {
			model.addAttribute("noItemMessage","商品は一件も存在しません。" );
		}
		model.addAttribute("itemList", itemList);
		return "item_list_coffee";

	}

}
