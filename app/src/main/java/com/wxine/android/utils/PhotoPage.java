package com.wxine.android.utils;

import com.wxine.android.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoPage {
	private int pageSize = 20;
	private List<Photo> items = new ArrayList<Photo>();
	private int totalCount = 0;
	private int pageCount = 0;
	private int currentPage = 1;
	private int[] pages = new int[0];
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public List<Photo> getItems() {
		return items;
	}

	public void setItems(List<Photo> items) {
		this.items = items;
	}

	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int[] getPages() {
		return pages;
	}
	
	public void setPages(int[] pages) {
		this.pages = pages;
	}
}
