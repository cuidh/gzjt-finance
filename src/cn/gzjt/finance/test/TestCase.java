package cn.gzjt.finance.test;

import cn.gzjt.finance.domain.OrgEntity;
import cn.gzjt.finance.domain.Project;
import cn.gzjt.finance.domain.User;
import cn.gzjt.finance.mapper.ContractMapper;
import cn.gzjt.finance.mapper.ProjectMapper;
import cn.gzjt.finance.mapper.UserMapper;
import cn.gzjt.finance.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestCase {
//    @Test
//    public void testUserDao() {
//        System.out.println("testUserDao start");
//        LoginRegisterService service = new LoginRegisterServiceImpl();
//        Pair<String, User> result = service.login("root", "12345678");
//        System.out.println("testUserDao end->:" + result);
//    }
//
//    @Test
//    public void testOrgEntityDao() {
//        System.out.println("testOrgEntityDaoInsert start");
//        OrgEntity orgEntity = new OrgEntity();
//        orgEntity.setName("测试业主1");
//        orgEntity.setCreateBy(1);
//        OrgEntityDao orgEntityDao = new OrgEntityDaoImpl(JDBCUtils.getJdbcTemplate());
//        OrgEntity result = orgEntityDao.insert(orgEntity);
//        System.out.println("testOrgEntityDao insert end->:" + result);
//        OrgEntity newOrg = orgEntityDao.getByName("测试业主1");
//        System.out.println("testOrgEntityDao getByName end->:" + newOrg);
//    }
//
//    @Test
//    public void testProjectDao() {
//        System.out.println("testProjectDao start");
//        Project project = new Project();
//        project.setNumber("XG20191012");
//        project.setName("迎宾路高架");
//        OrgEntity orgEntity = new OrgEntity();
//        orgEntity.setId(5);
//        project.setOwnerObject(orgEntity);
//        project.setNote("道路改造项目");
//        project.setCreateBy(1);
//        ProjectDao projectDao = new ProjectDaoImpl(JDBCUtils.getJdbcTemplate());
//        Project nproject = projectDao.insert(project);
//        System.out.println("testProjectDao insert end->:" + nproject);
//        Project project1 = projectDao.getByName("迎宾路高架");
//        System.out.println("testProjectDao getByName end->:" + project1);
//        Project project2 = projectDao.getByNumber("XG20191012");
//        System.out.println("testProjectDao getByNumber end->:" + project2);
//        System.out.println("testProjectDao end->:");
//    }
//
//    @Test
//    public void testProjectDaoListAll() {
//        System.out.println("testProjectDaoListAll start");
//        ProjectDao projectDao = new ProjectDaoImpl(JDBCUtils.getJdbcTemplate());
//        System.out.println("testProjectDaoListAll end->:" + projectDao.getAllProjects());
//    }
//
//    @Test
//    public void testSubProjectDaoListAll() {
//        System.out.println("testSubProjectDaoListAll start");
//        SubProjectDao projectDao = new SubProjectDaoImpl(JDBCUtils.getJdbcTemplate());
//        System.out.println("testSubProjectDaoListAll end->:" + projectDao.getAllSubProjects());
//    }
//
//    @Test
//    public void testSubProjectDaoListByKey() {
//        System.out.println("testSubProjectDaoListByKey start");
//        SubProjectDao projectDao = new SubProjectDaoImpl(JDBCUtils.getJdbcTemplate());
//        System.out.println("testSubProjectDaoListByKey end->:" + projectDao.getSubProjectsByKey("路", ""));
//    }
//
//    @Test
//    public void testContractDaoInsert() throws ParseException {
//        ContractDao dao = new ContractDaoImpl(JDBCUtils.getJdbcTemplate());
//        Contract contract = new Contract();
//        contract.setNumber("JL20191017");
//        contract.setType("监理");
//        contract.setPrice(100000);
//        contract.setPayRate(60);
//        contract.setQualityDepositRate(10);
//        contract.setQualityPeriod(100);
//        contract.setCompletionDate(new java.util.Date());
//        contract.setQualityFixPay(1000);
//        contract.setAuditPrice(100000);
//
//        SubProject project = new SubProject();
//        project.setId(1);
//        contract.setProject(project);
//
//        OrgEntity orgEntityA = new OrgEntity();
//        orgEntityA.setId(18);
//        OrgEntity orgEntityB = new OrgEntity();
//        orgEntityB.setId(10);
//
//        contract.setPartyA(orgEntityA);
//        contract.setPartyB(orgEntityB);
//        contract.setSignDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-16"));
//        contract.setNote("单元测试数据");
//        contract.setCreateBy(1);
//
//        dao.insert(contract);
//    }
//
//    @Test
//    public void testContractDaoGetByName() {
//        System.out.println("testContractDaoGetByName start");
//        ContractDao dao = new ContractDaoImpl(JDBCUtils.getJdbcTemplate());
//        Contract contract = dao.getByNumber("JL20191017");
//        System.out.println("testContractDaoGetByName end， output：" + contract);
//    }
//
//    @Test
//    public void testContractDaoListAll() {
//
//    }

    @Test
    public void testContractMapper() {
        System.out.println("testContractMapper start");
        SqlSession session = MyBatisUtils.getSessionFactory().openSession();
        ContractMapper mapper = session.getMapper(ContractMapper.class);
//        Contract contract = mapper.getContract("JL20191017");
//        List<Contract> contractList = mapper.getAllContract(new RowBounds(0, Integer.MAX_VALUE));
//        List<Contract> contractList = mapper.getByLikeNumber("16");
//        List<Contract> contractList = mapper.getByLikeProjectName("林");
//        List<Contract> contractList = mapper.getByLikeNumberOrProjectName("z", "z");
        session.close();
        System.out.println("testContractMapper end:");
    }

    @Test
    public void testUserMapper() {
        System.out.println("testUserMapper start");
        SqlSession session = MyBatisUtils.getSessionFactory().openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.getByKey("root");
        session.close();
        System.out.println("testContractMapper end:" + user);
    }

    @Test
    public void testProjectMapper() {
        System.out.println("testProjectMapper start");
        SqlSession session = MyBatisUtils.getSessionFactory().openSession();
        ProjectMapper mapper = session.getMapper(ProjectMapper.class);

        Project project = new Project();
        project.setNumber("EQ20191013");
        project.setName("QAQAQAQAq");
        OrgEntity orgEntity = new OrgEntity();
        orgEntity.setId(17);
        project.setOwnerObject(orgEntity);
        project.setNote("QAQAQAQAeq");
        project.setCreateBy(1);

        mapper.insert(project);
//        List<Project> projectList = mapper.getAllProjects();
        List<Project> projectList = mapper.getByNameOrNumber("QA");
        List<Project> projectList1 = mapper.getByNameOrNumber("2019");
        Project project1 = mapper.getByName("景岭路");
        Project project2 = mapper.getByNumber("xy20119933");
        session.commit();
        session.close();
        System.out.println("testProjectMapper end:" + projectList.size());
        System.out.println("testProjectMapper end:" + projectList);
    }
}
