package fr.epita.quiz.services.dao;

import java.util.Map;

import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.dao.GenericDAO;

public class StudentDAO extends GenericDAO<Student, Long> {
	@Override
	public String getQuery() {
		return "from Student where name = :pName ";
	}
	
	@Override
	public String getSearchAllQuery() {
		return "from Student";
	}

	@Override
	public void setParameters(Map<String, Object> parameters, Student criteria) {
		parameters.put("pName",criteria.getName());
	}

	@Override
	public Class<Student> getEntityClass() {
		return Student.class;
	}
}
