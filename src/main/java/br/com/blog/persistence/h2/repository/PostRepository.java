package br.com.blog.persistence.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.blog.persistence.h2.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
