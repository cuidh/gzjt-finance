package cn.gzjt.finance.service;

import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.User;

/**
 * @author jianwei.zhou
 * @date 2019/10/19 15:14
 */
public interface UserService {

    String addUser(User user);

    PageBean<User> getListByKey(int curPage, int rows, String key);

    String updatePassword(int id, String newPassword, int operatorId);

    String deleteUser(int id, int operatorId);
}
