package me.jimmy.jpashop.repository.order.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.jimmy.jpashop.domain.Address;
import me.jimmy.jpashop.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data @EqualsAndHashCode
public class OrderQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }

    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, List<OrderItemQueryDto> orderItems) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.orderItems = orderItems;
    }

    public OrderQueryDto(Long orderId, String itemName, int orderPrice, int count) {

    }
}
