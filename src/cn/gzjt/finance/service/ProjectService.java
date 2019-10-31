package cn.gzjt.finance.service;

import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.Project;

import java.util.List;

public interface ProjectService {

    /**
     * 添加工程
     *
     * @param project
     * @return
     */
    String addProject(Project project);

    /**
     * 获取所有主项目，暂时先不做分页
     *
     * @param number
     * @param name
     * @return
     */
    List<Project> getAllProjects();

    List<Project> getProjectsByFilter(String key);

    PageBean<Project> findProjectByPage(int curPage, int rowsOfOnePage);

    PageBean<Project> findProjectByPage(int curPage, int rowsOfOnePage, String key);

    String deleteProject(String number);
}

