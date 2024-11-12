<%@ page import="java.util.List" %>
<%@ page import="domain.Order" %>
<%@ include file="../common/top.jsp"%>

<h2>My Orders</h2>

<table>
    <tr>
        <th>Order ID</th>
        <th>Date</th>
        <th>Total Price</th>
    </tr>

    <%
        List<Order> orderList = (List<Order>) session.getAttribute("orderList");
        for (Order order : orderList) {
            out.println("<tr>" +
                    "<td><a href=viewOrderFromListForm?orderId="+order.getOrderId()+">"+order.getOrderId()+"</a></td>" +
                    "<td>"+order.getOrderDate()+"</td>" +
                    "<td>$"+order.getTotalPrice()+"</td>" +
                    "</tr>");
        }
    %>
</table>

<%@ include file="../common/bottom.jsp"%>


