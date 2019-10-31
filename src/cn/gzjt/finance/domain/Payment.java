package cn.gzjt.finance.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jianwei.zhou
 * @date 2019/10/17 11:34
 */
public class Payment {
    private int id;
    private String number;
    private Contract contract;
    private Integer amount;
    private OrgEntity from;
    private OrgEntity to;
    private String fromAccount;
    private String toAccount;
    private String note;
    private Date tradeTime;
    private int createBy;
    private Integer shouldAmount;//应付
    private Integer cutAmount;//扣款
    private Integer billAmount;//扣款
    private String cutNote;//扣款说明

    public Integer getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Integer billAmount) {
        this.billAmount = billAmount;
    }

    public Integer getShouldAmount() {
        return shouldAmount;
    }

    public void setShouldAmount(Integer shouldAmount) {
        this.shouldAmount = shouldAmount;
    }

    public Integer getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(Integer cutAmount) {
        this.cutAmount = cutAmount;
    }

    public String getCutNote() {
        return cutNote;
    }

    public void setCutNote(String cutNote) {
        this.cutNote = cutNote;
    }

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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getSafeAmount() {
        return amount == null ? 0 : amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public OrgEntity getFrom() {
        return from;
    }

    public void setFrom(OrgEntity from) {
        this.from = from;
    }

    public OrgEntity getTo() {
        return to;
    }

    public void setTo(OrgEntity to) {
        this.to = to;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public String getTradeTimeStr() {
        return new SimpleDateFormat("yyyy-MM").format(tradeTime);
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", contract=" + contract +
                ", amount=" + amount +
                ", from=" + from +
                ", to=" + to +
                ", fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", note='" + note + '\'' +
                ", tradeTime=" + tradeTime +
                ", createBy=" + createBy +
                ", shouldAmount=" + shouldAmount +
                ", cutAmount=" + cutAmount +
                ", billAmount=" + billAmount +
                ", cutNote='" + cutNote + '\'' +
                '}';
    }
}
