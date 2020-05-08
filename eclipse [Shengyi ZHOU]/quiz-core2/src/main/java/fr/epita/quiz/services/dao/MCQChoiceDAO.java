package fr.epita.quiz.services.dao;

import java.util.Map;
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.services.dao.GenericDAO;

public class MCQChoiceDAO extends GenericDAO<MCQChoice, Long> {
	
	@Override
	public String getQuery() {
		return "from MCQChoice where MCQC_choice = :choice";
	}
	
	@Override
	public String getSearchAllQuery() {
		return "from MCQChoice";
	}

	@Override
	public void setParameters(Map<String, Object> parameters, MCQChoice criteria) {
		parameters.put("choice", criteria.getChoice());
	}
	
	public void setQuestionParameters(Map<String, Object> parameters, MCQChoice criteria) {
		parameters.put("choice", criteria.getChoice());
	}
	
//	public List<MCQChoice> searchByQuestion(MCQChoice criteria){
//		Query searchQuery = em.createQuery("from MCQChoice where MCQC_questionFK = :question");
//		Map<String, Object> parameters = new LinkedHashMap<String, Object>();
//		setQuestionParameters(parameters, criteria);
//		for (Map.Entry<String,Object> entry : parameters.entrySet() ) {
//			searchQuery.setParameter(entry.getKey(), entry.getValue());
//		}
//		return searchQuery.getResultList();
//	}

	@Override
	public Class<MCQChoice> getEntityClass() {
		return MCQChoice.class;
	}
}
