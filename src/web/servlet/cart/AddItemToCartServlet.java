package web.servlet.cart;

import domain.Cart;
import domain.Item;
import service.CartService;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String workingItemId = req.getParameter("workingItemId");

        HttpSession session = req.getSession();
        Cart cart = (Cart)session.getAttribute("cart");

        if(cart == null){
            cart = new Cart();
        }

        CartService cartService = new CartService(cart);

        if (cartService.containsItemId(workingItemId)) {
            cartService.incrementQuantityByItemId(workingItemId);
        } else {
            CatalogService catalogService = new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cartService.addItem(item, isInStock);
        }

        session.setAttribute("cart",cart);
        req.getRequestDispatcher(CART_FORM).forward(req,resp);
//        resp.sendRedirect(CART_FORM);     // 注释内容或可解决页面刷新导致的购物车商品数量增加
    }
}
