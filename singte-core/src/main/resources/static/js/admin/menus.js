$(function () {
    // $("#example1").DataTable({
    //     "responsive": true,
    //     "lengthChange": false,
    //     "autoWidth": false,
    //     "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    // }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');


    $('#list_table').DataTable({
        // language: {
        //     "lengthMenu": '每页显示 <select>' + '<option value="10">10</option>'
        //         + '<option value="20">20</option>'
        //         + '<option value="30">30</option>'
        //         + '<option value="40">40</option>'
        //         + '<option value="50">50</option>' + '</select> 条',
        //     "paginate": {
        //         "first": "首页",
        //         "last": "尾页",
        //         "previous": "上一页",
        //         "next": "下一页"
        //     },
        //     "processing": "加载中...",  //DataTables载入数据时，是否显示‘进度’提示
        //     "emptyTable": "暂无数据",
        //     "info": "共 _PAGES_ 页  _TOTAL_ 条数据  ",
        //     "infoEmpty": "暂无数据",
        //     "search": "搜索:",
        //     "infoFiltered": " —— 从  _MAX_ 条数据中筛选",
        //     "zeroRecords": "没有找到记录"
        // },
        "serverSide": true,
        ajax: function (data, callback, settings) {
            console.log(data);
            //封装请求参数
            var param = {};
            param.pageNum = data.start + 1;   //开始的记录序号
            param.pageSize = data.length;       //页面显示记录条数，在页面显示每页显示多少项的时候
            param.keyword = "";   //开始的记录序号
            param.status = "";   //开始的记录序号
            console.log(param);
            $.ajax({
                type: 'post',
                url: '/admin/selectMenus',
                data: param,
                dataType: 'json',
                success: function (res) {
                    var out = {};
                    out.draw = data.draw;
                    out.recordsTotal = res.data.totalNum;
                    out.data = res.data.list;
                    out.recordsFiltered = res.data.list.length;
                    callback(out);
                },
                error() {
                    alert('error');
                }
            })
        },
        columns: [
            {
                "data": "menuCode",
                "name": "menuCode",
                "orderable": false,
                'sClass': "text-center",
            },
            {
                "data": "menuCode",
                "name": "menuCode",
                "orderable": false,
                'sClass': "text-center",
            },
            {
                "data": "menuLink",
                "orderable": false,
                'sClass': "text-center",
            },
            {
                "data": "menuIcon",
                "orderable": false,
                'sClass': "text-center",
                "sDefaultContent": "",  //默认空字符串
            }
        ]
    });

});