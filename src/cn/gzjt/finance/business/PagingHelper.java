package cn.gzjt.finance.business;

import cn.gzjt.finance.domain.PageBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jianwei.zhou
 * @date 2019/10/18 14:50
 */
public class PagingHelper {

    //分页导航行长度
    private static final int pageNavCount = 5;

    public static void out(HttpServletRequest req, PageBean pageBean, String pageServletPath, String paramPref) {
        //添加分页主键需要的
        List<Integer> pageIndexs = new ArrayList<>();
        int startIndex;
        if (pageBean.totalPage <= 5) {
            startIndex = 1;
        } else {
            //分页列表其实页数，保证总是存在5页
            startIndex = pageBean.curPage - 2;
            if (startIndex < 1) {
                startIndex = 1;
            } else if (startIndex + 4 > pageBean.totalPage) {
                startIndex = pageBean.totalPage - 4;
            }
        }
        int end = startIndex + 4;
        while (startIndex <= pageBean.totalPage && startIndex <= end) {
            pageIndexs.add(startIndex);
            startIndex++;
        }
        String pageHrefBase = req.getServletContext().getContextPath() + pageServletPath;
        String pageHrefPref = pageHrefBase + paramPref;
        req.setAttribute("pageHrefBase", pageHrefBase);
        req.setAttribute("pageHrefPref", pageHrefPref);
        req.setAttribute("pageBean", pageBean);
        req.setAttribute("pageIndex", pageIndexs);
    }
}
