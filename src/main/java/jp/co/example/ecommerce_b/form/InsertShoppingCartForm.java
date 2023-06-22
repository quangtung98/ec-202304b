package jp.co.example.ecommerce_b.form;

import java.util.List;

/**
 * ショッピングカートに商品を追加するためのフォームクラス.
 * 
 * @author mami.horioka
 *
 */
public class InsertShoppingCartForm {

	/** 商品ID */
	private Integer itemId;
	/** サイズ */
	private String size;
	/** トッピングのリスト */
	private List<Integer> orderToppingList;
	/** 数量 */
	private Integer quantity;

	public InsertShoppingCartForm() {
	}

	public InsertShoppingCartForm(Integer itemId, String size, List<Integer> toppingList, Integer quantity) {
		super();
		this.itemId = itemId;
		this.size = size;
		this.orderToppingList = toppingList;
		this.quantity = quantity;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Integer> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<Integer> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "InsertShoppingCartForm [itemId=" + itemId + ", size=" + size + ", toppingList=" + orderToppingList
				+ ", quantity=" + quantity + "]";
	}

}
