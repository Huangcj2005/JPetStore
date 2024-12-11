<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<html>

<head>
    <title>XX网宠物商店</title>
    <link rel="StyleSheet" href="css/petstore.css" type="text/css" media="screen" />
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>

<body>
<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="mainForm"><img src="images/logo-topbar.gif"></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <a href="cartForm"><img align="middle" name="img_cart" src="images/cart.gif" /></a>

            <img align="middle" src="images/separator.gif" />

            <%
                Account loginAccount = (Account) session.getAttribute("loginAccount");
                if(loginAccount==null){
                    out.println("<a href=signonForm> Sign In </a>");
                } else {
                    out.println(
                        "<a href=signOff"+"> Sign Out </a>"+
                        "<img align=middle src=images/separator.gif />"+
                        "<a href=editAccountForm> My Account </a>"+
                        "<img align=middle src=images/separator.gif />");
                }
            %>


            <a href="help.html">?</a>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="" method="POST">
                <input type="text" name="keyword" size="14" id="keyword" autocomplete="false">
                <input type="submit" value="Search">
            </form>
            <div id="productAutoComplete">
                <ul id="productAutoList">
<%--                    <li class="productAutoItem">Amazon Parrot</li>--%>
<%--                    <li class="productAutoItem">Labrador Retriever</li>--%>
<%--                    <li class="productAutoItem">Rattlesnake</li>--%>
<%--                    <li class="productAutoItem">Chihuahua</li>--%>
<%--                    <li class="productAutoItem">Tiger Shark</li>--%>
                </ul>
            </div>
        </div>
    </div>

    <div id="QuickLinks">
        <a href="categoryForm?categoryId=FISH"><img src="images/sm_fish.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=DOGS"><img src="images/sm_dogs.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=REPTILES"><img src="images/sm_reptiles.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=CATS"><img src="images/sm_cats.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=BIRDS"><img src="images/sm_birds.gif" /></a>
    </div>

</div>

<div id="Content">