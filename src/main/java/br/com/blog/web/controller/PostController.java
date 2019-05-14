package br.com.blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.service.PostService;
import br.com.blog.service.dto.PostDTO;

import com.google.common.base.Preconditions;

/**
 * Classe controller para operacoes da entidade Post.
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
@RestController
@RequestMapping(value = "/blog/post")
@Api(tags = {"Blog API"})
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Create post", notes="Creates a new post.")
	@ApiResponses(value ={@ApiResponse(code = 201, message = "Post created"/*, response = Response.class*/),
						  @ApiResponse(code = 400, message = "Bad Request"),
			  			  @ApiResponse(code = 403, message = "Forbidden"),
			  			  @ApiResponse(code = 404, message = "Not Found"),
			  			  @ApiResponse(code = 500, message = "Error"/*, response = Exception.class*/)})
	public @ResponseBody PostDTO create(@RequestBody final PostDTO postDTO) {
		Preconditions.checkNotNull(postDTO);
		
		final PostDTO createdPostDTO = postService.create(postDTO);
		
		return createdPostDTO;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = { "page", "size" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="Get posts", notes = "Gets a list of posts from database.")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
							@ApiResponse(code = 400, message = "Bad Request"),
							@ApiResponse(code = 403, message = "Forbidden"),
							@ApiResponse(code = 500, message = "Error"/*, response = Exception.class*/)})
	public Page<PostDTO> getAll() {
		
		Pageable pageable = PageRequest.of(0, 5, Sort.by("title"));
		
		Page<PostDTO> postDTOs = postService.list(pageable);
		
        return postDTOs;
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="Get post by ID", notes = "Get a post from an informed ID.")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
							@ApiResponse(code = 400, message = "Bad Request"),
							@ApiResponse(code = 403, message = "Forbidden"),
							@ApiResponse(code = 500, message = "Error"/*, response = Exception.class*/)})
	public @ResponseBody PostDTO getById(@PathVariable final Long id, final HttpServletResponse response) {
		
		final PostDTO post = postService.getById(id);

        return post;
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value="Delete post", notes = "Deletes a post given an ID.")
	@ApiResponses(value = {@ApiResponse(code = 202, message = "Accepted"),
							@ApiResponse(code = 400, message = "Bad Request"),
							@ApiResponse(code = 403, message = "Forbidden"),
							@ApiResponse(code = 500, message = "Error"/*, response = Exception.class*/)})
	public void delete(@PathVariable final Long id) {
		postService.delete(id);
	}
}
