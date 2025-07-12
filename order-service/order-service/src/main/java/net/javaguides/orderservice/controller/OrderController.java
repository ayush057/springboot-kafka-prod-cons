package net.javaguides.orderservice.controller;

import net.javaguides.basedomains.dto.Order;
import net.javaguides.basedomains.dto.OrderEvent;
import net.javaguides.orderservice.kafka.OrderProducer;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderProducer orderProducer;

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderController(OrderProducer orderProducer, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.orderProducer = orderProducer;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/newOrder")
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("ORDER_PENDING");
        orderEvent.setMessage("Order is in pending state");
        orderEvent.setOrder(order);

        orderProducer.produceEvent(orderEvent);
        return "order placed successfully with order id: " + order.getOrderId();
    }
}
