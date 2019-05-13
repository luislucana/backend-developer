package br.com.blog.persistence.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blog.persistence.mysql.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
