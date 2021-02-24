package me.jimmy.jpashop.repository.order.query;

import lombok.Data;
import me.jimmy.jpashop.domain.Address;
import me.jimmy.jpashop.domain.OrderStatus;

import java.time.LocalDateTime;

@Data // Order, OrderItem을 Join해서 가져온다.
public class OrderFlatDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    private String itemName;
    private int orderPrice;
    private int count;

    public OrderFlatDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
