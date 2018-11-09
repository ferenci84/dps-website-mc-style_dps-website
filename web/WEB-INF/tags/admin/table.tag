<%@tag pageEncoding="UTF-8"%><%--
--%><%@ attribute name="generate" required="false" fragment="false" type="java.lang.Boolean" %><%--
--%><%@ attribute name="buttons" required="false" fragment="false" type="java.lang.Boolean" %><%--
--%><%@ attribute name="colGroup" required="false" fragment="true" %><%--
--%><%@ attribute name="tableHeader" required="false" fragment="true" %><%--
--%><%@ attribute name="tableRowTpl" required="false" fragment="true" %><%--
--%>
    <div name="itemtable" class="section itemtable" data-controller="table-controller" data-resource="<%=jspContext.getAttribute("crudResource",PageContext.REQUEST_SCOPE)%>"><%--
        --%><jsp:doBody />
        <table name="table">
            <colgroup><%--
                --%><jsp:invoke fragment="colGroup"/>
            </colgroup>
            <thead>
            <tr><%--
                --%><jsp:invoke fragment="tableHeader"/>
                <% if (buttons != null && buttons) { %>
                <td>${localized.admin.tableHeadShow}</td>
                <td>${localized.admin.tableHeadModify}</td>
                <td>${localized.admin.tableHeadDelete}</td>
                <% } %>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${localized.admin.noData}</td>
                <td></td>
            </tr>
            </tbody>
        </table>
        <div name="pagination" class="pagination">
            <nav name="tblNav" class="tblNav">
                <a href="#" name="tablePrevPage" class="tablePrevPage">${localized.admin.paginationPrev}</a>
                <a href="#" name="tableNextPage" class="tableNextPage">${localized.admin.paginationNext}</a>
            </nav>
            <script name="tblPage" type="text/html">
                <a href="#" class="tablePage" data-page="\${page}">\${page}</a>
            </script>
        </div>
        <script name="tpl-row" type="text/html"><%--
            --%><jsp:invoke fragment="tableRowTpl" />
            <% if (buttons != null && buttons) { %>
            <td><a class="show-item button success" href="#" data-id="\${id}">${localized.admin.tableActionButtonShow}</a></td>
            <td><a class="modify-item button warning" href="#" data-id="\${id}">${localized.admin.tableActionButtonModify}</a></td>
            <td><a class="delete-item button danger" href="#" data-id="\${id}">${localized.admin.tableActionButtonDelete}</a></td>
            <% } %>
        </script>
    </div>