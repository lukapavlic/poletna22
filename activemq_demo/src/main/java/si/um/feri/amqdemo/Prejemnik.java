package si.um.feri.amqdemo;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Closeable;
import java.io.IOException;

public class Prejemnik implements MessageListener, Closeable {

	public Prejemnik(String url, String vrsta) throws JMSException {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		connection = connectionFactory.createConnection();
		connection.start();
		session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		consumer=session.createConsumer(session.createQueue(vrsta));
		consumer.setMessageListener(this);
	}

	@Override
	public void close() throws IOException {
		try {
			consumer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	Connection connection;
	Session session;
	MessageConsumer consumer;

	@Override
	public void onMessage(Message m) {
		System.out.println(m);
	}

	public static void main(String[] args) throws JMSException, IOException {
		try (Prejemnik p=new Prejemnik("tcp://localhost:61616","vrsta")) {
			Thread.sleep(60_000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
