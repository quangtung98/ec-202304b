package jp.co.example.ecommerce_b.form;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式が不正です")
	private String destinationEmail;
	/** お届け先の郵便番号 */
	@NotBlank(message = "郵便番号を入力してください")
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号はXXX-XXXXの形式で入力してください")
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
	@NotBlank(message = "電話番号を入力してください")
	@Pattern(regexp = "^[0-9]{2,4}-[0-9]{3,4}-[0-9]{3,4}$", message = "電話番号形式にしてください")
	private String destinationTel;
	/** 配達日 */
	@NotBlank(message = "配達日を入力してください")
	private Date deliveryDate;
	/** 配達時間 */
	@NotBlank(message = "配達時間を入力してください")
	private Date deliveryTime;
	/** お支払方法 */
	private Integer paymentMethod;

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

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "OrderForm [orderId=" + orderId + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationPref="
				+ destinationPref + ", destinationMunicipalities=" + destinationMunicipalities + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryDate=" + deliveryDate
				+ ", deliveryTime=" + deliveryTime + ", paymentMethod=" + paymentMethod + "]";
	}

}
