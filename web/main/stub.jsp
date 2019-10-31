<%--
  Created by IntelliJ IDEA.
  User: jianwei.zhou
  Date: 2019/10/10
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>stub</title>
    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="../js/jquery-1.11.0.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="../js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="../css/initial_default_props.css">
    <link rel="stylesheet" href="../css/main_stub.css">
</head>
<body>

<!--顶部栏-->
<div class="header">
    <p>财务数据平台</p>
    <div>
        欢迎，<span>${usr.name}</span> ！
    </div>
</div>

<div class="menu_box">
    <div class="list-group">
        <a id="menu_project" href="<%= request.getServletContext().getContextPath() + "/main/listProjectServlet.do" %>"
           class="list-group-item">项目</a>
        <a id="menu_contract"
           href="<%= request.getServletContext().getContextPath() + "/main/listContractServlet.do" %>"
           class="list-group-item">合同</a>
        <a id="menu_payment"
           href="<%= request.getServletContext().getContextPath() + "/main/listPaymentServlet.do" %>"
           class="list-group-item">付款</a>
        <a id="menu_user"
           href="<%= request.getServletContext().getContextPath() + "/main/listUserServlet.do" %>"
           class="list-group-item">用户</a>
    </div>
    <a href="<%= request.getServletContext().getContextPath() + "/logoutServlet.do" %>" class="list-group-item">退出系统</a>
</div>
</body>
</html>
