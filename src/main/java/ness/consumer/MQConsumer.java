package ness.consumer;

import ness.service.MessageService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
public class MQConsumer implements Consumer, ExceptionListener {

    @Value("${activemq.address}")
    private String address;

    @Value("${activemq.topic}")
    private String topic;

    private MessageService service;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public MQConsumer(MessageService messageService){
        this.service = messageService;
    }

    @Override
    public int consume() {
        try {
            int res;
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(address);

            // Create a Connection
            javax.jms.Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createTopic(topic);

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            Message message = consumer.receive(10000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                logger.info("Received: " + text);

                ness.model.Message dBMessage = new ness.model.Message(LocalDateTime.now(), text);
                res = service.save(dBMessage);

                if(res == 1)
                    logger.info("Message saved: " + dBMessage);

            } else {
                logger.warning("Received: " + message);
                res = 0;
            }

            consumer.close();
            session.close();
            connection.close();
            return res;
        } catch (Exception e) {
            logger.warning("Caught: " + e);
            return -1;
        }

    }

    public void onException(JMSException e) {
        logger.warning("JMS Exception occured!");
    }
}
