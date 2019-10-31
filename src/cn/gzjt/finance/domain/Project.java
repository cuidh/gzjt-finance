package cn.gzjt.finance.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 主项目
 */
public class Project {
    private int id;
    private String name;
    private String number;
    private OrgEntity ownerObject;
    private String note;
    private int createBy;
    private Date createTime;

    public String getOwnerName() {
        return ownerObject == null ? null : ownerObject.getName();
    }

    public void setOwnerName(String ownerName) {
        if (ownerObject == null) {
            ownerObject = new OrgEntity();
        }
        ownerObject.setName(ownerName);
    }

    public int getOwner() {
        return ownerObject == null ? -1 : ownerObject.getId();
    }

    public void setOwner(int ownerId) {
        if (ownerObject == null) {
            ownerObject = new OrgEntity();
        }
        ownerObject.setId(ownerId);
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public OrgEntity getOwnerObject() {
        return ownerObject;
    }

    public void setOwnerObject(OrgEntity ownerObject) {
        this.ownerObject = ownerObject;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getCreateDate() {
        if (createTime == null) {
            return "-";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(createTime);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", owner=" + ownerObject +
                ", note='" + note + '\'' +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                '}';
    }
}
