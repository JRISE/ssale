package com.ssale.util;

import java.util.List;

/**
 * 
 * @param pageSize
 *            每页最大显示条数，默认10
 * @param pageNum
 *            当前页码
 * @param totalPage
 *            所有页码
 * @param totalRecords
 *            所有记录数
 * @param currentRecords
 *            当前页面显示记录
 */
public class Page<T> {
	private int pageSize = 10;
	private int pageNum;
	private int totalPage; // 所有页码
	private int totalRecords; // 所有记录数
	private List<T> currentRecords; // 当前页面显示记录

	// 默认每页显示10条
	public Page(int pageNum, List<T> currentRecords, int totalRecords) {
		this.pageNum = pageNum;
		this.totalRecords = totalRecords;
		this.currentRecords = currentRecords;
		this.totalPage = (totalRecords % pageSize) == 0 ? totalRecords / pageSize : totalRecords
				/ pageSize + 1;
	}

	// 设置每页显示条数pageSize
	public Page(int pageNum, List<T> currentRecords, int totalRecords, int pageSize) {
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.totalRecords = totalRecords;
		this.currentRecords = currentRecords;
		this.totalPage = (totalRecords % pageSize) == 0 ? totalRecords / pageSize : totalRecords
				/ pageSize + 1;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<T> getCurrentRecords() {
		return currentRecords;
	}

	public void setCurrentRecords(List<T> currentRecords) {
		this.currentRecords = currentRecords;
	}

}
