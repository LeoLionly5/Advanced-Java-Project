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
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.services.dao.MCQChoiceDAO;

@Path("/exam")
@RestController
public class MCQChoiceResource {

	
//	@Inject
//	ExamDataService examDS;
	
	@Inject
	MCQChoiceDAO dao;
	
	@POST
	@Path("/mcqchoice/add")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response addMCQChoice(@RequestBody MCQChoiceDTO mcqchoiceDTO) {
		
		MCQChoice mcqchoice = new MCQChoice();
		mcqchoice.setChoice(mcqchoiceDTO.getChoice());
		mcqchoice.setValid(mcqchoiceDTO.isValid());
		//mcqchoice.setMcqanswer(mcqchoiceDTO.getMcqanswer());
		//mcqchoice.setQuestion(mcqchoiceDTO.getQuestion());
		try {
			dao.create(mcqchoice);
			List<MCQChoice> mcqchoices = dao.search(mcqchoice);
			for(MCQChoice mcqchoiceEntity : mcqchoices) {
				mcqchoice = mcqchoiceEntity;
				System.out.println(mcqchoice.toString());
			}
			return Response.created(new URI("/rest/exam/question/" + mcqchoice.getId())).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/mcqchoice/search")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchMCQChoice(@QueryParam("choice") String choice, @QueryParam("valid") boolean valid) {
		System.out.println("!!! choice: "+choice);
		System.out.println("!!! valid: "+valid);
		MCQChoice mcqchoice = new MCQChoice();
		mcqchoice.setChoice(choice);
		mcqchoice.setValid(valid);
		try {
			List<MCQChoice> mcqchoices = dao.search(mcqchoice);
			for(MCQChoice mcqchoiceEntity : mcqchoices) {
				mcqchoice = mcqchoiceEntity;
				System.out.println(mcqchoice.toString());
			}
			return Response.ok(mcqchoice).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/mcqchoice/searchbyid")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchMCQChoiceById(@QueryParam("id") Long id) {
		try {
			MCQChoice mcqchoice = dao.getById(id);
			System.out.println(mcqchoice.toString());
			return Response.ok(mcqchoice).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/mcqchoice/searchall")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchAllMCQChoice() {
		try {
			List<MCQChoice> mcqchoices = dao.searchAll();
			return Response.ok(mcqchoices).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@POST
	@Path("/mcqchoice/update")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response updateMCQChoice(@RequestBody MCQChoice mcqchoice) {
		try {
			System.out.println(mcqchoice.toString());
			dao.update(mcqchoice);
			return Response.created(new URI("/rest/exam/mcqchoice/" + mcqchoice.getId())).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@DELETE
	@Path("/mcqchoice/delete")
	public Response deleteMCQChoice(@QueryParam("id") Long id) {
		try {
			MCQChoice mcqchoice = new MCQChoice();
			List<MCQChoice> mcqchoices = dao.searchAll();
			for(MCQChoice mcqchoiceEntity : mcqchoices) {
				mcqchoice = mcqchoiceEntity;
				System.out.println("Delete: "+mcqchoice.toString());
			}
			dao.delete(mcqchoice);
			return Response.ok("Delete Question Succeed").build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
}
