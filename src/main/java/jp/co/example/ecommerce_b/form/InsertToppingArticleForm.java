package jp.co.example.ecommerce_b.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * おすすめトッピング投稿を追加するためのフォームクラス.
 * 
 * @author mami.horioka
 *
 */
public class InsertToppingArticleForm {

	/** 商品Id */
	private Integer itemId;
	/** 画像 */
	private MultipartFile image;
	/** トッピングリスト */
	private List<Integer> toppingList;
	/** コメント */
	private String content;

	public InsertToppingArticleForm() {

	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public List<Integer> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
