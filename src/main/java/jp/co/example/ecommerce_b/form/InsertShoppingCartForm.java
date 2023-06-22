package jp.co.example.ecommerce_b.form;

import java.util.List;

/**
 * 
 * @author mami.horioka
 *
 */
public class InsertShoppingCartForm {

	private Integer itemId;
	private String size;
	private List<Integer> toppingList;
	private Integer quantity;

	public InsertShoppingCartForm() {
	}

	public InsertShoppingCartForm(Integer itemId, String size, List<Integer> toppingList, Integer quantity) {
		super();
		this.itemId = itemId;
		this.size = size;
		this.toppingList = toppingList;
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

	public List<Integer> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "InsertShoppingCartForm [itemId=" + itemId + ", size=" + size + ", toppingList=" + toppingList
				+ ", quantity=" + quantity + "]";
	}

}
