package jp.co.example.ecommerce_b.domain;

/**
 * トッピング情報を表すドメイン.
 * 
 * @author mami.horioka
 *
 */
public class Topping {

	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** Mの価格 */
	private Integer priceM;
	/** Lの価格 */
	private Integer priceL;

	public Topping() {
	}

	public Topping(Integer id, String name, Integer priceM, Integer priceL) {
		super();
		this.id = id;
		this.name = name;
		this.priceM = priceM;
		this.priceL = priceL;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriceM() {
		return priceM;
	}

	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	public Integer getPriceL() {
		return priceL;
	}

	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}

	@Override
	public String toString() {
		return "Topping [id=" + id + ", name=" + name + ", priceM=" + priceM + ", priceL=" + priceL + "]";
	}

}
