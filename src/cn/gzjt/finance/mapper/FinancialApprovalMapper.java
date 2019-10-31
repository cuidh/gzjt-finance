package cn.gzjt.finance.mapper;

import cn.gzjt.finance.domain.FinancialApproval;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author jianwei.zhou
 * @date 2019/10/21 10:46
 */
public interface FinancialApprovalMapper {

    FinancialApproval getById(int id);

    void insert(FinancialApproval approval);

    void delete(int id);

    int getListByContractCount(@Param("contractId") int contractId);

    List<FinancialApproval> getListByContract(@Param("contractId") int contractId, RowBounds rowBounds);
}
