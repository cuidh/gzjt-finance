$(function () {
    //左边菜单选中
    $("#menu_contract").toggleClass("active");
    // 创建项目按钮的点击事件
    $("#create_project").click(function () {
        onContractAddClicked();
    });
});

//创建合同
// 弹窗的html内容
// var createContractlayerHtml = "";
// // 弹出层DOM上下文
// var createContractLayer = undefined;
//
// function getContractLayerHtml() {
//     if (createContractlayerHtml == "") {
//         var layerDiv = $("#create_contract_stub");
//         layerDiv.css("display", "block");
//         createContractlayerHtml = layerDiv.html();
//         layerDiv.css("display", "none");
//     }
//     return createContractlayerHtml;
// }
//
// function onContractAddClicked(subprojectNumber) {
//     var dialogConf = {
//         type: 1,
//         title: '创建合同',
//         skin: 'layui-layer-rim', //加上边框
//         area: ['650px', '600px'], //宽高
//         content: getContractLayerHtml(),
//         success: function (layerObject, index) {
//             createContractLayer = layerObject;
//             createContractLayer.find("#contract_project_key").val(subprojectNumber);
//             createContractLayer.find("#create_contract_confirm").click(onCreateContractConfirm);
//             createContractLayer.find("#create_contract_cancel").click(function () {
//                 layer.close(index);
//             });
//             // createContractLayer.find("#contract_is_completed").change(function () {
//             //     if (this.value == 0) {
//             //         createContractLayer.find(".complete_group").css("display", "none");
//             //     } else {
//             //         createContractLayer.find(".complete_group").css("display", "block");
//             //     }
//             // });
//             createContractLayer.find("#contract_type").change(function () {
//                 if (this.value != '施工') {
//                     createContractLayer.find("#construction_inputs").css("display", "none");
//                 } else {
//                     createContractLayer.find("#construction_inputs").css("display", "block");
//                 }
//             });
//             createContractLayer.find('.form_date').datetimepicker({
//                 language: 'zh-CN',
//                 weekStart: 1,
//                 todayBtn: 1,
//                 autoclose: 1,
//                 todayHighlight: 1,
//                 startView: 2,
//                 minView: 2,
//                 forceParse: 0,
//                 format: "yyyy-mm-dd"
//             });
//             createContractLayer.find("#contract_number").blur(check_contract_number);
//             createContractLayer.find("#contract_type").blur(check_contract_type);
//             createContractLayer.find("#contract_project_key").blur(check_contract_project);
//             createContractLayer.find("#contract_party_a").blur(check_contract_party_a);
//             createContractLayer.find("#contract_party_b").blur(check_contract_party_b);
//             createContractLayer.find("#contract_party_b_code").blur(check_contract_party_b_code);
//             createContractLayer.find("#contract_price").blur(check_contract_price);
//             createContractLayer.find("#contract_pay_rate").blur(check_contract_pay_rate);
//             createContractLayer.find("#contract_quality_deposit_rate").blur(check_contract_quality_deposit_rate);
//             createContractLayer.find("#contract_quality_period").blur(check_contract_quality_period);
//             createContractLayer.find("#contract_sign_date").blur(check_contract_sign_date);
//             // createContractLayer.find("#contract_is_completed").blur(check_contract_is_completed);
//             createContractLayer.find("#contract_completion_date").blur(check_contract_completion_date);
//             createContractLayer.find("#contract_audit_price").blur(check_contract_audit_price);
//             createContractLayer.find("#contract_quality_fix_pay").blur(check_contract_quality_fix_pay());
//         }
//     }
//     layer.open(dialogConf);
// }
//
// function onCreateContractConfirm() {
//     //总是提交所有字段
//     var parameters = {
//         "op_type": 1,
//         "number": check_contract_number(),
//         "type": check_contract_type(),
//         "project_key": check_contract_project(),
//         "party_a": check_contract_party_a(),
//         "party_b": check_contract_party_b(),
//         "party_b_code": check_contract_party_b_code(),
//         "price": check_contract_price(),
//         "pay_rate": check_contract_pay_rate(),
//         "quality_deposit_rate": check_contract_quality_deposit_rate(),
//         "quality_period": check_contract_quality_period(),
//         "sign_date": check_contract_sign_date(),
//         // "is_completed": check_contract_is_completed(),
//         "completion_date": check_contract_completion_date(),
//         "quality_fix_pay": check_contract_quality_fix_pay(),
//         "audit_price": check_contract_audit_price()
//     };
//     for (var key in parameters) {
//         var value = parameters[key];
//         if (value == undefined) {
//             return;
//         }
//     }
//     $.post($ctx + "/main/updateContractServlet.do", parameters, function (response, status) {
//         //如果服务器指定响应格式是json，实际数据格式错误，则内部转换错误，结果回调不会执行
//         if (status != "success" || response == undefined) {
//             layer.msg('创建失败！ 请检查网络');
//         } else if (200 == response.meta.code) {
//             // window.location.reload();
//             layer.msg('创建成功');
//             layer.close(index);
//         } else {
//             layer.msg(response.meta.msg);
//             createContractLayer.find("#create_payment_tips").html(response.meta.msg);
//         }
//     });
// }
//
// function check_contract_number() {
//     var number = createContractLayer.find("#contract_number").val();
//     var errorWidget = createContractLayer.find("#contract_number_error");
//     if (!(/^[\w]{6,10}$/.test(number))) {
//         errorWidget.html("字母数字且长度为6-10!");
//         return undefined;
//     } else {
//         errorWidget.html("");
//         return number;
//     }
// }
//
// function check_contract_type() {
//     var type = createContractLayer.find("#contract_type").val();
//     var errorWidget = createContractLayer.find("#contract_type_error");
//     if (type == "null") {
//         errorWidget.html("请选择合同类型！");
//         return undefined;
//     } else {
//         errorWidget.html("");
//         return type;
//     }
// }
//
// function check_contract_project() {
//     var project = createContractLayer.find("#contract_project_key").val();
//     var errorWidget = createContractLayer.find("#contract_project_key_error");
//     if (project.length == 0) {
//         errorWidget.html("所属项目不能为空！！");
//         return undefined;
//     } else {
//         errorWidget.html("");
//         return project;
//     }
// }
//
// function check_contract_party_a() {
//     var value = createContractLayer.find("#contract_party_a").val();
//     var errorWidget = createContractLayer.find("#contract_party_a_error");
//     if (value.length == 0) {
//         errorWidget.html("甲方不能为空！！");
//         return undefined;
//     } else {
//         errorWidget.html("");
//         return value;
//     }
// }
//
// function check_contract_party_b() {
//     var value = createContractLayer.find("#contract_party_b").val();
//     var errorWidget = createContractLayer.find("#contract_party_b_error");
//     if (value.length == 0) {
//         errorWidget.html("乙方不能为空！！");
//         return undefined;
//     } else {
//         errorWidget.html("");
//         return value;
//     }
// }
//
// function check_contract_party_b_code() {
//     var value = createContractLayer.find("#contract_party_b_code").val();
//     var errorWidget = createContractLayer.find("#contract_party_b_code_error");
//     if (value.length != 0 && !(/^[0-9A-Z]{18}$/.test(value))) {
//         errorWidget.html("机构信用代码有误！");
//         value = undefined;
//     } else {
//         errorWidget.html("");
//     }
//     return value;
// }
//
// function check_contract_price() {
//     var value = createContractLayer.find("#contract_price").val();
//     var errorWidget = createContractLayer.find("#contract_price_error");
//     if (value.length == 0) {
//         errorWidget.html("中标价不能为空！");
//         value = undefined;
//     } else if ((value < 0 || value > 100000000)) {
//         errorWidget.html("请输入有效金额0-1亿！");
//         value = undefined;
//     } else {
//         errorWidget.html("");
//     }
//     return value;
// }
//
// function check_contract_pay_rate() {
//     var value = createContractLayer.find("#contract_pay_rate").val();
//     var errorWidget = createContractLayer.find("#contract_pay_rate_error");
//     if (value.length == 0) {
//         errorWidget.html("验收付款比不能为空！");
//         value = undefined;
//     } else if ((value < 0 || value > 100)) {
//         errorWidget.html("请输入有效比例0-100！");
//         value = undefined;
//     } else {
//         errorWidget.html("");
//     }
//     return value;
// }
//
// function check_contract_quality_deposit_rate() {
//     var value = createContractLayer.find("#contract_quality_deposit_rate").val();
//     var errorWidget = createContractLayer.find("#contract_quality_deposit_rate_error");
//     if (value.length == 0) {
//         errorWidget.html("质保金比例不能为空！");
//         value = undefined;
//     } else if ((value < 0 || value > 100)) {
//         errorWidget.html("请输入有效比例0-100！");
//         value = undefined;
//     } else {
//         errorWidget.html("");
//     }
//     return value;
// }
//
// function check_contract_quality_period() {
//     var value = createContractLayer.find("#contract_quality_period").val();
//     var errorWidget = createContractLayer.find("#contract_quality_period_error");
//     if (value.length == 0) {
//         errorWidget.html("质保期不能为空！");
//         value = undefined;
//     } else if ((value < 0 || value > 120)) {
//         errorWidget.html("请输入有效值0-120月！");
//         value = undefined;
//     } else {
//         errorWidget.html("");
//     }
//     return value;
// }
//
// function check_contract_sign_date() {
//     var value = createContractLayer.find("#contract_sign_date").val();
//     var errorWidget = createContractLayer.find("#contract_sign_date_error");
//     if (value.length == 0) {
//         errorWidget.html("请选择签署日期！");
//         value = undefined;
//     } else {
//         errorWidget.html("");
//     }
//     return value;
// }
//
// // function is_completed() {
// //     return createContractLayer.find("#contract_is_completed").val() == 1;
// // }
//
// // function check_contract_is_completed() {
// //     return createContractLayer.find("#contract_is_completed").val();
// // }
//
// function check_contract_completion_date() {
//     var value = createContractLayer.find("#contract_completion_date").val();
//     // var errorWidget = createContractLayer.find("#contract_completion_date_error");
//     //     if (value.length == 0) {
//     //         errorWidget.html("请选择竣工日期！");
//     //         return undefined;
//     //     } else {
//     //         errorWidget.html("");
//     //         return value;
//     //     }
//     return value;
// }
//
// function check_contract_quality_fix_pay() {
//     var value = createContractLayer.find("#contract_quality_fix_pay").val();
//     var errorWidget = createContractLayer.find("#contract_quality_fix_pay_error");
//     if (value.length != 0 && (value < 0 || value > 100000000)) {
//         errorWidget.html("请输入有效金额0-1亿！");
//         return undefined;
//     } else {
//         errorWidget.html("");
//         return value;
//     }
// }
//
// function check_contract_audit_price() {
//     var value = createContractLayer.find("#contract_audit_price").val();
//     var errorWidget = createContractLayer.find("#contract_audit_price_error");
//     if (value.length != 0 && (value < 0 || value > 100000000)) {
//         errorWidget.html("请输入有效金额0-1亿！");
//         return undefined;
//     } else {
//         errorWidget.html("");
//         return value;
//     }
// }

