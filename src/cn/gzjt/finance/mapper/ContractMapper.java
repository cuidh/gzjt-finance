package cn.gzjt.finance.mapper;

import cn.gzjt.finance.domain.Contract;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author jianwei.zhou
 * @date 2019/10/16 10:03
 */
public interface ContractMapper {

    Contract getById(int id);

    Contract getByNumber(String number);

    //计算分页记录数
    int getAllContractCount();

    int getByLikeNumberOrProjectCount(String key);

    //分页
    List<Contract> getAllContract(RowBounds rowBounds);

    List<Contract> getByLikeNumberOrProject(String key, RowBounds rowBounds);

    //插入
    void insert(Contract contract);

    void update(Contract contract);

    void delete(int id);

    void updateApprovalSum(@Param("id") int id, @Param("appendApproval") int appendApproval);

    void updatePaySum(@Param("id") int id, @Param("appendPay") int appendPay);

    void updateBillSum(@Param("id") int id, @Param("appendBill") int appendBill);
}
