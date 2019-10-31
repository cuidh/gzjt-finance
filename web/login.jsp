<%--
  Created by IntelliJ IDEA.
  User: jianwei.zhou
  Date: 2019/10/6
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="./css/initial_default_props.css">
    <link rel="stylesheet" href="./css/login.css">

    <script type="text/javascript" src="./js/jquery-1.11.0.js"></script>
    <script type="text/javascript">
        $(function () {
            // 绑定点击事件
            $("#apply_account").click(function () {
                alert("功能未开放, 请联系管理员");
            });
            $("#forget_key").click(function () {
                alert("功能未开放, 请联系管理员");
            });
            $(".login-btn").click(function () {
                var user = $("#user_input").val();
                var key = $("#password_input").val();
                if (user == "") {
                    alert("请输入账号");
                    $("#user_input").focus();
                    return;
                }
                if (key == "") {
                    alert("请输入密码");
                    $("#password_input").focus();
                    return;
                }
                if (key.length < 8) {
                    alert("密码长度至少为8位");
                    $("#password_input").focus();
                    return;
                }
                $("#login_form").submit();
            });
        });
    </script>
</head>
<body>
<div class="content">
    <div class="banner"></div>
    <div class="login_box">
        <div class="logo"></div>
        <p class="logo_name">财务数据平台</p>
        <form id="login_form" action="${pageContext.request.contextPath}/loginServlet.do" method="post">
            <div class="login_input">
                <span class="span1"></span>
                <input type="text" id="user_input" placeholder="账号" name="usr_name"></input>
            </div>
            <div class="login_input">
                <span class="span2"></span>
                <input type="password" id="password_input" placeholder="密码" name="usr_key" size="8"></input>
            </div>
        </form>
        <div class="help">
            <!--            javascript：void(0)， 保持A标签链接样式，点击不跳转只是执行点击js函数-->
            <a id="apply_account" href="javascript:void(0)">申请账号</a>
            <a id="forget_key" href="javascript:void(0)">忘记密码</a>
        </div>
        <div style="text-align: center;">
            <p class="login_error">
                <%--                存在登录错误信息则输出--%>
                <c:if test="${error_info != null}">
                    <c:out value="${error_info}"/>
                </c:if>
            </p>
            <input class="login-btn" type="button" value="登录">
        </div>
    </div>
</div>
</body>
</html>

