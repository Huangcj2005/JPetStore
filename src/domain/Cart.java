package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Cart implements Serializable {
    private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<String, CartItem>());
    private final List<CartItem> itemList = new ArrayList<CartItem>();

    public Iterator<CartItem> getCartItems() {
        return itemList.iterator();
    }

    public List<CartItem> getCartItemList() {
        return itemList;
    }

    public int getNumberOfItems() {
        return itemList.size();
    }

    public Map<String, CartItem> getItemMap() {
        return itemMap;
    }

    public List<CartItem> getItemList() {
        return itemList;
    }

    public Iterator<CartItem> getAllCartItems() {
        return itemList.iterator();
    }

}
