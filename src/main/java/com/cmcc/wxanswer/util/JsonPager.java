package com.cmcc.wxanswer.util;
import java.util.List;

public class JsonPager<T>{
	private static int DEFAULT_COUNT_ON_EACH_PAGE = 10;
	private int page;//当前页数
	private int total;//总的记录数
	private int size;//每页记录数
	private List<T> rows;//当前页信息
	public JsonPager() {
    }

    public JsonPager(int size) {
        page = 0;
        if (size < 1) {
            throw new IllegalArgumentException("Count should be greater than zero!");
        } else {
            this.size = size;
        }
    }

    public JsonPager(int page, int total, int size) {
        this.page = page;
        this.total = total;
        this.size = size;
        if (this.size <= 0) {
            this.size = DEFAULT_COUNT_ON_EACH_PAGE;
        }
    }


    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public int getEndIndex() {
        int endIndex = getPage() + size;
        if (endIndex > total) {
            return total;
        } else {
            return endIndex;
        }
    }

    public int getNextIndex() {
        int[] nextpagees = getNextpagees();
        if (nextpagees == null) {
            return getTotal();
        } else {
            return nextpagees[0];
        }
    }

    public int getPreviousIndex() {
        int[] previousIndexes = getPreviouspagees();
        if (previousIndexes == null) {
            return getPage();
        } else {
            return previousIndexes[previousIndexes.length - 1];
        }
    }

    public int[] getNextpagees() {
        int index = getEndIndex();
        if (index == total) {
            return null;
        }
        int count = (total - index) / size;
        if ((total - index) % size > 0) {
            count++;
        }
        int result[] = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = index;
            index += size;
        }
        return result;
    }

    public int[] getPreviouspagees() {
        int index = getPage();
        if (index == 0) {
            return null;
        }
        int count = index / size;
        if (index % size > 0) {
            count++;
        }
        int result[] = new int[count];
        for (int i = count - 1; i > 0; i--) {
            index -= size;
            result[i] = index;
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public void setTotal(int total) {
        this.total = total;
        validate();
    }

    private void validate() {
        if (page > total) {
            int i = getTotal() % size;
            page = total - i;
        }
        if (page < 0) {
            page = 0;
        }
    }

    /**
     * Return the number of pages for the current query.
     */
    public int getTotalPage() {
        int pages = getTotal() / size;
        int i = getTotal() % size;
        if (i > 0) {
            pages++;
        }
        if (getTotal() == 0) {
            pages = 1;
        }
        return pages;
    }

    /**
     * Return the current page number. Page numbering starts with 1.
     */
    public int getPage() {
    	int pages = page/size;
        return pages + 1;
    }

    public int getPagePre() {
        int pages = page / size;
        return pages - 1;
    }

    public void setPage(int page) {
        if (page < 1) {
            page = 0;
        } else {
            page = (page - 1) * size;
        }
    }

    public String toString() {
        return "PaginationSupport[" + "total=" + total + ", page=" + page + ", pageCount=" + getTotalPage() + ", page=" + getPage() + "]";
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public List getRows() {
        return rows;
    }

}
