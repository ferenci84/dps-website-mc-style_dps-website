$(document).ready(function() {

    console.log("contactform");

    $("#contactform").validate({
        rules: {
            name: {
                required: true,
                minlength: 2
            },
            email: {
                required: true,
                email: true
            },
            message: {
                required: true,
                minlength: 5
            }
        },
        messages: {
            name: {
                required: "Kérjük, adja meg a nevét!",
                minlength: "Legalább 2 karaktert írjon!",
            },
            email: {
                required: "Kérjük, adja meg az emailcímét!",
                email: "Érvényes emailcímet legyen szíves megadni!"
            },
            message: {
                required: "Kérjük, írja meg az üzenetet!",
                minlength: "Legalább 5 karaktert írjon!"
            }
        },
        submitHandler: function(form) {

            var response = grecaptcha.getResponse();

            if (response.length == 0) {
                var errorLabel = document.createElement("label");
                errorLabel.innerText = "Kérjük, erősítse meg, hogy nem robot!";
                errorLabel.classList.add("error");
                errorLabel.setAttribute("id","g-recaptcha-error");
                errorLabel.setAttribute("for","g-recaptcha");
                $("#g-recaptcha").after(errorLabel);
                return;
            }

            gtag_report_conversion();

            form.submit();

        }
    });

});

function gtag_report_conversion(url) {
    var callback = function () {
        if (typeof(url) != 'undefined') {
            window.location = url;
        }
    };
    gtag('event', 'conversion', {
        'send_to': 'AW-1051744850/T8stCKrxjIgBENK0wfUD',
        'event_callback': callback
    });
    return false;
}

function gRecapatchaVerified() {
    $("#g-recaptcha-error").remove();
}