package fr.epita.quiz.resources;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.datamodel.MCQAnswer;
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Student;

public class MCQAnswerDTO {
	
	private boolean checked;
	
	private Student student;
	
	private Exam exam;
	
	private MCQChoice mcqchoice;
	
	public MCQAnswerDTO() {
		
	}
	
	public MCQAnswerDTO(MCQAnswer entity) {
		this.student = entity.getStudent();
		this.exam = entity.getExam();
		this.checked = entity.isChecked();
		this.mcqchoice = entity.getMcqchoice();
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
}
