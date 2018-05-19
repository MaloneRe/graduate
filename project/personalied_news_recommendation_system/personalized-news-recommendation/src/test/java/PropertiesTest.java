import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})
public class PropertiesTest {
	@Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.initialSize}")
    private String initialSize;
    @Value("${jdbc.maxActive}")
    private String maxActive;
    @Value("${jdbc.maxIdle}")
    private String maxIdle;
    @Value("${jdbc.minIdle}")
    private String minIdle;
    @Value("${jdbc.maxWait}")
    private String maxWait;
   
    @Test
    public void testJDBC(){
    	System.out.println(this.driver+"\n"+
    			this.url+"\n"+
    			this.username+"\n"+
    			this.password+"\n"+
    			this.initialSize+"\n"+
    			this.maxActive+"\n"+
    			this.maxIdle+"\n"+
    			this.minIdle+"\n"+
    			this.maxWait+"\n"+"");
    }
}
