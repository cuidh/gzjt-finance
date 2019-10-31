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
    <title>合同管理</title>
    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="../js/jquery-1.11.0.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/initial_default_props.css">
    <%--    js cs文件链接添加版本参数，防止内容修改之后浏览器仍然使用缓存--%>
    <link rel="stylesheet" href="../css/main_contract.css?version=0.9.2">
    <link rel="stylesheet" href="../css/table.css">
    <script src="../js/layer.js"></script>
    <script type="text/javascript" src="../js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../js/main_contract.js?version=0.9.15" charset="UTF-8"></script>
    <script type="text/javascript" src="../js/contract_layer.js?version=0.9.15" charset="UTF-8"></script>
    <script type="text/javascript" src="../js/payment_layer.js?version=0.9.15" charset="UTF-8"></script>
    <script type="text/javascript">
        var $ctx = '<%=request.getContextPath()%>';
    </script>
</head>
<body>

<%--主页插桩--%>
<%@ include file="./stub.jsp" %>
<%--主页差异化内容--%>
<div class="content_stub_box">
    <h2>合同管理</h2>
    <hr/>
    <div class="table_top_func">
        <form class="form-inline" method="get"
              href="<%= request.getServletContext().getContextPath() + "/main/listContractServlet.do" %>">
            <div class="form-group">
                <c:if test="${not empty key}">
                    <input type="text" class="form-control" id="key" name="key" placeholder="编号|项目"
                           value="${key}">
                </c:if>
                <c:if test="${empty key}">
                    <input type="text" class="form-control" id="key" name="key" placeholder="编号|项目">
                </c:if>
            </div>
            <button type="submit" class="btn btn-default">查询</button>
            <a id="create_project" href="javascript:void(0)" class="btn btn-primary">新建</a>
        </form>
    </div>
    <p class="table_name">合同信息列表</p>
    <table class="table table-bordered">
        <tr class="table_head_row">
            <td>编号</td>
            <td>类型</td>
            <td>施工方/项目</td>
            <td>中标</td>
            <td>结算(抽审)</td>
            <td>已审批</td>
            <td>已付款</td>
            <td>发票金额</td>
            <td>签署日期</td>
            <td>审批款</td>
            <td>付款</td>
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
            <td>${p.type}</td>
            <td style="font-size: 13px;text-align: center">
                    ${p.partyB.name}<br>
                <a href="${pageContext.request.contextPath}/main/listSubProjectServlet.do?subKey=${p.project.number}">
                        ${p.project.name}
                </a>
            </td>
            <td>${p.price}</td>
            <td STYLE="font-size: 13px">
                <c:if test="${empty p.auditPrice || 0 == p.auditPrice}">---</c:if>
                <c:if test="${not empty p.auditPrice && 0 != p.auditPrice}">${p.auditPrice}</c:if>
                <c:if test="${not empty p.finalAuditPrice && 0 != p.finalAuditPrice}">(${p.finalAuditPrice})</c:if>
            </td>
            <td>
                <c:if test="${empty p.approvalSum || 0 == p.approvalSum}">---</c:if>
                <c:if test="${not empty p.approvalSum && 0 != p.approvalSum}">${p.approvalSum}</c:if>
            </td>
            <td>
                <c:if test="${empty p.paySum || 0 == p.paySum}">---</c:if>
                <c:if test="${not empty p.paySum && 0 != p.paySum}">${p.paySum}</c:if>
            </td>
            <td>
                <c:if test="${empty p.billSum || 0 == p.billSum}">---</c:if>
                <c:if test="${not empty p.billSum && 0 != p.billSum}">${p.billSum}</c:if>
            </td>
            <td>${p.signDateStr}</td>
            <td>
                <p>
                    <button type="button" class="btn btn btn-info btn-sm" onclick="open_approval_list('${p.id}')">查看
                    </button>
                    <button type="button" class="btn btn btn-info btn-sm" onclick="openApprovalAddLayer('${p.number}')">
                        新增
                    </button>
                </p>
            </td>
            <td>
                <p>
                    <a href="${pageContext.request.contextPath}/main/listPaymentServlet.do?key=${p.number}"
                       class="btn btn btn-info btn-sm">查看</a>
                    <button type="button"
                            onclick="onCreatePaymentClicked('${p.number}', '${p.partyB.name}')"
                            class="btn btn btn-info btn-sm">新增
                    </button>
                    <c:if test="${not empty p.warning}">
                        <button onclick="onContractWarningClicked('${p.warning}')"
                                type="button" class="btn btn btn-danger btn-sm">风险
                        </button>
                    </c:if>
                </p>
            </td>
            <td>
                <p>
                        <%--                    <button type="button" class="btn btn btn-info btn-sm">详情</button>--%>
                    <button onclick="onContractModifyClicked('${p.number}','${p.type}','${p.state}',
                            '${p.project.number}','${p.partyA.name}','${p.partyB.name}',
                            '${p.partyB.creditCode}','${p.price}','${p.donePayRate}',
                            '${p.payRate}','${p.qualityDepositRate}','${p.qualityPeriod}',
                            '${p.signDateStr}','${p.completionDateStr}','${p.qualityFixPay}',
                            '${p.auditPrice}', '${p.note}')"
                            type="button" class="btn btn btn-danger btn-sm">修改
                    </button>
                    <button onclick="onContractDeleteClicked('${p.number}')"
                            type="button" class="btn btn btn-danger btn-sm">删除
                    </button>
                </p>
            </td>
            </tr>
        </c:forEach>
    </table>
    <div class="nav_box">
        <%@ include file="main_paging_nav.jsp" %>
    </div>
