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
            formQuery();
            return false;
        }
    })

    $(document).on("click", container + " .st-pager", function () {
        let pageIndex = $(this).data("page");
        pageNum.val(pageIndex);
        formQuery();
        return false;
    })

    $(searchBtn).on("click", function () {
        formQuery();
    })

    function formQuery() {
        $.ajax({
            url: _this.attr("action"),
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

    formQuery();

    return formQuery;
}