package jp.co.example.ecommerce_b.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ToppingArticleService {

	// private ToppingArticleRepository toppingArticleRepository;

	public List<String> show() {
		return new ArrayList<String>();
	}
//	public List<ToppingArticle> show() {
//		// return toppingArticleRepository.findAll();
//		return null;
//	}

}
