package sdu.masterthesis;

import com.rabbitmq.client.*;
import org.slf4j.MDC;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class PlayMQChannel implements Channel {

    private Channel channel;
    private static final String HEADER_CORRELATION_ID = "X-Correlation-ID";

    public PlayMQChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public int getChannelNumber() {
        return channel.getChannelNumber();
    }

    @Override
    public Connection getConnection() {
        return channel.getConnection();
    }

    @Override
    public void close() throws IOException, TimeoutException {
        channel.close();
    }

    @Override
    public void close(int i, String s) throws IOException, TimeoutException {
        channel.close(i, s);
    }

    @Override
    public boolean flowBlocked() {
        return channel.flowBlocked();
    }

    @Override
    public void abort() throws IOException {
        channel.abort();
    }

    @Override
    public void abort(int i, String s) throws IOException {
        channel.abort(i, s);
    }

    @Override
    public void addReturnListener(ReturnListener returnListener) {
        channel.addReturnListener(returnListener);
    }

    @Override
    public boolean removeReturnListener(ReturnListener returnListener) {
        return channel.removeReturnListener(returnListener);
    }

    @Override
    public void clearReturnListeners() {
        channel.clearReturnListeners();
    }

    @Override
    public void addFlowListener(FlowListener flowListener) {
        channel.addFlowListener(flowListener);
    }

    @Override
    public boolean removeFlowListener(FlowListener flowListener) {
        return channel.removeFlowListener(flowListener);
    }

    @Override
    public void clearFlowListeners() {
        channel.clearFlowListeners();
    }

    @Override
    public void addConfirmListener(ConfirmListener confirmListener) {
        channel.addConfirmListener(confirmListener);
    }

    @Override
    public boolean removeConfirmListener(ConfirmListener confirmListener) {
        return channel.removeConfirmListener(confirmListener);
    }

    @Override
    public void clearConfirmListeners() {
        channel.clearConfirmListeners();
    }

    @Override
    public Consumer getDefaultConsumer() {
        return channel.getDefaultConsumer();
    }

    @Override
    public void setDefaultConsumer(Consumer consumer) {
        channel.setDefaultConsumer(consumer);
    }

    @Override
    public void basicQos(int i, int i1, boolean b) throws IOException {
        channel.basicQos(i, i1, b);
    }

    @Override
    public void basicQos(int i, boolean b) throws IOException {
        channel.basicQos(i, b);
    }

    @Override
    public void basicQos(int i) throws IOException {
        channel.basicQos(i);
    }

    private AMQP.BasicProperties generateBasicPropertiesAndAddToMDC() {
        String uuid = UUID.randomUUID().toString();
        AMQP.BasicProperties props = new AMQP.BasicProperties().builder().correlationId(uuid).build();
        MDC.put(HEADER_CORRELATION_ID, uuid);
        return props;
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

    @Override
    public void basicPublish(String s, String s1, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public void basicPublish(String s, String s1, boolean b, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public void basicPublish(String s, String s1, boolean b, boolean b1, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public AMQP.Exchange.DeclareOk exchangeDeclare(String s, String s1) throws IOException {
        return channel.exchangeDeclare(s, s1);
    }

    @Override
    public AMQP.Exchange.DeclareOk exchangeDeclare(String s, BuiltinExchangeType builtinExchangeType) throws IOException {
        return channel.exchangeDeclare(s, builtinExchangeType);
    }

    @Override
    public AMQP.Exchange.DeclareOk exchangeDeclare(String s, String s1, boolean b) throws IOException {
        return channel.exchangeDeclare(s, s1, b);
    }

    @Override
    public AMQP.Exchange.DeclareOk exchangeDeclare(String s, BuiltinExchangeType builtinExchangeType, boolean b) throws IOException {
        return channel.exchangeDeclare(s, builtinExchangeType, b);
    }

    @Override
    public AMQP.Exchange.DeclareOk exchangeDeclare(String s, String s1, boolean b, boolean b1, Map<String, Object> map) throws IOException {
        return channel.exchangeDeclare(s, s1, b, b1, map);
    }

    @Override
    public AMQP.Exchange.DeclareOk exchangeDeclare(String s, BuiltinExchangeType builtinExchangeType, boolean b, boolean b1, Map<String, Object> map) throws IOException {
        return channel.exchangeDeclare(s, builtinExchangeType, b, b1, map);
    }

    @Override
    public AMQP.Exchange.DeclareOk exchangeDeclare(String s, String s1, boolean b, boolean b1, boolean b2, Map<String, Object> map) throws IOException {
        return channel.exchangeDeclare(s, s1, b, b1, b2, map);
    }

    @Override
    public AMQP.Exchange.DeclareOk exchangeDeclare(String s, BuiltinExchangeType builtinExchangeType, boolean b, boolean b1, boolean b2, Map<String, Object> map) throws IOException {
        return channel.exchangeDeclare(s, builtinExchangeType, b, b1, b2, map);
    }

    @Override
    public void exchangeDeclareNoWait(String s, String s1, boolean b, boolean b1, boolean b2, Map<String, Object> map) throws IOException {
        channel.exchangeDeclareNoWait(s, s1, b, b1, b2, map);
    }

    @Override
    public void exchangeDeclareNoWait(String s, BuiltinExchangeType builtinExchangeType, boolean b, boolean b1, boolean b2, Map<String, Object> map) throws IOException {
        channel.exchangeDeclareNoWait(s, builtinExchangeType, b, b1, b2, map);
    }

    @Override
    public AMQP.Exchange.DeclareOk exchangeDeclarePassive(String s) throws IOException {
        return channel.exchangeDeclarePassive(s);
    }

    @Override
    public AMQP.Exchange.DeleteOk exchangeDelete(String s, boolean b) throws IOException {
        return channel.exchangeDelete(s, b);
    }

    @Override
    public void exchangeDeleteNoWait(String s, boolean b) throws IOException {
        channel.exchangeDeleteNoWait(s, b);
    }

    @Override
    public AMQP.Exchange.DeleteOk exchangeDelete(String s) throws IOException {
        return channel.exchangeDelete(s);
    }

    @Override
    public AMQP.Exchange.BindOk exchangeBind(String s, String s1, String s2) throws IOException {
        return channel.exchangeBind(s, s1, s2);
    }

    @Override
    public AMQP.Exchange.BindOk exchangeBind(String s, String s1, String s2, Map<String, Object> map) throws IOException {
        return channel.exchangeBind(s, s1, s2, map);
    }

    @Override
    public void exchangeBindNoWait(String s, String s1, String s2, Map<String, Object> map) throws IOException {
        channel.exchangeBindNoWait(s, s1, s2, map);
    }

    @Override
    public AMQP.Exchange.UnbindOk exchangeUnbind(String s, String s1, String s2) throws IOException {
        return channel.exchangeUnbind(s, s1, s2);
    }

    @Override
    public AMQP.Exchange.UnbindOk exchangeUnbind(String s, String s1, String s2, Map<String, Object> map) throws IOException {
        return channel.exchangeUnbind(s, s1, s2, map);
    }

    @Override
    public void exchangeUnbindNoWait(String s, String s1, String s2, Map<String, Object> map) throws IOException {
        channel.exchangeUnbindNoWait(s, s1, s2, map);
    }

    @Override
    public AMQP.Queue.DeclareOk queueDeclare() throws IOException {
        return channel.queueDeclare();
    }

    @Override
    public AMQP.Queue.DeclareOk queueDeclare(String s, boolean b, boolean b1, boolean b2, Map<String, Object> map) throws IOException {
        return channel.queueDeclare(s, b, b1, b2, map);
    }

    @Override
    public void queueDeclareNoWait(String s, boolean b, boolean b1, boolean b2, Map<String, Object> map) throws IOException {
        channel.queueDeclareNoWait(s, b, b1, b2, map);
    }

    @Override
    public AMQP.Queue.DeclareOk queueDeclarePassive(String s) throws IOException {
        return channel.queueDeclarePassive(s);
    }

    @Override
    public AMQP.Queue.DeleteOk queueDelete(String s) throws IOException {
        return channel.queueDelete(s);
    }

    @Override
    public AMQP.Queue.DeleteOk queueDelete(String s, boolean b, boolean b1) throws IOException {
        return channel.queueDelete(s, b, b1);
    }

    @Override
    public void queueDeleteNoWait(String s, boolean b, boolean b1) throws IOException {
        channel.queueDeleteNoWait(s, b, b1);
    }

    @Override
    public AMQP.Queue.BindOk queueBind(String s, String s1, String s2) throws IOException {
        return channel.queueBind(s, s1, s2);
    }

    @Override
    public AMQP.Queue.BindOk queueBind(String s, String s1, String s2, Map<String, Object> map) throws IOException {
        return channel.queueBind(s, s1, s2, map);
    }

    @Override
    public void queueBindNoWait(String s, String s1, String s2, Map<String, Object> map) throws IOException {
        channel.queueBindNoWait(s, s1, s2, map);
    }

    @Override
    public AMQP.Queue.UnbindOk queueUnbind(String s, String s1, String s2) throws IOException {
        return channel.queueUnbind(s, s1, s2);
    }

    @Override
    public AMQP.Queue.UnbindOk queueUnbind(String s, String s1, String s2, Map<String, Object> map) throws IOException {
        return channel.queueUnbind(s, s1, s2, map);
    }

    @Override
    public AMQP.Queue.PurgeOk queuePurge(String s) throws IOException {
        return channel.queuePurge(s);
    }

    @Override
    public GetResponse basicGet(String s, boolean b) throws IOException {
        return channel.basicGet(s, b);
    }

    @Override
    public void basicAck(long l, boolean b) throws IOException {
        channel.basicAck(l, b);
    }

    @Override
    public void basicNack(long l, boolean b, boolean b1) throws IOException {
        channel.basicNack(l, b, b1);
    }

    @Override
    public void basicReject(long l, boolean b) throws IOException {
        channel.basicReject(l, b);
    }

    @Override
    public String basicConsume(String s, Consumer consumer) throws IOException {
        return channel.basicConsume(s, consumer);
    }

    @Override
    public String basicConsume(String s, boolean b, Consumer consumer) throws IOException {
        return channel.basicConsume(s, b, consumer);
    }

    @Override
    public String basicConsume(String s, boolean b, Map<String, Object> map, Consumer consumer) throws IOException {
        return channel.basicConsume(s, b, map, consumer);
    }

    @Override
    public String basicConsume(String s, boolean b, String s1, Consumer consumer) throws IOException {
        return channel.basicConsume(s, b, s1, consumer);
    }

    @Override
    public String basicConsume(String s, boolean b, String s1, boolean b1, boolean b2, Map<String, Object> map, Consumer consumer) throws IOException {
        return channel.basicConsume(s, b, s1, b1, b2, map, consumer);
    }

    @Override
    public void basicCancel(String s) throws IOException {
        channel.basicCancel(s);
    }

    @Override
    public AMQP.Basic.RecoverOk basicRecover() throws IOException {
        return channel.basicRecover();
    }

    @Override
    public AMQP.Basic.RecoverOk basicRecover(boolean b) throws IOException {
        return channel.basicRecover(b);
    }

    @Override
    public AMQP.Tx.SelectOk txSelect() throws IOException {
        return channel.txSelect();
    }

    @Override
    public AMQP.Tx.CommitOk txCommit() throws IOException {
        return channel.txCommit();
    }

    @Override
    public AMQP.Tx.RollbackOk txRollback() throws IOException {
        return channel.txRollback();
    }

    @Override
    public AMQP.Confirm.SelectOk confirmSelect() throws IOException {
        return channel.confirmSelect();
    }

    @Override
    public long getNextPublishSeqNo() {
        return channel.getNextPublishSeqNo();
    }

    @Override
    public boolean waitForConfirms() throws InterruptedException {
        return channel.waitForConfirms();
    }

    @Override
    public boolean waitForConfirms(long l) throws InterruptedException, TimeoutException {
        return channel.waitForConfirms(l);
    }

    @Override
    public void waitForConfirmsOrDie() throws IOException, InterruptedException {
        channel.waitForConfirmsOrDie();
    }

    @Override
    public void waitForConfirmsOrDie(long l) throws IOException, InterruptedException, TimeoutException {
        channel.waitForConfirmsOrDie(l);
    }

    @Override
    public void asyncRpc(Method method) throws IOException {
        channel.asyncRpc(method);
    }

    @Override
    public Command rpc(Method method) throws IOException {
        return channel.rpc(method);
    }

    @Override
    public long messageCount(String s) throws IOException {
        return channel.messageCount(s);
    }

    @Override
    public long consumerCount(String s) throws IOException {
        return channel.consumerCount(s);
    }

    @Override
    public void addShutdownListener(ShutdownListener shutdownListener) {
        channel.addShutdownListener(shutdownListener);
    }

    @Override
    public void removeShutdownListener(ShutdownListener shutdownListener) {
        channel.removeShutdownListener(shutdownListener);
    }

    @Override
    public ShutdownSignalException getCloseReason() {
        return channel.getCloseReason();
    }

    @Override
    public void notifyListeners() {
        channel.notifyListeners();
    }

    @Override
    public boolean isOpen() {
        return channel.isOpen();
    }
}
