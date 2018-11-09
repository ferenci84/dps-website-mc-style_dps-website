<%@ page contentType="text/html;charset=UTF-8"%><%--
--%><%@taglib prefix="p" tagdir="/WEB-INF/tags/parts" %><%--
--%><%@taglib prefix="t" tagdir="/WEB-INF/tags/templates" %><%--
--%><%@taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %><%--
--%><t:admin title="Admin felhasználók"><jsp:attribute name="scripts">
    <script src="${root}scripts/table-controller.js"></script>
    <script src="${root}scripts/crud-controller.js"></script>
</jsp:attribute>
<jsp:body>

<admin:crud
        controller="crud-controller"
        resource="${root}admin/users/rest"
        entityClass="dps.webapplication.entities.ApplicationUser">

    <jsp:attribute name="form">
        <label>Felhasznalonev</label>
        <input type="text" name="username" />
        <label>Jelszo</label>
        <input type="password" name="password" />
    </jsp:attribute>

    <jsp:body>

        <header>
            <h1>Admin felhasználók</h1>
        </header>

        <div name="messages" class="messages"></div>
        <script name="tpl-error-message" type="text/html">
            <div class="message error">\${message}</div>
        </script>
        <script name="tpl-success-message" type="text/html">
            <div class="message success">\${message}</div>
        </script>

        <admin:create>
            <h1>${localized.admin.sectionHeadCreate}</h1>
        </admin:create>

        <admin:edit>
            <h1>${localized.admin.sectionHeadModify}</h1>
        </admin:edit>

        <admin:table buttons="true">
            <jsp:attribute name="tableHeader">
                <td>Felhasznalonev</td>
            </jsp:attribute>
            <jsp:attribute name="tableRowTpl">
                <td>\${username}</td>
            </jsp:attribute>
            <jsp:body>
                <button class="new-item">${localized.admin.createButton}</button>
            </jsp:body>
        </admin:table>

        <admin:show>
            <label>Felhasznalonev:</label>
            <p>\${username}</p>
        </admin:show>

    </jsp:body>

</admin:crud>

</jsp:body>
</t:admin>