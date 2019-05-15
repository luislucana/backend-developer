package br.com.blog.service.dto;

import java.io.Serializable;


/**
 * DTO
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
@SuppressWarnings("serial")
public class PostDTO implements Serializable {
	
	private Long postDTOId;
	
	private String title;
	
	private String description;
	
	private String publicationDate;

	public Long getPostDTOId() {
		return postDTOId;
	}

	public void setPostDTOId(Long postDTOId) {
		this.postDTOId = postDTOId;
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
