package com.test.http.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private TestController controller;

	@MockBean
	private JSONPlaceHolderClient placeHolderClient;

	@Test
	public void whenGetPosts_thenListPosts() {
		var posts = placeHolderClient.getPosts();
		assertTrue(posts.isEmpty());
	}

	@Test
	public void whenGetTest_thenListPosts() {
		when(placeHolderClient.getPosts()).thenReturn(List.of(new Post(1,1,"a","b")));
		var posts = controller.getTestData("asd");
		assertFalse(posts.isEmpty());
		assertEquals(1, posts.size());
	}

	@Test
	void contextLoads() {
		assertNotNull(controller);
		assertNotNull(placeHolderClient);
	}

}
