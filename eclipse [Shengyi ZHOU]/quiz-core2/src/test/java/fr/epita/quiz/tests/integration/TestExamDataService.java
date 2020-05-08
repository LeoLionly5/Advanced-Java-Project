package fr.epita.quiz.tests.integration;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.business.ExamBusinessException;
import fr.epita.quiz.services.business.ExamDataService;
import fr.epita.quiz.services.dao.AnswerDAO;
import fr.epita.quiz.services.dao.QuestionDAO;
import fr.epita.quiz.services.dao.StudentDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!No longer use!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class TestExamDataService {

	@Inject
	ExamDataService eds;
	
	@Inject
	DataSource ds;
	
	@Inject
	StudentDAO studentDAO;
	
	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	AnswerDAO answerDAO;
	
	@Test
	public void testAQDS() throws ExamBusinessException {
		System.out.println("-----testAQDS-----");
		
		Question question = new Question();
		question.setTitle("Are you happy?");
		questionDAO.create(question);
		
		Student student = new Student();
		student.setName("Leo");
		studentDAO.create(student);
		
		Answer answer = new Answer();
		answer.setContent("Yep!");
		
		try {
			eds.answerToQuestion(student, question, answer);
		} catch (ExamBusinessException e) {
			//better to Log exception
			e.printStackTrace();
		} catch (Exception e) {
			//better to Log exception
			e.printStackTrace();
		}
		
		Answer fetchedAnswer = answerDAO.getById(answer.getId());
		Assert.assertEquals(fetchedAnswer.getStudent().getName(), student.getName());

	}
}
