package jp.co.example.ecommerce_b.domain;

/**
 * likesテーブルを表すドメイン.
 * 
 * @author kanae.osaki
 *
 */
public class Like {

	/** ID */
	private Integer id;
	/** ユーザーID */
	private Integer userId;
	/** 商品ID */
	private Integer itemId;

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

	@Override
	public String toString() {
		return "Like [id=" + id + ", userId=" + userId + ", itemId=" + itemId + "]";
	}

}
