import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.ujmp.core.Matrix;
import org.ujmp.core.*;

import com.personalizednewsrecommendation.manager.algorithm.Recommender;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring/springmvc.xml" })
public class RecommenderTest {

	private Recommender testR;
	@Before
	public void instanceRec(){
		testR = new Recommender();
	}
	
	@Test
	public void testRecommender(){
		Matrix matrix = SparseMatrix.Factory.zeros(2,3);
		/**
		 * 4 1 3
		 * 1 0 6
		 */
		matrix.setAsInt(4, 0, 0);
		matrix.setAsInt(1, 0, 1);
		matrix.setAsInt(3, 0, 2);
		matrix.setAsInt(1, 1, 0);
		matrix.setAsInt(0, 1, 1);
		matrix.setAsInt(6, 1, 2);
		System.out.println(matrix);
		System.out.println("svd:\nU:\n"+matrix.svd()[0]+"\nΣ:\n"
				+matrix.svd()[1]+"\nV:\n"+matrix.svd()[2]);
		
		System.out.println("svd---U V:\nUuk:\n"
				+matrix.svd()[0].mtimes(matrix.svd()[1])
				+"\nVki:\n"+matrix.svd()[2]);
		System.out.println("svd---U*V:\nU*V:\n"
				+matrix.svd()[0].mtimes(matrix.svd()[1].mtimes(matrix.svd()[2])));
		System.out.println("---1,1----\n"+testR.basicSVD(matrix, 0, 1));
		
		System.out.println("---3,8----\n"+testR.basicSVD(matrix, 3, 8));
	}
}
