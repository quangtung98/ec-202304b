package jp.co.example.ecommerce_b.form;

/**
 * クレジットカード入力情報を送信するためのフォームです.
 * 
 * @author kazuhiro.ishikawa
 *
 */
public class CreditCardForm {

	/** クレジットカード番号 */
	private String creditCardId;

	/** 有効期限の月 */
	private String monthOfExpiry;

	/** 有効期限の年 */
	private String yearOfExpiry;

	/** カード名義人 */
	private String cardHolder;

	/** セキュリティコード */
	private String securityCode;

	// getter setter
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

	// toString
	@Override
	public String toString() {
		return "CreditCardForm [creditCardId=" + creditCardId + ", monthOfExpiry=" + monthOfExpiry + ", yearOfExpiry="
				+ yearOfExpiry + ", cardHolder=" + cardHolder + ", securityCode=" + securityCode + "]";
	}
}
