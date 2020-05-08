package fr.epita.quiz.tests.unit;

import javax.inject.Inject;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.dao.QuestionDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!No longer use!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class TestQuestionDAO {
	
//	private static final Logger LOGGER = LogManager.getLogger(TestQuestionDAO.class);

	@Inject
	QuestionDAO dao;
	
	@Inject
	DataSource ds;
	
	@Test
	public void testCreate() {
		System.out.println("-----testCreate-----");
		Question question = new Question();
		dao.create(question);
		System.out.println(question.getId());
		System.out.println("");
		
//		LOGGER.info("question id{}", question.getId());
//		try(Connection connection = ds.getConnection();
//		PreparedStatement stmt = connection.prepareStatement("select count(1) from Question");
//		ResultSet rs = stmt.executeQuery();){
//			rs.next();
//			int count = rs.getInt(1);
//			Assert.assertEquals(1, count);
//		} catch (Exception e) {
//			LOGGER.error("Some exception occured while performing count verification",e);
//		}
	}
	
	@Test
	public void testUpdate() {
		System.out.println("-----testUpdate-----");
		Question question = new Question();
		dao.create(question);
		System.out.println(question.getId());
		dao.update(question);
		System.out.println("");
	}
	
//	@Test
//	public void testDelete() {
//		System.out.println("-----testDelete-----");
//		Question question = new Question();
//		dao.create(question);
//		System.out.println(question.getId());
//		dao.delete(question);
//		System.out.println("");
//	}
	
	@Test
	public void testGetById() {
		System.out.println("-----testGetById-----");
		Question question = new Question();
		dao.create(question);
		System.out.println(question.getId());
		Question result = dao.getById(1L);
		if (result != null) {
            System.out.println(result.toString());
        }
		else {
			System.out.println("The result is Null!");
		}
		System.out.println("");
	}
}
