package fr.epita.quiz.resources;

import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.dao.QuestionDAO;

@Path("/exam")
@RestController
public class QuestionResource {

	
//	@Inject
//	QuestionDataService questionDS;
	
	@Inject
	QuestionDAO dao;
	
	@POST
	@Path("/question/add")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response addQuestion(@RequestBody QuestionDTO questionDTO) {
		
		Question question = new Question();
		question.setTitle(questionDTO.getTitle());
		question.setMcqchoices(questionDTO.getMcqchoices());
		try {
			dao.create(question);
			return Response.ok(question).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/question/search")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchQuestion(@QueryParam("title") String title) {
		System.out.println("!!! title: "+title);
		Question question = new Question();
		question.setTitle(title);
		try {
			List<Question> questions = dao.search(question);
			for(Question questionEntity : questions) {
				question = questionEntity;
				System.out.println(question.toString());
			}
			return Response.ok(question).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/question/searchbyid")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchQuestionById(@QueryParam("id") Long id) {
		try {
			Question question = dao.getById(id);
			System.out.println(question.toString());
			return Response.ok(question).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/question/searchall")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchAllQuestion() {
		try {
			List<Question> questions = dao.searchAll();
			return Response.ok(questions).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@POST
	@Path("/question/update")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response updateQuestion(@RequestBody Question question) {
		try {
			System.out.println(question.toString());
			dao.update(question);
			return Response.created(new URI("/rest/exam/question/" + question.getId())).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@DELETE
	@Path("/question/delete")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response deleteQuestion(@QueryParam("id") Long id) {
		try {
			Question question = new Question();
			List<Question> questions = dao.searchAll();
			for(Question questionEntity : questions) {
				question = questionEntity;
				System.out.println("Delete: "+question.toString());
			}
			dao.delete(question);
			return Response.ok(question).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}
