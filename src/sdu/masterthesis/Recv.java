package sdu.masterthesis;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;

public class Recv {
    static { System.setProperty("logback.configurationFile",
            "/Users/jms/Git/TV2/MQCorrelation/src/resources/logback.xml"); }

    private final static String QUEUE_NAME = "hello";
    private static final String HEADER_CORRELATION_ID = "X-Correlation-ID";
    private static final Logger LOG = LoggerFactory.getLogger(Recv.class);

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        LOG.debug(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new PlayMQConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, basicProperties, body);
                String message = new String(body, "UTF-8");
                LOG.debug(" [X] Received '" + message + "' CorrelationId: " + MDC.get(HEADER_CORRELATION_ID));
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}