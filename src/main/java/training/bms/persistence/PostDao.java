package training.bms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import training.bms.business.Post;
import training.bms.business.PostSearchOptions;

public class PostDao {		
	
	public boolean containsPost(String postTitle){

		return searchPost(postTitle) != null;
	}
	
	public void insertPost(Post post){
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		manager.persist(post);
		transaction.commit();
		
	}
	
	public Post searchPost(String postTitle){
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();	
		TypedQuery<Post> query = manager.createQuery(
				"SELECT post FROM training.bms.business.Post post WHERE UPPER(post.title) = :postTitle", 
				Post.class);
		query.setParameter("postTitle", postTitle.toUpperCase());
		List<Post> result = query.getResultList();
		if (result.isEmpty()){
			return null;
		}else{
			return result.get(0);
		}
	}

	public List<Post> searchPost(PostSearchOptions options) {
		
		StringBuilder predicate = toPredicate(options);
		
		if(options.getOrder() != null){
			predicate.append(" order by post.");
			predicate.append(options.getOrder().getValue());
			if(options.isDesc()){
				predicate.append(" desc");
			}
		}	
		
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();	
		TypedQuery<Post> query = manager.createQuery(
				"SELECT post FROM training.bms.business.Post post where " + predicate, 
				Post.class);		
		
		setParameters(options, query);
		
		if (options.getStartPosition() != null){
			query.setFirstResult(options.getStartPosition());
		}
		
		if (options.getMaxResults() != null){
			query.setMaxResults(options.getMaxResults());
		}
		
		List<Post> result = query.getResultList();
		
		return result;
		
	}
	
	public int searchPostCount(PostSearchOptions options) {
		
		StringBuilder predicate = toPredicate(options);			

		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();	
		TypedQuery<Long> query = manager.createQuery(
				"SELECT count(post) FROM training.bms.business.Post post where " + predicate, 
				Long.class);		
		
		setParameters(options, query);
		
		Long result = query.getSingleResult();
		
		return result.intValue();		
		
	}	


	private StringBuilder toPredicate(PostSearchOptions options) {
		StringBuilder predicate = new StringBuilder("1 = 1");
		
		if (options.getBlogId() != null){
			predicate.append(" and post.blog.id = :blogId");
		}
		
		if (options.getPostId() != null){
			predicate.append(" and post.id = :postId");
		}
		
		if(options.getTitle() != null && options.getTitle().length() > 0){
			predicate.append(" and upper(post.title) like :postTitle");
		}
		
		if(options.getBody() != null && options.getBody().length() > 0){
			predicate.append(" and upper(post.body) like :postBody");
		}		
		
		if(options.getAuthor() != null && options.getAuthor().length() > 0){
			predicate.append(" and upper(post.author) like :postAuthor");
		}
		return predicate;
	}
	
	private void setParameters(PostSearchOptions options, TypedQuery<?> query) {
		if (options.getBlogId() != null){
			query.setParameter("blogId", options.getBlogId());
		}
		
		if (options.getPostId() != null){
			query.setParameter("postId", options.getPostId());
		}
		
		if(options.getTitle() != null && options.getTitle().length() > 0){
			query.setParameter("postTitle", "%" + options.getTitle().toUpperCase() + "%");
		}
		
		if(options.getBody() != null && options.getBody().length() > 0){
			query.setParameter("postBody", "%" + options.getBody().toUpperCase() + "%");
		}
		
		if(options.getAuthor() != null && options.getAuthor().length() > 0){
			query.setParameter("postAuthor", "%" + options.getAuthor().toUpperCase() + "%");
		}
	}

	public void deletePost(Post post) {
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		Post managedPost = manager.find(Post.class, post.getId());
		
		transaction.begin();
		manager.remove(managedPost);
		transaction.commit();
	}

	public void updatePost(Post post) {

		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		manager.merge(post);
		transaction.commit();
		
	}
}
