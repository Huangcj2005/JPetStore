<%@ page import="domain.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.LineItem" %>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

    <table>
        <tr>
            <th align="center" colspan="2">
                Order #${sessionScope.order.orderId} Date:${sessionScope.order.orderDate}
            </th>
        </tr>
        <tr>
            <th colspan="2">Payment Details</th>
        </tr>
        <tr>
            <td>Card Type:</td>
            <td>${sessionScope.order.cardType}</td>
        </tr>
        <tr>
            <td>Card Number:</td>
            <td>${sessionScope.order.creditCard} * Fake number!</td>
        </tr>
        <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td>${sessionScope.order.expiryDate}</td>
        </tr>
        <tr>
            <th colspan="2">Billing Address</th>
        </tr>
        <tr>
            <td>First name:</td>
            <td>${sessionScope.order.billToFirstName}</td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td>${sessionScope.order.billToLastName}</td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td>${sessionScope.order.billAddress1}</td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td>${sessionScope.order.billAddress2}</td>
        </tr>
        <tr>
            <td>City:</td>
            <td>${sessionScope.order.billCity}</td>
        </tr>
        <tr>
            <td>State:</td>
            <td>${sessionScope.order.billState}</td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td>${sessionScope.order.billZip}</td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>${sessionScope.order.billCountry}</td>
        </tr>
        <tr>
            <th colspan="2">Shipping Address</th>
        </tr>
        <tr>
            <td>First name:</td>
            <td>${sessionScope.order.shipToFirstName}</td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td>${sessionScope.order.shipToLastName}</td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td>${sessionScope.order.shipAddress1}</td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td>${sessionScope.order.shipAddress2}</td>
        </tr>
        <tr>
            <td>City:</td>
            <td>${sessionScope.order.shipCity}</td>
        </tr>
        <tr>
            <td>State:</td>
            <td>${sessionScope.order.shipState}</td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td>${sessionScope.order.shipZip}</td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>${sessionScope.order.shipCountry}</td>
        </tr>
        <tr>
            <td>Courier:</td>
            <td>${sessionScope.order.courier}</td>
        </tr>
        <tr>
            <td colspan="2">Status: ${sessionScope.order.status}</td>
        </tr>
        <tr>
            <td colspan="2">
                <table>
                    <tr>
                        <th>Item ID</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total Cost</th>
                    </tr>
                    <%
                        Order order = (Order) session.getAttribute("order");
                        List<LineItem> lineItems = order.getLineItems();
                        for (LineItem li : lineItems) {
                            out.println("<tr>" +
                                    "<td><a href=>"+li.getItem().getItemId()+"</a></td>");
                            if(li.getItem()!=null){
                                out.println("<td>"+ li.getItem().getAttribute1() + li.getItem().getProduct().getName() + "</td>");
                            }else {
                                out.println("<td><i>{description unavailable}</i></td>");
                            }
                            out.println("<td>"+ li.getQuantity() +"</td>");
                            out.println("<td>$"+ li.getUnitPrice() +"</td>");
                            out.println("<td>$"+ li.getTotal() +"</td></tr>");

                        }
                    %>
                    <tr>
                        <th colspan="5">$${sessionScope.order.totalPrice}</th>
                    </tr>

                </table>
            </td>
        </tr>

    </table>

</div>

<%@ include file="../common/bottom.jsp"%>
