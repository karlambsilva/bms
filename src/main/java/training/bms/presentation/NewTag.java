package training.bms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.bms.business.BusinessException;
import training.bms.business.Tag;
import training.bms.business.TagController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewTag {
	
	private Tag tag;
	private @Autowired TagController controller;
	
	public NewTag() {
		tag = new Tag();
	}
	
	public Tag getTag() {
		return tag;
	}
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	public void save(){				
		FacesMessage message = new FacesMessage();
		
		try{
			controller.saveTag(tag);
			message.setSummary("Tag successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
	}

}
