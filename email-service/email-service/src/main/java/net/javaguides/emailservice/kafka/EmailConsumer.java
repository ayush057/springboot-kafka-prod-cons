package net.javaguides.emailservice.kafka;

import net.javaguides.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "email-service-group")
    public void consumeEvent(OrderEvent orderEvent) {
        logger.info(String.format("Order event received -> %s", orderEvent.toString()));

        // save the OrderEvent into the database
    }
}
