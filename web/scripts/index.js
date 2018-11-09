document.addEventListener("DOMContentLoaded",function() {
    console.log("content loaded")

    var readingSpead = 200;
    var charPerWord = 5.79;
    var charPerSec = readingSpead * charPerWord / 60;

    var els = document.getElementsByClassName("log");
    var t = new Date();
    for (i in els)  {
        var el = els[i];
        if (el.dataset)
            el.dataset.time = ('0'+t.getHours()).slice(-2)+":"+('0'+t.getMinutes()).slice(-2)+":"+('0'+t.getSeconds()).slice(-2)+","+('00'+t.getMilliseconds()).slice(-3);
        if (el.innerText) {
            var chars = el.innerText.length;
            var addMs = chars * (1/charPerSec * 1000);
            t.setMilliseconds(t.getMilliseconds() + addMs);
        }
    }

})
document.addEventListener("DOMContentLoaded",function() {

    var elems = document.querySelectorAll("div[data-size]");
    elems.forEach(function(elem) {
        var link = elem.parentNode;
        var href = link.getAttribute("href");
        if (href && href.substr(0,1) == "#") {
            var id = href.substr(1);
            var section = document.getElementById(id);
            var inner = section.innerHTML;
            elem.dataset.size = inner.length;
        }
    })
})

document.addEventListener("DOMContentLoaded",function() {
    var links = document.querySelectorAll("a.page-selector[href]");
    links.forEach(function(link) {
        var href = link.getAttribute("href");
        if (href.charAt(0) == '#') {
            var id = href.substr(1);
            var element = document.getElementById(id);
            if (!link.classList.contains("selected")) {
                element.classList.add("hidden");
            } else {
                element.classList.remove("hidden");
            }
            link.addEventListener("click",pageSelectorClick);
        }
    });
    function pageSelectorClick(event) {
        event.preventDefault();
        var thisLink = event.currentTarget;
        var parent = thisLink;
        while(parent.parentNode) {
            parent = parent.parentNode;
            if (parent.classList.contains("page-list")) break;
        }
        var allLinks = parent.querySelectorAll("a");

        allLinks.forEach(function(link) {
            var href = link.getAttribute("href");
            if (href && href.charAt(0) == '#') {
                var id = href.substr(1);
                var element = document.getElementById(id);
                link.classList.remove("selected");
                element.classList.add("hidden");
            }
        })

        allLinks.forEach(function(link) {
            var href = link.getAttribute("href");
            if (href && href.charAt(0) == '#') {
                var id = href.substr(1);
                var element = document.getElementById(id);
                if (link === thisLink) {
                    link.classList.add("selected");
                    element.classList.remove("hidden");
                }
            }
        })


    }
});

document.addEventListener("DOMContentLoaded",function() {
    var links = document.querySelectorAll("a.open-modal[href]");
    links.forEach(function(link){
        var href = link.getAttribute("href");
        var id = href.substr(1);
        var element = document.getElementById(id);
        link.addEventListener("click",function(event) {
            event.preventDefault();
            element.classList.remove("hidden");
        })
        element.addEventListener("click",function(event) {
            if (event.target === element) {
                element.classList.add("hidden");
            }
        })
    })
});

document.addEventListener("DOMContentLoaded",function() {
    document.getElementById("toggle-mobile-menu").addEventListener("click",function() {
        document.getElementById("mobile-menu").classList.toggle("shown");

    });
    var toggleMenu = document.getElementById("toggle-mobile-menu");
    var menu = document.getElementById("mobile-menu");
    dropdown(menu,toggleMenu,menu,["focus","click"],function(elem) {
        if (elem.classList.contains("shown")) {
            elem.querySelectorAll("nav").forEach(function(subnav) {
                if (typeof subnav._eventtypes !== "undefined") {
                    subnav._eventtypes = ["focus"]
                }
            });
        } else {
            var subnav = elem.querySelectorAll("nav");
            if (typeof subnav._eventtypes !== "undefined") {
                subnav._eventtypes = ["hover","focus"];
            }
        }
    });
});

