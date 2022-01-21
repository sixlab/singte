$(function () {

    let stDataTable = $("#queryForm").stDataTable("#queryData");

    $(document).on("click", ".stSaveBtn", function () {
        $.ajax({
            url: '/admin/keyword/submitKeyword',
            data: $("#modalDataForm").serialize(),
            type: 'post',
            dataType: 'json',
            success: function (res) {
                console.log(res)
                $(".cancelBtn").trigger("click");
                stDataTable.formQuery(true);
            },
            error(err) {
                console.log(err)
            }
        })
    })

    $("#resetKeyword").on("click", function () {
        $.ajax({
            url: '/admin/keyword/resetKeyword',
            type: 'post',
            dataType: 'json',
            success: function (res) {
                Swal.fire(
                    '操作成功',
                    '重置关键词成功！',
                    'success'
                )
                stDataTable.formQuery(true);
            },
            error(err) {
                console.log(err)
            }
        })
    })
});