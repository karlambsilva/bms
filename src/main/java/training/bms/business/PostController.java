package training.bms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import training.bms.persistence.PostDao;

@Component
public class PostController {
	
	private @Autowired PostDao dao;
	
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
