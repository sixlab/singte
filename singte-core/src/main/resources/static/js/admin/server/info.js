$(function () {

    $(document).on("click", ".stRebootBtn", function () {
        alertAjax("确定重启?", "/admin/server/reboot");
    })

    $(document).on("click", ".stUpdateBtn", function () {
        alertAjax("确定更新并重启?", "/admin/server/update");
    })

    function alertAjax(text, url){
        Swal.fire({
            title: '警告',
            text: text,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: '取消',
            confirmButtonText: '确定'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: url,
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        if (200 === res.status) {
                            Swal.fire({
                                icon: "success",
                                text: data.message,
                                showConfirmButton: false,
                                timer: 2000
                            })
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
    }

});