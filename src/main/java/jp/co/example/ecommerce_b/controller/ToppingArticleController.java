package jp.co.example.ecommerce_b.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.form.InsertToppingArticleForm;
import jp.co.example.ecommerce_b.service.ShowItemDetailService;
import jp.co.example.ecommerce_b.service.ShowItemListService;
import jp.co.example.ecommerce_b.service.ToppingArticleService;

@Controller
@RequestMapping("/toppingArticle")
public class ToppingArticleController {

	@Autowired
	private ToppingArticleService toppingArticleService;

	@Autowired
	private ShowItemListService showItemListService;

	@Autowired
	private ShowItemDetailService showItemDetailService;

	@GetMapping("/show")
	public String show(Model model) {
		model.addAttribute("articleItemList", toppingArticleService.show());
		return "topping_article_list";
	}

	@GetMapping("/toPost")
	public String toPost(InsertToppingArticleForm form, Model model) {
		List<Item> itemList = showItemListService.showItemList("", 1);
		model.addAttribute("itemList", itemList);
		Map<Integer, String> toppingMap = showItemDetailService.showToppings();
		model.addAttribute("toppingMap", toppingMap);
		return "create_topping_article";
	}

	@PostMapping("/post")
	public String post(InsertToppingArticleForm form) {
		return "redirect:/toppingArticle/show";
	}

}
