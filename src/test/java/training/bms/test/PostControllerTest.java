package training.bms.test;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.Assert;

import training.bms.persistence.PostDao;

public class PostControllerTest {
	
	/*
	@Test
	public void happyTest(){
		Post post = new Post();
		post.setTitle("Inclusão digital!");
		post.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ut erat tortor. Maecenas congue "
				+ "ullamcorper leo. Etiam iaculis risus vitae est rhoncus, vitae ultricies quam lacinia. Nullam aliquet, sapien"
				+ " sit amet dignissim elementum, eros justo tincidunt augue, eget adipiscing diam orci sed sem. Suspendisse "
				+ "blandit, metus ut posuere volutpat, risus ligula bibendum arcu, id malesuada arcu risus eu mauris. Aliquam "
				+ "aliquet id sem non lacinia. Vestibulum a blandit tellus.");
		
		PostDao dao = EasyMock.createMock(PostDao.class);
		EasyMock.expect(dao.containsPost("Karla")).andReturn(false);
		
		dao.insertPost(post);
		EasyMock.replay(dao);
		
		PostController controller = new PostController();
		controller.setDao(dao);
		controller.savePost(post);
		
		EasyMock.verify(dao);
	}
	
	@Test
	public void unhappyTest(){
		Post post = new Post();
		post.setName("Karla");
		post.setDescription("Post da Karla");
		
		PostDao dao = EasyMock.createMock(PostDao.class);
		EasyMock.expect(dao.containsPost("Karla")).andReturn(true);
		
		//dao.insertPost(post); não vai ser chamado pelo controlodor pq já existe um post de nome "Karla"
		EasyMock.replay(dao);
		
		PostController controller = new PostController();
		controller.setDao(dao);
		
		
		// se eu chamar o método e ele não levanta uma exceção.. eu faço o teste falhar
		
		try{
			controller.savePost(post); //envolvo linha que deve levantar exceção
			Assert.fail("Exceção não levantada"); //caso não levante.. por padrão, ele continua o codigo para o proxima linha.. mas faco o teste falhar
		}catch(BusinessException e){
			
		}
		EasyMock.verify(dao);
	}
	
	@Test
	public void testSearchPost(){
		PostSearchOptions options = new PostSearchOptions();
		options.setName("Karla");
		
		PostDao dao = EasyMock.createMock(PostDao.class);
		//EasyMock.expect(dao.searchPost("Karla")).andReturn(new ArrayList<Post>());
		
		//EasyMock.replay(dao);
		
		PostController controller = new PostController();
		controller.searchPost(options);
		
		//EasyMock.verify(dao);		
	}
	
	@Test
	public void happyTestUpdatePostNull(){
		Post post = new Post();
		post.setId(1);
		post.setName("Karla");
		post.setDescription("Post da Karla");
		
		PostDao dao = EasyMock.createMock(PostDao.class);
		EasyMock.expect(dao.searchPost("Karla")).andReturn(null);
		
		dao.updatePost(post);
		EasyMock.replay(dao);
		
		PostController controller = new PostController();
		controller.setDao(dao);
		controller.updatePost(post);
		
		EasyMock.verify(dao);
	}
	
	@Test
	public void happyTestUpdatePostSameIds(){
		Post post = new Post();
		post.setId(1);
		post.setName("Karla");
		post.setDescription("Post da Karla");
		
		Post databasePost = new Post();
		databasePost.setId(1);
		databasePost.setName("Karla");
		databasePost.setDescription("Post da Karla Silva");
		
		PostDao dao = EasyMock.createMock(PostDao.class);
		EasyMock.expect(dao.searchPost("Karla")).andReturn(databasePost);
		
		dao.updatePost(post);
		EasyMock.replay(dao);
		
		PostController controller = new PostController();
		controller.setDao(dao);
		controller.updatePost(post);
		
		EasyMock.verify(dao);
	}
	
	@Test
	public void unhappyTestUpdatePost(){

		Post post = new Post();
		post.setId(1);
		post.setName("Karla");
		post.setDescription("Post da Karla");
		
		Post databasePost = new Post();
		databasePost.setId(2);
		databasePost.setName("Karla");
		databasePost.setDescription("Post da Karla Silva");
		
		PostDao dao = EasyMock.createMock(PostDao.class);
		EasyMock.expect(dao.searchPost("Karla")).andReturn(databasePost);
		//dao.updatePost(post);  não espero que o update seja chamado pq eu não vou atualizar.
		EasyMock.replay(dao);
		
		PostController controller = new PostController();
		controller.setDao(dao);
		
		try{
			controller.updatePost(post); //envolvo linha que deve levantar exceção
			Assert.fail("Exceção não levantada"); //caso não levante.. por padrão, ele continua o codigo para o proxima linha.. mas faco o teste falhar
		}catch(BusinessException e){
			
		}
		EasyMock.verify(dao);
	}*/
}
