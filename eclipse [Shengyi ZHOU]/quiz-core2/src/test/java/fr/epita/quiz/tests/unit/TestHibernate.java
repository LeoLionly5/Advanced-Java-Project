package fr.epita.quiz.tests.unit;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Question;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!No longer use!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class TestHibernate {
	
	@Inject
	SessionFactory sf;
	
	@Test
	public void testFirstSessionFactory() {
		Session openSession = sf.openSession();
		Question question = new Question();
		question.setTitle("What is a dependency?");
		openSession.save(question);
		
		Assert.assertNotEquals(0l, question.getId().longValue());
	}

}
