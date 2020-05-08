package fr.epita.quiz.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MCQChoice")
public class MCQChoice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="MCQC_choice")
	private String choice;
	
	@Column(name="MCQC_valid")
	private boolean valid;
	
//	@ManyToOne
//    @JoinColumn(name="MCQC_questionFK")
//	private Question question;
	
//	@OneToMany(mappedBy="mcqchoice")
//	List<MCQAnswer> mcqanswers;
//	@OneToOne
//	private MCQAnswer mcqanswer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

//	public MCQAnswer getMcqanswer() {
//		return mcqanswer;
//	}
//
//	public void setMcqanswer(MCQAnswer mcqanswer) {
//		this.mcqanswer = mcqanswer;
//	}

	@Override
	public String toString() {
		return "MCQChoice [id=" + id + ", choice=" + choice + ", valid=" + valid + "]";
	}

//	public Question getQuestion() {
//		return question;
//	}
//
//	public void setQuestion(Question question) {
//		this.question = question;
//	}
//
//	@Override
//	public String toString() {
//		return "MCQChoice [id=" + id + ", choice=" + choice + ", valid=" + valid + ", question=" + question.toString() + "]";
//	}
	
}
