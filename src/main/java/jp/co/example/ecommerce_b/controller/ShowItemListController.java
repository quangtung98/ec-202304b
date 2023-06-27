package jp.co.example.ecommerce_b.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.LoginUser;
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
	 * @param model      商品一覧情報を格納するスコープ
	 * @param fuzzyName  あいまい検索用の入力値
	 * @param sortMethod ソート方法
	 * @return 商品一覧画面
	 */
	@GetMapping("/")
	public String showItemList(Model model, String fuzzyName, Integer sortMethod) {
		List<Item> itemList = showItemListService.showItemList(fuzzyName, sortMethod);
		if (itemList.size() == 0) {
			model.addAttribute("noItemMessage", "商品は一件も存在しません。");
			itemList = showItemListService.showItemList(null, null); // nullを入れて全件検索をできるようにします
		}
//		if(sortMethod==null) {
//			sortMethod =1;
//		}
		Map<Integer, String> sortMethodMap = new HashMap<>();
		sortMethodMap.put(1, "名前の昇順");
		sortMethodMap.put(2, "名前の降順");
		sortMethodMap.put(3, "Mサイズ価格の昇順");
		sortMethodMap.put(4, "Mサイズ価格の降順");
		sortMethodMap.put(5, "Lサイズ価格の昇順");
		sortMethodMap.put(6, "Lサイズ価格の降順");
		sortMethodMap.put(7, "いいね💗が多い順");
		model.addAttribute("sortMethodMap", sortMethodMap);
		model.addAttribute("inputedFuzzyName", fuzzyName);
		model.addAttribute("selectedSortMethod", sortMethod);
		model.addAttribute("itemList", itemList);
		model.addAttribute("searchItemList", showItemListService.showItemList(null, sortMethod));
		return "item_list_coffee";
	}

	@GetMapping("/favorite")
	public String favorite(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		List<Item> itemList = showItemListService.findMyFavorite(loginUser.getUser().getId());
		model.addAttribute("itemList", itemList);
		return "my_favorite";
	}

	/**
	 * トップ画面を表示する.
	 * 
	 * @return トップ画面
	 */
	@GetMapping("/top")
	public String top() {
		return "top";
	}

}
