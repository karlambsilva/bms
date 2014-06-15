package training.bms.business;


public class PostSearchOptions {
	
	public enum Order{
		TITLE("title"),
		CREATION_DATE("creationDate");
		
		private String value;
		
		//construtor tem que ser privado.
		private Order(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}		
	}
	
	private Integer blogId;
	private String title;
	private String body;
	private String author;	
	private boolean desc;
	private Order order;
	private Integer startPosition;
	private Integer maxResults;
	
	public PostSearchOptions(){
		order = Order.TITLE;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}	

	public Integer getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Integer startPosition) {
		this.startPosition = startPosition;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public Integer getBlogId() {
		return blogId;
	}
	
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	
	public boolean isDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "PostSearchOptions [title=" + title + ", body="
				+ body + "author=" + author + "]";
	}

}
