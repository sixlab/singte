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
                if(200 === data.status){
                    location.href = "/auth/admin/index";
                }else{
                    layer.msg(data.message, {icon: 5, time: 2000});
                    loadCaptcha();
                }
            },
            error: function (err) {
                console.log(err);
            }
        })
        return false;
    });
});