package cn.gzjt.finance.domain;

import cn.gzjt.finance.utils.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 合同实体类
 *
 * @author jianwei.zhou
 * @date 2019/10/15 11:49
 */
public class Contract {

    /**
     * 合同状态枚举
     */
    public static final int start_state = 0; //开工
    public static final int done_state = 1; //完工
    public static final int check_state = 2; //竣工验收
    public static final int audit_state = 3; //结算
    public static final int quality_out_state = 4; //出质保期

    private int id;
    private String number;//编号
    private String type; //先存字符串，避免没有必要的关联查询
    private Integer state; //合同状态 0 开工  1 完工  2 竣工验收  3 结算
    private Integer price;//中标价
    private Integer donePayRate;//完工付款比例
    private Integer payRate;//验收付款比例
    private Integer qualityDepositRate;//质保金比例
    private Integer qualityPeriod;
    private Date completionDate; //完成日期
    private Integer qualityFixPay;
    private Integer auditPrice;//审计结算价
    private Integer finalAuditPrice;//二次抽审价，不一定会被抽审
    private SubProject project;//所属项目
    private OrgEntity partyA;//甲方
    private OrgEntity partyB;//乙方
    private String note;
    private int createBy;
    private Date signDate;//签署日期
    private Date createTime;
    private Integer approvalSum;//累计审批
    private Integer paySum;//累计支付
    private Integer billSum;//累计支付
    private String warning;

    public Integer getBillSum() {
        return billSum;
    }

    public void setBillSum(Integer billSum) {
        this.billSum = billSum;
    }

    public Integer getFinalAuditPrice() {
        return finalAuditPrice;
    }

    public void setFinalAuditPrice(Integer finalAuditPrice) {
        this.finalAuditPrice = finalAuditPrice;
    }

    public String getWarning() {
        return warning;
    }

    public Integer getApprovalSum() {
        return approvalSum;
    }

    public void setApprovalSum(Integer approvalSum) {
        this.approvalSum = approvalSum;
    }

    public Integer getPaySum() {
        return paySum;
    }

