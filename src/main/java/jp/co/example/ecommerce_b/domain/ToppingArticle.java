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
	private List<RecommendTopping> recommendToppingList;
	/** 商品情報 */
	private Item item;
	/** ユーザー情報 */
	private User user;
	/** いいね数 */
	private Integer likeCount;
	/** いいねチェック */
	private Integer checklike;

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

	public List<RecommendTopping> getRecommendToppingList() {
		return recommendToppingList;
	}

	public void setRecommendToppingList(List<RecommendTopping> recommendToppingList) {
		this.recommendToppingList = recommendToppingList;
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

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getChecklike() {
		return checklike;
	}

	public void setChecklike(Integer checklike) {
		this.checklike = checklike;
	}

	@Override
	public String toString() {
		return "ToppingArticle [id=" + id + ", userId=" + userId + ", itemId=" + itemId + ", content=" + content
				+ ", image=" + image + ", recommendToppingList=" + recommendToppingList + ", item=" + item + ", user="
				+ user + ", likeCount=" + likeCount + ", checklike=" + checklike + "]";
	}

}
