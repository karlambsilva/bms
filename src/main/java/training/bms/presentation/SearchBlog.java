package training.bms.presentation;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.bms.business.Blog;
import training.bms.business.BlogController;
import training.bms.business.BlogSearchOptions;
import training.bms.business.BusinessException;

//@ManagedBean
//@SessionScoped
@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchBlog {
	
	private List<Blog> result;
	private BlogSearchOptions options;
	private Blog blog;
	private boolean blogDeleted;
	private @Autowired BlogController controller;
	
	public SearchBlog() {
		reset();
	}
	
	public void reset(){
		options = new BlogSearchOptions();
		result = null;
	}
	
	public List<Blog> getResult() {
		return result;
	}
	
	public void setResult(List<Blog> result) {
		this.result = result;
	}

	public BlogSearchOptions getOptions() {
		return options;
	}
	
	public void setOptions(BlogSearchOptions options) {
		this.options = options;
	}
	
	public Blog getBlog() {
		return blog;
	}
	
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public boolean isBlogDeleted() {
		return blogDeleted;
	}
	
	public void setBlogDeleted(boolean blogDeleted) {
		this.blogDeleted = blogDeleted;
	}
	
	public void search(){
		result = controller.searchBlog(options);
	}
	
	public String update(Blog blog){
		Blog blogAux = new Blog();
		blogAux.setId(blog.getId());
		blogAux.setName(blog.getName());
		blogAux.setDescription(blog.getDescription());
		this.blog = blogAux;
		return "updateBlog";
	}
	
	public void confirmUpdate(){
				
		FacesMessage message = new FacesMessage();
		
		try{
			controller.updateBlog(blog);
			reset();
			message.setSummary("Blog successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		//context.addMessage("form:name", message);
	}
	
	public String delete(Blog blog){
		this.blog = blog;
		this.blogDeleted = false;
		return "deleteBlog";
	}
	
	public void confirmDeletion(){		
		
		controller.deleteBlog(blog);
		this.blogDeleted = true;
		reset();
		
		FacesMessage message = new FacesMessage();
		message.setSummary("Blog successufully deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
		
	}
}
