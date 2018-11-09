<%@ page contentType="text/html;charset=UTF-8"%><%--
--%><%@taglib prefix="t" tagdir="/WEB-INF/tags/templates" %><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><t:template title="Dynamic Programming Solutions - Web Development"
                description=""
                index="true"
                keywords="">
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
        <script src='https://www.google.com/recaptcha/api.js?render=onload&hl=hu'></script>
    </jsp:attribute>
    <jsp:body>

        <div class="container page-head">
            <h1>Dynamic Programming Solutions</h1>
        </div>

        <article class="container main">
            <div class="red-box">
                <p>Hi user!</p>
                <p>Welcome to the <span class="explanation">backend<span aria-hidden="true" class="explanation-content">The end of something which is furthest from the front or the working end. In computing: a subordinate processor or program, not directly accessed by the user.</span></span>! We are NOT human beings. We are web developers who feel at home in this strange world.</p>
                <p>We provide <em>custom web development for small companies</em>. We simplify problems and solve anything.</p>
            </div>

            <div class="main-content">
                <h2>Our solutions for you</h2>
                <ul>
                    <li><em>Automatisms</em> created for your company that provides fluent interaction
                        with your customers.</li>

                    <li><em>Web applications</em> specialized for your service created for your own need.</li>

                    <li><em>Website development</em> and renewing old ones. We can create web design for human beings. More about the design services at our other website, <a href="https://artflowdesign.hu" target="_blank">ArtFlow Design</a></li>

                    <li><em>Your own web server</em> built and managed based on your needs that will give you the fastest
                        possible website with independence, flexibility and security.</li>
                </ul>

                <h2>Technologies we use</h2>

                <p>We use pure languages, and those frameworks that are highly flexible. We know the core language and
                    do not depend on the frameworks, achieving high flexibility.</p>

                <p><span class="log warning">The following content may contain elements that are not suitable for some audiences.</span></p>

                <ul>

                    <li>Backend: Java EE or PHP with laravel framework.</li>

                    <li>Frontend: pure javascript and CSS</li>

                    <li>Servers: Ubuntu linux server on Amazon AWS EC2</li>

                    <li>Database: MySQL and PostgreSQL</li>

                </ul>

                <h2>Everything based on your specific needs</h2>

                <p>We do not use inflexible prebuilt systems. Instead, we do everything based on your own needs in KISS
                    way: keep it stupid simple. You get everything you need, and nothing that you don't.</p>

                <h2>We are a small team</h2>

                <p>Your project will not be lost among 3rd class programmers in a big company. We have just a few team
                    members with complementing skills, who master their own field, thus your project will be handled
                    firsthand by the top developers.</p>

                <h2>Get a quote</h2>

                <p>Tell us what you need. We will get back to you with specific questions and will provide pricing for
                    the project. You will get an answer within 24 hours during business days.</p>

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
