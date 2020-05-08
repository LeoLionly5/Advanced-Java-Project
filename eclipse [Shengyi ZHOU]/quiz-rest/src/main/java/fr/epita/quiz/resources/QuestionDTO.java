package fr.epita.quiz.resources;

import java.util.List;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;

public class QuestionDTO {
	private String title;
	
	List<MCQChoice> mcqchoices;
	
	public QuestionDTO() {
		
	}
	
	public QuestionDTO(Question entity) {
		this.title = entity.getTitle();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String content) {
		this.title = content;
	}

	public List<MCQChoice> getMcqchoices() {
		return mcqchoices;
	}

	public void setMcqchoices(List<MCQChoice> mcqchoices) {
		this.mcqchoices = mcqchoices;
	}
	
	
}
