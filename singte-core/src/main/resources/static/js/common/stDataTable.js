$.fn.stDataTable = function (options) {
    let _this = this;
    let pageNum = this.find(".pageNum");
    let pageSize = this.find(".pageSize");
    let searchBtn = this.find(".searchBtn");

    let container = options;
    if (typeof options === 'object') {
        container = options.container;
    }

    let StDataTable = {};
    StDataTable.formQuery = function (firstPage) {
        if (!!firstPage) {
            pageNum.val(1);
        }

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

    this.on("keydown", function (e) {
        if (e.keyCode === 13) {
            pageNum.val("1");
            StDataTable.formQuery();
            return false;
        }
    })

    $(document).on("click", container + " .st-pager", function () {
        let pageIndex = $(this).data("page");
        pageNum.val(pageIndex);
        StDataTable.formQuery();
        return false;
    })

    $(document).on("click", container + " .st-pager-btn", function () {
        let pageIndex = $(container + " .st-pager-input").val();
        pageNum.val(pageIndex);
        StDataTable.formQuery();
        return false;
    })

    $(document).on("keydown", container + " .st-pager-input", function (e) {
        if (e.keyCode === 13) {
            let pageIndex = $(this).val();
            pageNum.val(pageIndex);
            StDataTable.formQuery();
            return false;
        }
    })

    $(document).on("change", container + " .st-pager-size", function () {
        let pageSizeVal = $(this).val();
        pageSize.val(pageSizeVal);
        StDataTable.formQuery();
        return false;
    })

    $(searchBtn).on("click", function () {
        pageNum.val("1");
        StDataTable.formQuery();
    })

    StDataTable.formQuery();

    return StDataTable;
}