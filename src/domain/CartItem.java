package domain;

import service.CartItemService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class CartItem implements Serializable {
    private Item item;
    private int quantity;
    private boolean inStock;
    private BigDecimal total;

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        CartItemService cartItemService = new CartItemService(this);
        cartItemService.calculateTotal();
//        calculateTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        CartItemService cartItemService = new CartItemService(this);
        cartItemService.calculateTotal();
//        calculateTotal();
    }

//
//    public void incrementQuantity() {
//        quantity++;
//        calculateTotal();
//    }

//    private void calculateTotal() {
//        if (item != null && item.getListPrice() != null) {
//            total = item.getListPrice().multiply(new BigDecimal(quantity));
//        } else {
//            total = null;
//        }
//    }
}
