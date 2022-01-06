$.fn.stDataTable = function (options) {
    let _this = this;

    let container = options;
    if (typeof options === 'object') {
        container = options.container;
    }

    let pageNum = this.find(".pageNum");
    let pageSize = this.find(".pageSize");
    let searchBtn = this.find(".searchBtn");

    this.on("keydown", function (e) {
        if (e.keyCode === 13) {
            query();
            return false;
        }
    })

    $(document).on("click", container + " .st-pager", function () {
        let pageIndex = $(this).data("page");
        pageNum.val(pageIndex);
        query();
        return false;
    })

    $(searchBtn).on("click", function () {
        query();
    })

    function query() {
        $.ajax({
            url: '/admin/menusData',
            data: _this.serialize(),
            type: 'post',
            success: function (res) {
                $(container).html(res);
            },
            error() {
                alert('error');
            }
        })
    }

    query();

    return query;
}