package web.servlet.order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewOrderServlet extends HttpServlet {
    private static final String SHIPPING_FORM ="/WEB-INF/jsp/order/shipping.jsp";
    private static final String CONFIRM_ORDER_FORM ="/WEB-INF/jsp/order/confirmOrder.jsp";
    private boolean shippingAddressRequired = false;

    // 接收表单的数据
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        shippingAddressRequired = Boolean.parseBoolean(req.getParameter("shippingAddressRequired"));

        if (shippingAddressRequired) {  // 需要更改地址信息
            shippingAddressRequired = false;
            req.getRequestDispatcher(SHIPPING_FORM).forward(req,resp);
        } else{    // 跳转至确认订单
            req.getRequestDispatcher(CONFIRM_ORDER_FORM);
        }
    }
}
