package web.servlet.order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewOrderListFormServlet extends HttpServlet {
    private static final String LIST_ORDERS_FORM = "/WEB-INF/jsp/order/listOrders.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LIST_ORDERS_FORM).forward(req,resp);   // 展示历史订单列表
    }
}
