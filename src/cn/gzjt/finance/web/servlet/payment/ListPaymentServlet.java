package cn.gzjt.finance.web.servlet.payment;

import cn.gzjt.finance.business.PagingHelper;
import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.Payment;
import cn.gzjt.finance.service.PaymentService;
import cn.gzjt.finance.service.impl.PaymentServiceImpl;
import cn.gzjt.finance.utils.TextUtils;
import cn.gzjt.finance.web.servlet.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jianwei.zhou
 * @date 2019/10/17 16:04
 */
@WebServlet("/main/listPaymentServlet.do")
public class ListPaymentServlet extends BaseHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        int curPage = getInt(req, "curPage", 1);
        int rows = getInt(req, "row", 5);
        if (TextUtils.isNotEmpty(key)) {
            req.setAttribute("key", key);
        }
        PaymentService service = new PaymentServiceImpl();
        PageBean<Payment> pageBean = service.getByKey(curPage, rows, key);
        PagingHelper.out(req, pageBean, "/main/listPaymentServlet.do",
                "?key=" + (key == null ? "" : key) + "&rows=5&curPage=");
        req.getRequestDispatcher("/main/payment.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
