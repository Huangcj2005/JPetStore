package web.servlet.order;

import domain.Account;
import domain.Cart;
import domain.Order;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class NewOrderServlet extends HttpServlet {
    private static final String ERROR ="/WEB-INF/jsp/common/error.jsp";
    private static final String SHIPPING ="/WEB-INF/jsp/order/shipping.jsp";
    private static final String CONFIRM_ORDER ="/WEB-INF/jsp/order/confirmOrder.jsp";
    private Order order = new Order();
    private boolean shippingAddressRequired = false;

    // 接收表单的数据
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        shippingAddressRequired = Boolean.parseBoolean(req.getParameter("shippingAddressRequired"));
        order = (Order) session.getAttribute("newOrder");

        if (shippingAddressRequired) {  // 需要更改地址信息
            shippingAddressRequired = false;
            req.getRequestDispatcher(SHIPPING).forward(req,resp);
        } else{    // 跳转至确认订单
            req.getRequestDispatcher(CONFIRM_ORDER);
        }
    }
}
