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
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.dao.StudentDAO;

@Path("/exam")
@RestController
public class StudentResource {
	
	@Inject
	StudentDAO dao;
	
	@POST
	@Path("/student/add")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response addStudent(@RequestBody StudentDTO studentDTO) {
		Student student = new Student();
		student.setName(studentDTO.getName());
		try {
			dao.create(student);
			List<Student> students = dao.search(student);
			for(Student studentEntity : students) {
				student = studentEntity;
				System.out.println(student.toString());
			}
			return Response.created(new URI("/rest/exam/student/" + student.getId())).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/student/search")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchStudent(@QueryParam("name") String name) {
		System.out.println("!!! name: "+name);
		Student student = new Student();
		student.setName(name);
		try {
			List<Student> students = dao.search(student);
			for(Student studentEntity : students) {
				student = studentEntity;
				System.out.println(student.toString());
			}
			return Response.ok(student).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/student/searchbyid")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchStudentById(@QueryParam("id") Long id) {
		try {
			Student student = dao.getById(id);
			System.out.println(student.toString());
			return Response.ok(student).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/student/searchall")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response searchAllStudent() {
		try {
			List<Student> students = dao.searchAll();
			return Response.ok(students).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@POST
	@Path("/student/update")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response updateStudent(@RequestBody Student student) {
		try {
			System.out.println(student.toString());
			dao.update(student);
			return Response.created(new URI("/rest/exam/student/" + student.getId())).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	@DELETE
	@Path("/student/delete")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response deleteStudent(@QueryParam("id") Long id) {
		try {
			Student student = new Student();
			List<Student> students = dao.searchAll();
			for(Student studentEntity : students) {
				student = studentEntity;
				System.out.println("Delete: "+student.toString());
			}
			dao.delete(student);
			return Response.ok(student).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
}
