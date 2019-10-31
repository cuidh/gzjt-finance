package cn.gzjt.finance.mapper;

import cn.gzjt.finance.domain.Department;

/**
 * @author jianwei.zhou
 * @date 2019/10/16 12:34
 */
public interface DepartmentMapper {
    Department getDepartment(int id);

    Department getDepartmentByName(String name);
}
