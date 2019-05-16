package br.com.blog.web.controller;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Implementacao de paginacao e de links de referencia (first, last, prev, next, self).
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
public class PageResource<T> extends ResourceSupport {
	
	@JsonIgnore
	private final Page<T> page;
	
	private final String PAGE = "page";
	
	private final String SIZE = "size";
	
	public PageResource(Page<T> page) {
		super();
		this.page = page;
		
		if (page.hasPrevious()) {
			String path = getServletUriBuilder()
				.queryParam(PAGE, page.getNumber() - 1)
				.queryParam(SIZE, page.getSize())
				.build().toUriString();
			Link link = new Link(path, Link.REL_PREVIOUS);
			add(link);
		}
		
		if (page.hasNext()) {
			String path = getServletUriBuilder()
				.queryParam(PAGE, page.getNumber() + 1)
				.queryParam(SIZE, page.getSize())
				.build().toUriString();
			
			Link link = new Link(path, Link.REL_NEXT);
			add(link);
		}
		
		Link link = buildPageLink(0, page.getSize(), Link.REL_FIRST);
		add(link);
		
		int indexOfLastPage = page.getTotalPages() - 1;
		
		link = buildPageLink(indexOfLastPage, page.getSize(), Link.REL_LAST);
		add(link);
		
		link = buildPageLink(page.getNumber(), page.getSize(), Link.REL_SELF);
		add(link);
	}
	
	private ServletUriComponentsBuilder getServletUriBuilder() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri();
	}
	
	private Link buildPageLink(int page, int size, String rel) {
		String path = getServletUriBuilder()
				.queryParam(PAGE, page)
				.queryParam(SIZE, size)
				.build().toUriString();
		Link link = new Link(path, rel);
		return link;
	}
	
	public int getNumber() {
		return page.getNumber();
	}

	public int getSize() {
		return page.getSize();
	}

	public int getTotalPages() {
		return page.getTotalPages();
	}

	public int getNumberOfElements() {
		return page.getNumberOfElements();
	}

	public long getTotalElements() {
		return page.getTotalElements();
	}

	public boolean hasPrevious() {
		return page.hasPrevious();
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean hasNext() {
		return page.hasNext();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public Iterator<T> iterator() {
		return page.iterator();
	}

	public List<T> getContent() {
		return page.getContent();
	}

	public boolean hasContent() {
		return page.hasContent();
	}

	public Sort getSort() {
		return page.getSort();
	}

	public Pageable nextPageable() {
		return hasNext() ? page.nextPageable() : Pageable.unpaged();
	}

	public Pageable previousPageable() {
		return hasPrevious() ? page.previousPageable() : Pageable.unpaged();
	}
}
