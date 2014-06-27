package training.bms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.bms.business.Tag;
import training.bms.business.TagSearchOptions;

@Component
public class TagDao {
	
	private @PersistenceContext EntityManager manager;
	
	public boolean containsTag(String tagName){

		return searchTag(tagName) != null;
	}
	
	public void insertTag(Tag tag){	
		manager.persist(tag);		
	}
	
	public Tag searchTag(String tagName){
		TypedQuery<Tag> query = manager.createQuery(
				"SELECT tag FROM training.bms.business.Tag tag WHERE UPPER(tag.name) = :tagName", 
				Tag.class);
		query.setParameter("tagName", tagName.toUpperCase());
		List<Tag> result = query.getResultList();
		if (result.isEmpty()){
			return null;
		}else{
			return result.get(0);
		}
	}

	public List<Tag> searchTag(TagSearchOptions options) {
		
		StringBuilder predicate = new StringBuilder("1 = 1");
		
		if(options.getTagId() != null){
			predicate.append(" and tag.id = :tagId");
		}
		
		if(options.getName() != null && options.getName().length() > 0){
			predicate.append(" and upper(tag.name) like :tagName");
		}
		
		TypedQuery<Tag> query = manager.createQuery(
				"SELECT tag FROM training.bms.business.Tag tag where " + predicate, 
				Tag.class);
		
		if(options.getTagId() != null){
			query.setParameter("tagId", options.getTagId());
		}
		
		if(options.getName() != null && options.getName().length() > 0){
			query.setParameter("tagName", "%" + options.getName().toUpperCase() + "%");
		}
		
		List<Tag> result = query.getResultList();
		
		return result;
	}

	public void deleteTag(Tag tag) {		
		Tag managedTag = manager.find(Tag.class, tag.getId());
		manager.remove(managedTag);
	}

	public void updateTag(Tag tag) {
		manager.merge(tag);
	}
}