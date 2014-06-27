package training.bms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.bms.business.Blog;
import training.bms.business.BlogController;
import training.bms.business.BlogSearchOptions;
import training.bms.business.Post;
import training.bms.business.Tag;
import training.bms.business.TagController;
import training.bms.business.TagSearchOptions;

public class PostForm {

	private List <Blog> blogs;
	private List<Tag> tags;
	private Post post;
	
	public PostForm() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils.getWebApplicationContext(facesContext);
		
		applicationContext.getBean(BlogController.class);

		
		BlogController controller = applicationContext.getBean(BlogController.class);	
		blogs = controller.searchBlog(new BlogSearchOptions());
		
		TagController tagController = applicationContext.getBean(TagController.class);
		tags = tagController.searchTag(new TagSearchOptions());
		
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
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
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
	
	public void setTagIds(List<String> tagIds){
		post.getTags().clear();
		for (String tagId: tagIds){
			for (Tag tag : tags){
				if ((tag.getId().toString()).equals(tagId)){
					post.getTags().add(tag);
				}
			}
		}
	}
	
	public List<String> getTagIds() {		
		List<String> tagIds = new ArrayList<>();
		
		for (Tag tag: post.getTags()){
			tagIds.add(tag.getId().toString());
		}
		
		return tagIds;
	}
	
}
