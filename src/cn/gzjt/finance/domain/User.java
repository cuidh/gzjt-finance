package cn.gzjt.finance.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CREATE TABLE t_user(
 * id           INT AUTO_INCREMENT,
 * NAME         VARCHAR(30) UNIQUE NOT NULL,
 * PASSWORD     VARCHAR(15) NOT NULL,
 * real_name    VARCHAR(20), -- 姓名
 * gender       INT, -- 0 male 1 female
 * mail         VARCHAR(50),
 * phone_number VARCHAR(20),
 * dept_id      INT,        -- 部门
 * create_time  TIMESTAMP,
 * PRIMARY KEY (id),
 * FOREIGN KEY(dept_id) REFERENCES t_department(id)
 * );
 */
public class User {
    private int id;
    private String name;
    private String password;
    private String realName;
    private int gender;
    private String mail;
    private String phoneNumber;
    private Date createTime;
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getCreateTimeStr() {
        return new SimpleDateFormat("yyyy-MM-dd").format(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", gender=" + gender +
                ", mail='" + mail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createTime=" + createTime +
                ", department=" + department +
                '}';
    }
}
