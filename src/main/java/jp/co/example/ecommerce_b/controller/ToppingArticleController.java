package jp.co.example.ecommerce_b.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.form.InsertToppingArticleForm;
import jp.co.example.ecommerce_b.service.ShowItemDetailService;
import jp.co.example.ecommerce_b.service.ShowItemListService;
import jp.co.example.ecommerce_b.service.ToppingArticleService;

/**
 * おすすめトッピング投稿を処理するコントローラ.
 * 
 * @author mami.horioka
 *
 */
@Controller
@RequestMapping("/toppingArticle")
public class ToppingArticleController {

	@Autowired
	private ToppingArticleService toppingArticleService;

	@Autowired
	private ShowItemListService showItemListService;
	
	@Autowired
	private ShowItemDetailService showItemDetailService;


	/**
	 * おすすめトッピング投稿一覧を表示する.
	 * 
	 * @param model モデル
	 * @return おすすめトッピング投稿一覧画面
	 */
	@GetMapping("/show")
	public String show(Model model,@AuthenticationPrincipal LoginUser loginUser) {
		model.addAttribute("articleItemList", toppingArticleService.show(loginUser.getUser().getId()));
		return "topping_article_list";
	}

	/**
	 * おすすめトッピング投稿画面を表示する。
	 * 
	 * @param form  フォーム
	 * @param model モデル
	 * @return おすすめトッピング投稿画面
	 */
	@GetMapping("/toPost")
	public String toPost(InsertToppingArticleForm form, Model model) {
		List<Item> itemList = showItemListService.showItemList("", 1);
		model.addAttribute("itemList", itemList);
		Map<Integer, String> toppingMap = showItemDetailService.showToppings();
		System.out.println(toppingMap);
		model.addAttribute("toppingMap", toppingMap);
		return "create_topping_article";
	}

	/**
	 * おすすめトッピングを投稿する.
	 * 
	 * @param form      フォーム
	 * @param result
	 * @param loginUser ログインユーザー
	 * @param model     モデル
	 * @return おすすめトッピング投稿一覧画面
	 * @throws IOException
	 */
	@PostMapping("/post")
	public String post(@Validated InsertToppingArticleForm form, BindingResult result,
			@AuthenticationPrincipal LoginUser loginUser, Model model) throws IOException {

		System.out.println("post!");

		// 画像ファイル形式チェック
		MultipartFile imageFile = form.getImage();
		System.out.println(imageFile);
		String fileExtension = null;
		try {
			fileExtension = getExtension(imageFile.getOriginalFilename());

			if (!"jpg".equals(fileExtension) && !"png".equals(fileExtension)) {
				result.rejectValue("image", "", "拡張子は.jpgか.pngのみに対応しています");
			}
		} catch (Exception e) {
			result.rejectValue("image", "", "拡張子は.jpgか.pngのみに対応しています");
		}
		System.out.println(fileExtension);

//		// 一つでもエラーがあれば入力画面へ戻りエラーメッセージを出す
//		if (result.hasErrors()) {
//			return toPost(form, model);
//		}
		toppingArticleService.insert(form, loginUser.getUser().getId(), fileExtension);
		return "redirect:/toppingArticle/show";
	}
	
	/**
	 * いいね情報を登録するメソッド.
	 * 
	 * @param loginUser ログインユーザーID
	 * @param toppingArticleId    トッピング投稿ID
	 * @return 投稿一覧画面へリダイレクト
	 */
	@PostMapping("/like")
	public String like(@AuthenticationPrincipal LoginUser loginUser, Integer toppingArticleId) {
		toppingArticleService.insert(loginUser.getUser().getId(), toppingArticleId);
		return "redirect:/toppingArticle/show";
	}
	
	/**
	 * いいねを解除する.
	 * 
	 * @param loginUser ログインユーザー
	 * @param toppingArticleId    トッピング投稿ID
	 * @return 投稿一覧画面へリダイレクト
	 */
	@PostMapping("/deleteLike")
	public String deleteLike(@AuthenticationPrincipal LoginUser loginUser, Integer toppingArticleId) {
		toppingArticleService.deleteLike(loginUser.getUser().getId(), toppingArticleId);
		return "redirect:/toppingArticle/show";
	}

	private String getExtension(String originalFileName) throws Exception {
		if (originalFileName == null) {
			throw new FileNotFoundException();
		}
		int point = originalFileName.lastIndexOf(".");
		if (point == -1) {
			throw new FileNotFoundException();
		}
		return originalFileName.substring(point + 1);
	}
	
	

}
