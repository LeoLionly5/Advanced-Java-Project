package fr.epita.quiz.services.dao;

import java.util.List;
import java.util.Map;
import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.services.dao.GenericDAO;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!No longer use!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class AnswerDAO extends GenericDAO<Answer, Long> {
	
	@Override
	public String getQuery() {
		return "from Answer";
	}
	
	@Override
	public String getSearchAllQuery() {
		return "from Answer";
	}

	@Override
	public void setParameters(Map<String, Object> parameters, Answer criteria) {
		return;
	}

	@Override
	public Class<Answer> getEntityClass() {
		// TODO Auto-generated method stub
		return Answer.class;
	}
}