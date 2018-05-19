package ActiveMQTest;
import  com.personalizednewsrecommendation.manager.activemq.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.annotation.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})
public class ActiveMQTest {
	private final Logger log = LoggerFactory.getLogger(ActiveMQTest.class);
	
	 
    @Value("${activemq.brokerURL}")
    private String brokerURL;
    @Value("${activemq.userName}")
    private String aUserName;
    @Value("${activemq.password}")
    private String aPassword;
    
    @Resource(name="queueSender")
    private QueueSender queueSender;
    @Resource(name="queueReceiver")
    private QueueReceiver queueReceiver;
    
    @Before
    public void afterActiveMQ(){
    	log.info("activemq properties:{},{},{}"
    			, brokerURL
    			, aUserName
    			, aPassword);
    	queueSender.send("test.queue", "hello world");
    }
    
    @Test
    public void testActiveMQ(){
    	//queueReceiver.onMessage(message);
    	//queueSender.send("test.queue", "hello world");
    }
    
    @After
    public void finish(){
    	
    }
}
