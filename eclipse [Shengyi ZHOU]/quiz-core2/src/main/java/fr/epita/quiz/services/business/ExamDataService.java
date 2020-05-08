package fr.epita.quiz.services.business;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.dao.AnswerDAO;
import fr.epita.quiz.services.dao.QuestionDAO;
import fr.epita.quiz.services.dao.StudentDAO;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!No longer use!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class ExamDataService {
	
	@Inject
	StudentDAO studentDAO;
	
	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	AnswerDAO answerDAO;
	
	@Transactional(value = TxType.REQUIRED)
	public void saveStudent(Student student) {
		studentDAO.create(student);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void saveQuestion(Question question) {
		questionDAO.create(question);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public Student getStudent(long id) {
		return studentDAO.getById(id);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public Question getQuestion(long id) {
		return questionDAO.getById(id);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public List<Question> searchQuestion(Question criteria) {
		return questionDAO.search(criteria);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public List<Student> searchStudent(Student criteria) {
		return studentDAO.search(criteria);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public List<Answer> searchAnswer(Answer criteria) {
		return answerDAO.search(criteria);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void updateAnswer(Answer criteria) {
		answerDAO.update(criteria);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void deleteAnswer(Answer criteria) {
		answerDAO.delete(criteria);
	}
	
	
	@Transactional(value = TxType.REQUIRED)
	public void answerToQuestion(Student student, Question question, Answer answer) throws ExamBusinessException{
		//check values
		if (student==null || question==null) {
			//if not valid : throw exception?
		}
		
		//main logic part
		//checks if the question exists in db, same for student
		//		first : check if there's an id
		//  call dao.getById(obj) to check if it there
//		if (!studentDAO.getById(student.getId()).equals(student) || !questionDAO.getById(question.getId()).equals(question)) {
//			//otherwise : throw exception?
//		}
		//Assing student and questions to the answer
		answer.setStudent(student);
		answer.setQuestion(question);
		answerDAO.create(answer);
	}
	
//	public Answer getAnswer(Student student, Question question) {
//		
//	}
	
	//public answerToMCQ(Student student, MCQQuestion mcq, List<MCQChoices> choices);
	

}
