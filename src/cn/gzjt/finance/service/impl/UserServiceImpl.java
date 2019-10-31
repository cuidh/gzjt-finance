package cn.gzjt.finance.service.impl;

import cn.gzjt.finance.domain.Department;
import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.User;
import cn.gzjt.finance.mapper.DepartmentMapper;
import cn.gzjt.finance.mapper.UserMapper;
import cn.gzjt.finance.service.UserService;
import cn.gzjt.finance.utils.MyBatisUtils;
import cn.gzjt.finance.utils.TextUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

/**
 * @author jianwei.zhou
 * @date 2019/10/19 15:17
 */
public class UserServiceImpl implements UserService {

    @Override
    public String addUser(User user) {
        if ("root".equals(user.getName())) {
            return "非法操作！";
        }
        SqlSession session = MyBatisUtils.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User byKey = mapper.getByKey(user.getName());
        if (byKey != null) {
            return "账号已存在！";
        }
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
        Department department = departmentMapper.getDepartmentByName(user.getDepartment().getName());
        if (department == null) {
            return "部门不存在！";
        }
        user.setDepartment(department);
        try {
            mapper.insert(user);
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "创建失败！";
        } finally {
            session.close();
        }
    }

    @Override
    public PageBean<User> getListByKey(int curPage, int rows, String key) {
        SqlSession session = MyBatisUtils.openSession();
        PageBean<User> pageBean = new PageBean<>();
        int totalCount;
        int totalPage;
        int start;
        UserMapper mapper = session.getMapper(UserMapper.class);
        if (TextUtils.isEmpty(key)) {
            totalCount = mapper.getAllListCount();
            totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1;
            //防止超出总数
            if (curPage > totalPage) {
                curPage = totalPage;
            }
            start = (curPage - 1) * rows;
            pageBean.list = mapper.getAllList(new RowBounds(start, rows));
        } else {
            totalCount = mapper.getListByKeyCount(key);
            totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1;
            //防止超出总数
            if (curPage > totalPage) {
                curPage = totalPage;
            }
            start = (curPage - 1) * rows;
            pageBean.list = mapper.getListByKey(key, new RowBounds(start, rows));
        }
        pageBean.totalPage = totalPage;
        pageBean.curPage = curPage;
        pageBean.totalCount = totalCount;
        pageBean.rows = rows;
        return pageBean;
    }

    @Override
    public String updatePassword(int id, String newPassword, int operatorId) {
        SqlSession session = MyBatisUtils.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            //检查权限，仅限root
            User operator = mapper.getById(operatorId);
            if (!"root".equals(operator.getName()) && id != operatorId) {
                return "无其他用户的改密权限！";
            }
            User user = mapper.getById(id);
            if ("root".equals(user.getName())) {
                return "禁止修改管理员密码！";
            }
            mapper.updatePassword(id, newPassword);
            session.commit();
            return null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            session.rollback();
            return "更新失败!";
        } finally {
            session.close();
        }
    }

    @Override
    public String deleteUser(int id, int operatorId) {
        SqlSession session = MyBatisUtils.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            //检查权限，仅限root
            User operator = mapper.getById(operatorId);
            if (!"root".equals(operator.getName())) {
                return "无删除用户权限！";
            }
            User user = mapper.getById(id);
            if ("root".equals(user.getName())) {
                return "禁止删除管理员！";
            }
            mapper.delete(id);
            session.commit();
            return null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            session.rollback();
            return "更新失败!";
        } finally {
            session.close();
        }
    }
}
