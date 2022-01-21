$(function () {

    let stDataTable = $("#queryForm").stDataTable("#queryData");

    $(document).on("click", ".stCountBtn", function () {
        $.ajax({
            url: '/admin/ancient/set/reload',
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
        $.ajax({
            url: '/admin/ancient/set/submitSet',
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
    })

    $(document).on("click", ".stStatusBtn", function () {
        $.ajax({
            url: '/admin/ancient/set/submitStatus',
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

    $(document).on("click", ".stModifyBtn", function () {
        $.ajax({
            url: '/admin/ancient/set/get',
            data: {
                id: $(this).data("itemId")
            },
            type: 'post',
            dataType: 'json',
            success: function (res) {
                if (200 === res.status) {
                    $('#modal-default').modal();
                    $("#id").val(res.data.id);
                    $("#ancientSet").val(res.data.ancientSet);
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
        $("#ancientSet").val("");
        $("#weight").val("");
        $("#intro").val("");
    })

});