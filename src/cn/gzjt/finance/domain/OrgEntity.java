package cn.gzjt.finance.domain;

import java.util.Date;

/**
 * 组织实体类
 */
public class OrgEntity {
    private int id;
    private String name;
    private String creditCode;
    private String adress;
    private String bankAccount;
    private String note;
    private boolean isInternal;
    private int createBy;
    private Date createTime;

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

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isInternal() {
        return isInternal;
    }

    public void setInternal(boolean internal) {
        isInternal = internal;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrgEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creditCode='" + creditCode + '\'' +
                ", adress='" + adress + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", note='" + note + '\'' +
                ", isInternal=" + isInternal +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                '}';
    }
}
