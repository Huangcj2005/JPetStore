<%@ page import="domain.Product" %>
<%@ page import="java.util.List" %>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

    <h2>${sessionScope.category.name}</h2>

    <table>
        <tr>
            <th>Product ID</th>
            <th>Name</th>
        </tr>
        <%
            List<Product> productList = (List<Product>) session.getAttribute("productList");
            for (Product product : productList) {
                out.print("<tr><td><a href=productForm?productId="+ product.getProductId() +">" + product.getProductId() + "</a></td><td>"+product.getName()+"</td></tr>");
            }
        %>
<%--        <c:forEach var="product" items="${sessionScope.productList}">--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <a href="">${product.productId}</a>--%>
<%--                </td>--%>
<%--                <td>${product.name}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
    </table>

</div>

<%@ include file="../common/bottom.jsp"%>


