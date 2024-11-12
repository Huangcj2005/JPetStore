<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="newOrder" method="post">
        <table>
            <tr>
                <th colspan=2>Payment Details</th>
            </tr>
            <tr>
                <td>Card Type:</td>
                <td>
                    <select name="order.cardType">
                        <option>Identity Card</option>
                        <option>Credit Card</option>
                        <option>Visa</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Card Number:</td>
                <td><input type="text" name="order.creditCard" value="${sessionScope.order.creditCard}"> * Use a fake number!</td>
            </tr>
            <tr>
                <td>Expiry Date (MM/YYYY):</td>
                <td><input type="text" name="order.expiryDate" value="${sessionScope.order.expiryDate}"></td>
            </tr>
            <tr>
                <th colspan=2>Billing Address</th>
            </tr>

            <tr>
                <td>First name:</td>
                <td><input type="text" name="order.billToFirstName" value="${sessionScope.order.billToFirstName}"></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text" name="order.billToLastName" value="${sessionScope.order.billToLastName}"></td>
            </tr>
            <tr>
                <td>Address 1:</td>
                <td><input type="text" name="order.billAddress1" value="${sessionScope.order.billAddress1}"></td>
            </tr>
            <tr>
                <td>Address 2:</td>
                <td><input type="text" name="order.billAddress2" value="${sessionScope.order.billAddress2}"></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="order.billCity" value="${sessionScope.order.billCity}"></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" name="order.billState" value="${sessionScope.order.billState}"></td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td><input type="text" name="order.billZip" value="${sessionScope.order.billZip}"></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="order.billCountry" value="${sessionScope.order.billCountry}"></td>
            </tr>

            <tr>
                <td colspan=2><label><input type="checkbox" name="shippingAddressRequired" value="true">Ship to different address...</label></td>
            </tr>

        </table>

        <input type="submit" value="Continue">
    </form>

</div>

<%@ include file="../common/bottom.jsp"%>