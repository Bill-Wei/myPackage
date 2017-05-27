package com.restrant.entity;

/*
 * 分页实体类
 * 用于实现分页显示餐品列表
 */
public class Pager {
	private int curPage; // 待显示页
	private int perPageRows; // 一页显示的记录数
	private int rowCount;// 记录总数
	private int pageCount;// 总页数

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPerPageRows() {
		return perPageRows;
	}

	public void setPerPageRows(int perPageRows) {
		this.perPageRows = perPageRows;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	// 获得总页数
	public int getPageCount() {
		return (rowCount + perPageRows - 1) / perPageRows;
		// （记录总数+每页显示记录数-1）÷每页显示的记录数
	}

}
