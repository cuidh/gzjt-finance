package cn.gzjt.finance.web.servlet.approval;

import cn.gzjt.finance.domain.Contract;
import cn.gzjt.finance.domain.FinancialApproval;
import cn.gzjt.finance.domain.JsonResponse;
import cn.gzjt.finance.service.ApprovalService;
import cn.gzjt.finance.service.impl.ApprovalServiceImpl;
import cn.gzjt.finance.web.servlet.base.BaseUpdateJsonServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jianwei.zhou
 * @date 2019/10/21 10:48
 */
@WebServlet("/main/updateApprovalServlet.do")
public class UpdateApprovalServlet extends BaseUpdateJsonServlet {
    @Override
    public void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FinancialApproval approval = null;
        try {
            String contract = getString(req, "contract");
            Integer amount = getInt(req, "amount");
            String number = getString(req, "number");
            String note = getString(req, "note");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(getString(req, "date"));
            approval = new FinancialApproval();
            approval.setAmount(amount);
            approval.setNumber(number);
            approval.setDate(date);
            approval.setNote(note);
            Contract contractObject = new Contract();
            contractObject.setNumber(contract);
            approval.setContract(contractObject);
            approval.setCreateBy(getCurUsrId(req));
        } catch (ParseException e) {
            e.printStackTrace();
            JsonResponse.write("参数错误！", resp);
            return;
        }
        ApprovalService service = new ApprovalServiceImpl();
        String ret = service.insertApproval(approval);
        JsonResponse.write(ret, resp);
    }

    @Override
    public void doModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = null;
        try {
            id = getInt(req, "id");
        } catch (Exception e) {
            e.printStackTrace();
            JsonResponse.write("参数错误！", resp);
            return;
        }
        ApprovalService service = new ApprovalServiceImpl();
        String ret = service.deleteApproval(id);
        JsonResponse.write(ret, resp);
    }
}