document.addEventListener("DOMContentLoaded",function() {
    var h2s = document.querySelectorAll("h2");
    h2s.forEach(function(h2) {
        var wrapper = document.createElement("div");
        wrapper.classList.add("h2-wrapper-block");
        h2.parentNode.insertBefore(wrapper,h2);
        var h2wrapper = document.createElement("div");
        h2wrapper.classList.add("h2-wrapper");

        var h2innerwrapper = document.createElement("div");
        h2innerwrapper.appendChild(document.createElement("div"));
        h2innerwrapper.appendChild(h2);
        h2innerwrapper.appendChild(document.createElement("div"));

        wrapper.appendChild(h2wrapper);
        h2wrapper.appendChild(document.createElement("div"));
        h2wrapper.children[0].appendChild(document.createElement("div"));
        h2wrapper.appendChild(h2innerwrapper);
        h2wrapper.appendChild(document.createElement("div"));
        h2wrapper.children[2].appendChild(document.createElement("div"));
    })
});

document.addEventListener("DOMContentLoaded",function() {
    document.querySelectorAll(".container").forEach(function(container) {
        container.querySelectorAll(".explanation").forEach(function(expl) {
            expl.addEventListener("mouseover",function (event) {
                    var content = expl.querySelector(".explanation-content");
                    content.style.left = 0;
                    var contentOffset = content.getBoundingClientRect();
                    var containerOffset = container.getBoundingClientRect();
                    var delta = contentOffset.right - containerOffset.right;
                    if (delta > 0) {
                        content.style.left = "-" + delta + "px";
                    }
            })
        });
    });
});


document.addEventListener("DOMContentLoaded",function() {
    document.querySelectorAll("nav nav").forEach(function(elem) {
        var parent = elem.parentElement;
        var activator = elem.parentElement.querySelector("a");
        dropdown(elem,activator,parent,["focus","hover"]);
    })
})

window.addEventListener("load",function() {
    console.log("page loaded")
})


function dropdown(elem,activator,parent,eventtypes,callback)
{
    elem._eventtypes = eventtypes;
    activator.addEventListener("click",function(event) {
        event.preventDefault();
        toggle("click");
    });
    document.body.addEventListener("click",function(event) {
        deactivate("click");
    })
    activator.addEventListener("focusin",focusin);
    elem.querySelectorAll("a").forEach(function(e) { e.addEventListener("focusin",focusin); });
    elem.querySelectorAll("a").forEach(function(e) { e.addEventListener("focusout",focusout); });
    activator.addEventListener("focusout",focusout);
    elem.querySelectorAll("a").forEach(function(e) { e.addEventListener("focusout",focusout); });
    parent.addEventListener("mouseover",function() {activate("hover");});
    parent.addEventListener("mouseout",function() {deactivate("hover");});
    function focusin(event) {
        activate("focus");
        event.target.classList.add("focus");
    }
    function focusout(event) {
        event.target.classList.remove("focus");
        var focus = false;
        window.setTimeout(function() {
            parent.querySelectorAll("a").forEach(function(e) {
                if (e.classList.contains("focus")) {
                    focus = true;
                }
            })
            if (activator.classList.contains("focus")) {
                focus = true;
            }
            if (!focus) {
                deactivate("focus");
            }
        },100);
    }
    function activate(eventtype) {
        //console.log("activate: ",eventtype);
        elem.classList.add(eventtype);
        checkstate();
    }
    function deactivate(eventtype) {
        //console.log("deactivate: ",eventtype);
        elem.classList.remove(eventtype);
        checkstate();
    }
    function toggle(eventtype) {
        //console.log("toggle: ",eventtype);
        elem.classList.toggle(eventtype);
        checkstate();
    }
    function checkstate() {
        var active = false;
        elem._eventtypes.forEach(function(e) {
            if (elem.classList.contains(e)) {
                active = true;
            }
        })
        if (active) {
            elem.classList.add("shown");
            if (typeof callback !== "undefined") callback(elem);
        } else {
            elem.classList.remove("shown");
            if (typeof callback !== "undefined") callback(elem);
        }
    }
}