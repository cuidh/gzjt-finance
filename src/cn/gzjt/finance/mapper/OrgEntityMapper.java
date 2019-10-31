package cn.gzjt.finance.mapper;

import cn.gzjt.finance.domain.OrgEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author jianwei.zhou
 * @date 2019/10/16 10:43
 */
public interface OrgEntityMapper {
    OrgEntity getOrgEntity(int id);

    OrgEntity getByName(String name);

    void insert(OrgEntity entity);

    void updateBankAccount(@Param("id") int id, @Param("bankAccount") String bankAccount);
}
