
<%@ page import="java.util.List" %>
<%@ page import="domain.Item" %>
<%@ include file="../common/top.jsp"%>


<div id="BackLink">
    <a href="categoryForm?categoryId=${sessionScope.category.categoryId}">Return to ${sessionScope.category.name}</a>
</div>

<div id="Catalog">

    <h2>${sessionScope.product.name}</h2>

    <table>
        <tr>
            <th>Item ID</th>
            <th>Product ID</th>
            <th>Description</th>
            <th>List Price</th>
            <th>&nbsp;</th>
        </tr>
        <%
            List<Item> productList = (List<Item>) session.getAttribute("itemList");
            for (Item item : productList) {
                out.print("<tr><td><a href=ItemForm?itemId="+ item.getItemId() +">" + item.getItemId() + "</a></td>" +
                        "<td>"+item.getProductId()+"</td>" +
                        "<td>"+item.getAttribute1()+"</td>" +
                        "<td>$"+item.getListPrice()+"</td>"+
                        "<td><a href=addItemToCart?workingItemId="+ item.getItemId()+" class=Button>Add to Cart</a></td>" +
                        "</tr>");
            }
        %>
    </table>

</div>

<%@ include file="../common/bottom.jsp"%>