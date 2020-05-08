package fr.epita.quiz.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Answer")

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!No longer use!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class Answer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="A_CONTENT")
	private String content;
	
	@ManyToOne
    @JoinColumn(name="A_questionFK")
	private Question question;
	
//	@ManyToOne
//    @JoinColumn(name="A_examFK")
//	private Exam exam;
//	
	@ManyToOne
    @JoinColumn(name="A_studentFK")
	private Student student;
	
	public Answer() {
		
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

//	public Exam getExam() {
//		return exam;
//	}
//
//	public void setExam(Exam exam) {
//		this.exam = exam;
//	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", content=" + content + ", question=" + question.toString() + ", student=" + student.toString() + "]";
	}
}
