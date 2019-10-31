package cn.gzjt.finance.mapper;

import cn.gzjt.finance.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author jianwei.zhou
 * @date 2019/10/16 11:55
 */
public interface UserMapper {
    User getById(int id);

    User getByKey(String usrName);

    int getAllListCount();

    List<User> getAllList(RowBounds rowBounds);

    int getListByKeyCount(String key);

    List<User> getListByKey(String key, RowBounds rowBounds);

    void insert(User user);

    void updatePassword(@Param("id") int id, @Param("password") String password);

    void delete(int id);
}
