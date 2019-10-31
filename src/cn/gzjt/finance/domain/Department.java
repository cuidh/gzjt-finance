package cn.gzjt.finance.domain;

//CREATE TABLE t_department
//        (
//        id   INT AUTO_INCREMENT,
//        NAME VARCHAR(30) UNIQUE NOT NULL,
//        PRIMARY KEY (id)
//        );

/**
 * 部门
 */
public class Department {
    private int id;
    private String name;
    private User manager; //部门管理者

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

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name=" + name +
                ", manager=" + manager +
                '}';
    }
}
