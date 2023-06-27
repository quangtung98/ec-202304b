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

	/** 商品名 */
	private String itemName;
	/** 画像 */
	private MultipartFile image;
	/** トッピングリスト */
	private List<Integer> toppingList;
	/** コメント */
	private String comment;

	public InsertToppingArticleForm() {

	}

	public InsertToppingArticleForm(String itemName, MultipartFile image, List<Integer> toppingList, String comment) {
		super();
		this.itemName = itemName;
		this.image = image;
		this.toppingList = toppingList;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "InsertToppingArticleForm [itemName=" + itemName + ", image=" + image + ", toppingList=" + toppingList
				+ ", comment=" + comment + "]";
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
