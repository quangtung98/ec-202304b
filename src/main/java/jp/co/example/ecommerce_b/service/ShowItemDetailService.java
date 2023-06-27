package jp.co.example.ecommerce_b.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.Like;
import jp.co.example.ecommerce_b.domain.Topping;
import jp.co.example.ecommerce_b.repository.ItemRepository;
import jp.co.example.ecommerce_b.repository.LikeRepository;
import jp.co.example.ecommerce_b.repository.ToppingRepository;

/**
 * itemRepositoryとtoppingRepositoryのメソッドを操作するサービス.
 * 
 * @author kazuhiro.ishikawa
 *
 */
@Service
@Transactional
public class ShowItemDetailService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ToppingRepository toppingRepository;

	@Autowired
	private LikeRepository likeRepository;

	/**
	 * 商品詳細情報を検索するメソッド.
	 * 
	 * @param id ID
	 * @return 商品詳細情報
	 */
	public Item showItemDetail(Integer id) {
		Item item = itemRepository.load(id);
		item.setToppingList(toppingRepository.findAll());
		return item;
	}

	/**
	 * トッピング一覧を取得するメソッド.
	 * 
	 * @return トッピングのマップ
	 */
	public Map<Integer, String> showToppings() {
		List<Topping> toppingList = toppingRepository.findAll();
		Map<Integer, String> toppingMap = new LinkedHashMap<>();
		for (Topping topping : toppingList) {
			toppingMap.put(topping.getId(), topping.getName());
		}
		return toppingMap;
	}

	/**
	 * いいね情報の挿入.
	 * 
	 * @param userId ユーザーID
	 * @param itemId 商品ID
	 */
	public void insert(Integer userId, Integer itemId) {
		Like like = new Like();
		like.setItemId(itemId);
		like.setUserId(userId);
		likeRepository.insert(like);
	}

	/**
	 * 既にいいねしているかのチェック.
	 * 
	 * @param userId ユーザーID
	 * @param itemId 商品ID
	 * @return 既に存在していたら１を存在していなければ0を返す
	 */
	public Integer checkLike(Integer userId, Integer itemId) {
		return likeRepository.findByUserIdAndItemId(userId, itemId);
	}

	/**
	 * いいね件数を取得.
	 * 
	 * @param itemId 商品ID
	 * @return いいね件数
	 */
	public Integer countLike(Integer itemId) {
		return likeRepository.findByItemId(itemId);
	}

	/**
	 * いいねを解除する.
	 * 
	 * @param userId ユーザーID
	 * @param itemId アイテムID
	 */
	public void deleteLike(Integer userId, Integer itemId) {
		likeRepository.delete(userId, itemId);
	}

}
