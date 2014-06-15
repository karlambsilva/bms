package training.bms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import training.bms.business.Blog;
import training.bms.business.BlogSearchOptions;

public class BlogDao {		
	
	public boolean containsBlog(String blogName){

		return searchBlog(blogName) != null;
	}
	
	public void insertBlog(Blog blog){
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		manager.persist(blog);
		transaction.commit();
		
	}
	
	public Blog searchBlog(String blogName){
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();	
		TypedQuery<Blog> query = manager.createQuery(
				"SELECT blog FROM training.bms.business.Blog blog WHERE UPPER(blog.name) = :blogName", 
				Blog.class);
		query.setParameter("blogName", blogName.toUpperCase());
		List<Blog> result = query.getResultList();
		if (result.isEmpty()){
			return null;
		}else{
			return result.get(0);
		}
	}

	public List<Blog> searchBlog(BlogSearchOptions options) {
		
		StringBuilder predicate = new StringBuilder("1 = 1");
		
		if(options.getId() != null){
			predicate.append(" and blog.id = :blogId");
		}
		
		if(options.getName() != null && options.getName().length() > 0){
			predicate.append(" and upper(blog.name) like :blogName");
		}
		if(options.getDescription() != null && options.getDescription().length() > 0){
			predicate.append(" and upper(blog.description) like :blogDescription");
		}
		
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();	
		TypedQuery<Blog> query = manager.createQuery(
				"SELECT blog FROM training.bms.business.Blog blog where " + predicate, 
				Blog.class);
		
		if(options.getId() != null){
			query.setParameter("blogId", options.getId());
		}
		
		if(options.getName() != null && options.getName().length() > 0){
			query.setParameter("blogName", "%" + options.getName().toUpperCase() + "%");
		}
		
		if(options.getDescription() != null && options.getDescription().length() > 0){
			query.setParameter("blogDescription", "%" + options.getDescription().toUpperCase() + "%");
		}
		
		List<Blog> result = query.getResultList();
		
		return result;
	}

	public void deleteBlog(Blog blog) {
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		Blog managedBlog = manager.find(Blog.class, blog.getId());
		
		transaction.begin();
		manager.remove(managedBlog);
		transaction.commit();
	}

	public void updateBlog(Blog blog) {

		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		manager.merge(blog);
		transaction.commit();
		
	}
}
