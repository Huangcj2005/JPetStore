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
import java.util.List;

public class ViewOrderListFormServlet extends HttpServlet {
    private static final String LIST_ORDERS_FORM = "/WEB-INF/jsp/order/listOrders.jsp";
    private List<Order> orderList;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");

        OrderService orderService = new OrderService();
        orderList = orderService.getOrdersByUsername(account.getUsername());    // 查询出 orderList

        session.setAttribute("orderList",orderList);
        req.getRequestDispatcher(LIST_ORDERS_FORM).forward(req,resp);
    }
}
