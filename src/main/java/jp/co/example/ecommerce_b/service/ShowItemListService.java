package jp.co.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.repository.ItemRepository;

/**
 * 全件検索のためItemRepojitoryを操作するサービス.
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
	 * 商品の全件検索を行うメソッド.
	 * 
	 * @return 全商品一覧
	 */
	public List<Item> showItemList() {
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}
}
