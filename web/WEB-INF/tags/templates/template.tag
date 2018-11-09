<%@tag pageEncoding="UTF-8"%><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><%@taglib prefix="p" tagdir="/WEB-INF/tags/parts" %><%--
--%><%@ attribute name="title" required="true" %><%--
--%><%@ attribute name="index" required="false" type="java.lang.Boolean" %><%--
--%><%@ attribute name="keywords" required="false" %><%--
--%><%@ attribute name="development" required="false" %><%--
--%><%@ attribute name="description" required="false" %><%--
--%><%@ attribute name="scripts" required="false" fragment="true" %><%--
--%><%@ attribute name="scriptsBottom" required="false" fragment="true" %><%--
--%><%@ attribute name="styles" required="false" fragment="true" %><%--
--%><%@ attribute name="deferredStyles" required="false" fragment="true" %><%--
--%><!DOCTYPE html>
<html lang="${locale.tag}">
<head>

    <title>${title}</title>
    <meta charset="UTF-8">
    <meta name="keywords" lang="${locale.tag}" content="${keywords}" />
    <meta name="description" lang="${locale.tag}" content="${description}" />

    <meta name="robots" content="index,follow" />

    <meta name="viewport" content="width=device-width, initial-scale=1">

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
                    <p:frontend-menu />
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
                    <p:frontend-menu />
                </nav>
            </header>

        </c:otherwise>
    </c:choose>

    <jsp:doBody/>

    <div class="container can-stretch"></div>

    <footer>
        <nav>
            <c:choose>
                <c:when test="${locale.is('en')}">
                    <ul>
                        <li><a href="${root}">Home</a></li>
                        <li><a href="${root}en/services">Services</a></li>
                        <li><a href="${root}en/products">Products</a></li>
                        <li><a href="${root}en/terms">Terms and Conditions</a></li>
                        <li><a href="${root}en/privacy">Privacy Policy</a></li>
                        <li><a href="${root}en/contact">Contact</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul>
                        <li><a href="${root}hu">Főoldal</a></li>
                        <li><a href="${root}hu/szolgaltatasok">Szolgáltatások</a></li>
                        <li><a href="${root}hu/termekek">Termékek</a></li>
                        <li><a href="${root}hu/felhasznalasi-feltetelek">Feltételek</a></li>
                        <li><a href="${root}hu/adatkezeles">Adatkezelés</a></li>
                        <li><a href="${root}hu/kapcsolat">Kapcsolat</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>        </nav>
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
