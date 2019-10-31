package cn.gzjt.finance.service;

import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.Payment;

/**
 * @author jianwei.zhou
 * @date 2019/10/17 11:45
 */
public interface PaymentService {

    String addPayment(Payment payment);

    /**
     * 通过支付编号-合同编号-子项目名称或者编号查找付款记录
     *
     * @param key key
     * @return list
     */
    PageBean<Payment> getByKey(int curPage, int rowsOfOnePage, String key);

    String deletePaymentById(int id);
}
