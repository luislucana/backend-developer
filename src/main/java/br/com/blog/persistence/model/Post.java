package br.com.blog.persistence.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
@Entity
@Table(name = "POST")
public class Post implements Serializable {

	private static final long serialVersionUID = 3104036239799007274L;

	@Id
    @GeneratedValue
    private Long id;
	
	private String title;
	
	private String description;
	
	private Date publicationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
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

	@Override
    public boolean equals(Object o) {
        if (this == o)
        	return true;
        
        if (o == null || getClass() != o.getClass())
        	return false;
        
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
