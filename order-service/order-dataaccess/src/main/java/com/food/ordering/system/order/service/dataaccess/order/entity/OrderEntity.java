package com.food.ordering.system.order.service.dataaccess.order.entity;


import com.food.ordering.system.domain.valueobject.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class OrderEntity {
    @Id
    private UUID id;
    private UUID customerId;
    private UUID restaurantId;
    private UUID trackingId;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String failureMessages;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderAddressEntity address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

/*
Алгоритм: Построение JPA-модели

1) Определи предметную область (например, "Заказ состоит из товаров и адреса")
2) Создай классы-модели: Order, OrderItem, Address
3) Добавь @Entity, @Table(name=...) на каждый
4) Добавь @Id и тип идентификатора (UUID, Long)
5) Свяжи классы между собой:
 - @OneToMany, @ManyToOne, @OneToOne
 - Добавь @JoinColumn если нужно задать имя foreign key
6) Если есть составной ключ — создай @IdClass
7) Добавь @Builder, @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor
8) Обязательно переопредели equals и hashCode
9) Создай соответствующие таблицы в БД или доверь это Hibernate
*/
