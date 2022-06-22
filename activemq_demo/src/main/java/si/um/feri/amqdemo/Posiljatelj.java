package si.um.feri.amqdemo;


import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import java.io.Closeable;
import java.io.IOException;

public class Posiljatelj implements Closeable {

    public Posiljatelj(String url) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();
        connection.start();
        session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    @Override
    public void close() throws IOException {
        try {
            session.close();
            connection.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    Connection connection;
    Session session;

	public static void main(String[] args) throws Exception {

        try (Posiljatelj p=new Posiljatelj("tcp://localhost:61616")) {

            MessageProducer vrsta = p.session.createProducer(p.session.createQueue("vrsta"));
            MessageProducer tema = p.session.createProducer(p.session.createTopic("tema"));

            tema.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            vrsta.send(p.session.createTextMessage("Sporocilo"));

            MapMessage mm=p.session.createMapMessage();
            mm.setString("ime","Peter");
            mm.setInt("starost",40);
            tema.send(mm,DeliveryMode.PERSISTENT,3,60_000);

        }

	}

}
