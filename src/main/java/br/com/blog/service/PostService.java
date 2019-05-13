package br.com.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blog.persistence.mysql.repository.PostRepository;
import br.com.blog.service.dto.Post;

/**
 * Classe de servico para a entidade Post.
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Transactional
	public Post createPlayer(Post post) {

		if (post == null) {
			throw new IllegalArgumentException("Nenhum dado foi informado.");
		}

		// TODO call
		//postRepository.save(null);

		return null;
	}

	public br.com.blog.persistence.mysql.model.Post getPost(Long id) {

		br.com.blog.persistence.mysql.model.Post post = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Identificador invalido:" + id));

		return post;
	}

	@Transactional
	public br.com.blog.persistence.mysql.model.Post updatePost(br.com.blog.persistence.mysql.model.Post post, Long id) {
		if (post.getId() != id) {
			throw new IllegalArgumentException("Identificador invalido:" + id);
		}
		
		if (!postRepository.existsById(id)) {
			throw new IllegalArgumentException("Identificador invalido:" + id);
		}

		br.com.blog.persistence.mysql.model.Post savedPost = postRepository.save(post);

		return savedPost;
	}

	@Transactional
	public void deletePlayer(Long id) {
		br.com.blog.persistence.mysql.model.Post post = 
				postRepository.findById(id).orElseThrow(() -> 
				new IllegalArgumentException("Identificador invalido:" + id));

		postRepository.delete(post);
	}
}
