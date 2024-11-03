<%@ page import="domain.Item" %>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="productForm?productId=${sessionScope.product.productId}">Return to ${sessionScope.product.name}</a>
</div>

<div id="Catalog">

    <form action="addItemToCart" method="post">
    <table>
        <tr>
            <td>${sessionScope.product.description}</td>
        </tr>
        <tr>
            <td><b> ${sessionScope.item.itemId} </b></td>
        </tr>
        <tr>
            <td><b><font size="4"> ${sessionScope.item.attribute1}
                ${sessionScope.item.attribute2} ${sessionScope.item.attribute3}
                ${sessionScope.item.attribute4} ${sessionScope.item.attribute5}
                ${sessionScope.product.name} </font></b></td>
        </tr>
        <tr>
            <td>${sessionScope.product.name}</td>
        </tr>
        <tr>
            <td>
                <%
                    Item item = (Item) session.getAttribute("item");
                    if(item.getQuantity() <= 0){
                        out.println("Back ordered.");
                    }else {
                        out.println(item.getQuantity()+" in stock.");
                    }
                %>
            </td>
        </tr>
        <tr>
            <td>
                <%
                    out.println("$"+item.getListPrice());
                %>
            </td>
        </tr>

        <tr>
            <td>
                <a href="addItemToCart?workingItemId=${sessionScope.item.itemId}" class=Button>Add to Cart</a>
            </td>
        </tr>
    </table>
    </form>
</div>

<%@ include file="../common/bottom.jsp"%>