<%--
  Created by IntelliJ IDEA.
  User: jianwei.zhou
  Date: 2019/10/10
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>付款管理</title>
    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="../js/jquery-1.11.0.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/layer.js"></script>
    <script type="text/javascript" src="../js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../js/payment_layer.js" charset="UTF-8"></script>
    <link rel="stylesheet" href="../css/initial_default_props.css">
    <link rel="stylesheet" href="../css/table.css">

    <style type="text/css">
        .content_stub_box {
            position: absolute;
            top: 50px;
            left: 200px;
            right: 0px;
            bottom: 0px;
            background-color: #fff;
            padding-left: 30px;
            padding-top: 30px;
            overflow: auto;
        }

        .table_top_func {
            border: #dddddd 1px solid;
            padding: 10px;
        }

        .table_name {
            font-size: 12px;
            height: 35px;
            line-height: 35px;
            padding-left: 30px;
            background-color: #eeeeee;
            border: #dddddd 1px solid;
            margin-top: 30px;
        }

        /*内容区底部分页栏目*/
        .nav_box {
            text-align: center;
            margin-top: 30px;
        }

        .content_stub_box nav {
            margin: 0 auto;
        }

        /*内容区标题*/
        .content_title_box h2 {
            float: left;
        }

        .content_title_box ul {
            float: left;
            margin-left: 50px;
        }

        /*创建合同dialog*/
        #create_projct_stub {
            width: 650px;
            height: 600px;
        }

        .create_form {
            margin-left: 30px;
            margin-top: 30px;
            margin-bottom: 30px;
        }

        #create_projct_cancel {
            float: right;
            margin-right: 30px;
        }

        #create_projct_confirm {
            float: right;
            margin-right: 20px;
        }

        .form_group {
            margin-top: 10px;
        }

        .form_input {
            width: 300px;
            height: 34px;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form_group > label {
            display: inline-block;
            width: 120px;
            text-align: right;
        }

        /*时间选择控件*/
        .date_picker_label {
            float: left;
            display: inline-block;
            height: 34px;
            line-height: 34px;
            margin-left: 0px;
        }

        .date_picker_div {
            float: left;
            width: 300px;
            margin-left: 5px;
        }

        .date_picker_error_tip {
            display: inline-block;
            height: 34px;
            line-height: 34px;
            margin-left: 18px !important;
        }

        .input_error_tips {
            color: red;
            margin-left: 15px;
            font-size: 12px
        }

        .create_layer_cancel {
            float: right;
            margin-right: 30px;
        }

        .create_layer_confirm {
            float: right;
            margin-right: 20px;
        }
    </style>

    <script type="text/javascript">

        // 获取项目路径，需放置在页面才有效
        var $ctx = '<%=request.getContextPath()%>';

        $(function () {
            //左边菜单选中
            $("#menu_payment").toggleClass("active");
            // 创建项目按钮的点击事件
            $("#create_project").click(function () {
                onCreatePaymentClicked();
            });
        });

        function onPaymentDeleteClicked(paymentId) {
            layer.confirm('真的要删除吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                var parameters = {
                    "op_type": "3",
                    "id": paymentId
                };
                $.post($ctx + "/main/updatePaymentServlet.do", parameters, function (response, status) {
                    //如果服务器指定响应格式是json，实际数据格式错误，则内部转换错误，结果回调不会执行
                    if (status != "success" || response == undefined) {
                        layer.msg('删除失败！ 请检查网络');
                    } else if (200 == response.meta.code) {
                        window.location.reload();
                    } else {
                        layer.msg(response.meta.msg);
                    }
                });
            }, function (index) {
                layer.close(index);
            });
        }
    </script>
</head>
<body>

<%--主页插桩--%>
<%@ include file="./stub.jsp" %>
<%--主页差异化内容--%>
<div class="content_stub_box">
    <h2>付款管理</h2>
    <hr/>
    <div class="table_top_func">
        <form class="form-inline" method="get"
              href="<%= request.getServletContext().getContextPath() + "/main/listPaymentServlet.do" %>">
            <div class="form-group">
                <c:if test="${not empty key}">
                    <input type="text" class="form-control" id="key" name="key" placeholder="编号|合同|项目" value="${key}">
                </c:if>
                <c:if test="${empty key}">
                    <input type="text" class="form-control" id="key" name="key" placeholder="编号|合同|项目">
                </c:if>
            </div>
            <button type="submit" class="btn btn-default">查询</button>
            <a id="create_project" href="javascript:void(0)" class="btn btn-primary">新建</a>
        </form>
    </div>
    <p class="table_name">付款信息列表</p>
    <table class="table table-bordered">
        <tr class="table_head_row">
            <td>凭证号</td>
            <td>实付(扣款)</td>
            <td>发票</td>
            <td>付款方</td>
            <td>收款方</td>
            <td>合同/项目</td>
            <td>转账时间</td>
            <td>关联</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${pageBean.list}" var="p" varStatus="s">
            <c:if test="${s.count % 2 == 0}">
                <tr bgcolor="white" class="table_content_row">
            </c:if>
            <c:if test="${s.count % 2 != 0}">
                <tr bgcolor="#f5f5f5" class="table_content_row">
            </c:if>

            <td>${p.number}</td>
            <td>
                    ${p.amount}
                <c:if test="${not empty p.cutAmount && 0 != p.cutAmount}">(-${p.cutAmount})</c:if>
            </td>
            <td>
                <c:if test="${empty p.billAmount || 0 == p.billAmount}">---</c:if>
                <c:if test="${not empty p.billAmount && 0 != p.billAmount}">${p.billAmount}</c:if>
            </td>
            <td>
                    ${p.from.name}
            </td>
            <td>
                    ${p.to.name}
            </td>
            <td>
                <p>
                    <a href="${pageContext.request.contextPath}/main/listContractServlet.do?key=${p.contract.number}">
                            ${p.contract.number}
                    </a>
                </p>
                <p>
                    <a href="${pageContext.request.contextPath}/main/listSubProjectServlet.do?subKey=${p.contract.project.number}">
                            ${p.contract.project.name}
                    </a>
                </p>
            </td>
            <td>${p.tradeTimeStr}</td>
            <td>
                <a href="${pageContext.request.contextPath}/main/listContractServlet.do?key=${p.contract.number}"
                   type="button" class="btn btn btn-info btn-sm">合同</a>
                <a href="${pageContext.request.contextPath}/main/listSubProjectServlet.do?subKey=${p.contract.project.number}"
                   type="button" class="btn btn btn-info btn-sm">项目</a>
            </td>
            <td>
                <button onclick="onPaymentDeleteClicked(${p.id})"
                        type="button" class="btn btn btn-danger btn-sm">删除
                </button>
            </td>
            </tr>
        </c:forEach>
    </table>
    <div class="nav_box">
        <%@ include file="main_paging_nav.jsp" %>
    </div>
</div>

<%--创建支付记录对话框，不直接显示，需通过layer.open展示--%>
<%@include file="payment_layer.jsp" %>
</body>
</html>
