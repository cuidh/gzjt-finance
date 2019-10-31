//创建合同
// 弹窗的html内容
var createContractlayerHtml = "";
// 弹出层DOM上下文
var createContractLayer = undefined;

var createContractLayerIndex = undefined;

function getContractLayerHtml() {
    if (createContractlayerHtml == "") {
        var layerDiv = $("#create_contract_stub");
        layerDiv.css("display", "block");
        createContractlayerHtml = layerDiv.html();
        layerDiv.css("display", "none");
    }
    return createContractlayerHtml;
}

/**
 *  "op_type": 1,
 "number": check_contract_number(),
 "type": check_contract_type(),
 "state": check_contract_state(),
 "project_key": check_contract_project(),
 "party_a": check_contract_party_a(),
 "party_b": check_contract_party_b(),
 "party_b_code": check_contract_party_b_code(),
 "price": check_contract_price(),
 // 完工验收比
 "done_pay_rate": check_contract_done_pay_rate(),
 // 竣工验收比例
 "check_pay_rate": check_contract_pay_rate(),
 "quality_deposit_rate": check_contract_quality_deposit_rate(),
 "quality_period": check_contract_quality_period(),
 "sign_date": check_contract_sign_date(),
 "completion_date": check_contract_completion_date(),
 "quality_fix_pay": check_contract_quality_fix_pay(),
 "audit_price": check_contract_audit_price()
 */
