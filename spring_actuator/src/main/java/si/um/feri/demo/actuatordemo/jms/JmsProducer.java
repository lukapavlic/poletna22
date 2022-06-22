package si.um.feri.demo.actuatordemo.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Component
public class JmsProducer {

    private static final Logger log = Logger.getLogger(JmsProducer.class.toString());

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${active-mq.queue}")
    private String dest;

    public void sendMessage(String msg){
        try{
            log.info("Sending: "+ dest);
            jmsTemplate.convertAndSend(dest, msg);
        } catch(Exception e){
            log.info("Error: "+ e.getMessage());
        }
    }
}