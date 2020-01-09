<%@ page contentType="text/html;charset=UTF-8"%><%--
--%><%@taglib prefix="t" tagdir="/WEB-INF/tags/templates" %><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><t:template title="Dynamic Programming Solutions"
                description="We design custom programming solutions that enhance user experiences, improve productivity and can help you grow your business."
                robots="index,follow"
                keywords=""
                index="true">
    <jsp:attribute name="styles">
    </jsp:attribute>
    <jsp:attribute name="deferredStyles">
    </jsp:attribute>
    <jsp:attribute name="scripts">
    </jsp:attribute>
    <jsp:attribute name="scriptsBottom">
        <script src="${root}scripts/jquery.min.js"></script>
        <script src="${root}scripts/jquery-validation/jquery.validate.min.js"></script>
        <script src="${root}scripts/jquery-validation/localization/messages_hu.min.js"></script>
        <script src="${root}scripts/contactform.js"></script>
        <script src='https://www.google.com/recaptcha/api.js?render=onload&hl=${locale.tag}'></script>
    </jsp:attribute>
    <jsp:body>

        <div class="container page-head">
            <h1>Dynamic Programming Solutions</h1>
            <p class="tagline tagline--green">We simplify business processes with tailor-made programming solutions.</p>
        </div>

        <article class="container main">

            <div class="red-box">
                <p><em>Attention! Do not attempt to reload this page or adjust your monitor.</em> This web page is not broken. It is exactly as it should be.</p>
                <p>While it may look ugly to you, to the programmers at Dynamic Programming Solutions, it is a thing of beauty.</p>
                <p>We’re well accustomed to creating lines of code and managing server via command-line terminal that look just like this “unattractive” page. Visitors never see our work, yet it enables the functionality of the websites they use. </p>
            </div>

            <div class="main-content">

                <h2>What We Do</h2>

                <ul>
                    <li>Could the customer experience on your site be improved?</li>

                    <li>Does your staff need a better administration system?</li>

                    <li>Are there repetitive tasks you’d like to automate?</li>
                </ul>

                <p>Dynamic Programming Solutions solves these types of problems and more. We do custom programming that
                    enhances user experiences, improves productivity, increases functionality and helps you grow your business.
                    <a href="${root}${pages.getPage("services","en").link}">learn more</a></p>

                <h2>Who We Are</h2>

                <p>We’ve been designing custom programming solutions for over a decade. We’re adept at working with
                    small to mid-sized businesses in diverse industries, helping them to succeed with technology that
                    supports their critical workflows.
                    <a href="${root}${pages.getPage("about","en").link}">learn more</a></p>

                <h2>How We Work</h2>

                <p>Our development process begins with listening. We learn about your business and gain an understanding
                    of your needs. Then we develop a cost-effective solution that achieves your objective and is
                    scalable as your business grows.
                    <a href="${root}${pages.getPage("process","en").link}">learn more</a></p>

                <h2>Let’s Talk</h2>

                <p>We offer a free, no-obligation consultation. Contact us to discuss how we can help you optimize your business processes.
                    <a href="${root}${pages.getPage("contact","en").link}">contact us</a></p>

            </div>


<%--            <div class="form-container">
                <header><h3>Get A Quote!</h3></header>
                <form id="contactform" action="${root}contact/send" method="post">
                    <div class="form-section">
                        <div class="text-input-container">
                            <label for="c_name">Your name:</label>
                            <input id="c_name" type="text" name="name" placeholder="Your full name" />
                        </div>
                        <div class="text-input-container">
                            <label for="c_email">Your email:</label>
                            <input id="c_email" type="text" name="email" placeholder="Your email address" />
                        </div>
                        <div class="text-input-container">
                            <label for="c_subject">Subject:</label>
                            <input id="c_subject" type="text" name="subject" placeholder="The subject of your message" />
                        </div>
                        <div class="textarea-container">
                            <label for="c_message">Message:</label>
                            <textarea id="c_message" name="message" rows="8" placeholder="Description of your project"></textarea>
                        </div>
                    </div>
                    <div class="form-section">
                        <div id="g-recaptcha" class="g-recaptcha" data-sitekey="6LfgWW0UAAAAAPphowcgKAGlZFMY007YZiYNj0xh" data-callback="gRecapatchaVerified"></div>
                    </div>
                    <div class="form-section">
                        <div class="button-container">
                            <button id="contactform_sendbutton" type="submit"><span class="hotkey">S</span>ubmit</button>
                        </div>
                    </div>
                </form>
            </div>--%>

        </article>
    </jsp:body>
</t:template>
