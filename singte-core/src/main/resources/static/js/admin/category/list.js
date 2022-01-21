$(function () {

    let stDataTable = $("#queryForm").stDataTable("#queryData");

    $(document).on("click", ".stSaveBtn", function () {
        $.ajax({
            url: '/admin/category/submitCategory',
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

    $("#resetCategory").on("click", function () {
        $.ajax({
            url: '/admin/category/resetCategory',
            type: 'post',
            dataType: 'json',
            success: function (res) {
                Swal.fire(
                    '操作成功',
                    '重置分类成功！',
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