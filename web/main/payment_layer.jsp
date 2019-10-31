<%--
  Created by IntelliJ IDEA.
  User: jianwei.zhou
  Date: 2019/10/25
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--创建支付记录对话框，不直接显示，需通过layer.open展示--%>
<div id="create_payment_stub" style="display:none;">
    <form id="create_payment_form" class="create_form">
        <div class="form_group">
            <label for="payment_contract_key"><span style="color: red">*&nbsp</span>合同：</label>
            <input class="form_input" type="text" id="payment_contract_key" placeholder="合同"
                   name="payment_contract_key">
            <span class="input_error_tips" id="payment_contract_key_error"></span>
        </div>
        <div class="form_group">
            <label for="payment_party_a"><span style="color: red">*&nbsp</span>付款方：</label>
            <input class="form_input" type="text" id="payment_party_a" placeholder="付款方" name="payment_party_a"
                   value="集团财务部">
            <span class="input_error_tips" id="payment_party_a_error"></span>
        </div>

        <div class="form_group">
            <label for="payment_party_b"><span style="color: red">*&nbsp</span>收款方：</label>
            <input class="form_input" type="text" id="payment_party_b" placeholder="收款方" name="payment_party_b">
            <span class="input_error_tips" id="payment_party_b_error"></span>
        </div>

        <div class="form_group">
            <label for="payment_number"><span style="color: red">*&nbsp</span>凭证号：</label>
            <input class="form_input" type="text" id="payment_number" name="payment_number" maxlength="9"
                   placeholder="凭证号"
                   onkeyup="this.value=this.value.replace(/\D/g,'')"
                   onafterpaste="this.value=this.value.replace(/\D/g,'')">
            <span class="input_error_tips" id="payment_number_error"></span>
        </div>
        <div class="form_group">
            <label for="payment_should_amount">应付：</label>
            <input class="form_input" type="number" maxlength="9" id="payment_should_amount" placeholder="金额:元"
                   name="payment_should_amount">
            <span class="input_error_tips" id="payment_should_amount_error"></span>
        </div>
        <div class="form_group">
            <label for="payment_cut_amount">扣款：</label>
            <input class="form_input" type="number" maxlength="9" id="payment_cut_amount" placeholder="金额:元"
                   name="payment_cut_amount">
            <span class="input_error_tips" id="payment_cut_amount_error"></span>
        </div>
        <div class="form_group">
            <label for="payment_amount"><span style="color: red">*&nbsp</span>实付：</label>
            <input class="form_input" type="number" maxlength="9" id="payment_amount" placeholder="金额:元"
                   name="payment_amount">
            <span class="input_error_tips" id="payment_amount_error"></span>
        </div>
        <div class="form_group">
            <label for="payment_bill_amount">发票：</label>
            <input class="form_input" type="number" maxlength="9" id="payment_bill_amount" placeholder="金额:元"
                   name="payment_bill_amount">
            <span class="input_error_tips" id="payment_bill_amount_error"></span>
        </div>
        <div class="form_group">
            <label for="payment_cut_note" style="visibility: hidden;"><span
                    style="color: red">*&nbsp</span>扣款说明：</label>
            <input class="form_input" type="text" maxlength="30" id="payment_cut_note" placeholder="说明"
                   name="payment_cut_note">
            <span class="input_error_tips" id="payment_cut_note_error"></span>
        </div>

        <div class="form_group">
            <label for="payment_trade_date"><span style="color: red">*&nbsp</span>日期：</label>
            <input class="form_input" type="text" maxlength="10" id="payment_trade_date" placeholder="日期"
                   name="payment_trade_date" readonly>
            <span class="input_error_tips" id="ppayment_trade_date_error"></span>
        </div>
        <div class="form_group">
            <label for="payment_note">备注：</label>
            <input class="form_input" type="text" id="payment_note" name="payment_note">
        </div>
    </form>
    <div style="overflow: hidden;padding-bottom: 30px;">
        <span id="create_payment_tips" style="color:red;font-size: 12px;margin-left: 50px"></span>
        <button id="create_payment_cancel" type="button" class="create_layer_cancel btn btn-primary btn-sm">取消</button>
        <button id="create_payment_confirm" type="button" class="create_layer_confirm btn btn-primary btn-sm">确定
        </button>
    </div>
</div>
