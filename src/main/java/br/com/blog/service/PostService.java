package br.com.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blog.mapper.PostMapper;
import br.com.blog.persistence.model.Post;
import br.com.blog.persistence.repository.PostRepository;
import br.com.blog.service.dto.PostDTO;

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
	public PostDTO create(PostDTO postDTO) {

		if (postDTO == null) {
			throw new IllegalArgumentException("Nenhum dado foi informado.");
		}
		
		Post post = PostMapper.INSTANCE.postDTOToPost(postDTO);
		
		Post createdPost = postRepository.save(post);
		
        PostDTO createdPostDTO = PostMapper.INSTANCE.postToPostDTO(createdPost);

		return createdPostDTO;
	}

	public PostDTO getPost(Long id) {

		Post post = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Identificador invalido:" + id));
		
		PostDTO postDTO = PostMapper.INSTANCE.postToPostDTO(post);

		return postDTO;
	}
	
	public List<PostDTO> getPosts() {

		List<Post> allPosts = postRepository.findAll();
		
		List<PostDTO> allPostDTOs = PostMapper.INSTANCE.postsToPostDTOs(allPosts);

		return allPostDTOs;
	}

	@Transactional
	public PostDTO updatePost(PostDTO postDTO, Long id) {
		
		Post post = PostMapper.INSTANCE.postDTOToPost(postDTO);
		
		if (post.getId() != id) {
			throw new IllegalArgumentException("Identificador invalido:" + id);
		}
		
		if (!postRepository.existsById(id)) {
			throw new IllegalArgumentException("Identificador invalido:" + id);
		}
		
		Post updatedPost = postRepository.save(post);
		
		PostDTO updatedPostDTO = PostMapper.INSTANCE.postToPostDTO(updatedPost);

		return updatedPostDTO;
	}

	@Transactional
	public void delete(Long id) {
		
		if (!postRepository.existsById(id)) {
			throw new IllegalArgumentException("Identificador invalido:" + id);
		}
		
		postRepository.deleteById(id);
	}
}
