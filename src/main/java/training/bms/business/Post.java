package training.bms.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import training.bms.util.CreateDatabase;

@Entity
@Table(name="POS_POST")
public class Post implements Cloneable{

	private Integer id;
	private String title;
	private String body;
	private Date creationDate;
	private String author;
	private Blog blog;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="POS_ID")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Size(min=1, max=100)
	@Column(name="POS_TITLE")
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Size(min=1, max=1000)
	@Column(name="POS_BODY")
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="POS_CREATION_DATE")
	public Date getCreationDate() {
		return creationDate;
	}
	
	@Column(name="POS_AUTHOR")
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@ManyToOne()
	@JoinColumn(name="BLO_ID")
	public Blog getBlog() {
		return blog;
	}
	
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
}
