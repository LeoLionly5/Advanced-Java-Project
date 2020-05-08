package fr.epita.quiz.resources;

import fr.epita.quiz.datamodel.MCQChoice;

public class MCQChoiceDTO {
	
	private String choice;
	
	private boolean valid;
	
	//private MCQAnswer mcqanswer;
	
	//private Question question;
	
	public MCQChoiceDTO() {
		
	}
	
	public MCQChoiceDTO(MCQChoice entity) {
		this.choice = entity.getChoice();
		this.valid = entity.isValid();
		//this.mcqanswer = entity.getMcqanswer();
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

//	public Question getQuestion() {
//		return question;
//	}
//
//	public void setQuestion(Question question) {
//		this.question = question;
//	}
}
