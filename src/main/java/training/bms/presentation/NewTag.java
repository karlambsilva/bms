package training.bms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import training.bms.business.BusinessException;
import training.bms.business.Tag;
import training.bms.business.TagController;

@ManagedBean
public class NewTag {
	
	private Tag tag;
	
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
			TagController controller = new TagController();
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
