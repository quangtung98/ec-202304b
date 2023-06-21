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
	 * 商品の検索を行うメソッド（初回ログイン時はnullのため全件検索される）
	 * 
	 * @param fuzzyName 入力された名前（あいまい検索に使用）
	 * @return 表示する商品一覧
	 */
	public List<Item> showItemList(String fuzzyName) {
		List<Item> itemList = new ArrayList<>();
		if (fuzzyName == null) {
			itemList = itemRepository.findAll();
		} else {
			itemList = itemRepository.findByNameContaining(fuzzyName);
		}
		return itemList;
	}
}
