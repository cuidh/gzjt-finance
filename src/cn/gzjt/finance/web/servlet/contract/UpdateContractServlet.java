package cn.gzjt.finance.web.servlet.contract;

import cn.gzjt.finance.domain.Contract;
import cn.gzjt.finance.domain.JsonResponse;
import cn.gzjt.finance.service.ContractService;
import cn.gzjt.finance.service.impl.ContractServiceImpl;
import cn.gzjt.finance.utils.TextUtils;
import cn.gzjt.finance.web.servlet.base.BaseUpdateJsonServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jianwei.zhou
 * @date 2019/10/15 14:50
 */
@WebServlet("/main/updateContractServlet.do")
public class UpdateContractServlet extends BaseUpdateJsonServlet {

    @Override
    public void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Contract contract = new Contract();
        String projectKey;
        String partyA;
        String partyB;
        String partyBCode;
        try {
            String number = getString(req, "number");
            String type = getString(req, "type");
            projectKey = getString(req, "project_key");
            partyA = getString(req, "party_a");
            partyB = getString(req, "party_b");
            partyBCode = getString(req, "party_b_code");
            Integer price = getInt(req, "price");
            Integer state = getInt(req, "state");
            Integer donePayRate = getInt(req, "done_pay_rate");
            Integer checkPayRate = getInt(req, "check_pay_rate");
            Integer qualityDepositRate = getInt(req, "quality_deposit_rate");
            Integer qualityPeriod = getInt(req, "quality_period");
            String note = req.getParameter("note");
            String signDate = req.getParameter("sign_date");
//            String isCompleted = req.getParameter("is_completed");
            Date signDateObject = new SimpleDateFormat("yyyy-MM-dd").parse(signDate);
            contract.setNumber(number);
            contract.setType(type);
            contract.setState(state);
            contract.setPrice(price);
            contract.setNote(note);
            contract.setSignDate(signDateObject);
            contract.setCreateBy(getCurUsrId(req));
            contract.setPayRate(checkPayRate);
            contract.setDonePayRate(donePayRate);

            //todo jianwei.zhou 时间原因后端暂时不做校验，依赖前端校验逻辑
            Integer auditPrice = getInt(req, "audit_price");
            contract.setAuditPrice(auditPrice);
            Integer finalAuditPrice = getInt(req, "final_audit_price");
            contract.setFinalAuditPrice(finalAuditPrice);

            String completionDate = getString(req, "completion_date");
            if (TextUtils.isNotEmpty(completionDate)) {
                Date completionDateObject = new SimpleDateFormat("yyyy-MM-dd").parse(completionDate);
                contract.setCompletionDate(completionDateObject);
            }
            if ("施工".equals(type) || "配套".equals(type)) {
                //施工合同独有的属性
                contract.setQualityDepositRate(qualityDepositRate);
                contract.setQualityPeriod(qualityPeriod);
                Integer qualityFixPay = getInt(req, "quality_fix_pay");
                contract.setQualityFixPay(qualityFixPay);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsonResponse jsonResponse = JsonResponse.obtainError("参数错误！");
            new ObjectMapper().writeValue(resp.getWriter(), jsonResponse);
            return;
        }
        ContractService service = new ContractServiceImpl();
        String ret = service.addContract(contract, partyA, partyB, partyBCode, projectKey);
        JsonResponse response;
        if (ret == null) {
            response = JsonResponse.obtainOk();
        } else {
            response = JsonResponse.obtainError(ret);
        }
        new ObjectMapper().writeValue(resp.getWriter(), response);
    }

    @Override
    public void doModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //todo zjw 时间原因，先copy添加代码
        Contract contract = new Contract();
        String projectKey;
        String partyA;
        String partyB;
        String partyBCode;
        try {
            String number = getString(req, "number");
            String type = getString(req, "type");
            projectKey = getString(req, "project_key");
            partyA = getString(req, "party_a");
            partyB = getString(req, "party_b");
            partyBCode = getString(req, "party_b_code");
            Integer price = getInt(req, "price");
            Integer state = getInt(req, "state");
            Integer donePayRate = getInt(req, "done_pay_rate");
            Integer checkPayRate = getInt(req, "check_pay_rate");
            Integer qualityDepositRate = getInt(req, "quality_deposit_rate");
            Integer qualityPeriod = getInt(req, "quality_period");
            String note = req.getParameter("note");
            String signDate = req.getParameter("sign_date");
//            String isCompleted = req.getParameter("is_completed");
            Date signDateObject = new SimpleDateFormat("yyyy-MM-dd").parse(signDate);
            contract.setNumber(number);
            contract.setType(type);
            contract.setState(state);
            contract.setPrice(price);
            contract.setNote(note);
            contract.setSignDate(signDateObject);
            contract.setCreateBy(getCurUsrId(req));
            contract.setPayRate(checkPayRate);
            contract.setDonePayRate(donePayRate);

            //todo jianwei.zhou 时间原因后端暂时不做校验，依赖前端校验逻辑
            Integer auditPrice = getInt(req, "audit_price");
            contract.setAuditPrice(auditPrice);
            Integer finalAuditPrice = getInt(req, "final_audit_price");
            contract.setFinalAuditPrice(finalAuditPrice);

            String completionDate = getString(req, "completion_date");
            if (TextUtils.isNotEmpty(completionDate)) {
                Date completionDateObject = new SimpleDateFormat("yyyy-MM-dd").parse(completionDate);
                contract.setCompletionDate(completionDateObject);
            }
            if ("施工".equals(type) || "配套".equals(type)) {
                //施工合同独有的属性
                contract.setQualityDepositRate(qualityDepositRate);
                contract.setQualityPeriod(qualityPeriod);
                Integer qualityFixPay = getInt(req, "quality_fix_pay");
                contract.setQualityFixPay(qualityFixPay);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsonResponse jsonResponse = JsonResponse.obtainError("参数错误！");
            new ObjectMapper().writeValue(resp.getWriter(), jsonResponse);
            return;
        }
        ContractService service = new ContractServiceImpl();
        String ret = service.updateContract(contract, partyA, partyB, partyBCode, projectKey);
        JsonResponse response;
        if (ret == null) {
            response = JsonResponse.obtainOk();
        } else {
            response = JsonResponse.obtainError(ret);
        }
        new ObjectMapper().writeValue(resp.getWriter(), response);
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String number = getString(req, "number");
        ContractService service = new ContractServiceImpl();
        String ret = service.deleteContractByNumber(number);
        JsonResponse.write(ret, resp);
    }
}
