package fr.epita.quiz.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Question")
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="Q_TITLE")
	private String title;
	
	@OneToMany(fetch=FetchType.EAGER)
	List<MCQChoice> mcqchoices;
	
	public Question() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<MCQChoice> getMcqchoices() {
		return mcqchoices;
	}

	public void setMcqchoices(List<MCQChoice> mcqchoices) {
		this.mcqchoices = mcqchoices;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", mcqchoisces=" + mcqchoices.toString() + "]";
	}
}
