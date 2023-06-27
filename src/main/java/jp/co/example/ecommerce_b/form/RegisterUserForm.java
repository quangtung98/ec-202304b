package jp.co.example.ecommerce_b.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterUserForm {

	/** 姓 */
	@NotBlank(message = "姓を入力してください")
	private String lastName;
	/** 名 */
	@NotBlank(message = "名を入力してください")
	private String firstName;
	/** Eメール */
	@Email(message = "メールアドレスの形式が不正です")
	private String email;
	/** 郵便番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号はXXX-XXXXの形式で入力してください")
	private String zipcode;
	/** 都道府県 */
	private String pref;
	/** 市区町村 */
	private String municipalities;
	/** 残りの住所 */
	private String address;
	/** TEL */
	@Pattern(regexp = "^[0-9]{2,4}-[0-9]{3,4}-[0-9]{3,4}$", message = "電話番号形式にしてください")
	private String telephone;
	/** パスワード */
	@Size(min = 8, max = 16, message = "8字以上16字以内で設定してください")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力してください")
	private String confirmPassword;

	public RegisterUserForm() {
	}

	public RegisterUserForm(String firstName, String lastName, String email, String zipcode, String pref,
			String municipalities, String address, String telephone, String password, String confirmPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.zipcode = zipcode;
		this.pref = pref;
		this.municipalities = municipalities;
		this.address = address;
		this.telephone = telephone;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getName() {
		String name = lastName + firstName;
		return name;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPref() {
		return pref;
	}

	public void setPref(String pref) {
		this.pref = pref;
	}

	public String getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(String municipalities) {
		this.municipalities = municipalities;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "RegisterUserForm [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", zipcode="
				+ zipcode + ", pref=" + pref + ", municipalities=" + municipalities + ", address=" + address
				+ ", telephone=" + telephone + ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}

}
