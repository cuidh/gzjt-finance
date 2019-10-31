package cn.gzjt.finance.web.servlet.payment;

import cn.gzjt.finance.domain.Contract;
import cn.gzjt.finance.domain.JsonResponse;
import cn.gzjt.finance.domain.OrgEntity;
import cn.gzjt.finance.domain.Payment;
import cn.gzjt.finance.service.PaymentService;
import cn.gzjt.finance.service.impl.PaymentServiceImpl;
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
 * @date 2019/10/17 11:28
 */
@WebServlet("/main/updatePaymentServlet.do")
public class UpdatePaymentServlet extends BaseUpdateJsonServlet {
    @Override
    public void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Payment payment;
        try {
            String number = getString(req, "number");
            String contract = getString(req, "contract");
            Integer amount = getInt(req, "amount");
            Integer shouldAmount = getInt(req, "should_amount");
            Integer cutAmount = getInt(req, "cut_amount");
            Integer billAmount = getInt(req, "bill_amount");
            String partyA = getString(req, "party_a");
            String partyB = getString(req, "party_b");
            String partyABanka = getString(req, "party_a_banka");
            String partyBBanka = getString(req, "party_b_banka");
            String tradeDate = getString(req, "trade_date");
            Date tradeDateObject = new SimpleDateFormat("yyyy-MM").parse(tradeDate);
            String note = getString(req, "note");
            String cutNote = getString(req, "cut_note");
            payment = new Payment();
            payment.setNumber(number);
            payment.setAmount(amount);
            payment.setShouldAmount(shouldAmount);
            payment.setCutAmount(cutAmount);
            payment.setBillAmount(billAmount);
            payment.setCutNote(cutNote);
            //合同
            Contract contract1 = new Contract();
            contract1.setNumber(contract);
            payment.setContract(contract1);
            //付款方
            OrgEntity a = new OrgEntity();
            a.setName(partyA);
            a.setBankAccount(partyABanka);
            payment.setFrom(a);
            payment.setFromAccount(partyABanka);
            //收款方
            OrgEntity b = new OrgEntity();
            b.setName(partyB);
            b.setBankAccount(partyBBanka);
            payment.setTo(b);
            payment.setToAccount(partyBBanka);
            //交易时间以及备注
            payment.setTradeTime(tradeDateObject);
            payment.setNote(note);
            payment.setCreateBy(getCurUsrId(req));
        } catch (Exception e) {
            e.printStackTrace();
            JsonResponse jsonResponse = JsonResponse.obtainError("参数错误！");
            new ObjectMapper().writeValue(resp.getWriter(), jsonResponse);
            return;
        }

        PaymentService service = new PaymentServiceImpl();
        String ret = service.addPayment(payment);
        JsonResponse jsonResponse;
        if (TextUtils.isEmpty(ret)) {
            jsonResponse = JsonResponse.obtainOk();
        } else {
            jsonResponse = JsonResponse.obtainError(ret);
        }
        new ObjectMapper().writeValue(resp.getWriter(), jsonResponse);
    }

    @Override
    public void doModify(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = getInt(req, "id");
        PaymentService service = new PaymentServiceImpl();
        String ret = service.deletePaymentById(id);
        JsonResponse.write(ret, resp);
    }
}