function onContractModifyClicked(number, type, state, project_key, party_a, party_b, party_b_code,
                                 price, done_pay_rate, check_pay_rate, quality_deposit_rate,
                                 quality_period, sign_date, completion_date, quality_fix_pay, audit_price, note) {
    var dialogConf = {
        type: 1,
        title: '修改合同',
        skin: 'layui-layer-rim', //加上边框
        area: ['650px', '600px'], //宽高
        content: getContractLayerHtml(),
        success: function (layerObject, index) {
            createContractLayer = layerObject;
            createContractLayerIndex = index;
            //合同类型监听
            createContractLayer.find("#contract_type").change(function () {
                //只有施工类型才添加默认值，其他合同按需填写
                if (this.value == '施工' || this.value == '配套') {
                    createContractLayer.find("#construction_inputs").css("display", "block");
                } else {
                    createContractLayer.find("#construction_inputs").css("display", "none");
                }
            });
            //绑定数据
            createContractLayer.find("#contract_number").val(number);
            createContractLayer.find("#contract_type").val(type);
            createContractLayer.find("#contract_state").val(state);
            createContractLayer.find("#contract_project_key").val(project_key);
            createContractLayer.find("#contract_party_a").val(party_a);
            createContractLayer.find("#contract_party_b").val(party_b);
            createContractLayer.find("#contract_party_b_code").val(party_b_code);
            createContractLayer.find("#contract_price").val(price);
            createContractLayer.find("#contract_done_pay_rate").val(done_pay_rate);
            createContractLayer.find("#contract_pay_rate").val(check_pay_rate);
            createContractLayer.find("#contract_quality_deposit_rate").val(quality_deposit_rate);
            createContractLayer.find("#contract_quality_period").val(quality_period);
            createContractLayer.find("#contract_sign_date").val(sign_date);
            createContractLayer.find("#contract_completion_date").val(completion_date);
            createContractLayer.find("#contract_audit_price").val(audit_price);
            createContractLayer.find("#contract_quality_fix_pay").val(quality_fix_pay);
            createContractLayer.find("#contract_note").val(note);

            //设置只读字段
            createContractLayer.find("#contract_number").attr("readonly", "readonly");
            createContractLayer.find("#contract_type").attr("readonly", "readonly");
            createContractLayer.find("#contract_project_key").attr("readonly", "readonly");

            createContractLayer.find("#create_contract_confirm").click(function () {
                onModifyContractConfirm(true);
            });
            createContractLayer.find("#create_contract_cancel").click(function () {
                layer.close(index);
            });

            createContractLayer.find('.form_date').datetimepicker({
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
            createContractLayer.find("#contract_number").blur(check_contract_number);
            createContractLayer.find("#contract_type").blur(check_contract_type);
            createContractLayer.find("#contract_project_key").blur(check_contract_project);
            createContractLayer.find("#contract_party_a").blur(check_contract_party_a);
            createContractLayer.find("#contract_party_b").blur(check_contract_party_b);
            createContractLayer.find("#contract_party_b_code").blur(check_contract_party_b_code);
            createContractLayer.find("#contract_price").blur(check_contract_price);
            createContractLayer.find("#contract_done_pay_rate").blur(check_contract_done_pay_rate);
            createContractLayer.find("#contract_pay_rate").blur(check_contract_pay_rate);
            createContractLayer.find("#contract_quality_deposit_rate").blur(check_contract_quality_deposit_rate);
            createContractLayer.find("#contract_quality_period").blur(check_contract_quality_period);
            // createContractLayer.find("#contract_sign_date").blur(check_contract_sign_date);
            // createContractLayer.find("#contract_completion_date").blur(check_contract_completion_date);
            createContractLayer.find("#contract_audit_price").blur(check_contract_audit_price);
            createContractLayer.find("#contract_quality_fix_pay").blur(check_contract_quality_fix_pay);
        }
    }
    layer.open(dialogConf);
}

function onContractAddClicked(subprojectNumber, mainOwner) {
    var dialogConf = {
        type: 1,
        title: '创建合同',
        skin: 'layui-layer-rim', //加上边框
        area: ['650px', '600px'], //宽高
        content: getContractLayerHtml(),
        success: function (layerObject, index) {
            createContractLayer = layerObject;
            createContractLayerIndex = index;
            createContractLayer.find("#contract_project_key").val(subprojectNumber);
            createContractLayer.find("#contract_party_a").val(mainOwner);
            createContractLayer.find("#create_contract_confirm").click(function () {
                onCreateContractConfirm(subprojectNumber == undefined);
            });
            createContractLayer.find("#create_contract_cancel").click(function () {
                layer.close(index);
            });
            createContractLayer.find("#contract_type").change(function () {
                //只有施工类型才添加默认值，其他合同按需填写
                if (this.value == '施工' || this.value == '配套') {
                    createContractLayer.find("#construction_inputs").css("display", "block");
                    createContractLayer.find("#contract_done_pay_rate").val(75);
                    createContractLayer.find("#contract_pay_rate").val(80);
                    createContractLayer.find("#contract_quality_deposit_rate").val(5);
                    createContractLayer.find("#contract_quality_period").val(12);
                } else {
                    createContractLayer.find("#construction_inputs").css("display", "none");
                    createContractLayer.find("#contract_done_pay_rate").val('');
                    createContractLayer.find("#contract_pay_rate").val('');
                    createContractLayer.find("#contract_quality_deposit_rate").val('');
                    createContractLayer.find("#contract_quality_period").val('');
                }
            });
            createContractLayer.find('.form_date').datetimepicker({
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
            createContractLayer.find("#contract_number").blur(check_contract_number);
            createContractLayer.find("#contract_type").blur(check_contract_type);
            createContractLayer.find("#contract_project_key").blur(check_contract_project);
            createContractLayer.find("#contract_party_a").blur(check_contract_party_a);
            createContractLayer.find("#contract_party_b").blur(check_contract_party_b);
            createContractLayer.find("#contract_party_b_code").blur(check_contract_party_b_code);
            createContractLayer.find("#contract_price").blur(check_contract_price);
            createContractLayer.find("#contract_done_pay_rate").blur(check_contract_done_pay_rate);
            createContractLayer.find("#contract_pay_rate").blur(check_contract_pay_rate);
            createContractLayer.find("#contract_quality_deposit_rate").blur(check_contract_quality_deposit_rate);
            createContractLayer.find("#contract_quality_period").blur(check_contract_quality_period);
            // createContractLayer.find("#contract_sign_date").blur(check_contract_sign_date);
            // createContractLayer.find("#contract_completion_date").blur(check_contract_completion_date);
            createContractLayer.find("#contract_audit_price").blur(check_contract_audit_price);
            createContractLayer.find("#contract_final_audit_price").blur(check_contract_final_audit_price());
            createContractLayer.find("#contract_quality_fix_pay").blur(check_contract_quality_fix_pay);
        }
    }
    layer.open(dialogConf);
}

function onCreateContractConfirm(isReload) {
    onCreateOrModifyContractConfirm(false, isReload);
}

function onModifyContractConfirm(isReload) {
    onCreateOrModifyContractConfirm(true, isReload);
}

function onCreateOrModifyContractConfirm(isModify, isReload) {
    //总是提交所有字段
    var parameters = {
        "op_type": isModify ? 2 : 1,
        "number": check_contract_number(),
        "type": check_contract_type(),
        "state": check_contract_state(),
        "project_key": check_contract_project(),
        "party_a": check_contract_party_a(),
        "party_b": check_contract_party_b(),
        "party_b_code": check_contract_party_b_code(),
        "price": check_contract_price(),
        // 完工验收比
        "done_pay_rate": check_contract_done_pay_rate(),
        // 竣工验收比例
        "check_pay_rate": check_contract_pay_rate(),
        "quality_deposit_rate": check_contract_quality_deposit_rate(),
        "quality_period": check_contract_quality_period(),
        "sign_date": check_contract_sign_date(),
        "completion_date": check_contract_completion_date(),
        "quality_fix_pay": check_contract_quality_fix_pay(),
        "note": check_contract_note(),
        "audit_price": check_contract_audit_price(),
        "final_audit_price": check_contract_final_audit_price()
    };
    for (var key in parameters) {
        var value = parameters[key];
        if (value == undefined) {
            return;
        }
    }
    $.post($ctx + "/main/updateContractServlet.do", parameters, function (response, status) {
        //如果服务器指定响应格式是json，实际数据格式错误，则内部转换错误，结果回调不会执行
        if (status != "success" || response == undefined) {
            layer.msg('创建失败！ 请检查网络');
        } else if (200 == response.meta.code) {
            if (isReload) {
                window.location.reload();
            } else {
                layer.msg(isModify ? '修改成功' : '创建成功');
                layer.close(createContractLayerIndex);
            }
        } else {
            layer.msg(response.meta.msg);
            createContractLayer.find("#create_payment_tips").html(response.meta.msg);
        }
    });
}

function check_contract_number() {
    var number = createContractLayer.find("#contract_number").val();
    var errorWidget = createContractLayer.find("#contract_number_error");
    if (!(/^[\w]{6,10}$/.test(number))) {
        errorWidget.html("字母数字且长度为6-10!");
        return undefined;
    } else {
        errorWidget.html("");
        return number;
    }
}

function check_contract_type() {
    var type = createContractLayer.find("#contract_type").val();
    var errorWidget = createContractLayer.find("#contract_type_error");
    if (type == "null") {
        errorWidget.html("请选择合同类型！");
        return undefined;
    } else {
        errorWidget.html("");
        return type;
    }
}

function check_contract_state() {
    return createContractLayer.find("#contract_state").val();
}

function check_contract_project() {
    var project = createContractLayer.find("#contract_project_key").val();
    var errorWidget = createContractLayer.find("#contract_project_key_error");
    if (project.length == 0) {
        errorWidget.html("所属项目不能为空！！");
        return undefined;
    } else {
        errorWidget.html("");
        return project;
    }
}

function check_contract_party_a() {
    var value = createContractLayer.find("#contract_party_a").val();
    var errorWidget = createContractLayer.find("#contract_party_a_error");
    if (value.length == 0) {
        errorWidget.html("甲方不能为空！！");
        return undefined;
    } else {
        errorWidget.html("");
        return value;
    }
}

function check_contract_party_b() {
    var value = createContractLayer.find("#contract_party_b").val();
    var errorWidget = createContractLayer.find("#contract_party_b_error");
    if (value.length == 0) {
        errorWidget.html("乙方不能为空！！");
        return undefined;
    } else {
        errorWidget.html("");
        return value;
    }
}

function check_contract_party_b_code() {
    var value = createContractLayer.find("#contract_party_b_code").val();
    var errorWidget = createContractLayer.find("#contract_party_b_code_error");
    if (value.length != 0 && !(/^[0-9A-Z]{18}$/.test(value))) {
        errorWidget.html("机构信用代码有误！");
        value = undefined;
    } else {
        errorWidget.html("");
    }
    return value;
}

function check_contract_price() {
    var value = createContractLayer.find("#contract_price").val();
    var errorWidget = createContractLayer.find("#contract_price_error");
    if (value.length == 0) {
        errorWidget.html("中标价不能为空！");
        value = undefined;
    } else if ((value < 0 || value > 100000000)) {
        errorWidget.html("请输入有效金额0-1亿！");
        value = undefined;
    } else {
        errorWidget.html("");
    }
    return value;
}

function check_contract_done_pay_rate() {
    var value = createContractLayer.find("#contract_done_pay_rate").val();
    var errorWidget = createContractLayer.find("#contract_done_pay_rate_error");
    var type = createContractLayer.find("#contract_type").val();
    if (value.length == 0 && (type == '施工' || type == '配套')) {
        errorWidget.html("完工付款比不能为空！");
        value = undefined;
    } else if ((value < 0 || value > 100)) {
        errorWidget.html("请输入有效比例0-100！");
        value = undefined;
    } else {
        errorWidget.html("");
    }
    return value;
}

function check_contract_pay_rate() {
    var value = createContractLayer.find("#contract_pay_rate").val();
    var errorWidget = createContractLayer.find("#contract_pay_rate_error");
    var type = createContractLayer.find("#contract_type").val();
    if (value.length == 0 && (type == '施工' || type == '配套')) {
        errorWidget.html("验收付款比不能为空！");
        value = undefined;
    } else if ((value < 0 || value > 100)) {
        errorWidget.html("请输入有效比例0-100！");
        value = undefined;
    } else {
        errorWidget.html("");
    }
    return value;
}

function check_contract_quality_deposit_rate() {
    var value = createContractLayer.find("#contract_quality_deposit_rate").val();
    var errorWidget = createContractLayer.find("#contract_quality_deposit_rate_error");
    var type = createContractLayer.find("#contract_type").val();
    if (value.length == 0 && (type == '施工' || type == '配套')) {
        errorWidget.html("质保金比例不能为空！");
        value = undefined;
    } else if ((value < 0 || value > 100)) {
        errorWidget.html("请输入有效比例0-100！");
        value = undefined;
    } else {
        errorWidget.html("");
    }
    return value;
}

function check_contract_quality_period() {
    var value = createContractLayer.find("#contract_quality_period").val();
    var errorWidget = createContractLayer.find("#contract_quality_period_error");
    var type = createContractLayer.find("#contract_type").val();
    if (value.length == 0 && (type == '施工' || type == '配套')) {
        errorWidget.html("质保期不能为空！");
        value = undefined;
    } else if ((value < 0 || value > 120)) {
        errorWidget.html("请输入有效值0-120月！");
        value = undefined;
    } else {
        errorWidget.html("");
    }
    return value;
}

function check_contract_sign_date() {
    var value = createContractLayer.find("#contract_sign_date").val();
    var errorWidget = createContractLayer.find("#contract_sign_date_error");
    if (value.length == 0) {
        errorWidget.html("请选择签署日期！");
        value = undefined;
    } else {
        errorWidget.html("");
    }
    return value;
}

// function is_completed() {
//     return createContractLayer.find("#contract_is_completed").val() == 1;
// }

// function check_contract_is_completed() {
//     return createContractLayer.find("#contract_is_completed").val();
// }

function check_contract_completion_date() {
    var value = createContractLayer.find("#contract_completion_date").val();
    var errorWidget = createContractLayer.find("#contract_completion_date_error");
    //竣工和结算状态才能输入竣工日期
    var isCompletionState = createContractLayer.find("#contract_state").val() >= 2;
    if (isCompletionState) {
        if (value.length == 0) {
            errorWidget.html("竣工态日期不能为空！");
            value = undefined;
        } else {
            errorWidget.html("");
        }
    } else {
        if (value.length != 0) {
            errorWidget.html("竣工态才能输入日期！");
            value = undefined;
        } else {
            errorWidget.html("");
        }
    }
    return value;
}

function check_contract_quality_fix_pay() {
    var value = createContractLayer.find("#contract_quality_fix_pay").val();
    var errorWidget = createContractLayer.find("#contract_quality_fix_pay_error");
    var type = createContractLayer.find("#contract_type").val();
    //只有施工和配套合同，质保期维修费才有意义
    if (type != '施工' && type != '配套') {
        value = '';
    } else {
        var isCompletionState = createContractLayer.find("#contract_state").val() >= 2;
        if (isCompletionState) {
            if (value.length != 0 && (value < 0 || value > 100000000)) {
                errorWidget.html("请输入有效金额0-1亿！");
                value = undefined;
            }
        } else {
            if (value.length != 0) {
                errorWidget.html("非竣工态质保维修费非法！");
                value = undefined;
            }
        }
    }
    if (value != undefined) {
        errorWidget.html('');
    }
    return value;
}

function check_contract_audit_price() {
    var value = createContractLayer.find("#contract_audit_price").val();
    var errorWidget = createContractLayer.find("#contract_audit_price_error");
    //是否审计结算状态，则必须输入结算价格
    var isAuditState = createContractLayer.find("#contract_state").val() >= 3;
    if (isAuditState) {
        if (value.length == 0) {
            errorWidget.html("结算态结算价不能为空！");
            value = undefined;
        } else if (value < 0 || value > 100000000) {
            errorWidget.html("请输入有效金额0-1亿！");
            value = undefined;
        }
    } else {
        if (value.length != 0) {
            errorWidget.html("结算态才能输入结算价！");
            value = undefined;
        }
    }
    if (value != undefined) {
        errorWidget.html("");
    }
    return value;
}

function check_contract_final_audit_price() {
    var value = createContractLayer.find("#contract_final_audit_price").val();
    var errorWidget = createContractLayer.find("#contract_final_audit_price_error");
    //是否审计结算状态，则必须输入结算价格
    var isAuditState = createContractLayer.find("#contract_state").val() >= 3;
    if (isAuditState) {
        if (value < 0 || value > 100000000) {
            errorWidget.html("请输入有效金额0-1亿！");
            value = undefined;
        }
    } else {
        if (value.length != 0) {
            errorWidget.html("结算态才能输入抽审价！");
            value = undefined;
        }
    }
    if (value != undefined) {
        errorWidget.html("");
    }
    return value;
}

function check_contract_note() {
    return createContractLayer.find("#contract_note").val();
}