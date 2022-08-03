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
                        console.log(res)
                    },
                    error(err) {
                        console.log(err)
                    }
                });

                Swal.fire({
                    title: "Loading...",
                    showConfirmButton: false,
                    imageUrl: "/static/images/loading.gif",
                    showCancelButton: false,
                });

                let intervalId = setInterval(function () {
                    $.ajax({
                        url: "/admin/server/info",
                        type: 'post',
                        dataType: 'json',
                        success: function (res) {
                            if (200 === res.status && res.newServer) {
                                clearInterval(intervalId);
                                Swal.fire({
                                    icon: "success",
                                    text: "操作完成",
                                    showConfirmButton: false,
                                    timer: 2000
                                }).then(function () {
                                    location.reload();
                                });
                            }
                        },
                        error(err) {
                            console.log(err)
                        }
                    });
                }, 5000);
            }
        })
    }

});