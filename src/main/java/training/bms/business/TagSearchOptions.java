package training.bms.business;

public class TagSearchOptions {
	public enum Order {
		NAME("name");
		
		private String value;
		
		private Order(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}

	private Integer tagId;
	private String name;
	private Order order;
	private boolean desc;
	private Integer startPosition;
	private Integer maxResults;
	
	public TagSearchOptions() {
		order = Order.NAME;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setDesc(boolean desc) {
		this.desc = desc;
	}
	
	public boolean getDesc() {
		return desc;
	}
	
	public void setStartPosition(Integer startPosition) {
		this.startPosition = startPosition;
	}
	
	public Integer getStartPosition() {
		return startPosition;
	}
	
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	
	public Integer getMaxResults() {
		return maxResults;
	}

	public Integer getTagId() {
		return tagId;
	}
	
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
}