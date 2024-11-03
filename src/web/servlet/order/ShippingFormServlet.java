package web.servlet.order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 跳转至改变地址用jsp
public class ShippingFormServlet extends HttpServlet {
    private static final String SHIPPING_FORM ="/WEB-INF/jsp/order/shipping.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SHIPPING_FORM).forward(req,resp);
    }
}