// 创建审批记录

// 添加审批弹窗的html内容
var approvallayerHtml = "";
var approvallayer;

function getApprovalLayerHtml() {
    if (approvallayerHtml == "") {
        var layerDiv = $("#create_approval_stub");
        layerDiv.css("display", "block");
        approvallayerHtml = layerDiv.html();
        layerDiv.css("display", "none");
    }
    return approvallayerHtml;
}

function openApprovalAddLayer(contractNumber) {
    var dialogConf = {
        type: 1,
        title: '创建审批',
        skin: 'layui-layer-rim', //加上边框
        area: ['620px', '385px'], //宽高
        content: getApprovalLayerHtml(),
        success: function (layerObject, index) {
            approvallayer = layerObject;
            layerObject.find("#approval_contract").val(contractNumber);
            layerObject.find('.form_date').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: "yyyy-mm-dd"
            });
            layerObject.find("#create_approval_cancel").click(function () {
                layer.close(index);
            });
            layerObject.find("#create_approval_confirm").click(function () {
                var parameters = {
                    "op_type": 1,
                    "contract": check_approval_contract(),
                    "number": check_approval_number(),
                    "amount": check_approval_amount(),
                    "date": check_approval_date(),
                    "note": check_approval_note()
                };
                for (var key in parameters) {
                    var value = parameters[key];
                    if (value == undefined) {
                        return;
                    }
                }
                $.post($ctx + "/main/updateApprovalServlet.do", parameters, function (response, status) {
                    //如果服务器指定响应格式是json，实际数据格式错误，则内部转换错误，结果回调不会执行
                    if (status != "success" || response == undefined) {
                        layer.msg('创建失败！ 请检查网络');
                    } else if (200 == response.meta.code) {
                        window.location.reload();
                        // layer.msg('创建成功');
                        // layer.close(index);
                    } else {
                        layer.msg(response.meta.msg);
                        approvallayer.find("#create_approval_tips").html(response.meta.msg);
                    }
                });
            });
            layerObject.find("#approval_number").blur(check_approval_number);
            layerObject.find("#approval_amount").blur(check_approval_amount);
            layerObject.find("#approval_date").blur(check_approval_date);
        }
    }
    layer.open(dialogConf);
}

