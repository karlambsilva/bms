package training.bms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.bms.business.Blog;
import training.bms.business.BlogController;
import training.bms.business.BusinessException;

//@ManagedBean
@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewBlog {
	
	private @Autowired BlogController controller;
	private Blog blog;
	
	public NewBlog() {
		blog = new Blog();
	}
	
	public Blog getBlog() {
		return blog;
	}
	
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public void save(){				
		FacesMessage message = new FacesMessage();
		
		try{
			controller.saveBlog(blog);
			message.setSummary("Blog successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("form:blog:name", message);
		
	}

}
