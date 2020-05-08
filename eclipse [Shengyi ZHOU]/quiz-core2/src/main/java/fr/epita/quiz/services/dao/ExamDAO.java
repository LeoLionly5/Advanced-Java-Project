package fr.epita.quiz.services.dao;

import java.util.Map;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.services.dao.GenericDAO;

public class ExamDAO extends GenericDAO<Exam, Long> {
	@Override
	public String getQuery() {
		return "from Exam where title = :pTitle ";
	}
	
	@Override
	public String getSearchAllQuery() {
		return "from Exam";
	}

	@Override
	public void setParameters(Map<String, Object> parameters, Exam criteria) {
		parameters.put("pTitle",criteria.getTitle());
	}

	@Override
	public Class<Exam> getEntityClass() {
		return Exam.class;
	}
}
