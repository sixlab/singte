$(function () {
    function loadCaptcha() {
        let key = Date.now().toString(36) + Math.random().toString(36);

        $("#captchaKey").val(key);
        $("#captchaImg").attr("src", "/captcha?width=100&height=41&key=" + key);
    }

    loadCaptcha();
    $(".reloadImg").click(loadCaptcha);

    $("#loginBtn").click(function () {
        $.ajax({
            url: "/admin/login",
            type: "post",
            dataType: "json",
            data: {
                username:$("#username").val(),
                password:$("#password").val(),
                captchaKey: $("#captchaKey").val(),
                captchaCode: $("#captchaCode").val(),
            },
            success: function (data) {
                console.log(data);
            },
            error: function (err) {
                console.log(err);
            }
        })
        return false;
    });
});