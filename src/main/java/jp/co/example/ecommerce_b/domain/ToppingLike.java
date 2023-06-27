package jp.co.example.ecommerce_b.domain;

/**
 * topping_likesテーブルを表すドメイン.
 * 
 * @author kanae.osaki
 *
 */
public class ToppingLike {

	/** ID */
	private Integer id;
	/** ユーザーID */
	private Integer userId;
	/** トッピング投稿ID */
	private Integer toppingArticleId;

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

	public Integer getToppingArticleId() {
		return toppingArticleId;
	}

	public void setToppingArticleId(Integer toppingArticleId) {
		this.toppingArticleId = toppingArticleId;
	}

	@Override
	public String toString() {
		return "ToppingLike [id=" + id + ", userId=" + userId + ", toppingArticleId=" + toppingArticleId + "]";
	}

}
