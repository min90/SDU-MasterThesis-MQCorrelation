package sdu.masterthesis;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;

public class PlayMQConsumer implements Consumer {
    private static final String HEADER_CORRELATION_ID = "X-Correlation-ID";
    private final Channel channel;
    private volatile String consumerTag;

    public PlayMQConsumer(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        this.consumerTag = consumerTag;
    }

    @Override
    public void handleCancelOk(String s) {

    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {

    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
        if(basicProperties != null) {
            MDC.put(HEADER_CORRELATION_ID, basicProperties.getCorrelationId());
        }

    }

    public Channel getChannel() {
        return channel;
    }

    public String getConsumerTag() {
        return consumerTag;
    }
}
