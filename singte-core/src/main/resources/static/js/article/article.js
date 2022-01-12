$(function () {
    $(".diggit").on("click", function () {
        $.ajax({
            url: '/article/thumb',
            data: {
                articleId:$(".st-article-id").val()
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