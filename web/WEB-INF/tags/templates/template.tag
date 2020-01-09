<%@tag pageEncoding="UTF-8"%><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><%@taglib prefix="p" tagdir="/WEB-INF/tags/parts" %><%--
--%><%@ attribute name="title" required="true" %><%--
--%><%@ attribute name="keywords" required="false" %><%--
--%><%@ attribute name="development" required="false" %><%--
--%><%@ attribute name="description" required="false" %><%--
--%><%@ attribute name="scripts" required="false" fragment="true" %><%--
--%><%@ attribute name="scriptsBottom" required="false" fragment="true" %><%--
--%><%@ attribute name="styles" required="false" fragment="true" %><%--
--%><%@ attribute name="deferredStyles" required="false" fragment="true" %><%--
--%><%@ attribute name="robots" required="false" fragment="false" %><%--
--%><%@ attribute name="index" required="false" fragment="false" %><%--
--%><!DOCTYPE html>
<html lang="${locale.tag}">
<head>

    <title>${title}</title>
    <meta charset="UTF-8">
    <meta name="keywords" lang="${locale.tag}" content="${keywords}" />
    <meta name="description" lang="${locale.tag}" content="${description}" />

    <c:if test="${not empty robots}">
        <meta name="robots" content="${robots}" />
    </c:if>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/css?family=Oxygen+Mono|Share+Tech+Mono|Ubuntu+Mono:400,400i,700,700i&display=swap" rel="stylesheet" />

    <link rel="stylesheet" href="${root}styles${dev?"-dev":(development?"-dev":"")}/reset.css" />
    <link rel="stylesheet" href="${root}styles${dev?"-dev":(development?"-dev":"")}/typography.css" />
    <link rel="stylesheet" href="${root}styles${dev?"-dev":(development?"-dev":"")}/headings.css" />
    <link rel="stylesheet" href="${root}styles${dev?"-dev":(development?"-dev":"")}/forms.css" />
    <link rel="stylesheet" href="${root}styles${dev?"-dev":(development?"-dev":"")}/colors.css" />
    <link rel="stylesheet" href="${root}styles${dev?"-dev":(development?"-dev":"")}/box1.css" />
    <link rel="stylesheet" href="${root}styles${dev?"-dev":(development?"-dev":"")}/pagewidth1.css" />
    <link rel="stylesheet" href="${root}styles${dev?"-dev":(development?"-dev":"")}/style2.css" />

    <jsp:invoke fragment="styles" />

    <script src="${root}scripts/index.js"></script>

    <c:if test="${not dev}">
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-129033182-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag(){dataLayer.push(arguments);}
            gtag('js', new Date());

            gtag('config', 'UA-129033182-1');
        </script>
    </c:if>

    <jsp:invoke fragment="scripts" />
</head>
<body>

<div class="main-container">

    <c:choose>
        <c:when test="${index}">

            <header>
                <nav class="language-selector">
                    <ul>
                        <li><a href="${root}">EN</a></li>
                        <li><a href="${root}hu">HU</a></li>
                    </ul>
                    <div id="toggle-mobile-menu" tabindex="0">Menu</div>
                </nav>
                <nav id="mobile-menu">
                    <ul>
                        <c:forEach items="${pages.getMenu('top',locale.tag).items}" var="item">
                            <li><a href="${root}${item.page.link}">${item.name}</a></li>
                        </c:forEach>
                    </ul>

                </nav>
            </header>

        </c:when>
        <c:otherwise>

            <header>
                <div class="header-logo">
                    <h3>Dynamic Programming Solutions</h3>
                    <div id="toggle-mobile-menu" tabindex="0">Menu</div>
                </div>
                <nav id="mobile-menu">
                    <ul>
                        <c:forEach items="${pages.getMenu('top',locale.tag).items}" var="item">
                            <li><a href="${root}${item.page.link}">${item.name}</a></li>
                        </c:forEach>
                    </ul>

                </nav>
            </header>

        </c:otherwise>
    </c:choose>

    <jsp:doBody/>

    <div class="container can-stretch"></div>

    <footer>
        <nav>
            <ul>
                <c:forEach items="${pages.getMenu('bottom',locale.tag).items}" var="item">
                    <li><a href="${root}${item.page.link}">${item.name}</a></li>
                </c:forEach>
            </ul>
        </nav>
    </footer>

</div>

<noscript id="deferred-styles">
    <jsp:invoke fragment="deferredStyles" />
</noscript>
<script>
    var loadDeferredStyles = function() {
        var addStylesNode = document.getElementById("deferred-styles");
        var replacement = document.createElement("div");
        replacement.innerHTML = addStylesNode.textContent;
        document.body.appendChild(replacement)
        addStylesNode.parentElement.removeChild(addStylesNode);
    };
    var raf = window.requestAnimationFrame || window.mozRequestAnimationFrame ||
        window.webkitRequestAnimationFrame || window.msRequestAnimationFrame;
    if (raf) raf(function() { window.setTimeout(loadDeferredStyles, 0); });
    else window.addEventListener('load', loadDeferredStyles);
</script>

<jsp:invoke fragment="scriptsBottom" />

</body>
</html>
