/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.utils 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:37:04 
 */
package com.smxy.recipe.utils;

/**
 * @author zpx
 *
 */
import java.util.Arrays;
import java.util.List;

public class PagingApi {
	private List<Object> data;
	private int[] pageArr;
	private int page;
	private int pageTotal;
	private boolean pageFlag;
	public PagingApi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PagingApi(List<Object> data, int[] pageArr, int page, int pageTotal, boolean pageFlag) {
		super();
		this.data = data;
		this.pageArr = pageArr;
		this.page = page;
		this.pageTotal = pageTotal;
		this.pageFlag = pageFlag;
	}
	public PagingApi(List<Object> data, boolean pageFlag) {
		super();
		this.data = data;
		this.pageFlag = pageFlag;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public int[] getPageArr() {
		return pageArr;
	}
	public void setPageArr(int[] pageArr) {
		this.pageArr = pageArr;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public boolean isPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}
	@Override
	public String toString() {
		return "PagingApi [data=" + data + ", pageArr=" + Arrays.toString(pageArr) + ", page=" + page + ", pageTotal="
				+ pageTotal + ", pageFlag=" + pageFlag + "]";
	}
}

