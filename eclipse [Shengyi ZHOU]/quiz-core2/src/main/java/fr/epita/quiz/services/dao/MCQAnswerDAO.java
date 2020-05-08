package fr.epita.quiz.services.dao;

import java.util.Map;
import fr.epita.quiz.datamodel.MCQAnswer;
import fr.epita.quiz.services.dao.GenericDAO;

public class MCQAnswerDAO extends GenericDAO<MCQAnswer, Long> {
	
	@Override
	public String getQuery() {
		return "from MCQAnswer where MCQA_studentFK = :student and MCQA_examFK = :exam";
	}
	
	@Override
	public String getSearchAllQuery() {
		return "from MCQChoice";
	}

	@Override
	public void setParameters(Map<String, Object> parameters, MCQAnswer criteria) {
		parameters.put("student", criteria.getStudent());
		parameters.put("exam", criteria.getExam());
		//parameters.put("mcqchoice", criteria.getMcqchoice());
	}

	@Override
	public Class<MCQAnswer> getEntityClass() {
		return MCQAnswer.class;
	}
}
