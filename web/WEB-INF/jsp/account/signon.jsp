<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="signOn" method="post">
        <p>Please enter your username and password.</p>
        <c:if test="${requestScope.signOnMsg != null}">
            <p><font color="red">${requestScope.signOnMsg}</font></p>
        </c:if>
        <p>Username:<input type="text" name="username"> <br/>
            Password:<input type="password" name="password"> <br/>
            VerifyCode:<input type="text"  name = "verifyCode"/> <br/><img src="<%=request.getContextPath()%>/verifyCode"   onClick="change()" id="picture"/>
            unclear?<input type="button" value="change" onClick="change()" /></p>

        <input type="submit" value="Login">
    </form>
    Need a username and password?<a href="newAccountForm">Register Now!</a>

</div>
<script language="javascript" type="text/javascript">

    function change(){

        var pic=document.getElementById("picture");


        var random = new Date().getTime();
        pic.src="<%=request.getContextPath()%>/verifyCode?time=" + random;


    }
</script>
<%@ include file="../common/bottom.jsp"%>

