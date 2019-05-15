package br.com.blog.web.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import br.com.blog.service.dto.PostDTO;


@Component
public class PostDTOResourceAssembler implements ResourceAssembler<PostDTO, Resource<PostDTO>> {

  @Override
  public Resource<PostDTO> toResource(PostDTO postDTO) {

    // Unconditional links to single-item resource and aggregate root
	  Resource<PostDTO> orderResource = null;

    orderResource = new Resource<>(postDTO
    		//,
      //linkTo(methodOn(PostController.class).getById(postDTO.getPostDTOId())).withSelfRel()
      //,
      //linkTo(methodOn(PostController.class).getAll(null)).withRel("posts")
    );

    // Conditional links based on state of the order
    //if (order.getStatus() == Status.IN_PROGRESS) {
      //orderResource.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
      
      //orderResource.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
    //}

    return orderResource;
  }
}