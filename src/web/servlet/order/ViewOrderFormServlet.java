package web.servlet.order;

import domain.Account;
import domain.Order;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewOrderFormServlet extends HttpServlet {
    private static final String VIEW_ORDER_FORM = "/WEB-INF/jsp/order/viewOrder.jsp";
    private static final String ERROR_FORM ="/WEB-INF/jsp/common/error.jsp";
    private String msg;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        OrderService orderService = new OrderService();

        Order order = (Order) session.getAttribute("order");
        Account account = (Account) session.getAttribute("loginAccount");

        orderService.insertOrder(order);       //将订单存入数据库


        if (account.getUsername().equals(order.getUsername())) {    // 校验
            session.setAttribute("cart", null);
            req.getRequestDispatcher(VIEW_ORDER_FORM).forward(req,resp);
        } else {
            order = null;
            msg = "You may only view your own orders.";
            req.setAttribute("errorMsg",msg);
            req.getRequestDispatcher(ERROR_FORM).forward(req,resp);
        }
    }
}
