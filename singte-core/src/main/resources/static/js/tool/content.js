$(function () {
    $(".diggit").on("click", function () {
        $.ajax({
            url: '/tool/thumb',
            data: {
                toolId:$(".st-tool-id").val()
            },
            type: 'post',
            dataType: 'json',
            success: function (res) {
                if(res.status === 200){
                    $("#diggnum").text(res.data);
                }
            },
            error(err) {
                console.log(err)
            }
        })
    })
});