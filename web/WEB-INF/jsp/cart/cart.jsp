<%@ page import="domain.Cart" %>
<%@ page import="domain.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.Product" %>
<%@ page import="service.CartService" %>
<%@ include file="../common/top.jsp"%>

<div id="Welcome">
    <div id="WelcomeContent">
        <%
            if(loginAccount==null){
                out.println("<font color=red>Please Sign In</font>");
            }
        %>
    </div>
</div>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

    <div id="Cart">

        <h2>Shopping Cart</h2>

        <form action="updateCartItem" method="post">
            <table>
                <tr>
                    <th><b>Item ID</b></th>
                    <th><b>Product ID</b></th>
                    <th><b>Description</b></th>
                    <th><b>In Stock?</b></th>
                    <th><b>Quantity</b></th>
                    <th><b>List Price</b></th>
                    <th><b>Total Cost</b></th>
                    <th>&nbsp;</th>
                </tr>

                <%
                    Cart cart = (Cart) session.getAttribute("cart");
                    CartService cartService = new CartService(cart);
                    if(cart.getNumberOfItems()==0){
                        out.println("<tr><td colspan=\"8\"><b>Your cart is empty.</b></td></tr>");
                    } else {
                        List<CartItem> CartItems = cart.getItemList();
                        for(CartItem cartItem : CartItems){
                            out.println(
                                "<tr>"+
                                    "<td><a href=ItemForm?itemId="+ cartItem.getItem().getItemId() + ">" + cartItem.getItem().getItemId() +"</a></td>"+
                                    "<td>" + cartItem.getItem().getProduct().getProductId() + "</td>" +
                                    "<td>" + cartItem.getItem().getAttribute1() + cartItem.getItem().getProduct().getName() +"</td>"+
                                    "<td>" + cartItem.isInStock() + "</td>" +
                                    "<td><input type=text name="+ cartItem.getItem().getItemId() +" value="+ cartItem.getQuantity() +" ></td>"+
                                    "<td>$"+ cartItem.getItem().getListPrice()+"</td>"+
                                    "<td class=total total="+ cartItem.getTotal()+">$"+ cartItem.getTotal()+"</td>"+
                                    "<td><a href=removeCart?cartItemId="+ cartItem.getItem().getItemId() +" class=Button>Remove</a></td>"+
                                "</tr>"
                            );
                        }
                        out.println("<tr class=sub>" +
                                "<td colspan=5> </td>"+
                                "<td colspan=3 subTotal="+ cartService.getSubTotal() +">Sub Total:$"+ cartService.getSubTotal() +"</td>" +
                                "</tr>");
                    }
                %>
                <script src="js/cartAlter.js?v=1"></script>
            </table>
        </form>

        <%
            if(cart.getNumberOfItems() > 0)
                out.println("<a href=\"newOrderForm\" class=\"Button\">Proceed to Checkout</a>");
        %>

    </div>

    <div id="MyList">
        <%
            if(loginAccount!=null && loginAccount.isListOption()){
                List<Product> myFavoriteList = (List<Product>) session.getAttribute("myFavoriteList");
                if(myFavoriteList!=null){
                    out.println("<p> Pet Favorites <br>" +
                            "Shop for more of your favorite pets here.</p>" +
                            "<ul>");
                    for(Product product : myFavoriteList){
                        out.println("<li><a href=productForm?productId="+product.getProductId()+">"+ product.getName() +"</a>("+product.getProductId()+")</li>");
                    }
                    out.println("</ul>");
                }

            }
        %>
    </div>

    <div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/bottom.jsp"%>