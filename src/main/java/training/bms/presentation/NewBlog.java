package training.bms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import training.bms.business.Blog;
import training.bms.business.BlogController;
import training.bms.business.BusinessException;

@ManagedBean
public class NewBlog {
	
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
			BlogController controller = new BlogController();
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
