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
    <title>用户管理</title>
    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/initial_default_props.css">
    <link rel="stylesheet" href="../css/table.css">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="../js/jquery-1.11.0.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/layer.js"></script>

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
            width: 610px;
            height: 380px;
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

        .form_select {
            display: inline-block !important;
            width: 300px !important;
            height: 34px !important;
        }

        .form_group > label {
            display: inline-block;
            width: 120px;
            text-align: right;
        }

        .input_error_tips {
            color: red;
            margin-left: 15px;
            font-size: 12px
        }

    </style>
    <script type="text/javascript">
        // 弹窗的html内容
        var layerHtml = "";
        // 创建主项目弹窗层级
        var createlayerIndex = -1;
        // 获取项目路径
        var $ctx = '<%=request.getContextPath()%>';
        // 弹出层DOM上下文
        var createProjectLayer = undefined;

        $(function () {
            //左边菜单选中
            $("#menu_user").toggleClass("active");
            // 创建项目按钮的点击事件
            $("#create_project").click(function () {
                openCreateLayer();
            });
        });

        function getLayerHtml() {
            if (layerHtml == "") {
                var layerDiv = $("#create_projct_stub");
                layerDiv.css("display", "block");
                layerHtml = layerDiv.html();
                layerDiv.css("display", "none");
            }
            return layerHtml;
        }

        function openCreateLayer() {
            closeCreateLayer();
            var dialogConf = {
                type: 1,
                title: '创建用户',
                skin: 'layui-layer-rim', //加上边框
                area: ['610px', '380px'], //宽高
                content: getLayerHtml(),
                success: function onCreateProjectLayerShowed(layer, index) {
                    createProjectLayer = layer;
                    createProjectLayer.find("#create_projct_confirm").click(onCreateProjectConfirm);
                    createProjectLayer.find("#create_projct_cancel").click(closeCreateLayer);
                    createProjectLayer.find("#account").blur(check_account);
                    createProjectLayer.find("#password").blur(check_password);
                }
            }
            createlayerIndex = layer.open(dialogConf);
        }

        function closeCreateLayer() {
            if (createlayerIndex > 0) {
                layer.close(createlayerIndex);
                createlayerIndex = -1;
            }
        }

        function onCreateProjectConfirm() {
            var parameters = {
                "op_type": 1,
                "account": check_account(),
                "password": check_password(),
                "realName": check_realName(),
                "dept": check_dept()
            };
            for (var key in parameters) {
                var value = parameters[key];
                if (value == undefined) {
                    return;
                }
            }
            $.post($ctx + "/main/updateUserServlet.do", parameters, function (response, status) {
                //如果服务器指定响应格式是json，实际数据格式错误，则内部转换错误，结果回调不会执行
                if (status != "success" || response == undefined) {
                    layer.msg('创建失败！ 请检查网络');
                } else if (200 == response.meta.code) {
                    window.location.reload();
                } else {
                    layer.msg(response.meta.msg);
                    createProjectLayer.find("#create_tips").html(response.meta.msg);
                }
            });
        }

        function check_account() {
            var value = createProjectLayer.find("#account").val();
            var widget = createProjectLayer.find("#account_error");
            if (!/^[a-zA-Z0-9.]{3,20}$/.test(value)) {
                widget.html("3-20位字母或者数字!");
                value = undefined;
            } else {
                widget.html("");
            }
            return value;
        }

        function check_password() {
            var value = createProjectLayer.find("#password").val();
            var widget = createProjectLayer.find("#password_error");
            if (!/^[a-zA-Z0-9.]{8}$/.test(value)) {
                widget.html("8位字母或者数字!");
                value = undefined;
            } else {
                widget.html("");
            }
            return value;
        }

        function check_realName() {
            return createProjectLayer.find("#realName").val();
        }

        function check_dept() {
            return createProjectLayer.find("#dept").val();
            // var value = createProjectLayer.find("#dept").val();
            // var widget = createProjectLayer.find("#dept_error");
            // if (value == "null") {
            //     widget.html("请选择部门!");
            //     value = undefined;
            // } else {
            //     widget.html("");
            // }
            // return value;
        }

        function onModifyClicked(usrId) {
            layer.prompt({title: '请输入新密码', formType: 1}, function (pass, index) {
                if (!/^[a-zA-Z0-9.]{8}$/.test(pass)) {
                    layer.msg('字母或者数字长度为8!');
                    return
                }
                var parameters = {
                    "op_type": 2,
                    "id": usrId,
                    "password": pass
                };
                $.post($ctx + "/main/updateUserServlet.do", parameters, function (response, status) {
                    //如果服务器指定响应格式是json，实际数据格式错误，则内部转换错误，结果回调不会执行
                    if (status != "success" || response == undefined) {
                        layer.msg('操作失败！ 请检查网络');
                    } else if (200 == response.meta.code) {
                        layer.msg('修改成功！');
                        layer.close(index);
                    } else {
                        layer.msg(response.meta.msg);
                    }
                });
            });
        }

        function onDeleteClicked(usrId) {
            layer.confirm('真的要删除吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                var parameters = {
                    "op_type": 3,
                    "id": usrId
                };
                $.post($ctx + "/main/updateUserServlet.do", parameters, function (response, status) {
                    //如果服务器指定响应格式是json，实际数据格式错误，则内部转换错误，结果回调不会执行
                    if (status != "success" || response == undefined) {
                        layer.msg('操作失败！ 请检查网络');
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
    <h2>用户管理</h2>
    <hr/>
    <div class="table_top_func">
        <form class="form-inline" method="get"
              href="<%= request.getServletContext().getContextPath() + "/main/listUserServlet.do" %>">
            <div class="form-group">
                <c:if test="${not empty key}">
                    <input type="text" class="form-control" id="key" name="key" placeholder="账号" value="${key}">
                </c:if>
                <c:if test="${empty key}">
                    <input type="text" class="form-control" id="key" name="key" placeholder="账号">
                </c:if>
            </div>
            <button type="submit" class="btn btn-default">查询</button>
            <a id="create_project" href="javascript:void(0)" class="btn btn-primary">新建</a>
        </form>
    </div>
    <p class="table_name">用户信息列表</p>
    <table class="table table-bordered">
        <tr class="table_head_row">
            <td>账号</td>
            <td>姓名</td>
            <td>部门</td>
            <td>创建时间</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${pageBean.list}" var="p" varStatus="s">
            <c:if test="${s.count % 2 == 0}">
                <tr bgcolor="white" class="table_content_row">
            </c:if>
            <c:if test="${s.count % 2 != 0}">
                <tr bgcolor="#f5f5f5" class="table_content_row">
            </c:if>
            <td>${p.name}</td>
            <td>${p.realName}</td>
            <td>${p.department.name}</td>
            <td>${p.createTimeStr}</td>
            <td>
                <p>
                    <button type="button" class="btn btn btn-info btn-sm" onclick="onModifyClicked(${p.id})">修改密码
                    </button>
                    <button type="button" class="btn btn btn-danger btn-sm" onclick="onDeleteClicked(${p.id})">删除
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

<%--创建用户对话框，不直接显示，需通过layer.open展示--%>
<div id="create_projct_stub" style="display:none">
    <form id="create_projct_form" class="create_form">
        <div class="form_group">
            <label for="account"><span style="color: red">*&nbsp</span>账号：</label>
            <input class="form_input" type="text" id="account" maxlength="20" placeholder="账号">
            <span class="input_error_tips" id="account_error"></span>
        </div>
        <div class="form_group">
            <label for="password"><span style="color: red">*&nbsp</span>密码：</label>
            <input class="form_input" type="text" id="password" maxlength="8" placeholder="密码">
            <span class="input_error_tips" id="password_error"></span>
        </div>
        <div class="form_group">
            <label for="dept"><span style="color: red">*&nbsp</span>部门：</label>
            <select class="form-control form_select" id="dept" name="dept">
                <option value="计划统计部">计划统计部</option>
                <option value="财务部">财务部</option>
                <option value="其他">其他</option>
            </select>
            <span class="input_error_tips" id="dept_error"></span>
        </div>
        <div class="form_group">
            <label for="realName">姓名：</label>
            <input class="form_input" type="text" maxlength="20" id="realName" placeholder="姓名">
            <span class="input_error_tips" id="realName_error"></span>
        </div>
    </form>
    <span id="create_tips" style="color:red;font-size: 12px;margin-left: 50px"></span>
    <button id="create_projct_cancel" type="button" class="btn btn-primary btn-sm">取消</button>
    <button id="create_projct_confirm" type="button" class="btn btn-primary btn-sm">确定</button>
</div>

</body>
</html>
