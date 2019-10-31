<%--
  Created by IntelliJ IDEA.
  User: jianwei.zhou
  Date: 2019/10/24
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="create_contract_stub" style="display:none;">
    <form id="create_contract_form" class="create_form">
        <div class="form_group">
            <label for="contract_number"><span style="color: red">*&nbsp</span>编号：</label>
            <input class="form_input" type="text" id="contract_number" name="contract_number" maxlength="10"
                   placeholder="编号">
            <span class="input_error_tips" id="contract_number_error"></span>
        </div>
        <div class="form_group">
            <label for="contract_project_key"><span style="color: red">*&nbsp</span>所属项目：</label>
            <input class="form_input" type="text" id="contract_project_key" placeholder="编号|名称"
                   name="contract_project_key">
            <span class="input_error_tips" id="contract_project_key_error"></span>
        </div>
        <div class="form_group">
            <label for="contract_party_a"><span style="color: red">*&nbsp</span>甲方：</label>
            <input class="form_input" type="text" id="contract_party_a" placeholder="名称" name="contract_party_a">
            <span class="input_error_tips" id="contract_party_a_error"></span>
        </div>
        <div class="form_group">
            <label for="contract_party_b"><span style="color: red">*&nbsp</span>乙方：</label>
            <input class="form_input" type="text" id="contract_party_b" placeholder="名称" name="contract_party_b">
            <span class="input_error_tips" id="contract_party_b_error"></span>
        </div>
        <div class="form_group">
            <label for="contract_party_b_code" style="visibility: hidden"><span style="color: red">*&nbsp</span>机构信用代码：</label>
            <input class="form_input" type="text" id="contract_party_b_code" placeholder="机构信用代码"
                   name="contract_party_b_code">
            <span class="input_error_tips" id="contract_party_b_code_error"></span>
        </div>
        <div class="form_group">
            <label for="contract_price"><span style="color: red">*&nbsp</span>中标价：</label>
            <input class="form_input" type="number" maxlength="9" id="contract_price" placeholder="单位：元"
                   name="contract_price">
            <span class="input_error_tips" id="contract_price_error"></span>
        </div>
        <%--        <div class="form_group">--%>
        <%--            <label for="contract_file">上传扫面件：</label>--%>
        <%--            <input class="form_input" type="text" id="contract_file" name="contract_file" placeholder="扫面件">--%>
        <%--            <span class="input_error_tips" id="contract_file_error"></span>--%>
        <%--        </div>--%>
        <div class="form_group">
            <label for="contract_note">备注：</label>
            <input class="form_input" type="text" id="contract_note" name="contract_note">
        </div>
        <%--        日期选择控件--%>
        <div class="form_group" style="overflow: hidden;">
            <label for="contract_sign_date" class="date_picker_label control-label"><span
                    style="color: red;">*&nbsp</span>签署日期：</label>
            <div class="date_picker_div input-group date form_date" data-date="" data-date-format="dd MM yyyy"
                 data-link-field="sign_date" data-link-format="yyyy-mm-dd">
                <input class="form-control" size="14" type="text" id="contract_sign_date" value="" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
            <span class="input_error_tips date_picker_error_tip" id="contract_sign_date_error"></span>
            <input type="hidden" value="" name="contract_sign_date"/><br/>
        </div>
        <div class="form_group">
            <label for="contract_state"><span style="color: red">*&nbsp</span>进度：</label>
            <select class="form-control form_select" id="contract_state" name="contract_state">
                <option value="0">开工</option>
                <option value="1">完工</option>
                <option value="2">验收</option>
                <option value="3">结算</option>
                <option value="4">出质保</option>
            </select>
            <span class="input_error_tips" id="contract_state_error"></span>
        </div>
        <div class="form_group">
            <label for="contract_type"><span style="color: red">*&nbsp</span>类型：</label>
            <select class="form-control form_select" id="contract_type" name="contract_type">
                <option value="施工">施工</option>
                <option value="监理">监理</option>
                <option value="可contract_type研编制">可研编制</option>
                <option value="见证">见证</option>
                <option value="环评">环评</option>
                <option value="设计">设计</option>
                <option value="图审">图审</option>
                <option value="招标代理">招标代理</option>
                <option value="测量放线">测量放线</option>
                <option value="勘察">勘察</option>
                <option value="平场">平场</option>
                <option value="勘察">勘察</option>
                <option value="检测">检测</option>
                <option value="配套">配套</option>
                <option value="水电气">水电气</option>
                <option value="融资">融资</option>
                <option value="其他">其他</option>
            </select>
            <span class="input_error_tips" id="contract_type_error"></span>
        </div>
        <div class="form_group">
            <label for="contract_audit_price">结算价：</label>
            <input class="form_input" type="number" maxlength="9" id="contract_audit_price" placeholder="单位：元"
                   name="contract_audit_price">
            <span class="input_error_tips" id="contract_audit_price_error"></span>
        </div>
        <div class="form_group">
            <label for="contract_final_audit_price">抽审价：</label>
            <input class="form_input" type="number" maxlength="9" id="contract_final_audit_price" placeholder="单位：元"
                   name="contract_final_audit_price">
            <span class="input_error_tips" id="contract_final_audit_price_error"></span>
        </div>
        <div class="form_group" style="overflow: hidden">
            <label for="contract_completion_date" class="date_picker_label control-label">竣工日期：</label>
            <div class="date_picker_div input-group date form_date" data-date="" data-date-format="yyyy-MM-dd"
                 data-link-field="completion_date" data-link-format="yyyy-mm-dd">
                <input class="form-control" size="14" type="text" id="contract_completion_date" value="" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
            <span class="input_error_tips date_picker_error_tip" id="contract_completion_date_error"></span>
            <input type="hidden" value="" name="contract_completion_date"/><br/>
        </div>
        <div class="form_group">
            <label for="contract_done_pay_rate">完工付款比例：</label>
            <input class="form_input" type="number" maxlength="3" id="contract_done_pay_rate" placeholder="百分数%"
                   value="75"
                   name="contract_done_pay_rate">
            <span class="input_error_tips" id="contract_done_pay_rate_error"></span>
        </div>
        <div class="form_group">
            <label for="contract_pay_rate">验收付款比例：</label>
            <input class="form_input" type="number" maxlength="3" id="contract_pay_rate" placeholder="百分数%"
                   value="80"
                   name="contract_pay_rate">
            <span class="input_error_tips" id="contract_pay_rate_error"></span>
        </div>
        <%--        结算默认付款比例 （100 % -质保金比例）--%>
        <%--        施工合同独有的属性--%>
        <div id="construction_inputs">
            <div class="form_group">
                <label for="contract_quality_deposit_rate">质保金比例：</label>
                <input class="form_input" type="number" maxlength="3" id="contract_quality_deposit_rate"
                       placeholder="百分数%" value="5"
                       name="contract_quality_deposit_rate">
                <span class="input_error_tips" id="contract_quality_deposit_rate_error"></span>
            </div>
            <div class="form_group">
                <label for="contract_quality_period">质保期：</label>
                <input class="form_input" data-date-format="yyyy-mm-dd" type="number" maxlength="3"
                       id="contract_quality_period"
                       name="contract_quality_period" placeholder="单位:月" value="12">
                <span class="input_error_tips" id="contract_quality_period_error"></span>
            </div>
            <div class="form_group complete_group">
                <label for="contract_quality_fix_pay">质保期维修费：</label>
                <input class="form_input" type="number" maxlength="9" id="contract_quality_fix_pay"
                       name="contract_quality_fix_pay"
                       placeholder="质保期维修费">
                <span class="input_error_tips" id="contract_quality_fix_pay_error"></span>
            </div>
        </div>
    </form>
    <div style="overflow: hidden;padding-bottom: 30px;">
        <span id="create_contract_tips" style="color:red;font-size: 12px;margin-left: 50px"></span>
        <button id="create_contract_cancel" type="button" class="create_layer_cancel btn btn-primary btn-sm">取消</button>
        <button id="create_contract_confirm" type="button" class="create_layer_confirm btn btn-primary btn-sm">确定
        </button>
    </div>
</div>
