<%@ page import="domain.Account" %>
</div>

<div id="Footer">

    <div id="PoweredBy">&nbsp<a href="http://www.csu.edu.cn">www.csu.edu.cn</a>
    </div>

    <div id="Banner">
        <%
            if(session.getAttribute("account") != null){
                Account account = (Account)session.getAttribute("account");
                if(account.isBannerOption()){
                    out.println(account.getBannerName());
                }
            }
        %>
    </div>

</div>

</body>
</html>