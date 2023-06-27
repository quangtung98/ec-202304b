package jp.co.example.ecommerce_b.form;

import java.sql.Date;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 注文情報を保持するフォーム.
 * 
 * @author kanae.osaki
 *
 */
public class OrderForm {

	/** 注文ＩＤ */
	private Integer orderId;
	/** お届け先の名前 */
	@NotBlank(message = "名前を入力してください")
	private String destinationName;
	/** Eメール */
	@Email(message = "メールアドレスを正しい形式で入力してください")
	private String destinationEmail;
	/** お届け先の郵便番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号をXXX-XXXXの形式で入力してください")
	private String destinationZipcode;
	/** お届け先の都道府県 */
	@NotBlank(message = "都道府県を入力してください")
	private String destinationPref;
	/** お届け先の市区町村 */
	@NotBlank(message = "市区町村を入力してください")
	private String destinationMunicipalities;
	/** お届け先の住所 */
	@NotBlank(message = "住所を入力してください")
	private String destinationAddress;
	/** お届け先の電話番号 */
	@Pattern(regexp = "^[0-9]{2,4}-[0-9]{3,4}-[0-9]{3,4}$", message = "電話番号形式で入力してください")
	private String destinationTel;
	/** 配達日 */
	private Date deliveryDate;
	/** 配達時間 */
	private int deliveryTime;
	/** お支払方法 */
	private Integer paymentMethod;
	/** クレジットカード番号 */
	@Pattern(regexp = "^[0-9]{14,16}", message = "14~16桁の数字を入力してください")
	private String creditCardId;
	/** 有効期限の月 */
	@Pattern(regexp = "^[0-9]{1,2}", message = "1~2桁の数字を入力してください")
	private String monthOfExpiry;
	/** 有効期限の年 */
	@Pattern(regexp = "^[0-9]{4}", message = "4桁の数字を入力してください")
	private String yearOfExpiry;
	/** カード名義人 */
	@Size(min = 1, max = 50, message = "1~50文字で入力してください")
	private String cardHolder;
	/** セキュリティコード */
	@Pattern(regexp = "^[0-9]{3,4}", message = "3~4桁の数字を入力してください")
	private String securityCode;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(String creditCardId) {
		this.creditCardId = creditCardId;
	}

	public String getMonthOfExpiry() {
		return monthOfExpiry;
	}

	public void setMonthOfExpiry(String monthOfExpiry) {
		this.monthOfExpiry = monthOfExpiry;
	}

	public String getYearOfExpiry() {
		return yearOfExpiry;
	}

	public void setYearOfExpiry(String yearOfExpiry) {
		this.yearOfExpiry = yearOfExpiry;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	public String toString() {
		return "OrderForm [orderId=" + orderId + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationPref="
				+ destinationPref + ", destinationMunicipalities=" + destinationMunicipalities + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryDate=" + deliveryDate
				+ ", deliveryTime=" + deliveryTime + ", paymentMethod=" + paymentMethod + ", creditCardId="
				+ creditCardId + ", monthOfExpiry=" + monthOfExpiry + ", yearOfExpiry=" + yearOfExpiry + ", cardHolder="
				+ cardHolder + ", securityCode=" + securityCode + "]";
	}

}
