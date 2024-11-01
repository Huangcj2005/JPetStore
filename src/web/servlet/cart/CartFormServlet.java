package web.servlet.cart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 转跳至购物车界面
public class CartFormServlet extends HttpServlet {
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CART_FORM).forward(req,resp);
    }
}