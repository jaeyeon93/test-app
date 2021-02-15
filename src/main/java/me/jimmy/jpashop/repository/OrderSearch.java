package me.jimmy.jpashop.repository;

import lombok.Getter;
import lombok.Setter;
import me.jimmy.jpashop.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus; // 주문상태[ORDER, CANCEL]
}
