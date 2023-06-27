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
import jp.co.example.ecommerce_b.domain.ToppingLike;
import jp.co.example.ecommerce_b.form.InsertToppingArticleForm;
import jp.co.example.ecommerce_b.repository.RecommendToppingRepository;
import jp.co.example.ecommerce_b.repository.ToppingArticleRepository;
import jp.co.example.ecommerce_b.repository.ToppingLikeRepository;

/**
 * おすすめトッピング投稿を管理するサービス.
 * 
 * @author mami.horioka
 *
 */
@Service
@Transactional
public class ToppingArticleService {

	@Autowired
	private ToppingArticleRepository toppingArticleRepository;

	@Autowired
	private RecommendToppingRepository recommendToppingRepository;

	@Autowired
	private ToppingLikeRepository toppingLikeRepository;

	/**
	 * おすすめトッピング投稿一覧を取得.
	 * 
	 * @return 投稿一覧
	 */
	public List<ToppingArticle> show() {
		List<ToppingArticle> toppingArticleList = toppingArticleRepository.findAll();
		for (ToppingArticle toppingArticle : toppingArticleList) {
			System.out.println(toppingArticle.getId());
			Integer count = toppingLikeRepository.findByToppingArticleId(toppingArticle.getId());
			Integer checkLike = toppingLikeRepository.findByUserIdAndToppingArticleId(toppingArticle.getUserId(),
					toppingArticle.getId());
			System.out.println(count);
			System.out.println(checkLike);
			toppingArticle.setLikeCount(count);
			toppingArticle.setCheckLike(checkLike);
		}

		return toppingArticleList;
	}

	/**
	 * おすすめトッピング投稿を新たに登録する.
	 * 
	 * @param form          フォーム
	 * @param userId        ユーザーID
	 * @param fileExtension
	 * @throws IOException
	 */
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

	/**
	 * いいね情報の挿入.
	 * 
	 * @param userId           ユーザーID
	 * @param toppingArticleId トッピング投稿ID
	 */
	public void insert(Integer userId, Integer toppingArticleId) {
		ToppingLike toppingLike = new ToppingLike();
		toppingLike.setToppingArticleId(toppingArticleId);
		toppingLike.setUserId(userId);
		toppingLikeRepository.insert(toppingLike);
	}

	/**
	 * いいねを解除する.
	 * 
	 * @param userId           ユーザーID
	 * @param toppingArticleId トッピング投稿ID
	 */
	public void deleteLike(Integer userId, Integer toppingArticleId) {
		toppingLikeRepository.delete(userId, toppingArticleId);
	}

}
