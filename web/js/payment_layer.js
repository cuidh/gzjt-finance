//创建支付记录
//弹窗的html内容
var createPaymentlayerHtml = "";
//弹出层DOM上下文
var createPaymentLayer = undefined;

function getCreatePaymentlayerHtml() {
    if (createPaymentlayerHtml == "") {
        var layerDiv = $("#create_payment_stub");
        layerDiv.css("display", "block");
        createPaymentlayerHtml = layerDiv.html();
        layerDiv.css("display", "none");
    }
    return createPaymentlayerHtml;
}

/**
 * 合同页面创建支付记录
 * @param contractNumber 合同编号
 * @param partyB 乙方名称
 * @param partyBBankCode 乙方收款银行账号
 */
function onCreatePaymentClicked(contractNumber, partyB) {
    var dialogConf = {
        type: 1,
        title: '创建付款',
        skin: 'layui-layer-rim', //加上边框
        area: ['630px', '560px'], //宽高
        content: getCreatePaymentlayerHtml(),
        success: function (layerObject, index) {
            createPaymentLayer = layerObject;
            createPaymentLayer.find("#payment_contract_key").val(contractNumber);
            createPaymentLayer.find("#payment_party_b").val(partyB);

            createPaymentLayer.find("#create_payment_cancel").click(function () {
                layer.close(index);
            });
            createPaymentLayer.find("#create_payment_confirm").click(onCreatePaymentConfirm);
            createPaymentLayer.find('.form_datetime').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                format: "yyyy-mm-dd hh:mm:ss",
                minuteStep: 1
            });
            createPaymentLayer.find("#payment_number").blur(check_payment_number);
            createPaymentLayer.find("#payment_contract_key").blur(check_payment_contract_key);
            createPaymentLayer.find("#payment_amount").blur(check_payment_amount);
            createPaymentLayer.find("#payment_party_b").blur(check_payment_party_b);
            createPaymentLayer.find("#payment_trade_date").blur(check_payment_trade_date);
            createPaymentLayer.find("#payment_should_amount").blur(check_payment_should_amount);
            createPaymentLayer.find("#payment_cut_amount").blur(check_payment_cut_amount);
            createPaymentLayer.find("#payment_cut_note").blur(check_payment_cut_note);
        }
    }
    layer.open(dialogConf);
}

function onCreatePaymentConfirm() {
    //总是提交所有字段
    var parameters = {
        "op_type": 1,
        "number": check_payment_number(),
        "contract": check_payment_contract_key(),
        "amount": check_payment_amount(),
        "should_amount": check_payment_should_amount(),
        "cut_amount": check_payment_cut_amount(),
        "bill_amount": check_payment_bill_amount(),
        "party_a": check_payment_party_a(),
        "party_b": check_payment_party_b(),
        "trade_date": check_payment_trade_date(),
        "note": check_payment_note(),
        "cut_note": check_payment_cut_note()
    };
    for (var key in parameters) {
        var value = parameters[key];
        if (value == undefined) {
            return;
        }
    }
    $.post($ctx + "/main/updatePaymentServlet.do", parameters, function (response, status) {
        //如果服务器指定响应格式是json，实际数据格式错误，则内部转换错误，结果回调不会执行
        if (status != "success" || response == undefined) {
            layer.msg('创建失败！ 请检查网络');
        } else if (200 == response.meta.code) {
            window.location.reload();
            // layer.msg(response.meta.msg);
        } else {
            layer.msg(response.meta.msg);
            createPaymentLayer.find("#payment_create_tips").html(response.meta.msg);
        }
    });
}

function check_payment_number() {
    var number = createPaymentLayer.find("#payment_number").val();
    var errorWidget = createPaymentLayer.find("#payment_number_error");
    if (!(/^[\d]{9}$/.test(number))) {
        errorWidget.html("凭证号为9位数字!");
        return undefined;
    } else {
        errorWidget.html("");
        //提取交易时间
        var y = number.substr(0, 4);
        var m = number.substr(4, 2);
        var tradeTime = createPaymentLayer.find("#payment_trade_date");
        tradeTime.val(y + "-" + m);
        return number;
    }
}

function check_payment_contract_key() {
    var contract = createPaymentLayer.find("#payment_contract_key").val();
    var errorWidget = createPaymentLayer.find("#payment_contract_key_error");
    if (contract.length == 0) {
        errorWidget.html("合同不能为空！！");
        return undefined;
    } else {
        errorWidget.html("");
        return contract;
    }
}

