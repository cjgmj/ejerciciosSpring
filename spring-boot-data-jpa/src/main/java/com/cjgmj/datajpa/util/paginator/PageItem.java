package com.cjgmj.datajpa.util.paginator;

public class PageItem {

	private int numPage;
	private boolean actual;

	public PageItem(int numPage, boolean actual) {
		this.numPage = numPage;
		this.actual = actual;
	}

	public int getNumPage() {
		return numPage;
	}

	public boolean isActual() {
		return actual;
	}

}
