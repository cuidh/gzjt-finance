<%--
  Created by IntelliJ IDEA.
  User: jianwei.zhou
  Date: 2019/10/18
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:if test="${pageBean.curPage != 1}">
            <li>
                <a href="${pageHrefPref.concat(pageBean.curPage - 1)}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pageBean.curPage == 1}">
            <li class="disabled">
                        <span aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </span>
            </li>
        </c:if>
        <c:forEach items="${pageIndex}" var="p" varStatus="s">
            <c:if test="${pageBean.curPage != p}">
                <li><a href="${pageHrefPref.concat(p)}">${p}</a></li>
            </c:if>
            <c:if test="${pageBean.curPage == p}">
                <li class="active"><span>${p}</span></li>
            </c:if>
        </c:forEach>
        <c:if test="${pageBean.curPage != pageBean.totalPage}">
            <li>
                <a href="${pageHrefPref.concat(pageBean.curPage + 1)}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pageBean.curPage == pageBean.totalPage}">
            <li class="disabled">
                        <span aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </span>
            </li>
        </c:if>
    </ul>
</nav>
