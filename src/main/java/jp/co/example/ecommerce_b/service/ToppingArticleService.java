package jp.co.example.ecommerce_b.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.RecommendTopping;
import jp.co.example.ecommerce_b.domain.ToppingArticle;
import jp.co.example.ecommerce_b.form.InsertToppingArticleForm;
import jp.co.example.ecommerce_b.repository.RecommendToppingRepository;
import jp.co.example.ecommerce_b.repository.ToppingArticleRepository;

@Service
@Transactional
public class ToppingArticleService {

	@Autowired
	private ToppingArticleRepository toppingArticleRepository;

	@Autowired
	private RecommendToppingRepository recommendToppingRepository;

	public List<ToppingArticle> show() {
		return toppingArticleRepository.findAll();
	}

	public void insert(InsertToppingArticleForm form, int userId, String fileExtension) throws IOException {
		ToppingArticle toppingArticle = new ToppingArticle();
		toppingArticle.setUserId(userId);
		BeanUtils.copyProperties(form, toppingArticle);

		String base64FileString = Base64.getEncoder().encodeToString(form.getImage().getBytes());
		if ("jpg".equals(fileExtension)) {
			base64FileString = "data:image/jpeg;base64," + base64FileString;
		} else if ("png".equals(fileExtension)) {
			base64FileString = "data:image/png;base64," + base64FileString;
		}
		toppingArticle.setImage(base64FileString);

		int id = toppingArticleRepository.insert(toppingArticle);
		for (int toppingId : form.getToppingList()) {
			RecommendTopping recommendTopping = new RecommendTopping();
			recommendTopping.setToppingArticleId(id);
			recommendTopping.setToppingId(toppingId);
			recommendToppingRepository.insert(recommendTopping);
		}

	}

}
