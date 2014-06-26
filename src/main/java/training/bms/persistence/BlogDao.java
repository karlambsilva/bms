package training.bms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.bms.business.Blog;
import training.bms.business.BlogSearchOptions;

@Component
public class BlogDao {	
	
	private @PersistenceContext EntityManager manager;
	
	public boolean containsBlog(String blogName){

		return searchBlog(blogName) != null;
	}
	
	public void insertBlog(Blog blog){	
		manager.persist(blog);		
	}
	
	public Blog searchBlog(String blogName){
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
		Blog managedBlog = manager.find(Blog.class, blog.getId());
		
		manager.remove(managedBlog);
	}

	public void updateBlog(Blog blog) {
		manager.merge(blog);
	}
}
