package net.javaguides.orderservice.kafka;

import net.javaguides.basedomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    private NewTopic topic;
    public KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceEvent(OrderEvent orderEvent) {
        logger.info("Order event sent -> {}", orderEvent.toString());

        // create message
        Message<OrderEvent> message = (Message)
                MessageBuilder
                    .withPayload(orderEvent)
                    .setHeader(KafkaHeaders.TOPIC, topic.name())
                    .build();

        kafkaTemplate.send(message);
    }
}
