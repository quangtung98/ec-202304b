package jp.co.example.ecommerce_b.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.Topping;
import jp.co.example.ecommerce_b.repository.ItemRepository;
import jp.co.example.ecommerce_b.repository.ToppingRepository;

/**
 * itemRepositoryとtoppingRepositoryのメソッドを操作するレポジトリ.
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

	public Map<Integer, String> showToppings() {
		List<Topping> toppingList = toppingRepository.findAll();
		Map<Integer, String> toppingMap = new LinkedHashMap<>();
		for (Topping topping : toppingList) {
			toppingMap.put(topping.getId(), topping.getName());
		}
		return toppingMap;
	}

}
