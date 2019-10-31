package cn.gzjt.finance.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 财政审批记录
 *
 * @author jianwei.zhou
 * @date 2019/10/21 10:42
 */
public class FinancialApproval {
    private int id;
    private String number;
    private int amount;
    private Date date;
    private int createBy;
    private Contract contract;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public String getDateStr() {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "FinancialApproval{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", createBy=" + createBy +
                ", contract=" + contract +
                ", note='" + note + '\'' +
                '}';
    }
}
