package jp.co.example.ecommerce_b.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 注文情報を表すドメイン.
 * 
 * @author mami.horioka
 *
 */
public class Order {

	/** ID */
	private Integer id;
	/** ユーザーID */
	private Integer userId;
	/** 状態 */
	private Integer status;
	/** 合計金額 */
	private Integer totalPrice;
	/** 注文日 */
	private Date orderDate;
	/** 宛先氏名 */
	private String destinationName;
	/** 宛先Eメール */
	private String destinationEmail;
	/** 宛先郵便番号 */
	private String destinationZipcode;
	/** 宛先都道府県 */
	private String destinationPref;
	/** 宛先市区町村 */
	private String destinationMunicipalities;
	/** 宛先残りの住所 */
	private String destinationAddress;
	/** 宛先TEL */
	private String destinationTel;
	/** 配達時間 */
	private Timestamp deliveryTime;
	/** 支払方法 */
	private Integer paymentMethod;
	/** ユーザー */
	private User user;
	/** 注文リスト */
	private List<OrderItem> orderItemList;

	public Order() {
	}

	public Order(Integer id, Integer userId, Integer status, Integer totalPrice, Date orderDate, String destinationName,
			String destinationEmail, String destinationZipcode, String destinationPref,
			String destinationMunicipalities, String destinationAddress, String destinationTel, Timestamp deliveryTime,
			Integer paymentMethod, User user, List<OrderItem> orderItemList) {
		super();
		this.id = id;
		this.userId = userId;
		this.status = status;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.destinationName = destinationName;
		this.destinationEmail = destinationEmail;
		this.destinationZipcode = destinationZipcode;
		this.destinationPref = destinationPref;
		this.destinationMunicipalities = destinationMunicipalities;
		this.destinationAddress = destinationAddress;
		this.destinationTel = destinationTel;
		this.deliveryTime = deliveryTime;
		this.paymentMethod = paymentMethod;
		this.user = user;
		this.orderItemList = orderItemList;
	}

	/**
	 * 消費税を計算する.
	 * 
	 * @return 消費税
	 */
	public int getTax() {
		int priceWithoutTax = 0;
		for (OrderItem orderItem : orderItemList) {
			priceWithoutTax += orderItem.getSubTotal();
		}
		return (int) (priceWithoutTax * 0.1);
	}

	/**
	 * 合計金額を計算する.
	 * 
	 * @return 合計金額
	 */
	public int getCalcTotalPrice() {
		return this.getTax() * 11;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationPref() {
		return destinationPref;
	}

	public void setDestinationPref(String destinationPref) {
		this.destinationPref = destinationPref;
	}

	public String getDestinationMunicipalities() {
		return destinationMunicipalities;
	}

	public void setDestinationMunicipalities(String destinationMunicipalities) {
		this.destinationMunicipalities = destinationMunicipalities;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationPref="
				+ destinationPref + ", destinationMunicipalities=" + destinationMunicipalities + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime
				+ ", paymentMethod=" + paymentMethod + ", user=" + user + ", orderItemList=" + orderItemList + "]";
	}

}
