<%@tag pageEncoding="UTF-8"%><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul>
    <c:forEach items="${pages.getMenu('top',locale.tag).items}" var="item">
        <li><a href="${root}${item.page.link}">${item.name}</a></li>
    </c:forEach>
</ul>
