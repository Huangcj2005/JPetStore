<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="editAccount" method="post">
        <h3>Edit User Information</h3>

        <table>
            <tr>
                <td>User ID:</td>
                <td>${sessionScope.loginAccount.username}</td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="text" name="repeatedPassword"></td>
            </tr>
        </table>

        <%@ include file="accountFields.jsp"%>

        <input type="submit" value="Save Account Information">
        <c:if test="${requestScope.editMsg != null}">
            <p><font color="red">${requestScope.editMsg}</font></p>
        </c:if>
    </form>
    <a href="viewOrderList">My Orders</a>
</div>

<%@ include file="../common/bottom.jsp"%>
