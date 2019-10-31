package cn.gzjt.finance.service.impl;

import cn.gzjt.finance.domain.OrgEntity;
import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.Project;
import cn.gzjt.finance.mapper.OrgEntityMapper;
import cn.gzjt.finance.mapper.ProjectMapper;
import cn.gzjt.finance.mapper.SubProjectMapper;
import cn.gzjt.finance.service.ProjectService;
import cn.gzjt.finance.utils.MyBatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    @Override
    public String addProject(Project project) {
        SqlSession session = MyBatisUtils.openSession();
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
        if (null != projectMapper.getByNumber(project.getNumber())) {
            return "项目编号已存在！";
        }
        if (null != projectMapper.getByName(project.getName())) {
            return "项目名称已存在！";
        }
        //创建组织实体
        OrgEntity owner = project.getOwnerObject();
        OrgEntityMapper orgEntityMapper = session.getMapper(OrgEntityMapper.class);
        OrgEntity oOrg = orgEntityMapper.getByName(owner.getName());
        try {
            if (oOrg == null) {
                owner.setCreateBy(project.getCreateBy());
                orgEntityMapper.insert(owner);
            } else {
                owner = oOrg;
            }
            project.setOwnerObject(owner);
            projectMapper.insert(project);
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "创建失败";
        } finally {
            session.close();
        }
    }

    @Override
    public List<Project> getAllProjects() {
        SqlSession session = MyBatisUtils.openSession();
        List<Project> allProjects = session.getMapper(ProjectMapper.class).getAllProjects();
        session.close();
        return allProjects;
    }

    @Override
    public List<Project> getProjectsByFilter(String key) {
        SqlSession session = MyBatisUtils.openSession();
        ProjectMapper mapper = session.getMapper(ProjectMapper.class);
        List<Project> projects = mapper.getByNameOrNumber(key);
        session.close();
        return projects;
    }

    @Override
    public PageBean<Project> findProjectByPage(int curPage, int rowsOfOnePage) {
        SqlSession session = MyBatisUtils.openSession();
        ProjectMapper mapper = session.getMapper(ProjectMapper.class);
        int totalCount = mapper.getTotalCount();
        int totalPage = totalCount % rowsOfOnePage == 0 ? totalCount / rowsOfOnePage : totalCount / rowsOfOnePage + 1;
        //防止超出总数
        if (curPage > totalPage) {
            curPage = totalPage;
        }
        int start = (curPage - 1) * rowsOfOnePage;
        List<Project> projectList = mapper.getProjectByPage(new RowBounds(start, rowsOfOnePage));
        PageBean pageBean = new PageBean();
        pageBean.list = projectList;
        pageBean.curPage = curPage;
        pageBean.totalPage = totalPage;
        pageBean.totalCount = totalCount;
        pageBean.rows = rowsOfOnePage;
        session.close();
        return pageBean;
    }

    @Override
    public PageBean<Project> findProjectByPage(int curPage, int rowsOfOnePage, String key) {
        SqlSession session = MyBatisUtils.openSession();
        ProjectMapper mapper = session.getMapper(ProjectMapper.class);
        int totalCount = mapper.getLikeTotalCount(key);
        int totalPage = totalCount % rowsOfOnePage == 0 ? totalCount / rowsOfOnePage : totalCount / rowsOfOnePage + 1;
        //防止超出总数
        if (curPage > totalPage) {
            curPage = totalPage;
        }
        int start = (curPage - 1) * rowsOfOnePage;
        List<Project> projectList = mapper.getProjectByPageAndKey(key, new RowBounds(start, rowsOfOnePage));
        PageBean pageBean = new PageBean();
        pageBean.list = projectList;
        pageBean.curPage = curPage;
        pageBean.totalPage = totalPage;
        pageBean.totalCount = totalCount;
        pageBean.rows = rowsOfOnePage;
        session.close();
        return pageBean;
    }

    @Override
    public String deleteProject(String number) {
        SqlSession session = MyBatisUtils.openSession();
        SubProjectMapper subProjectMapper = session.getMapper(SubProjectMapper.class);
        int subProjectCount = subProjectMapper.getByMainKeyCount(number);
        if (subProjectCount > 0) {
            return "存在附属子项目, 无法删除!";
        }
        ProjectMapper mapper = session.getMapper(ProjectMapper.class);
        try {
            mapper.deleteByNumber(number);
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "删除失败";
        } finally {
            session.close();
        }
    }
}
