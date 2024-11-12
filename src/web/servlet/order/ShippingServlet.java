package web.servlet.order;

import domain.Order;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShippingServlet extends HttpServlet {
    private static final String CONFIRM_ORDER_FORM ="/WEB-INF/jsp/order/confirmOrder.jsp";
    private String shipToFirstName;
    private String shipToLastName;
    private String shipAddress1;
    private String shipAddress2;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private String shipCountry;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");

        this.shipToFirstName = req.getParameter("shipToFirstName");
        this.shipToLastName = req.getParameter("shipToLastName");
        this.shipAddress1 = req.getParameter("shipAddress1");
        this.shipAddress2 = req.getParameter("shipAddress2");
        this.shipCity = req.getParameter("shipCity");
        this.shipState = req.getParameter("shipState");
        this.shipZip = req.getParameter("shipZip");
        this.shipCountry = req.getParameter("shipCountry");
        initShipOrder(order);   // 更新订单信息

        OrderService orderService = new OrderService();
        orderService.insertOrder(order);

        session.setAttribute("orderId",order.getOrderId());

        req.getRequestDispatcher(CONFIRM_ORDER_FORM).forward(req,resp);
    }

    private void initShipOrder(Order order){
        order.setShipToFirstName(this.shipToFirstName);
        order.setShipToLastName(this.shipToLastName);
        order.setShipAddress1(this.shipAddress1);
        order.setShipAddress2(this.shipAddress2);
        order.setShipCity(this.shipCity);
        order.setShipState(this.shipState);
        order.setShipZip(this.shipZip);
        order.setShipCountry(this.shipCountry);
    }
}
