package br.com.blog;

import br.com.blog.persistence.model.Post;
import br.com.blog.persistence.repository.PostRepository;
import br.com.blog.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @InjectMocks
	private PostService service;

    @Mock
	private PostRepository repository;

	private List<Post> posts;

	@Test
	public void retornarTodosOsPosts() {
		dadaUmaListaDePosts();
		gravarPosts();
		retornarTodosOsPostsGravados();
	}

	private void dadaUmaListaDePosts() {
		posts = new ArrayList<>();

		Post post1 = new Post();
		post1.setId(1l);
		post1.setTitle("Meu primeiro post!");
		post1.setDescription("Este é o meu primeiro post.");
		post1.setPublicationDate(new Date());

		Post post2 = new Post();
		post2.setId(2l);
		post2.setTitle("Meu segundo post");
		post2.setDescription("Segundo post. Não sei mais o que escrever aqui..");
		post2.setPublicationDate(new Date());

		posts.add(post1);
		posts.add(post2);
	}

	private void gravarPosts() {
		repository.saveAll(posts);
	}

	private void retornarTodosOsPostsGravados() {
		List<Post> todosOsPosts = repository.findAll();
		assertEquals(2, todosOsPosts.size());
		assertThat(todosOsPosts, hasItems(posts.get(0), posts.get(1)));
	}
}
