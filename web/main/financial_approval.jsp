<%--
  Created by IntelliJ IDEA.
  User: jianwei.zhou
  Date: 2019/10/21
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>财政审批</title>
    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="../js/jquery-1.11.0.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/layer.js"></script>
    <link rel="stylesheet" href="../css/initial_default_props.css">

    <script type="text/javascript">
        var $ctx = '<%=request.getContextPath()%>';

        function onDeleteClicked(id) {
            var iframeLocation = window.location;
            var parameters = {
                "op_type": 3,
                "id": id
            };
            $.post($ctx + "/main/updateApprovalServlet.do", parameters, function (response, status) {
                //如果服务器指定响应格式是json，实际数据格式错误，则内部转换错误，结果回调不会执行
                if (status != "success" || response == undefined) {
                    parent.layer.msg('操作失败！ 请检查网络');
                } else if (200 == response.meta.code) {
                    parent.layer.msg('删除成功');
                    iframeLocation.reload();
                } else {
                    parent.layer.msg(response.meta.msg);
                }
            });
        }
    </script>

</head>
<body>
<table class="table table-bordered" style="overflow: auto;margin:0 auto;">
    <tr>
        <td>编号</td>
        <td>金额</td>
        <td>日期</td>
        <td>备注</td>
        <td>操作</td>
    </tr>
    <%--数据行--%>
    <c:forEach items="${pageBean.list}" var="p" varStatus="s">
        <c:if test="${s.count % 2 == 0}">
            <tr bgcolor="white">
        </c:if>
        <c:if test="${s.count % 2 != 0}">
            <tr bgcolor="#f5f5f5">
        </c:if>
        <td>${p.number}</td>
        <td>${p.amount}</td>
        <td>${p.dateStr}</td>
        <td>${p.note}</td>
        <td>
            <button type="button" class="btn btn btn-danger btn-sm" onclick="onDeleteClicked(${p.id})">删除</button>
        </td>
        </tr>
    </c:forEach>
</table>
<div class="nav_box" style="text-align: center;margin-top: 30px;">
    <c:if test="${empty pageBean.list}">
        <p>没有相关数据！</p>
    </c:if>
    <c:if test="${not empty pageBean.list}">
        <%@ include file="main_paging_nav.jsp" %>
    </c:if>
</div>
</body>
</html>
