package cn.gzjt.finance.mapper;

import cn.gzjt.finance.domain.SubProject;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author jianwei.zhou
 * @date 2019/10/16 10:36
 */
public interface SubProjectMapper {

    SubProject getSubProject(int id);

    SubProject getByName(String name);

    SubProject getByNumber(String number);

    int getAllSubProjectCount();

    int getBySubKeyCount(String key);

    int getByMainKeyCount(String key);

    int getByKeyCount(@Param("subKey") String subKey, @Param("mainKey") String mainKey);

    List<SubProject> getAllSubProjects(RowBounds rowBounds);

    List<SubProject> getBySubKey(String key, RowBounds rowBounds);

    List<SubProject> getByMainKey(String key, RowBounds rowBounds);

    List<SubProject> getByKey(@Param("subKey") String subKey, @Param("mainKey") String mainKey, RowBounds rowBounds);

    void insert(SubProject subProject);

    void deleteByNumber(String number);
}
