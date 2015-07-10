package training.bms.presentation;

import java.util.List;

import training.bms.business.Blog;
import training.bms.business.BlogController;
import training.bms.business.BlogSearchOptions;
import training.bms.business.Post;

public class PostForm {

	private List <Blog> blogs;
	private Post post;
	
	public PostForm() {
		BlogController controller = new BlogController();		
		blogs = controller.searchBlog(new BlogSearchOptions());
		
		post = new Post();
	}
	
	public List<Blog> getBlogs() {
		return blogs;
	}
	
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	
	public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	
	public void setBlogId(Integer blogId){
		if (blogId == null){
			post.setBlog(null);
		}else{
			for(Blog blog : blogs){
				if (blog.getId().equals(blogId)){
					post.setBlog(blog);
					break;
				}
			}
		}
	}
	
	public Integer getBlogId() {
		Blog blog = post.getBlog();
		
		if(blog==null){
			return null;
		}else{
			return blog.getId();
		}
	}
	
}
