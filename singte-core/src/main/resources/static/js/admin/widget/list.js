$(function () {

    let stDataTable = $("#queryForm").stDataTable("#queryData");

    $(document).on("click", ".changeStatusBtn", function () {
        $.ajax({
            url: '/admin/widget/submitStatus',
            data: {
                id: $(this).data("recordId"),
                widgetStatus: $(this).data("targetStatus")
            },
            type: 'post',
            dataType: 'json',
            success: function (res) {
                console.log(res)
                stDataTable.formQuery(true);
            },
            error(err) {
                console.log(err)
            }
        })
    })

});