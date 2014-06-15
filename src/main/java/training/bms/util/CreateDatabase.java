package training.bms.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import training.bms.business.Blog;
import training.bms.business.Post;

public class CreateDatabase {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BMS");
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		Blog blog = new Blog();
		blog.setName("Karla");
		blog.setDescription("Blog da Karla");
		manager.persist(blog);
				
		
		Post post = new Post();
		post.setTitle("Tecnologia");
		post.setBody("la la la la la la la ala ala a la a");
		post.setAuthor("Karla");
		post.setCreationDate(new Date());
		post.setBlog(blog);
		
				
		/*
		 * blog.getPosts().add(post);
		 * manager.persist(blog);
		 */
		manager.persist(post);
		
		transaction.commit();

		/*
		TypedQuery<Blog> query = manager.createQuery(
				"SELECT blog FROM training.bms.business.Blog blog WHERE UPPER(blog.name) = :blogName", 
				Blog.class);
		query.setParameter("blogName", "KARLA");
		List<Blog> result = query.getResultList();
		System.out.println(result);
		*/
		
		post = manager.find(Post.class, 1);
		System.out.println(post.getTitle());
		//blog = post.getBlog();
		System.out.println(blog.getName());
	}
}
