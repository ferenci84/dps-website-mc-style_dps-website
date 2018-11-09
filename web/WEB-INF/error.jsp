<%@ page contentType="text/html;charset=UTF-8"%><%--
--%><%@taglib prefix="t" tagdir="/WEB-INF/tags/templates" %><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><%@taglib prefix="md" uri="http://dynamicprogrammingsolutions.com/tags/mdstring" %><%--
--%><t:template title="Sample Page" keywords="development, programming">
<jsp:attribute name="scripts">
    <script src="${root}scripts/index-controller.js"></script>
</jsp:attribute>
    <jsp:body>
        <h1>${statusCode} ${reasonPhrase}</h1>
    </jsp:body>
</t:template>