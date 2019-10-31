package cn.gzjt.finance.service;

import cn.gzjt.finance.domain.User;
import javafx.util.Pair;

public interface LoginRegisterService {
    /**
     * 登录
     *
     * @param usrName     用户名
     * @param usrPassword 密码
     * @return <错误信息，用户>
     */
    Pair<String, User> login(String usrName, String usrPassword);
}
