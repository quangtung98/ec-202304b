package jp.co.example.ecommerce_b.domain;

import java.util.List;

/**
 * 注文商品情報を表すドメイン.
 * 
 * @author mami.horioka
 *
 */
public class OrderItem {

	/** ID */
	private Integer id;
	/** アイテムID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** 商品 */
	private Item item;
	/** 注文トッピングリスト */
	private List<OrderTopping> orderToppingList;

	public OrderItem() {
	}

	public OrderItem(Integer id, Integer itemId, Integer orderId, Integer quantity, Character size, Item item,
			List<OrderTopping> orderToppingList) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.size = size;
		this.item = item;
		this.orderToppingList = orderToppingList;
	}

	/**
	 * 小計を計算する.
	 * 
	 * @return 小計
	 */
	public int getSubTotal() {
		int price = 0;
		if (this.size == 'm') {
			price += this.item.getPriceM();
			price += 200 * this.orderToppingList.size();
		} else if (this.size == 'l') {
			price += this.item.getPriceL();
			price += 300 * this.orderToppingList.size();
		}
		return price;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

}
