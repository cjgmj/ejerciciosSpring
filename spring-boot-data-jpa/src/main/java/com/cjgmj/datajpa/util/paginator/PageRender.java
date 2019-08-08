package com.cjgmj.datajpa.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPages;
	private int size;
	private int actualPage;
	private List<PageItem> pages;

	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.pages = new ArrayList<>();

		actualPage = page.getNumber() + 1;
		totalPages = page.getTotalPages();
		size = page.getSize();

		int fromPage, toPage;
		if (totalPages <= size) {
			fromPage = 1;
			toPage = totalPages;
		} else {
			if (actualPage <= size / 2) {
				fromPage = 1;
				toPage = size;
			} else if (actualPage >= totalPages - size / 2) {
				fromPage = totalPages - size + 1;
				toPage = size;
			} else {
				fromPage = actualPage - size / 2;
				toPage = size;
			}
		}

		for (int i = 0; i < toPage; i++) {
			pages.add(new PageItem(fromPage + i, actualPage == fromPage + i));
		}
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getActualPage() {
		return actualPage;
	}

	public List<PageItem> getPages() {
		return pages;
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean isHasNext() {
		return page.hasNext();
	}

	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
