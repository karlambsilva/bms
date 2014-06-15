package training.bms.presentation;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import training.bms.business.Blog;
import training.bms.business.BlogController;
import training.bms.business.BlogSearchOptions;
import training.bms.business.Post;
import training.bms.business.PostController;
import training.bms.business.BusinessException;

@ManagedBean
public class NewPost {
	
	private PostForm form;
	
	public NewPost() {
		form = new PostForm();		
	}
	
	public PostForm getForm() {
		return form;
	}
	
	public void setForm(PostForm form) {
		this.form = form;
	}
	
	public void save(){				
		FacesMessage message = new FacesMessage();
		
		try{
			PostController controller = new PostController();
			controller.savePost(form.getPost());
			message.setSummary("Post successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
	}

}