</div>

<%--创建合同对话框，不直接显示，需通过layer.open展示--%>
<%@include file="contract_layer.jsp" %>

<%--创建财政审批记录--%>
<div id="create_approval_stub" style="display:none;">
    <form id="create_approval_form" class="create_form">
        <div class="form_group">
            <label for="approval_contract"><span style="color: red">*&nbsp</span>合同：</label>
            <input class="form_input" type="text" id="approval_contract" name="approval_contract" maxlength="10"
                   placeholder="合同" readonly>
        </div>
        <div class="form_group">
            <label for="approval_number"><span style="color: red">*&nbsp</span>编号：</label>
            <input class="form_input" type="text" id="approval_number" name="approval_number" maxlength="10"
                   placeholder="编号">
            <span class="input_error_tips" id="approval_number_error"></span>
        </div>
        <div class="form_group">
            <label for="approval_amount"><span style="color: red">*&nbsp</span>金额：</label>
            <input class="form_input" type="number" maxlength="9" id="approval_amount" placeholder="金额"
                   name="approval_amount">
            <span class="input_error_tips" id="approval_amount_error"></span>
        </div>
        <div class="form_group" style="overflow: hidden;">
            <label for="approval_date" class="date_picker_label control-label"><span
                    style="color: red;">*&nbsp</span>日期：</label>
            <div class="date_picker_div input-group date form_date" data-date="" data-date-format="dd MM yyyy"
                 data-link-field="sign_date" data-link-format="yyyy-mm-dd">
                <input class="form-control" size="14" type="text" id="approval_date" value="" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
            <span class="input_error_tips date_picker_error_tip" id="approval_date_error"></span>
            <input type="hidden" value="" name="approval_date"/><br/>
        </div>
        <div class="form_group">
            <label for="approval_note">备注：</label>
            <input class="form_input" type="text" id="approval_note" maxlength="30" name="approval_note">
        </div>
    </form>
    <div style="overflow: hidden;padding-bottom: 30px;">
        <span id="create_approval_tips" style="color:red;font-size: 12px;margin-left: 50px"></span>
        <button id="create_approval_cancel" type="button" class="create_layer_cancel btn btn-primary btn-sm">取消</button>
        <button id="create_approval_confirm" type="button" class="create_layer_confirm btn btn-primary btn-sm">确定
        </button>
    </div>
</div>

<%--创建支付记录对话框，不直接显示，需通过layer.open展示--%>
<%@include file="payment_layer.jsp" %>

</body>
</html>