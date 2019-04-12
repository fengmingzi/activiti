package com.main.activity.common;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Magp on 2017/4/28.
 */
public class Pager<T> implements Serializable {

  public static final int DEFAULT_PAGESIZE = 10; //默认显示10条.
  private List<T> records;
  private long page = 1; //页数
  private long rows = 10; //行数
  private long pageOffset; //页面偏移
  private String sort; //排序字段
  private String order; //顺序方式
  private long totalPage; //总页数
  private long totalCount; //总条数
  private long startPageIndex; //起始页索引
  private long endPageIndex; //结束页索引
  private long pageCode = 5;
  private long previewPage = 1;
  private long nextPage = 1;
  private long startRow = 0;//初始行号

  public Pager() {
  }

  public Pager(int page, int rows) {
    this.page = page;
    this.rows = rows;
  }

  public Pager(int page) {
    this.setPage(page);
    this.rows = 10;
  }

  public long getPageOffset() {
    this.pageOffset = (this.page - 1) * this.rows;
    return this.pageOffset;
  }

  public void setPageOffset(int pageOffset) {
    this.pageOffset = pageOffset;
  }

  public long getPage() {
    return this.page;
  }

  public void setPage(int page) {
    if (page > 0) {
      this.page = page;
    }

  }

  public long getRows() {
    return this.rows;
  }

  public void setRows(int rows) {
    if (rows > 0) {
      this.rows = rows;
    }

  }

  public String getSort() {
    return camelToUnderline(this.sort);
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public String getOrder() {
    return this.order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public long getTotalPage() {
    return this.totalPage;
  }

  public void setTotalPage(long totalPage) {
    this.totalPage = totalPage;
    this.startPageIndex =
        this.page - (this.pageCode % 2 == 0 ? this.pageCode / 2 - 1 : this.pageCode / 2);
    this.endPageIndex = this.page + this.pageCode / 2;
    if (this.startPageIndex < 1) {
      this.startPageIndex = 1;
      if (totalPage >= this.pageCode) {
        this.endPageIndex = this.pageCode;
      } else {
        this.endPageIndex = totalPage;
      }
    }

    if (this.endPageIndex > totalPage) {
      this.endPageIndex = totalPage;
      if (this.endPageIndex - this.pageCode > 0) {
        this.startPageIndex = this.endPageIndex - this.pageCode + 1;
      } else {
        this.startPageIndex = 1;
      }
    }

    if (this.endPageIndex <= 1) {
      this.endPageIndex = 1;
    }

    this.previewPage = this.page - 1;
    this.nextPage = this.page + 1;
    if (this.page <= 1) {
      this.previewPage = 1;
    }

    if (this.page >= this.totalPage) {
      this.nextPage = this.totalPage;
    }

  }

  public long getTotalCount() {
    return this.totalCount;
  }

  public void setTotalCount(long totalCount) {
    this.totalCount = totalCount;
    this.setTotalPage(
        totalCount % this.rows == 0 ? totalCount / this.rows : totalCount / this.rows + 1);
  }

  public long getStartPageIndex() {
    return this.startPageIndex;
  }

  public void setStartPageIndex(long startPageIndex) {
    this.startPageIndex = startPageIndex;
  }

  public long getEndPageIndex() {
    return this.endPageIndex;
  }

  public void setEndPageIndex(long endPageIndex) {
    this.endPageIndex = endPageIndex;
  }

  public long getPageCode() {
    return this.pageCode;
  }

  public void setPageCode(long pageCode) {
    this.pageCode = pageCode;
  }

  public long getPreviewPage() {
    return this.previewPage;
  }

  public void setPreviewPage(long previewPage) {
    this.previewPage = previewPage;
  }

  public long getNextPage() {
    return this.nextPage;
  }

  public void setNextPage(long nextPage) {
    this.nextPage = nextPage;
  }

  public List<T> getRecords() {
    return this.records;
  }

  public void setRecords(List<T> records) {
    this.records = records;
  }

  private String camelToUnderline(String param) {
    if (StringUtils.isEmpty(param)) {
      return "";
    }
    int len = param.length();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      char c = param.charAt(i);
      if (Character.isUpperCase(c) && i > 0) {
        sb.append('_');
      }
      sb.append(Character.toLowerCase(c));
    }
    return sb.toString();
  }
  public long getStartRow() {
    //计算当前开始行
    this.startRow =(this.page-1)*this.rows;
    return this.startRow;
  }
  public void setStartRow(long startRow) {
    this.startRow = startRow;
  }
}
