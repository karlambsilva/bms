package training.bms.presentation;

import training.bms.business.Tag;

public class TagForm {
	private Tag tag;
	
	public TagForm() {
		tag = new Tag();
	}
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	public Tag getTag() {
		return tag;
	}
}