$(function () {

    $("#queryForm").stDataTable("#queryData");

    $(document).on("click", ".saveMenuBtn", function () {
        $.ajax({
            url: '/admin/submitMenu',
            data: $("#menuForm").serialize(),
            type: 'post',
            dataType: 'json',
            success: function (res) {
                console.log(res)
            },
            error(err) {
                console.log(err)
            }
        })
    })

});