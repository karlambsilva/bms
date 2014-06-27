package training.bms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.bms.business.BusinessException;
import training.bms.business.Tag;
import training.bms.business.TagController;
import training.bms.business.TagSearchOptions;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchTag {

	
	private static final int RESULTS_PER_PAGE = 2;

	private @Autowired TagController controller;
	private TagSearchOptions options;
	private List<Integer> pages;
	private int page;
	private List<Tag> result;
	private TagForm form;
	private boolean tagDeleted;
	
	public SearchTag() {
		reset();
	}
	
	public void reset() {
		options = new TagSearchOptions();
		result = null;
	}	
	
	public void setOptions(TagSearchOptions options) {
		this.options = options;
	}
	
	public TagSearchOptions getOptions() {
		return options;
	}
	
	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
	
	public List<Integer> getPages() {
		return pages;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setResult(List<Tag> result) {
		this.result = result;
	}
	
	public List<Tag> getResult() {
		return result;
	}
	
	public void setForm(TagForm form) {
		this.form = form;
	}
	
	public TagForm getForm() {
		return form;
	}
		
	public void setTagDeleted(boolean tagDeleted) {
		this.tagDeleted = tagDeleted;
	}
	
	public boolean getTagDeleted() {
		return tagDeleted;
	}
	
	public void search() {
		int resultCount = controller.searchTagCount(options);
		int pageCount = resultCount / RESULTS_PER_PAGE;
		if (resultCount % RESULTS_PER_PAGE > 0) {
			++pageCount;
		}
		pages = new ArrayList<Integer>();
		for (int page = 1; page <= pageCount; ++page) {
			pages.add(page);
		}
		goToPage(1);
	}
	
	public void goToPage(int page) {
		this.page = page;
		
		int startPosition = (page - 1) * RESULTS_PER_PAGE;
		options.setStartPosition(startPosition);
		options.setMaxResults(RESULTS_PER_PAGE);
		result = controller.searchTag(options);
	}
	
	public String getPageClass(int page) {
		if (page == this.page) {
			return "active"; 
		} else {
			return "";
		}
	}
	
	public String update(Tag tag) {
		
		TagSearchOptions options = new TagSearchOptions();
		options.setTagId(form.getTag().getId());
		Tag tagAux = controller.searchTag(options).get(0);
		
		this.form = new TagForm();
		this.form.setTag(tagAux);
		return "updateTag";
	}
	
	public void confirmUpdate() {
		String cliendId;
		FacesMessage message = new FacesMessage();
		try {
			controller.updateTag(form.getTag());
			reset();
			cliendId = null;			
			message.setSummary("Tag was successfully updated");
			message.setSeverity(FacesMessage.SEVERITY_INFO);			
		} catch (BusinessException e) {
			cliendId = "form:tag:name";
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);						
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(cliendId, message);
	}
		
	public String delete(Tag tag) {
		this.form = new TagForm();
		this.form.setTag(tag);
		this.tagDeleted = false;
		return "deleteTag";
	}
	
	public void confirmDeletion() {
		TagSearchOptions options = new TagSearchOptions();
		options.setTagId(form.getTag().getId());
		Tag tag = controller.searchTag(options).get(0);
		
		controller.deleteTag(tag);
		tagDeleted = true;
		reset();
		FacesMessage message = new FacesMessage();
		message.setSummary("Tag was successfully deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);		
	}
}

	
