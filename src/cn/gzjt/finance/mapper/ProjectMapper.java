package cn.gzjt.finance.mapper;

import cn.gzjt.finance.domain.Project;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author jianwei.zhou
 * @date 2019/10/16 12:57
 */
public interface ProjectMapper {
    Project getById(int id);

    Project getByName(String name);

    Project getByNumber(String number);

    int getTotalCount();

    int getLikeTotalCount(String key);

    void insert(Project project);

    List<Project> getAllProjects();

    List<Project> getByNameOrNumber(String key);

    List<Project> getProjectByPage(RowBounds rowBounds);

    List<Project> getProjectByPageAndKey(@Param("key") String key, RowBounds rowBounds);

    void deleteByNumber(String number);
}
