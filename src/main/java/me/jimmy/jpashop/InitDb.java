package me.jimmy.jpashop;

import lombok.RequiredArgsConstructor;
import me.jimmy.jpashop.domain.*;
import me.jimmy.jpashop.domain.item.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "11111");
            em.persist(member);

            Book book = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }
         public void dbInit2() {
            Member member2 = createMember("userB", "용인", "2", "22222");
            em.persist(member2);

            Book book = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book);

            Book book2 = createBook("SPRING2 BOOK", 40000, 400);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

             Delivery delivery = createDelivery(member2);
             Order order = Order.createOrder(member2, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String bookName, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(bookName);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
    }
}
