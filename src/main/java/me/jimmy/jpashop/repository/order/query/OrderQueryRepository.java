package me.jimmy.jpashop.repository.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
    private final EntityManager em;

    // OrderItems는 일대다이기 때문에 바로 못넣는다.
    public List<OrderQueryDto> findOrders() {
        return em.createQuery(
                "select new me.jimmy.jpashop.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                        "from Order o " +
                        "join o.member m " +
                        "join o.delivery d", OrderQueryDto.class)
                .getResultList();
    }

    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();

        // 컬렉션 부분을 직접채운다.
        result.forEach(o -> {
           List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
           o.setOrderItems(orderItems);
        });
        return result;
    }

    // 일대다 부분을 해결하기 위한 쿼리
    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "select new me.jimmy.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, oi.item.name, oi.item.price, oi.item.stockQuantity) " +
                        "from OrderItem oi " +
                        "join oi.item i " +
                        "where oi.order.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    public List<OrderQueryDto> findAllByDtoOptimization() {
        List<OrderQueryDto> result = findOrders(); // 주문을 다 가지고 온다.
        List<Long> orderIds = toOrderIds(result);
        Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(orderIds);
        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));
        return result;
    }

    private List<Long> toOrderIds(List<OrderQueryDto> result) {
        List<Long> orderIds = result.stream() // orderId의 List로 변
                .map(o -> o.getOrderId())
                .collect(Collectors.toList());
        return orderIds;
    }

    private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(List<Long> orderIds) {
        List<OrderItemQueryDto> orderItems = em.createQuery("select new me.jimmy.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, oi.item.name, oi.item.price, oi.item.stockQuantity) " +
                "from OrderItem oi " +
                "join oi.item i " +
                "where oi.order.id in :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();

        // 최적화
        Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems
                .stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
        return orderItemMap;
    }

    public List<OrderFlatDto> findAllByDtoFlat() {
        return em.createQuery(
                "select new " +
                        "me.jimmy.jpashop.repository.order.query.OrderFlatDto(o.id, m.name, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count) " +
                        "from Order  o " +
                        "join o.member m " +
                        "join o.delivery d " +
                        "join o.orderItems oi " +
                        "join oi.item i",
                OrderFlatDto.class).getResultList();
    }
}
