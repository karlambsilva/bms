package training.bms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import training.bms.business.Post;
import training.bms.business.PostController;
import training.bms.business.PostSearchOptions;
import training.bms.business.BusinessException;

@ManagedBean
@SessionScoped
public class SearchPost {
	
	private static final int RESULTS_PER_PAGE = 2;
	private List<Post> result;
	private PostSearchOptions options;
	private PostForm form;
	private boolean postDeleted;
	private List<Integer> pages;
	private int page;
	
	public SearchPost() {		
		reset();
	}
	
	public void reset(){
		options = new PostSearchOptions();
		result = null;
	}	
	
	public List<Post> getResult() {
		return result;
	}

	public void setResult(List<Post> result) {
		this.result = result;
	}

	public PostSearchOptions getOptions() {
		return options;
	}

	public void setOptions(PostSearchOptions options) {
		this.options = options;
	}

	public PostForm getForm() {
		return form;
	}

	public void setForm(PostForm form) {
		this.form = form;
	}

	public boolean isPostDeleted() {
		return postDeleted;
	}

	public void setPostDeleted(boolean postDeleted) {
		this.postDeleted = postDeleted;
	}
	
	public List<Integer> getPages() {
		return pages;
	}
	
	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}

	public void search(){		
		PostController controller = new PostController();		
		
		int resultCount = controller.searchPostCount(options);
		int pageCount = resultCount / RESULTS_PER_PAGE;
		
		if (resultCount % RESULTS_PER_PAGE > 0){
			++pageCount;
		}
		
		pages = new ArrayList<Integer>();		
		for (int page = 1; page <= pageCount; ++page){
			pages.add(page);
		}
		
		goToPage(1);
		
	}
	
	public void goToPage(int page){
		this.page = page;
		
		int startPosition = (page - 1) * RESULTS_PER_PAGE;
		options.setStartPosition(startPosition);
		options.setMaxResults(RESULTS_PER_PAGE);


		PostController controller = new PostController();
		result = controller.searchPost(options);
	}
	
	public String update(Post post){
		Post postAux = new Post();
		postAux.setId(post.getId());
		postAux.setTitle(post.getTitle());
		postAux.setBody(post.getBody());
		postAux.setAuthor(post.getAuthor());
		postAux.setCreationDate(post.getCreationDate());
		postAux.setTags(post.getTags());
		
		this.form = new PostForm();
		this.form.setPost(postAux);
		return "updatePost";
	}
	
	public void confirmUpdate(){
				
		FacesMessage message = new FacesMessage();
		
		try{
			PostController controller = new PostController();
			controller.updatePost(form.getPost());
			reset();
			message.setSummary("Post successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		//context.addMessage("form:name", message);
	}
	
	public String delete(Post post){
		this.form = new PostForm();
		this.form.setPost(post);
		this.postDeleted = false;
		return "deletePost";
	}
	
	public void confirmDeletion(){		
		
		PostController controller = new PostController();
		controller.deletePost(form.getPost());
		this.postDeleted = true;
		reset();
		
		FacesMessage message = new FacesMessage();
		message.setSummary("Post successufully deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
		
	}
}