    public void setPaySum(Integer paySum) {
        this.paySum = paySum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDonePayRate() {
        return donePayRate;
    }

    public void setDonePayRate(Integer donePayRate) {
        this.donePayRate = donePayRate;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPayRate() {
        return payRate;
    }

    public void setPayRate(Integer payRate) {
        this.payRate = payRate;
    }

    public Integer getQualityDepositRate() {
        return qualityDepositRate;
    }

    public void setQualityDepositRate(Integer qualityDepositRate) {
        this.qualityDepositRate = qualityDepositRate;
    }

    public Integer getQualityPeriod() {
        return qualityPeriod;
    }

    public void setQualityPeriod(Integer qualityPeriod) {
        this.qualityPeriod = qualityPeriod;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public String getCompletionDateStr() {
        return new SimpleDateFormat("yyyy-MM-dd").format(signDate);
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Integer getQualityFixPay() {
        return qualityFixPay;
    }

    public void setQualityFixPay(Integer qualityFixPay) {
        this.qualityFixPay = qualityFixPay;
    }

    public Integer getAuditPrice() {
        return auditPrice;
    }

    public void setAuditPrice(Integer auditPrice) {
        this.auditPrice = auditPrice;
    }

    public SubProject getProject() {
        return project;
    }

    public void setProject(SubProject project) {
        this.project = project;
    }

    public OrgEntity getPartyA() {
        return partyA;
    }

    public void setPartyA(OrgEntity partyA) {
        this.partyA = partyA;
    }

    public OrgEntity getPartyB() {
        return partyB;
    }

    public void setPartyB(OrgEntity partyB) {
        this.partyB = partyB;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getSignDate() {
        return signDate;
    }

    public String getSignDateStr() {
        return new SimpleDateFormat("yyyy-MM-dd").format(signDate);
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", state=" + state +
                ", price=" + price +
                ", donePayRate=" + donePayRate +
                ", payRate=" + payRate +
                ", qualityDepositRate=" + qualityDepositRate +
                ", qualityPeriod=" + qualityPeriod +
                ", completionDate=" + completionDate +
                ", qualityFixPay=" + qualityFixPay +
                ", auditPrice=" + auditPrice +
                ", finalAuditPrice=" + finalAuditPrice +
                ", project=" + project +
                ", partyA=" + partyA +
                ", partyB=" + partyB +
                ", note='" + note + '\'' +
                ", createBy=" + createBy +
                ", signDate=" + signDate +
                ", createTime=" + createTime +
                ", approvalSum=" + approvalSum +
                ", paySum=" + paySum +
                ", billSum=" + billSum +
                ", warning='" + warning + '\'' +
                '}';
    }

    private static int getSafeInt(Integer integer) {
        return integer == null ? 0 : integer.intValue();
    }

    public boolean existWarning() {
        if (TextUtils.isNotEmpty(this.warning)) {
            return true;
        }
        //状态缺失可能是历史数据, 不生成警告信息
        if (state == null) {
            return false;
        }
        //检测生成警告信息
        String warnings = "";
        switch (state) {
            case quality_out_state: {
                //# 出质保态（退质保金） 累计已支付  <=  结算价
                //二次抽审价不为空以抽审价为限
                if (getSafeInt(finalAuditPrice) != 0) {
                    if (getSafeInt(paySum) > getSafeInt(finalAuditPrice)) {
                        warnings += "[已出质保]累计支付超出(抽审价);";
                    }
                    if (getSafeInt(qualityFixPay) > getSafeInt(finalAuditPrice) * (getSafeInt(qualityDepositRate) / 100f)) {
                        warnings += "[已出质保]维修费超出(质保金);";
                    }
                } else {
                    if (getSafeInt(paySum) > getSafeInt(auditPrice)) {
                        warnings += "[已出质保]累计支付超出(结算价);";
                    }
                    if (getSafeInt(qualityFixPay) > getSafeInt(auditPrice) * (getSafeInt(qualityDepositRate) / 100f)) {
                        warnings += "[已出质保]维修费超出(质保金);";
                    }
                }
                break;
            }
            case audit_state: {
                if (getSafeInt(finalAuditPrice) != 0) {
                    if (getSafeInt(paySum) > getSafeInt(finalAuditPrice) * ((100 - getSafeInt(qualityDepositRate)) / 100f)) {
                        warnings = "[已结算]累计支付超出(抽审价减去质保金);";
                    }
                    if (getSafeInt(qualityFixPay) > getSafeInt(finalAuditPrice) * (getSafeInt(qualityDepositRate) / 100f)) {
                        warnings += "[已结算]维修费超出(质保金);";
                    }
                } else {
                    if (getSafeInt(paySum) > getSafeInt(auditPrice) * ((100 - getSafeInt(qualityDepositRate)) / 100f)) {
                        warnings = "[已结算]累计支付超出(结算价减去质保金);";
                    }
                    if (getSafeInt(qualityFixPay) > getSafeInt(auditPrice) * (getSafeInt(qualityDepositRate) / 100f)) {
                        warnings += "[已结算]维修费超出(质保金);";
                    }
                }
                break;
            }

            case check_state: {
                if (getSafeInt(paySum) > getSafeInt(approvalSum)) {
                    warnings = "[已验收]累计支付超出(累计财政审批);";
                }
                if (getSafeInt(qualityFixPay) > getSafeInt(price) * (getSafeInt(qualityDepositRate) / 100f)) {
                    warnings += "[已结算]维修费超出(质保金);";
                }
                break;
            }
            case done_state: {
                if (getSafeInt(paySum) > getSafeInt(approvalSum)) {
                    warnings = "[已完工]累计支付超出(累计财政审批);";
                }
                break;
            }
            case start_state: {
                if (getSafeInt(paySum) > getSafeInt(approvalSum)) {
                    warnings = "[已开工]累计支付超出(累计财政审批);";
                }
                break;
            }
        }
        boolean existWarning = TextUtils.isNotEmpty(warnings);
        if (existWarning) {
            this.warning = warnings;
        }
        return existWarning;
    }
}
