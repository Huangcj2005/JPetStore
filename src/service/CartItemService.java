package service;

import domain.CartItem;

import java.math.BigDecimal;

public class CartItemService {
    private CartItem cartItem;

    public CartItemService(){
        this.cartItem = new CartItem();
    }

    public CartItemService(CartItem cartItem){
        this.cartItem = cartItem;
    }

    public void incrementQuantity() {
        int quantity = cartItem.getQuantity() + 1;
        cartItem.setQuantity(quantity);;
        calculateTotal();
    }

    public void calculateTotal() {
        if (cartItem.getItem() != null && cartItem.getItem().getListPrice() != null) {
            cartItem.setTotal(cartItem.getItem().getListPrice().multiply(new BigDecimal(cartItem.getQuantity())));
        } else {
            cartItem.setTotal(null);
        }
    }
}
