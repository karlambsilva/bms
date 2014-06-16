package training.bms.presentation;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang.StringEscapeUtils;

import training.bms.business.Post;
import training.bms.business.PostController;
import training.bms.business.PostSearchOptions;

@ManagedBean
public class ShowPost {

	private int postId;
	private Post post;
	
	public int getPostId() {
		return postId;
	}
	
	public void setPostId(int postId) {

		this.postId = postId;
		
		PostSearchOptions options =  new PostSearchOptions();
		options.setPostId(postId);
		
		PostController controller = new PostController();
		List<Post> posts = controller.searchPost(options);
		
		if (posts.size() > 0){
			post = posts.get(0);
		}
	}
	
	public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	
	public StringBuilder getPostBody() {
		String escapedBody = StringEscapeUtils.escapeHtml(post.getBody());
		StringBuilder body = new StringBuilder();
		
		body.append("<p>");
		
		for (int i = 0; i < escapedBody.length(); ++i){
			char c = escapedBody.charAt(i);
			
			if ((c == '\n' || c == '\r')){
				body.append("</p><p>");
			}else{
				body.append(c);
			}
		}
		
		body.append("</p>");
		return body;
	}
}
