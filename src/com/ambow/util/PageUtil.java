package com.ambow.util;

public class PageUtil {
	private int startIndex = 0; // 起始笔数
	private int endIndex = 10; // 结束笔数
	private int pageSize = 10; // 每页多少笔资料
	private int currentPageIndex = 0; // 当前是第几页

	public PageUtil() {
		super();
	}

	// 每页3笔资料 , 第2页
	// 0 ====> 1 ~ 3
	// 1 ====> 4 ~ 6
	// 2 ====> 7 ~ 9
	// startIndex====> currentPageIndex*pageSize + 1
	// endIndex====> (currentPageIndex+1)*pageSize
	public PageUtil(int pageSize, int currentPageIndex) {
		super();
		this.pageSize = pageSize;
		this.currentPageIndex = currentPageIndex-1;

		this.startIndex = this.currentPageIndex * this.pageSize + 1;
		this.endIndex = (this.currentPageIndex + 1) * this.pageSize;
	}

	public PageUtil(int currentPageIndex) {
		super();
		this.currentPageIndex = currentPageIndex-1;

		this.startIndex = this.currentPageIndex * this.pageSize + 1;
		this.endIndex = (this.currentPageIndex + 1) * this.pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.startIndex = this.currentPageIndex * this.pageSize + 1;
		this.endIndex = (this.currentPageIndex + 1) * this.pageSize;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex-1;
		this.startIndex = this.currentPageIndex * this.pageSize + 1;
		this.endIndex = (this.currentPageIndex + 1) * this.pageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}
}