package web.servlet.cart;

import com.alibaba.fastjson.JSON;
import domain.Cart;
import domain.CartItem;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class UpdateCartItemServlet extends HttpServlet {

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        session.setAttribute("flag",1);

        CartService cartService = new CartService(cart);

        String alterItemId = req.getParameter("alterItemId");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        cartService.setQuantityByItemId(alterItemId, quantity);

        String cartItem = JSON.toJSONString(cartService.getCartItemById(alterItemId));
        System.out.println(cartItem);

        resp.setContentType("text/json");
        PrintWriter out = resp.getWriter();
        out.println(cartItem);





//        Iterator<CartItem> cartItems = cartService.getAllCartItems();       // 迭代器
//
//        while (cartItems.hasNext()) {
//            CartItem cartItem = (CartItem) cartItems.next();
//            String itemId = cartItem.getItem().getItemId();
//            try {
//                String quantityString = req.getParameter(itemId);
//                int quantity = Integer.parseInt(quantityString);
//                cartService.setQuantityByItemId(itemId, quantity);
//                if (quantity < 1) {
//                    cartItems.remove();
//                }
//            } catch (Exception e) {
//                //ignore parse exceptions on purpose
//            }
//        }
//
//        req.getRequestDispatcher(CART_FORM).forward(req,resp);
    }
}
