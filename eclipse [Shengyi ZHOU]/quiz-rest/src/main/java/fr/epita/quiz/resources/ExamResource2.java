package fr.epita.quiz.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.business.ExamDataService;
import fr.epita.quiz.services.dao.AnswerDAO;

@Path("/exam")

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!No longer use!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class ExamResource2 {

	
	@Inject
	ExamDataService examDS;
	
	@Inject
	AnswerDAO dao;
	
	@POST
	@Path("/answer")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response addAnswerToQuestion(@RequestBody AnswerDTO answerDTO) {
		
		Answer answer = new Answer();
		answer.setContent(answerDTO.getContent());
		answer.setId(1l);
		try {
			return Response.created(new URI("/rest/exam/answer/" + answer.getId())).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@POST
	@Path("/addanswer")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response addAnswer(@RequestBody Answer answer) {
		System.out.println("testPost");
		
		Student student_ = answer.getStudent();
		examDS.saveStudent(student_);
		List<Student> students = examDS.searchStudent(student_);
		for(Student student : students) {
			student_ = student;
			System.out.println(student.toString());
		}
		
		Question question_ = answer.getQuestion();
		examDS.saveQuestion(question_);
		List<Question> questions = examDS.searchQuestion(question_);
		for(Question question : questions) {
			question_ = question;
			System.out.println(question.toString());
		}
		
		try {
			examDS.answerToQuestion(student_, question_, answer);
			return Response.created(new URI("/exam/answer/s" + student_.getId() + "q" + question_.getId() + "a" + answer.getId())).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/updateanswer")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response updateAnswer(@RequestBody Answer answer) {
		try {
			examDS.updateAnswer(answer);
			List<Answer> answers = examDS.searchAnswer(answer);
			for(Answer answerEntity : answers) {
				answer = answerEntity;
				System.out.println(answerEntity.toString());
			}
			return Response.ok(answer).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
//	@GET
//	@Path("/deleteanswer")
//	@Consumes(value = MediaType.APPLICATION_JSON)
//	public Response deleteAnswer(@RequestBody Answer answer) {
//		try {
//			examDS.deleteAnswer(answer);
//			//return Response.created(new URI("/exam/answer/s" + answer.getStudent().getId() + "q" + answer.getQuestion().getId() + "a" + answer.getId())).build();
//			return Response.ok("Delete Succeed").build();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return Response.serverError().build();
//	}
	
	@GET
	@Path("/answer/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getAnswer(@PathParam("id") long answerId) {
		Answer answer = new Answer();
		answer.setContent("This is a sampleAnswer with id " + answerId);
		answer.setId(answerId);
		return Response.ok(answer).build();
	}
	
	@GET
	@Path("/answer")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getAnswers() {
		System.out.println("testGet");
		Answer answer = new Answer();
		answer.setContent("This is a sampleAnswer");
		
		AnswerDTO answerDTO = new AnswerDTO(answer);
		
		return Response.ok(Arrays.asList(answerDTO)).build();
	}
	
//	
//	@POST
//	
//	@PATCH
//	
	@DELETE
	@Path("/answer")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response removeAnswers(@RequestBody AnswerDTO answerDTO) {
		try {
			Answer answer = new Answer();
			answer.setContent(answerDTO.getContent());			
			List<Answer> answers = examDS.searchAnswer(answer);
			for(Answer answerEntity : answers) {
				dao.delete(answerEntity);
				System.out.println("[OP] Delete: " + answerEntity.toString());
			}
			//return Response.created(new URI("/exam/answer/s" + answer.getStudent().getId() + "q" + answer.getQuestion().getId() + "a" + answer.getId())).build();
			return Response.ok("Delete Succeed").build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}
