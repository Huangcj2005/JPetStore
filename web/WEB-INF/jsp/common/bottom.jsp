<%@ page import="domain.Account" %>
</div>

<div id="Footer">

    <div id="PoweredBy">&nbsp<a href="http://www.csu.edu.cn">www.csu.edu.cn</a>
    </div>

    <div id="Banner">
        <%
            if(loginAccount != null){
                if(loginAccount.isBannerOption()){
                    out.println(loginAccount.getBannerName());
                }
            }
        %>
    </div>

</div>
<script src="js/productAuto.js?v=1"></script>

<div id="test">111</div>
</body>
</html>