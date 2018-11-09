<%@ page contentType="text/html;charset=UTF-8"%><%@taglib prefix="t" tagdir="/WEB-INF/tags/templates" %><%--
--%><t:admin title="Bejelentkezés" disableMenu="true"><%--
--%>
    <div class="login section">
      <div>
        <h1>${localized.admin.loginHead}</h1>
        <form action="${root}admin/auth/login" method="post">
            <label>${localized.admin.loginLabelUsername}</label>
            <input type="text" name="username" />
            <label>${localized.admin.loginLabelPassword}</label>
            <input type="password" name="password" />
            <button type="submit">${localized.admin.loginButtonLogin}</button>
        </form>
      </div>
    </div>
<%--
--%></t:admin>