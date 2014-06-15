package training.bms.presentation;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.hibernate.event.spi.PostCollectionRecreateEvent;

import training.bms.business.Blog;
import training.bms.business.BlogController;
import training.bms.business.BlogSearchOptions;
import training.bms.business.Post;
import training.bms.business.PostController;
import training.bms.business.PostSearchOptions;
import training.bms.business.PostSearchOptions.Order;

@ManagedBean
public class ShowBlog {

	private Blog blog;
	private int blogId;
	private List<Post> posts;
	
	public Blog getBlog() {
		return blog;
	}
	
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public int getBlogId() {
		return blogId;
	}
	
	public void setBlogId(int blogId) {
		this.blogId = blogId;
		
		BlogSearchOptions options = new BlogSearchOptions();
		options.setId(blogId);
		
		BlogController controller = new BlogController();
		List <Blog> blogs = controller.searchBlog(options);
		
		if (blogs.size() > 0){
			blog = blogs.get(0);
						
			PostSearchOptions postOptions = new PostSearchOptions();
			postOptions.setOrder(Order.CREATION_DATE);
			postOptions.setDesc(true);
			
			postOptions.setBlogId(blogId);
			
			PostController postController = new PostController();
			posts = postController.searchPost(postOptions);
		}
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
}
