$(function () {
    function loadCaptcha() {
        let key = Date.now().toString(36) + Math.random().toString(36);

        $("#captchaKey").val(key);
        $("#captchaImg").attr("src", "/captcha/image?width=100&height=41&key=" + key);
    }

    function login(){
        $.ajax({
            url: "/admin/login",
            type: "post",
            dataType: "json",
            data: {
                username: $("#username").val(),
                password: $("#password").val(),
                captchaKey: $("#captchaKey").val(),
                captchaCode: $("#captchaCode").val(),
            },
            success: function (data) {
                if (200 === data.status) {
                    location.href = "/admin/index";
                } else {
                    Swal.fire({
                        icon: "error",
                        text: data.message,
                        showConfirmButton: false,
                        timer: 2000
                    })
                    loadCaptcha();
                }
            },
            error: function (err) {
                console.log(err);
            }
        })
    }

    loadCaptcha();
    $(document).on("click", ".reloadImg", loadCaptcha);

    $(document).on("keydown", "#captchaCode", function (e) {
        if (e.keyCode === 13) {
            login();
            return false;
        }
    })

    $(document).on("click", "#loginBtn", function () {
        login();
        return false;
    });
});