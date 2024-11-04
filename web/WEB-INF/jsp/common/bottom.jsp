<%@ page import="domain.Account" %>
</div>

<div id="Footer">

    <div id="PoweredBy">&nbsp<a href="http://www.csu.edu.cn">www.csu.edu.cn</a>
    </div>

    <div id="Banner">
        <%
            if(login_account != null){
                if(login_account.isBannerOption()){
                    out.println(login_account.getBannerName());
                }
            }
        %>
    </div>

</div>

</body>
</html>