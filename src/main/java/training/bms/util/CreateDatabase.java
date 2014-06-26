package training.bms.util;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import training.bms.business.Blog;
import training.bms.business.Post;
import training.bms.business.Tag;

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
		
		Tag tag1 = new Tag();
		tag1.setName("Tag 1");
		manager.persist(tag1);
		
		Tag tag2 = new Tag();
		tag2.setName("Tag 2");
		manager.persist(tag2);
		
		post.getTags().add(tag1);
		post.getTags().add(tag2);
				
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
		System.out.println(post.getTags());
		System.out.println(post.getTitle());
		//blog = post.getBlog();
		System.out.println(blog.getName());
		
		/* Explicando o manager.close();
		 * 
			manager.clear();
			post = manager.find(Post.class, 1);
			manager.close();
			System.out.println(post.getBlog());
			post.getTags();
			System.out.println(post.getTags().size());
		*/
	}
}
