package web.servlet.cart;

import domain.Cart;
import domain.Item;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveCartServlet extends HttpServlet {
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        session.setAttribute("flag",1);
        CartService cartService = new CartService(cart);

        String workingItemId = req.getParameter("workingId");
        Item item = cartService.removeItemById(workingItemId);

        if (item == null) { // 删除失败
            req.getRequestDispatcher(ERROR_FORM).forward(req,resp);
        } else {    // 删除成功
            req.getRequestDispatcher(CART_FORM).forward(req,resp);
        }
    }


}
