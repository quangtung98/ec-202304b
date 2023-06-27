package jp.co.example.ecommerce_b.domain;

import java.util.List;

/**
 * topping_articlesテーブルの情報を管理するドメイン.
 * 
 * @author kanae.osaki
 *
 */
public class ToppingArticle {

	/** ID */
	private Integer id;
	/** ユーザーID */
	private Integer userId;
	/** 商品ID */
	private Integer itemId;
	/** 投稿内容 */
	private String content;
	/** 投稿画像 */
	private String image;
	/** トッピングリスト */
	private List<Topping> toppingList;
	/** 商品情報 */
	private Item item;
	/** ユーザー情報 */
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Topping> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Topping> toppingList) {
		this.toppingList = toppingList;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ToppingArticle [id=" + id + ", userId=" + userId + ", itemId=" + itemId + ", content=" + content
				+ ", image=" + image + ", toppingList=" + toppingList + ", item=" + item + ", user=" + user + "]";
	}

}
