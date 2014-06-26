package training.bms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import training.bms.persistence.TagDao;

@Component
public class TagController {
	
	private @Autowired TagDao dao;
	
	public TagDao getDao() {
		return dao;
	}
	
	public void setDao(TagDao dao) {
		this.dao = dao;
	}
	
	public void saveTag(Tag tag) {
		if(dao.containsTag(tag.getName())){
			throw new BusinessException("There is a tag named " + tag.getName() + " already");
		}else{
			dao.insertTag(tag);
		}		
	}
	
	public List<Tag> searchTag(TagSearchOptions options){		
		return dao.searchTag(options);		
	}
	
	public void deleteTag(Tag tag){
		dao.deleteTag(tag);
	}
	
	public void updateTag(Tag tag){
		Tag databaseTag = dao.searchTag(tag.getName());
		
		if (databaseTag == null){
			dao.updateTag(tag);
		}else{
			if (tag.getId().equals(databaseTag.getId())){
				dao.updateTag(tag);
			}else{
				throw new BusinessException("There is a tag named " + tag.getName() + " already");
			}
		}
	}

	

}
