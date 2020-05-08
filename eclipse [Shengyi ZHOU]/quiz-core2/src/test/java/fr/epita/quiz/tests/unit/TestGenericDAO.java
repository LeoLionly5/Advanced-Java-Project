package fr.epita.quiz.tests.unit;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.dao.QuestionDAO;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!No longer use!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class TestGenericDAO<T> {

	public static void testGenericDAO() {
		QuestionDAO dao = new QuestionDAO();
		dao.create(new Question());
		dao.getById(1L);
	}
	
	public static void main(String[] args) {
		testGenericDAO();
	}
}
