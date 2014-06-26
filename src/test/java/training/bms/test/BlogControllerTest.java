package training.bms.test;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import training.bms.business.Blog;
import training.bms.business.BlogController;
import training.bms.business.BlogSearchOptions;
import training.bms.business.BusinessException;
import training.bms.persistence.BlogDao;

public class BlogControllerTest {
	
	@Test
	public void happyTest(){
		Blog blog = new Blog();
		blog.setName("Karla");
		blog.setDescription("Blog da Karla");
		
		BlogDao dao = EasyMock.createMock(BlogDao.class);
		EasyMock.expect(dao.containsBlog("Karla")).andReturn(false);
		
		dao.insertBlog(blog);
		EasyMock.replay(dao);
		
		BlogController controller = new BlogController();
		controller.setDao(dao);
		controller.saveBlog(blog);
		
		EasyMock.verify(dao);
	}
	
	@Test
	public void unhappyTest(){
		Blog blog = new Blog();
		blog.setName("Karla");
		blog.setDescription("Blog da Karla");
		
		BlogDao dao = EasyMock.createMock(BlogDao.class);
		EasyMock.expect(dao.containsBlog("Karla")).andReturn(true);
		
		//dao.insertBlog(blog); n�o vai ser chamado pelo controlodor pq j� existe um blog de nome "Karla"
		EasyMock.replay(dao);
		
		BlogController controller = new BlogController();
		controller.setDao(dao);
		
		
		// se eu chamar o m�todo e ele n�o levanta uma exce��o.. eu fa�o o teste falhar
		
		try{
			controller.saveBlog(blog); //envolvo linha que deve levantar exce��o
			Assert.fail("Exce��o n�o levantada"); //caso n�o levante.. por padr�o, ele continua o codigo para o proxima linha.. mas faco o teste falhar
		}catch(BusinessException e){
			
		}
		EasyMock.verify(dao);
	}
	
	@Test
	public void testSearchBlog(){
		BlogSearchOptions options = new BlogSearchOptions();
		options.setName("Karla");
		
		BlogDao dao = EasyMock.createMock(BlogDao.class);
		//EasyMock.expect(dao.searchBlog("Karla")).andReturn(new ArrayList<Blog>());
		
		//EasyMock.replay(dao);
		
		BlogController controller = new BlogController();
		controller.searchBlog(options);
		
		//EasyMock.verify(dao);		
	}
	
	@Test
	public void happyTestUpdateBlogNull(){
		Blog blog = new Blog();
		blog.setId(1);
		blog.setName("Karla");
		blog.setDescription("Blog da Karla");
		
		BlogDao dao = EasyMock.createMock(BlogDao.class);
		EasyMock.expect(dao.searchBlog("Karla")).andReturn(null);
		
		dao.updateBlog(blog);
		EasyMock.replay(dao);
		
		BlogController controller = new BlogController();
		controller.setDao(dao);
		controller.updateBlog(blog);
		
		EasyMock.verify(dao);
	}
	
	@Test
	public void happyTestUpdateBlogSameIds(){
		Blog blog = new Blog();
		blog.setId(1);
		blog.setName("Karla");
		blog.setDescription("Blog da Karla");
		
		Blog databaseBlog = new Blog();
		databaseBlog.setId(1);
		databaseBlog.setName("Karla");
		databaseBlog.setDescription("Blog da Karla Silva");
		
		BlogDao dao = EasyMock.createMock(BlogDao.class);
		EasyMock.expect(dao.searchBlog("Karla")).andReturn(databaseBlog);
		
		dao.updateBlog(blog);
		EasyMock.replay(dao);
		
		BlogController controller = new BlogController();
		controller.setDao(dao);
		controller.updateBlog(blog);
		
		EasyMock.verify(dao);
	}
	
	@Test
	public void unhappyTestUpdateBlog(){

		Blog blog = new Blog();
		blog.setId(1);
		blog.setName("Karla");
		blog.setDescription("Blog da Karla");
		
		Blog databaseBlog = new Blog();
		databaseBlog.setId(2);
		databaseBlog.setName("Karla");
		databaseBlog.setDescription("Blog da Karla Silva");
		
		BlogDao dao = EasyMock.createMock(BlogDao.class);
		EasyMock.expect(dao.searchBlog("Karla")).andReturn(databaseBlog);
		//dao.updateBlog(blog);  n�o espero que o update seja chamado pq eu n�o vou atualizar.
		EasyMock.replay(dao);
		
		BlogController controller = new BlogController();
		controller.setDao(dao);
		
		try{
			controller.updateBlog(blog); //envolvo linha que deve levantar exce��o
			Assert.fail("Exce��o n�o levantada"); //caso n�o levante.. por padr�o, ele continua o codigo para o proxima linha.. mas faco o teste falhar
		}catch(BusinessException e){
			
		}
		EasyMock.verify(dao);
	}
}
