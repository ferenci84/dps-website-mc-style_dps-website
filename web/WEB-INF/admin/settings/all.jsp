<%@ page contentType="text/html;charset=UTF-8"%><%--
--%><%@taglib prefix="p" tagdir="/WEB-INF/tags/parts" %><%--
--%><%@taglib prefix="t" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><t:admin title="Beállítások">

<jsp:attribute name="scripts">

</jsp:attribute>

<jsp:body>

    <header>
        <h1>Beállítások</h1>
    </header>

    <div class="section">
        <header>
            <h1>Általános</h1>
        </header>
        <form action="${root}admin/settings" method="post">
            <input type="hidden" name="settings" value="application-settings" />
            <label>locale</label><input type="text" name="locale" value="${applicationSettings.locale}" />
            <label>root</label><input type="text" name="root" value="${applicationSettings.root}" />
            <label></label>
            <button type="submit">Modify</button>
        </form>
    </div>

    <div class="section">
        <header>
            <h1>Email</h1>
        </header>


        <form action="${root}admin/settings" method="post">
            <input type="hidden" name="settings" value="email-settings" />
            <label>host</label><input type="text" name="host" value="${mailSettings.host}" />
            <label>port</label><input type="text" name="port" value="${mailSettings.port}" />
            <label>username</label><input type="text" name="username" value="${mailSettings.username}" />
            <label>password</label><input type="text" name="password" value="${mailSettings.password}" />
            <label>contactEmail</label><input type="text" name="contactEmail" value="${mailSettings.contactEmail}" />
            <label>systemEmail</label><input type="text" name="systemEmail" value="${mailSettings.systemEmail}" />
            <label>recapatchaSecret</label><input type="text" name="recapatchaSecret" value="${mailSettings.recapatchaSecret}" />
            <label></label>
            <button type="submit">Modify</button>
        </form>
    </div>

</jsp:body>

</t:admin>