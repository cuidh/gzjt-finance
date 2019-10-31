package cn.gzjt.finance.mapper;

import cn.gzjt.finance.domain.Payment;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author jianwei.zhou
 * @date 2019/10/17 11:47
 */
public interface PaymentMapper {
    Payment getById(int id);

    Payment getByNumber(String number);

    int getAllCount();

    int getListByKeyCount(String key);

    List<Payment> getAll(RowBounds rowBounds);

    List<Payment> getListByKey(String key, RowBounds rowBounds);

    void insert(Payment payment);

    void deleteById(int id);
}
