package cn.gzjt.finance.service.impl;

import cn.gzjt.finance.domain.User;
import cn.gzjt.finance.mapper.UserMapper;
import cn.gzjt.finance.service.LoginRegisterService;
import cn.gzjt.finance.utils.MyBatisUtils;
import javafx.util.Pair;
import org.apache.ibatis.session.SqlSession;

public class LoginRegisterServiceImpl implements LoginRegisterService {
    @Override
    public Pair<String, User> login(String usrName, String usrPassword) {
        SqlSession session = MyBatisUtils.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.getByKey(usrName);
        session.close();
        if (null == user) {
            return new Pair<>("账号不存在, 请核对", null);
        } else if (!usrPassword.equals(user.getPassword())) {
            return new Pair<>("密码错误, 请核对", null);
        }
        return new Pair<>(null, user);
    }
}
