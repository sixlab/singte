$(function () {

    let stDataTable = $("#queryForm").stDataTable("#queryData");
    let stValidate = $("#modalDataForm").validate();

    $(document).on("click", ".stReloadBtn", function () {
        $.ajax({
            url: '/admin/category/reload',
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

    $(document).on("click", ".stSaveBtn", function () {
        if ($('#modalDataForm').valid()) {
            $.ajax({
                url: '/admin/category/submit',
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
            url: '/admin/category/status',
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
                    url: '/admin/category/delete',
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
            url: '/admin/category/get',
            data: {
                id: $(this).data("itemId")
            },
            type: 'post',
            dataType: 'json',
            success: function (res) {
                if (200 === res.status) {
                    $('#modal-default').modal();
                    $("#id").val(res.data.id);
                    $("#category").val(res.data.category);
                    $("#weight").val(res.data.weight);
                    $("#intro").val(res.data.intro);
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
        $("#category").val("");
        $("#weight").val("");
        $("#intro").val("");

        stValidate.resetForm();
    })

});