$(function () {

    let stDataTable = $("#queryForm").stDataTable("#queryData");
    let stValidate = $("#modalDataForm").validate();

    $(document).on("click", ".stSaveBtn", function () {
        if ($('#modalDataForm').valid()) {
            $.ajax({
                url: '/admin/spider/submit',
                data: $("#modalDataForm").serialize(),
                type: 'post',
                dataType: 'json',
                success: function (res) {
                    if (200 === res.status) {
                        $(".cancelBtn").trigger("click");
                        stDataTable.formQuery(true);
                    } else {
                        Swal.fire({
                            icon: "error",
                            text: res.message,
                            showConfirmButton: false,
                            timer: 2000
                        })
                    }
                },
                error(err) {
                    console.log(err)
                    Swal.fire({
                        icon: "error",
                        text: "Error",
                        showConfirmButton: false,
                        timer: 2000
                    })
                }
            })
        }
    })

    $(document).on("click", ".stStatusBtn", function () {
        $.ajax({
            url: '/admin/spider/status',
            data: {
                id: $(this).data("itemId"),
                status: $(this).data("targetStatus")
            },
            type: 'post',
            dataType: 'json',
            success: function (res) {
                if (200 === res.status) {
                    stDataTable.formQuery(true);
                } else {
                    Swal.fire({
                        icon: "error",
                        text: res.message,
                        showConfirmButton: false,
                        timer: 2000
                    })
                }
            },
            error(err) {
                console.log(err)
                Swal.fire({
                    icon: "error",
                    text: "Error",
                    showConfirmButton: false,
                    timer: 2000
                })
            }
        })
    })

    $(document).on("click", ".stDeleteBtn", function () {
        Swal.fire({
            title: '警告',
            text: "确定删除?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: '取消',
            confirmButtonText: '确定'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: '/admin/spider/delete',
                    data: {
                        id: $(this).data("itemId"),
                    },
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        if (200 === res.status) {
                            stDataTable.formQuery(true);
                        } else {
                            Swal.fire({
                                icon: "error",
                                text: res.message,
                                showConfirmButton: false,
                                timer: 2000
                            })
                        }
                    },
                    error(err) {
                        console.log(err)
                        Swal.fire({
                            icon: "error",
                            text: "Error",
                            showConfirmButton: false,
                            timer: 2000
                        })
                    }
                })
            }
        })
    })

    $(document).on("click", ".stModifyBtn", function () {
        $.ajax({
            url: '/admin/spider/get',
            data: {
                id: $(this).data("itemId")
            },
            type: 'post',
            dataType: 'json',
            success: function (res) {
                if (200 === res.status) {
                    $('#modal-default').modal();
                    $("#id").val(res.data.id);
                    $("#spiderType").val(res.data.spiderType);
                    $("#spiderName").val(res.data.spiderName);
                    $("#pagerRule").val(res.data.pagerRule);
                    $("#linkRule").val(res.data.linkRule);
                    $("#titleRule").val(res.data.titleRule);
                    $("#contentRule").val(res.data.contentRule);
                    $("#summaryRule").val(res.data.summaryRule);
                    $("#categoryRule").val(res.data.categoryRule);
                    $("#keywordRule").val(res.data.keywordRule);
                    $("#waitTimes").val(res.data.waitTimes);
                    $("#startUrl").val(res.data.startUrl);
                    $("#urlParam").val(res.data.urlParam);
                } else {
                    Swal.fire({
                        icon: "error",
                        text: res.message,
                        showConfirmButton: false,
                        timer: 2000
                    })
                }
            },
            error(err) {
                console.log(err)
                Swal.fire({
                    icon: "error",
                    text: "Error",
                    showConfirmButton: false,
                    timer: 2000
                })
            }
        })
    })

    $('#modal-default').on('hide.bs.modal', function (e) {
        $("#id").val("");
        $("#spiderType").val("");
        $("#spiderName").val("");
        $("#pagerRule").val("");
        $("#linkRule").val("");
        $("#titleRule").val("");
        $("#contentRule").val("");
        $("#summaryRule").val("");
        $("#categoryRule").val("");
        $("#keywordRule").val("");
        $("#waitTimes").val("");
        $("#startUrl").val("");
        $("#urlParam").val("");

        stValidate.resetForm();
    })

});