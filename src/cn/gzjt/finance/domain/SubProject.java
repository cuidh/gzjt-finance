package cn.gzjt.finance.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jianwei.zhou
 * @date 2019/10/14 8:54
 */
public class SubProject {
    private int id;
    private String number;
    private String name;
    private String note;
    private OrgEntity owner;
    private Date createTime;
    private int createBy;
    private Project parentProject;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrgEntity getOwner() {
        return owner;
    }

    public void setOwner(OrgEntity owner) {
        this.owner = owner;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Project getParentProject() {
        return parentProject;
    }

    public void setParentProject(Project parentProject) {
        this.parentProject = parentProject;
    }

    public int getMainPid() {
        return parentProject == null ? 0 : parentProject.getId();
    }

    public void setMainPid(int mainPid) {
        if (parentProject == null) {
            parentProject = new Project();
        }
        parentProject.setId(mainPid);
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
        return "SubProject{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", owner=" + owner +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", parentProject=" + parentProject +
                '}';
    }
}
