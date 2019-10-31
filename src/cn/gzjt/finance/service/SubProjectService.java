package cn.gzjt.finance.service;

import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.SubProject;

/**
 * @author jianwei.zhou
 * @date 2019/10/14 9:00
 */
public interface SubProjectService {

    String addSubProject(String number, String name, String mainProject, String note, int curUsrId);

    /**
     * 通过主项目或者子项目帅选
     *
     * @param subKey     子项目编号或者名称
     * @param projectKey 主项目编号或者名称
     * @return 列表
     */
    PageBean<SubProject> findSubProjectByPage(int curPage, int rowsOfOnePage, String subKey, String projectKey);

    String deleteSubProject(String number);

}
