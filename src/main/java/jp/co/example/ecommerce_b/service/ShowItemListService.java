package jp.co.example.ecommerce_b.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.repository.ItemRepository;

/**
 * 全件検索のためItemRepositoryを操作するサービス.
 * 
 * @author kazuhiro.ishikawa
 *
 */
@Service
@Transactional
public class ShowItemListService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 表示する商品の検索を行うメソッド（初回ログイン時はnullのため全件検索される）
	 * 
	 * @param fuzzyName  入力された名前（あいまい検索に使用）
	 * @param sortMethod ソート方法
	 * @return 表示する商品一覧
	 */
	public List<Item> showItemList(String fuzzyName, String sortMethod) {
		List<Item> itemList = new ArrayList<>();
		if (fuzzyName == null) {
			itemList = getSortedFindAll(sortMethod);
		} else {
			itemList = getSortedFindByNameContaining(fuzzyName, sortMethod);
		}
		return itemList;
	}

	/**
	 * ソートされた全件検索を行うメソッド.
	 * 
	 * @param sortMethod ソート方法
	 * @return ソートされた全商品一覧
	 */
	private List<Item> getSortedFindAll(String sortMethod) {
		List<Item> itemList = new ArrayList<>();
		if (sortMethod == null) {
			itemList = itemRepository.findAll("name", "ASC");
		} else if (sortMethod.equals("名前の昇順")) {
			itemList = itemRepository.findAll("name", "ASC");
		} else if (sortMethod.equals("名前の降順")) {
			itemList = itemRepository.findAll("name", "DESC");
		} else if (sortMethod.equals("Mサイズ価格の昇順")) {
			itemList = itemRepository.findAll("price_m", "ASC");
		} else if (sortMethod.equals("Mサイズ価格の降順")) {
			itemList = itemRepository.findAll("price_m", "DESC");
		} else if (sortMethod.equals("Lサイズ価格の昇順")) {
			itemList = itemRepository.findAll("price_l", "ASC");
		} else if (sortMethod.equals("Lサイズ価格の降順")) {
			itemList = itemRepository.findAll("price_l", "DESC");
		}
		return itemList;
	}

	/**
	 * ソートされた曖昧検索を行うメソッド.
	 * 
	 * @param sortMethod ソート方法
	 * @return ソートされたあいまい検索をした全商品一覧
	 */
	private List<Item> getSortedFindByNameContaining(String fuzzyName, String sortMethod) {
		List<Item> itemList = new ArrayList<>();
		if (sortMethod == null) {
			itemList = itemRepository.findByNameContaining(fuzzyName,"name", "ASC");
		} else if (sortMethod.equals("名前の昇順")) {
			itemList = itemRepository.findByNameContaining(fuzzyName,"name", "ASC");
		} else if (sortMethod.equals("名前の降順")) {
			itemList = itemRepository.findByNameContaining(fuzzyName,"name", "DESC");
		} else if (sortMethod.equals("Mサイズ価格の昇順")) {
			itemList = itemRepository.findByNameContaining(fuzzyName,"price_m", "ASC");
		} else if (sortMethod.equals("Mサイズ価格の降順")) {
			itemList = itemRepository.findByNameContaining(fuzzyName,"price_m", "DESC");
		} else if (sortMethod.equals("Lサイズ価格の昇順")) {
			itemList = itemRepository.findByNameContaining(fuzzyName,"price_l", "ASC");
		} else if (sortMethod.equals("Lサイズ価格の降順")) {
			itemList = itemRepository.findByNameContaining(fuzzyName,"price_l", "DESC");
		}
		return itemList;
	}
}
