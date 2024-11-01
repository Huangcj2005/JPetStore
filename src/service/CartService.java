package service;

import domain.Cart;
import domain.CartItem;
import domain.Item;

import java.math.BigDecimal;
import java.util.Iterator;

public class CartService {
    private Cart cart;


    public CartService(){
        this.cart = new Cart();
    }

    public CartService(Cart cart){
        this.cart = cart;
    }

    // Cart
    public Iterator<CartItem> getAllCartItems() {
        return cart.getItemList().iterator();
    }

    public boolean containsItemId(String itemId) {
        return cart.getItemMap().containsKey(itemId);
    }

    public void addItem(Item item, boolean isInStock) {
        CartItem cartItem = (CartItem) cart.getItemMap().get(item.getItemId());
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(0);
            cartItem.setInStock(isInStock);
            cart.getItemMap().put(item.getItemId(), cartItem);
            cart.getItemList().add(cartItem);
        }

        CartItemService cartItemService = new CartItemService(cartItem);
        cartItemService.incrementQuantity();
        cartItemService = null;
    }

    public Item removeItemById(String itemId) {
        CartItem cartItem = (CartItem) cart.getItemMap().remove(itemId);
        if (cartItem == null) {
            return null;
        } else {
            cart.getItemList().remove(cartItem);
            return cartItem.getItem();
        }
    }

    public void incrementQuantityByItemId(String itemId) {
        CartItem cartItem = (CartItem) cart.getItemMap().get(itemId);
        CartItemService cartItemService = new CartItemService(cartItem);
        cartItemService.incrementQuantity();
        cartItemService = null;
    }

    public void setQuantityByItemId(String itemId, int quantity) {
        CartItem cartItem = (CartItem) cart.getItemMap().get(itemId);
        cartItem.setQuantity(quantity);
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");
        Iterator<CartItem> items = getAllCartItems();
        while (items.hasNext()) {
            CartItem cartItem = (CartItem) items.next();
            Item item = cartItem.getItem();
            BigDecimal listPrice = item.getListPrice();
            BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getQuantity()));
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }

    public void clear() {
        cart = new Cart();
//        workingItemId = null;
    }
}
