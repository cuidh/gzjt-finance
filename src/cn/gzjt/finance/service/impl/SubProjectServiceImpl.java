package cn.gzjt.finance.service.impl;

import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.Project;
import cn.gzjt.finance.domain.SubProject;
import cn.gzjt.finance.mapper.ContractMapper;
import cn.gzjt.finance.mapper.ProjectMapper;
import cn.gzjt.finance.mapper.SubProjectMapper;
import cn.gzjt.finance.service.SubProjectService;
import cn.gzjt.finance.utils.MyBatisUtils;
import cn.gzjt.finance.utils.TextUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author jianwei.zhou
 * @date 2019/10/14 9:01
 */
public class SubProjectServiceImpl implements SubProjectService {

    @Override
    public String addSubProject(String number, String name, String mainProject, String note, int curUsrId) {
        //检查必要参数存在
        if (TextUtils.isEmpty(number) || TextUtils.isEmpty(name) || TextUtils.isEmpty(mainProject)) {
            return "必要参数缺失！";
        }
        SubProject oldSubProject;
        SqlSession session = MyBatisUtils.openSession();
        SubProjectMapper subProjectMapper = session.getMapper(SubProjectMapper.class);
        //检查编号存在
        oldSubProject = subProjectMapper.getByNumber(number);
        if (oldSubProject != null) {
            return "子项目编号已存在！不能重复";
        }
        //检查姓名存在
        oldSubProject = subProjectMapper.getByName(name);
        if (oldSubProject != null) {
            return "子项目名称已存在！不能重复";
        }
        //检查主项目存在
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
        Project parentProject = projectMapper.getByNumber(mainProject);
        if (parentProject == null) {
            parentProject = projectMapper.getByName(mainProject);
        }
        if (parentProject == null) {
            return "主项目不存在！请先创建主项目";
        }
        //创建新的子项目
        try {
            SubProject penddingInsert = new SubProject();
            penddingInsert.setName(name);
            penddingInsert.setNumber(number);
            penddingInsert.setNote(note);
            penddingInsert.setCreateBy(curUsrId);
            penddingInsert.setParentProject(parentProject);
            subProjectMapper.insert(penddingInsert);
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "创建失败！";
        }
    }

    @Override
    public PageBean<SubProject> findSubProjectByPage(int curPage, int rowsOfOnePage, String subKey, String projectKey) {
        SqlSession session = MyBatisUtils.openSession();
        SubProjectMapper mapper = session.getMapper(SubProjectMapper.class);

        PageBean<SubProject> pageBean = new PageBean<>();
        int totalCount;
        int totalPage;
        int start;
        List<SubProject> subProjects;
        if (TextUtils.isEmpty(subKey) && TextUtils.isEmpty(projectKey)) {
            totalCount = mapper.getAllSubProjectCount();
            totalPage = totalCount % rowsOfOnePage == 0 ? totalCount / rowsOfOnePage : totalCount / rowsOfOnePage + 1;
            //防止超出总数
            if (curPage > totalPage) {
                curPage = totalPage;
            }
            start = (curPage - 1) * rowsOfOnePage;
            subProjects = mapper.getAllSubProjects(new RowBounds(start, rowsOfOnePage));
        } else if (TextUtils.isEmpty(subKey)) {
            totalCount = mapper.getByMainKeyCount(projectKey);
            totalPage = totalCount % rowsOfOnePage == 0 ? totalCount / rowsOfOnePage : totalCount / rowsOfOnePage + 1;
            //防止超出总数
            if (curPage > totalPage) {
                curPage = totalPage;
            }
            start = (curPage - 1) * rowsOfOnePage;
            subProjects = mapper.getByMainKey(projectKey, new RowBounds(start, rowsOfOnePage));
        } else if (TextUtils.isEmpty(projectKey)) {
            totalCount = mapper.getBySubKeyCount(subKey);
            totalPage = totalCount % rowsOfOnePage == 0 ? totalCount / rowsOfOnePage : totalCount / rowsOfOnePage + 1;
            //防止超出总数
            if (curPage > totalPage) {
                curPage = totalPage;
            }
            start = (curPage - 1) * rowsOfOnePage;
            subProjects = mapper.getBySubKey(subKey, new RowBounds(start, rowsOfOnePage));
        } else {
            totalCount = mapper.getByKeyCount(subKey, projectKey);
            totalPage = totalCount % rowsOfOnePage == 0 ? totalCount / rowsOfOnePage : totalCount / rowsOfOnePage + 1;
            //防止超出总数
            if (curPage > totalPage) {
                curPage = totalPage;
            }
            start = (curPage - 1) * rowsOfOnePage;
            subProjects = mapper.getByKey(subKey, projectKey, new RowBounds(start, rowsOfOnePage));
        }

        pageBean.totalPage = totalPage;
        pageBean.curPage = curPage;
        pageBean.totalCount = totalCount;
        pageBean.list = subProjects;
        pageBean.rows = rowsOfOnePage;
        return pageBean;
    }

    @Override
    public String deleteSubProject(String number) {
        SqlSession session = MyBatisUtils.openSession();
        ContractMapper contractMapper = session.getMapper(ContractMapper.class);
        //todo jianwei.zhou 暂时使用模糊查询，需要严格规范编号长度否则会出现编号中嵌套编号导致bug
        int contractCount = contractMapper.getByLikeNumberOrProjectCount(number);
        if (contractCount > 0) {
            return "存在关联合同，无法删除！";
        }
        SubProjectMapper mapper = session.getMapper(SubProjectMapper.class);
        try {
            mapper.deleteByNumber(number);
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "删除失败！";
        } finally {
            session.close();
        }
    }
}
