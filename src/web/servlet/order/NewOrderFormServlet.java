package web.servlet.order;

import domain.Account;
import domain.Cart;
import domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 跳转至新建订单
public class NewOrderFormServlet extends HttpServlet {
    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";
    private static final String ERROR_FORM ="/WEB-INF/jsp/common/error.jsp";
    private Order order = new Order();
    private String msg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");  // 取用户 判断是否登录
        Cart cart = (Cart) session.getAttribute("cart");    // 取用户购物车对象 判断是否为空

        if (loginAccount == null) {   // 未登录 跳转至登录
            this.msg = ("You must sign on before attempting to check out.  Please sign on and try checking out again.");
            req.setAttribute("signOnMsg",this.msg);
            req.getRequestDispatcher("signonForm").forward(req, resp);
            //重定向改成了转发（目前未有问题）
        } else if (cart != null) {  // 跳转至新建订单页面
            order.initOrder(loginAccount, cart);
            session.setAttribute("order",order);    // 将 order 放进session中
            req.getRequestDispatcher(NEW_ORDER_FORM).forward(req,resp);
        } else {    // 用户购物车为空
            msg = "";
            req.setAttribute("errorMsg",msg);
            req.getRequestDispatcher(ERROR_FORM);
        }
    }
}
