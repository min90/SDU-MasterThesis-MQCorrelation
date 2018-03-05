package sdu.masterthesis;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Send {
    static { System.setProperty("logback.configurationFile",
            "/Users/jms/Git/TV2/MQCorrelation/src/resources/logback.xml"); }

    private final static String QUEUE_NAME = "hello";
    private static final String HEADER_CORRELATION_ID = "X-Correlation-ID";
    private static final Logger LOG = LoggerFactory.getLogger(Send.class);

    public static void main(String[] argv) throws Exception {

        // An alternative to the current proposal is to use the builder pattern.
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        PlayMQChannel playMQChannel = new PlayMQChannel(connection.createChannel());

        playMQChannel.getChannel().queueDeclare(QUEUE_NAME, false, false, false, null);

        String message = "Hello World!";
        playMQChannel.basicPublishCustomPlay("", QUEUE_NAME, message.getBytes("UTF-8"));
        LOG.debug(" [X] Sent '" + message + "'" + " IN MDC: " + MDC.get(HEADER_CORRELATION_ID));

        playMQChannel.getChannel().close();
        connection.close();
    }
}