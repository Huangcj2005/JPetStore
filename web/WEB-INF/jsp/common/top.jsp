<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<html>

<head>
    <title>XX网宠物商店</title>
    <link rel="StyleSheet" href="css/petstore.css" type="text/css" media="screen" />
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
                Account login_account = (Account) session.getAttribute("loginAccount");
                if(login_account==null){
                    out.println("<a href=signonForm"+"> Sign In </a>");
                } else if (!login_account.getStatus().equals("OK")) {
                    out.println("<a href=signonForm"+"> Sign In </a>");
                } else {
                    out.println(
                        "<a href=#"+"> Sign Out </a>"+
                        "<img align=middle src=images/separator.gif />"+
                        "<a href=#"+"> My Account </a>"+
                        "<img align=middle src=images/separator.gif />");
                }
            %>


            <a href="help.html">?</a>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="" method="POST">
                <input type="text" name="keyword" size="14">
                <input type="submit" value="Search">
            </form>
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