package me.jimmy.jpashop.service;


import lombok.RequiredArgsConstructor;
import me.jimmy.jpashop.domain.*;
import me.jimmy.jpashop.domain.item.Item;
import me.jimmy.jpashop.repository.ItemRepository;
import me.jimmy.jpashop.repository.MemberRepository;
import me.jimmy.jpashop.repository.OrderRepository;
import me.jimmy.jpashop.repository.OrderSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setDeliveryStatus(DeliveryStatus.READY);

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order); // Order에 orderItem, Delivery에 대해 cascade가 걸려있어서 save한번만 해도 다 persist가 된다.

        return order.getId();
    }


    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    //검색
    public List<Order> findOrders(OrderSearch search) {
        return orderRepository.findAllByString(search);
    }
}
