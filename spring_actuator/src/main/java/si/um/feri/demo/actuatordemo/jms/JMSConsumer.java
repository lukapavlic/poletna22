package si.um.feri.demo.actuatordemo.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;

@Component
public class JMSConsumer implements MessageListener {

    private static final Logger log = Logger.getLogger(JmsListener.class.toString());

    @Override
    @JmsListener(destination = "${active-mq.queue}")
    public void onMessage(Message message) {
        log.info("Received: "+message);
    }
}