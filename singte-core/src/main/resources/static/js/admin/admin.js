$(function () {

    $(document).on("click", ".stLang", function () {
        let lang = $(this).data("lang");
        setCookie("lang", lang, 100);
        location.reload();
    })

    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toGMTString();
        document.cookie = cname + "=" + cvalue + "; " + expires + " ; path=/";
    }
});