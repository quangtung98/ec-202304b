package jp.co.example.ecommerce_b.domain;

/**
 * recommend_toppingsテーブル情報を管理するドメイン .
 * 
 * @author kanae.osaki
 *
 */
public class RecommendTopping {

	/** ID */
	private Integer id;
	/** トッピングID */
	private Integer toppingId;
	/** トッピング投稿ID */
	private Integer toppingArticleId;
	/** トッピング名前 */
	private String toppingName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getToppingId() {
		return toppingId;
	}

	public void setToppingId(Integer toppingId) {
		this.toppingId = toppingId;
	}

	public Integer getToppingArticleId() {
		return toppingArticleId;
	}

	public void setToppingArticleId(Integer toppingArticleId) {
		this.toppingArticleId = toppingArticleId;
	}

	public String getToppingName() {
		return toppingName;
	}

	public void setToppingName(String toppingName) {
		this.toppingName = toppingName;
	}

	@Override
	public String toString() {
		return "RecommendTopping [id=" + id + ", toppingId=" + toppingId + ", toppingArticleId=" + toppingArticleId
				+ ", toppingName=" + toppingName + "]";
	}

}
