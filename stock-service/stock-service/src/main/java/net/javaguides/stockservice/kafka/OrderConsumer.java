package net.javaguides.stockservice.kafka;

import net.javaguides.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "stock-service-group")
    public void consumeEvent(OrderEvent orderEvent) {
        logger.info(String.format("Order event received -> %s", orderEvent.toString()));

       // save the OrderEvent into the database
    }
}
