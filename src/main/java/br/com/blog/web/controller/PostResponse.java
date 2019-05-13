package br.com.blog.web.controller;


/**
 * 
 * b
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
public class PostResponse {
	
	private Long id;
	
	private String title;
	
	private String description;
	
	private String publicationDate;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
}
