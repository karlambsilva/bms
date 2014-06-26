package training.bms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import training.bms.persistence.BlogDao;

@Component
public class BlogController {
	
	private @Autowired BlogDao dao;
		
	public BlogDao getDao() {
		return dao;
	}
	
	public void setDao(BlogDao dao) {
		this.dao = dao;
	}

	public void saveBlog(Blog blog) {
		if(dao.containsBlog(blog.getName())){
			throw new BusinessException("There is a blog named " + blog.getName() + " already");
		}else{
			dao.insertBlog(blog);
		}
		
	}

	public List<Blog> searchBlog(BlogSearchOptions options) {
		
		return dao.searchBlog(options);
	}

	public void deleteBlog(Blog blog) {
		dao.deleteBlog(blog);
	}

	public void updateBlog(Blog blog) {
		Blog databaseBlog = dao.searchBlog(blog.getName());
		
		if (databaseBlog == null){
			dao.updateBlog(blog);
		}else{
			if (blog.getId().equals(databaseBlog.getId())){
				dao.updateBlog(blog);
			}else{
				throw new BusinessException("There is a blog named " + blog.getName() + " already");
			}
		}
	}


}
