<%@ page contentType="text/html;charset=UTF-8"%><%--
--%><%@taglib prefix="t" tagdir="/WEB-INF/tags/templates" %><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><t:template title="Request a Free Consultation, Explore Custom Programming Solutions for Your Business"
                description="Dynamic Programming Solutions is happy to talk with you about your project. We provide free, no obligation estimates. Discover what a custom solution can do for your business."
                robots="index,follow">
    <jsp:attribute name="styles">
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
            <h1>Request a Free Consultation</h1>
        </div>

        <article class="container main">

            <div class="main-content">
                <p>Because we specialize in custom programming, every project we do is different and must be quoted individually. When we talk with you about the parameters for the project, we’ll be able to assess the scope of the project and the number of hours needed to complete it. We’ll give you a firm quote and timeline so you know exactly what to expect. </p>
                <p>For larger projects, we’ll set milestones that allow you to review our progress and provide feedback throughout the process. </p>
                <p>There’s no cost, no obligation to get a quote on your project. </p>
            </div>

            <div class="form-container">
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
            </div>
        </article>

    </jsp:body>
</t:template>
