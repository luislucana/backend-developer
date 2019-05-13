package br.com.blog.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.blog.persistence.model.Post;
import br.com.blog.service.dto.PostDTO;
import br.com.blog.web.controller.PostResponse;

/**
 * Interface Mapper.
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
@Mapper
public interface PostMapper {
	
	PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostResponse postToPostResponse(Post post);

    PostDTO postToPostDTO(Post post);
    
    List<PostDTO> postsToPostDTOs(List<Post> posts);
    
    Post postDTOToPost(PostDTO postDTO);
}
