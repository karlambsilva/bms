package training.bms.business;

import java.util.List;

import javax.management.RuntimeErrorException;

import training.bms.persistence.PostDao;

public class PostController {
	
	private PostDao dao;
	
	public PostController(){
		dao = new PostDao();
	}
	
	public PostDao getDao() {
		return dao;
	}
	
	public void setDao(PostDao dao) {
		this.dao = dao;
	}

	public void savePost(Post post) {
		dao.insertPost(post);		
	}

	public List<Post> searchPost(PostSearchOptions options) {		
		return dao.searchPost(options);
	}
	
	public Integer searchPostCount(PostSearchOptions options) {		
		return dao.searchPostCount(options);
	}

	public void deletePost(Post post) {
		dao.deletePost(post);
	}

	public void updatePost(Post post) {
		dao.updatePost(post);
	}

}
