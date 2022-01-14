$(function () {
    $(".diggit").on("click", function () {
        $.ajax({
            url: '/ancient/thumb',
            data: {
                sectionId:$(".st-section-id").val()
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