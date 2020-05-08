package fr.epita.quiz.resources;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fr.epita.quiz.datamodel.MCQAnswer;
import fr.epita.quiz.services.dao.ExamDAO;
import fr.epita.quiz.services.dao.MCQAnswerDAO;
import fr.epita.quiz.services.dao.StudentDAO;

@Path("/exam")
@RestController
public class MCQAnswerResource {

	
//	@Inject
//	ExamDataService examDS;
	
	@Inject
	MCQAnswerDAO dao;
	
	@Inject
	ExamDAO edao;
	
	@Inject
	StudentDAO sdao;
	
	@POST
	@Path("/mcqanswer/add")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response addMCQAnswer(@RequestBody MCQAnswerDTO mcqanswerDTO) {
		
		MCQAnswer mcqanswer = new MCQAnswer();
		mcqanswer.setChecked(mcqanswerDTO.isChecked());
		mcqanswer.setExam(mcqanswerDTO.getExam());
		mcqanswer.setStudent(mcqanswerDTO.getStudent());
		mcqanswer.setMcqchoice(mcqanswerDTO.getMcqchoice());
		try {
			dao.create(mcqanswer);
			List<MCQAnswer> mcqanswers = dao.search(mcqanswer);
			for(MCQAnswer mcqanswerEntity : mcqanswers) {
				mcqanswer = mcqanswerEntity;
				System.out.println(mcqanswer.toString());
			}
			return Response.ok(mcqanswer).build();
			//return Response.created(new URI("/rest/exam/question/" + mcqanswer.getId())).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/mcqanswer/searchbyid")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchMCQAnswerById(@QueryParam("id") Long id) {
		try {
			MCQAnswer mcqanswer = dao.getById(id);
			System.out.println(mcqanswer.toString());
			return Response.ok(mcqanswer).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/mcqanswer/searchbyexamstudent")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchMCQAnswerByExamStudent(@QueryParam("examId") Long examId, @QueryParam("studentId") Long studentId) {
		try {
			
			MCQAnswer mcqanswer = new MCQAnswer();
			mcqanswer.setExam(edao.getById(examId));
			mcqanswer.setStudent(sdao.getById(studentId));
			List<MCQAnswer> mcqanswers = dao.search(mcqanswer);
			System.out.println(mcqanswers.toString());
			return Response.ok(mcqanswers).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/mcqanswer/searchall")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchAllMCQAnswer() {
		try {
			List<MCQAnswer> mcqanswers = dao.searchAll();
			return Response.ok(mcqanswers).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}
