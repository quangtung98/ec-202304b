package jp.co.example.ecommerce_b.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.example.ecommerce_b.domain.User;
import jp.co.example.ecommerce_b.form.RegisterUserForm;
import jp.co.example.ecommerce_b.service.UserService;

/**
 * ユーザー情報を操作するコントローラー.
 * 
 * @author mami.horioka
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	/////////////////////////////////////////////////////
	// ユースケース：ログインをする
	/////////////////////////////////////////////////////
	/**
	 * ログイン画面を出力する.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/toLogin")
	public String toLogin(Model model, @RequestParam(required = false) String error) {
		System.err.println("login error:" + error);
		if (error != null) {
			System.err.println("login failed");
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
		}
		return "login";
	}

	/////////////////////////////////////////////////////
	// ユースケース：ユーザーを登録する
	/////////////////////////////////////////////////////
	/**
	 * ユーザー登録画面を出力する.
	 * 
	 * @param form フォーム
	 * @return ユーザー登録画面
	 */
	@GetMapping("/toRegister")
	public String toRegister(RegisterUserForm form) {
		return "register_user";
	}

	/**
	 * ユーザー登録を行う.
	 * 
	 * @param form   フォーム
	 * @param result エラー情報格納用オブジェクト
	 * @return ログイン画面
	 */
	@PostMapping("/register")
	public String register(@Validated RegisterUserForm form, BindingResult result) {
		if (!form.getPassword().equals(form.getConfirmPassword())) {
			result.rejectValue("password", "", "パスワードが一致していません");
			result.rejectValue("confirmationPassword", "", "");
		}

		User existUser = service.findByEmail(form.getEmail());
		if (existUser != null) {
			result.rejectValue("email", "", "そのメールアドレスは既に登録されています");
		}

		if (result.hasErrors()) {
			return toRegister(form);
		}

		User user = new User();
		BeanUtils.copyProperties(form, user);
		service.insert(user);
		return "redirect:/user/toLogin";
	}
}