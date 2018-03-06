package sdu.masterthesis;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

public class PlayMQChannel {

    private Channel channel;
    private static final String HEADER_CORRELATION_ID = "X-Correlation-ID";

    public PlayMQChannel(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    private AMQP.BasicProperties generateBasicPropertiesAndAddToMDC() {
        if(MDC.get(HEADER_CORRELATION_ID) == null) {
            String uuid = UUID.randomUUID().toString();
            MDC.put(HEADER_CORRELATION_ID, uuid);
            return new AMQP.BasicProperties().builder().correlationId(uuid).build();
        } else {
            String uuid = MDC.get(HEADER_CORRELATION_ID);
            return new AMQP.BasicProperties().builder().correlationId(uuid).build();
        }
    }

    public void basicPublishCustomPlay(String s, String queueName, byte[] bytes) throws IOException {
        channel.basicPublish(s, queueName, generateBasicPropertiesAndAddToMDC(), bytes);
    }

    public void basicPublishCustomPlay(String s, String queueName, boolean b, byte[] bytes) throws IOException {
        channel.basicPublish(s, queueName, b, generateBasicPropertiesAndAddToMDC(), bytes);
    }

    public void basicPublishCustomPlay(String s, String queueName, boolean b, boolean b1, byte[] bytes) throws IOException {
        channel.basicPublish(s, queueName, b, b1, generateBasicPropertiesAndAddToMDC(), bytes);
    }
}
