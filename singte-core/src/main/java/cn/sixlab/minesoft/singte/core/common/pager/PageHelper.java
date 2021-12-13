package cn.sixlab.minesoft.singte.core.common.pager;


import java.util.List;

public class PageHelper {
    public static <T> PageResult<T> pager(List<T> contentList, int pageNum, int pageSize, long totalNum, int totalPages) {
        PageResult<T> page = new PageResult<>();

        page.setPageNum(pageNum);
        page.setPageNum(pageSize);

        page.setTotalNum(totalNum);
        page.setTotalPages(totalPages);

        if(contentList.size()>0){
            page.setHasContent(true);
        }

        if (pageNum == 1) {
            page.setFirst(true);
        } else if (pageNum > 1) {
            page.setHasPrevious(true);
        }

        if (pageNum == totalPages) {
            page.setLast(true);
        } else if (pageNum < totalPages) {
            page.setHasNext(true);
        }

        page.setContent(contentList);

        return page;
    }
}
