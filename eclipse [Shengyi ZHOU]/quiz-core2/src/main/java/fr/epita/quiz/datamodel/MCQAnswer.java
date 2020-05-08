package fr.epita.quiz.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MCQAnswer")
public class MCQAnswer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="MCQA_checked")
	private boolean checked;
	
	@ManyToOne
    @JoinColumn(name="MCQA_studentFK")
	private Student student;
	
	@ManyToOne
    @JoinColumn(name="MCQA_examFK")
	private Exam exam;
	
	@OneToOne
	@JoinColumn(name="MCQA_mcqchoiceFK")
	private MCQChoice mcqchoice;
	
//	@ManyToOne
//    @JoinColumn(name="MCQA_mcqchoiceFK")
//	private MCQChoice mcqchoice;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Exam getExam() {
		return exam;
	}
	
	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public MCQChoice getMcqchoice() {
		return mcqchoice;
	}

	public void setMcqchoice(MCQChoice mcqchoice) {
		this.mcqchoice = mcqchoice;
	}

	@Override
	public String toString() {
		return "MCQAnswer [id=" + id + ", checked=" + checked + ", student=" + student.toString() + ", exam=" + exam.toString() + "]";
	}
	
//	public MCQChoice getMcqchoice() {
//		return mcqchoice;
//	}
//	
//	public void setMcqchoice(MCQChoice mcqchoice) {
//		this.mcqchoice = mcqchoice;
//	}
	
}
