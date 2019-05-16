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
public class PageResource<T> extends ResourceSupport implements Page<T> {
	
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
	
	@Override
	public int getNumber() {
		return page.getNumber();
	}

	@Override
	public int getSize() {
		return page.getSize();
	}

	@Override
	public int getTotalPages() {
		return page.getTotalPages();
	}

	@Override
	public int getNumberOfElements() {
		return page.getNumberOfElements();
	}

	@Override
	public long getTotalElements() {
		return page.getTotalElements();
	}

	@Override
	public boolean hasPrevious() {
		return page.hasPrevious();
	}

	@Override
	public boolean isFirst() {
		return page.isFirst();
	}

	@Override
	public boolean hasNext() {
		return page.hasNext();
	}

	@Override
	public boolean isLast() {
		return page.isLast();
	}

	@Override
	public Iterator<T> iterator() {
		return page.iterator();
	}

	@Override
	public List<T> getContent() {
		return page.getContent();
	}

	@Override
	public boolean hasContent() {
		return page.hasContent();
	}

	@Override
	public Sort getSort() {
		return page.getSort();
	}

	@Override
	public Pageable nextPageable() {
		return hasNext() ? page.nextPageable() : Pageable.unpaged();
	}

	@Override
	public Pageable previousPageable() {
		return hasPrevious() ? page.previousPageable() : Pageable.unpaged();
	}

	@Override
	public Stream<T> get() {
		return stream();
	}

	@Override
	public <U> Page<U> map(Function<? super T, ? extends U> converter) {
		return new PageImpl<>(getConvertedContent(converter), getPageable(), getTotalElements());
	}
	
	private <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {
		Assert.notNull(converter, "Function must not be null!");

		return this.stream().map(converter::apply).collect(Collectors.toList());
	}
}