function check_payment_amount() {
    var value = createPaymentLayer.find("#payment_amount").val();
    var errorWidget = createPaymentLayer.find("#payment_amount_error");
    if (value.length == 0) {
        errorWidget.html("实付不能为空！");
        value = undefined;
    } else if ((value < 0 || value > 100000000)) {
        errorWidget.html("请输入有效金额0-1亿！");
        value = undefined;
    } else {
        errorWidget.html("");
    }
    return value;
}

function check_payment_bill_amount() {
    var value = createPaymentLayer.find("#payment_bill_amount").val();
    var errorWidget = createPaymentLayer.find("#payment_bill_amount_error");
    if (value.length != 0 && (value < 0 || value > 100000000)) {
        errorWidget.html("请输入有效金额0-1亿！");
        value = undefined;
    } else {
        errorWidget.html("");
    }
    return value;
}

function check_payment_should_amount() {
    var value = createPaymentLayer.find("#payment_should_amount").val();
    var errorWidget = createPaymentLayer.find("#payment_should_amount_error");
    if (value.length != 0 && (value < 0 || value > 100000000)) {
        errorWidget.html("请输入有效金额0-1亿！");
        value = undefined;
    } else {
        errorWidget.html("");
        var cutAmount = createPaymentLayer.find("#payment_cut_amount").val();
        if (cutAmount.length != 0 && (cutAmount >= 0 || cutAmount <= 100000000)) {
            //自动计算实际付款值
            var actualAmount = value - cutAmount;
            if (actualAmount >= 0 || actualAmount <= 100000000) {
                createPaymentLayer.find("#payment_amount").val(actualAmount);
            }
        }
    }
    return value;
}

function check_payment_cut_amount() {
    var value = createPaymentLayer.find("#payment_cut_amount").val();
    var errorWidget = createPaymentLayer.find("#payment_cut_amount_error");
    if (value.length != 0 && (value < 0 || value > 100000000)) {
        errorWidget.html("请输入有效金额0-1亿！");
        value = undefined;
    } else {
        errorWidget.html("");
        var shouldAmount = createPaymentLayer.find("#payment_should_amount").val();
        if (shouldAmount.length != 0 && (shouldAmount >= 0 || shouldAmount <= 100000000)) {
            //自动计算实际付款值
            var actualAmount = shouldAmount - value;
            if (actualAmount >= 0 || actualAmount <= 100000000) {
                createPaymentLayer.find("#payment_amount").val(actualAmount);
            }
        }
    }
    return value;
}

function check_payment_cut_note() {
    return createPaymentLayer.find("#payment_cut_note").val();
}

function check_payment_party_a() {
    var value = createPaymentLayer.find("#payment_party_a").val();
    var errorWidget = createPaymentLayer.find("#payment_party_a_error");
    if (value.length == 0) {
        errorWidget.html("付款方不能为空！");
        return undefined;
    } else {
        errorWidget.html("");
        return value;
    }
}

function check_payment_party_a_banka() {
    var value = createPaymentLayer.find("#payment_party_a_banka").val();
    var errorWidget = createPaymentLayer.find("#payment_party_a_banka_error");
    if (value.length == 0) {
        errorWidget.html("付款账号不能为空！");
        return undefined;
    } else if (!/^([1-9]{1})(\d{14}|\d{18})$/.test(value)) {
        errorWidget.html("付款账号格式有误！");
        return undefined;
    } else {
        errorWidget.html("");
        return value;
    }
}

function check_payment_party_b() {
    var value = createPaymentLayer.find("#payment_party_b").val();
    var errorWidget = createPaymentLayer.find("#payment_party_b_error");
    if (value.length == 0) {
        errorWidget.html("收款方不能为空！");
        return undefined;
    } else {
        errorWidget.html("");
        return value;
    }
}

function check_payment_party_b_banka() {
    var value = createPaymentLayer.find("#payment_party_b_banka").val();
    var errorWidget = createPaymentLayer.find("#payment_party_b_banka_error");
    if (value.length == 0) {
        errorWidget.html("收款账号不能为空！");
        return undefined;
    } else if (!/^([1-9]{1})(\d{14}|\d{18})$/.test(value)) {
        errorWidget.html("收款账号格式有误！");
        return undefined;
    } else {
        errorWidget.html("");
        return value;
    }
}

function check_payment_trade_date() {
    return createPaymentLayer.find("#payment_trade_date").val();
    // var value = createPaymentLayer.find("#payment_trade_date").val();
    // var errorWidget = createPaymentLayer.find("#payment_trade_date_error");
    // if (value.length == 0) {
    //     errorWidget.html("请选择交易日期！");
    //     value = undefined;
    // } else {
    //     errorWidget.html("");
    // }
    // return value;
}

function check_payment_note() {
    var value = createPaymentLayer.find("#payment_note").val();
    return value;
}