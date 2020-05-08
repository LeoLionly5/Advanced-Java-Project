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
import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.services.dao.ExamDAO;

@Path("/exam")
@RestController
public class ExamResource {
	
	@Inject
	ExamDAO dao;
	
	@POST
	@Path("/exam/add")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response addExam(@RequestBody ExamDTO examDTO) {
		Exam exam = new Exam();
		exam.setTitle(examDTO.getTitle());
		try {
			dao.create(exam);
			List<Exam> exams = dao.search(exam);
			for(Exam examEntity : exams) {
				exam = examEntity;
				System.out.println(exam.toString());
			}
			return Response.created(new URI("/rest/exam/exam/" + exam.getId())).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/exam/search")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchExam(@QueryParam("title") String title) {
		System.out.println("!!! title: "+title);
		Exam exam = new Exam();
		exam.setTitle(title);
		try {
			List<Exam> exams = dao.search(exam);
			for(Exam examEntity : exams) {
				exam = examEntity;
				System.out.println(exam.toString());
			}
			return Response.ok(exam).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/exam/searchbyid")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchExamById(@QueryParam("id") Long id) {
		try {
			Exam exam = dao.getById(id);
			System.out.println("searchExamById");
			System.out.println(exam.toString());
			return Response.ok(exam).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/exam/searchall")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchAllExam() {
		try {
			List<Exam> exams = dao.searchAll();
			return Response.ok(exams).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	
	@POST
	@Path("/exam/update")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response updateExam(@RequestBody Exam exam) {
		try {
			System.out.println("updateExam");
			System.out.println(exam.toString());
			dao.update(exam);
			List<Exam> exams = dao.search(exam);
			for(Exam examEntity : exams) {
				exam = examEntity;
				System.out.println(exam.toString());
			}
			return Response.created(new URI("/rest/exam/exam/" + exam.getId())).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@DELETE
	@Path("/exam/delete")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response deleteExam(@QueryParam("id") Long id) {
		try {
			Exam exam = new Exam();
			List<Exam> exams = dao.searchAll();
			for(Exam examEntity : exams) {
				exam = examEntity;
				System.out.println("Delete: "+exam.toString());
			}
			dao.delete(exam);
			return Response.ok(exam).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
}
