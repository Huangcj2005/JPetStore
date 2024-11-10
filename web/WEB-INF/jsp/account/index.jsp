<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>

<h2>用户登录</h2>
<font color="red" size="5px">${msg}</font>
<form action="<%=request.getContextPath()%>/login" method = "post">
    用户名：<input type="text" name="userName"/><br/>
    密码：<input type ="password" name="password"/><br/>
    验证码： <input type="text"  name = "verifyCode"/> <img src="<%=request.getContextPath()%>/verifyCode"   onClick="change()" id="picture"/>   看不清？<input type="button" value="换一张" onClick="change()" /><br/>
    <input type ="submit" value="登录"/>
</form>
</body>
<script language="javascript" type="text/javascript">

    function change(){

        var pic=document.getElementById("picture");


        var random = new Date().getTime();
        pic.src="<%=request.getContextPath()%>/verifyCode?time=" + random;


    }
</script>
</html>