function check_approval_contract() {
    return approvallayer.find("#approval_contract").val();
}

function check_approval_note() {
    return approvallayer.find("#approval_note").val();
}

function check_approval_number() {
    var value = approvallayer.find("#approval_number").val();
    var errorTipWidget = approvallayer.find("#approval_number_error");
    if (value.length != 0 && !(/^[\w]{6,10}$/.test(value))) {
        errorTipWidget.html("6-10位数字字母！");
        value = undefined;
    } else {
        errorTipWidget.html("");
    }
    return value;
}

function check_approval_amount() {
    var value = approvallayer.find("#approval_amount").val();
    var errorWidget = approvallayer.find("#approval_amount_error");
    if (value.length == 0) {
        errorWidget.html("金额不能为空！");
        value = undefined;
    } else if ((value < 0 || value > 100000000)) {
        errorWidget.html("请输入有效金额0-1亿！");
        value = undefined;
    } else {
        errorWidget.html("");
    }
    return value;
}

function check_approval_date() {
    var value = approvallayer.find("#approval_date").val();
    var errorWidget = approvallayer.find("#approval_date_error");
    if (value.length == 0) {
        errorWidget.html("请选择审批日期！");
        value = undefined;
    } else {
        errorWidget.html("");
    }
    return value;
}

//查看审批记录
function open_approval_list(contractId) {
    layer.open({
        type: 2,
        title: '财政审批列表',
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['560px', '390px'],
        content: $ctx + '/main/listApprovalServlet.do?contractId=' + contractId
    });
}

//删除合同
function onContractDeleteClicked(contractNumber) {
    layer.confirm('真的要删除吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var parameters = {
            "op_type": "3",
            "number": contractNumber
        };
        $.post($ctx + "/main/updateContractServlet.do", parameters, function (response, status) {
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

//查看风险信息
function onContractWarningClicked(warnings) {
    warnings = warnings.replace(/;/g, '<br>');
    layer.alert(warnings);
}





