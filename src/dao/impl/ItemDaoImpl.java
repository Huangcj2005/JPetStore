package dao.impl;

import dao.ItemDao;
import domain.Item;

import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {
    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {

    }

    @Override
    public int getInventoryQuantity(String itemId) {
        return 0;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        return null;
    }

    @Override
    public Item getItem(String itemId) {
        return null;
    }
}
