package com.food.ordering.system.order.service.dataaccess.order.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntityId implements Serializable {
    private Long id;
    private OrderEntity order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntityId that = (OrderItemEntityId) o;
        return Objects.equals(id, that.id) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }
}

/*
Вопрос	Ответ
Зачем implements Serializable? - Это требование JPA для @IdClass
Будет ли OrderItemEntityId таблицей? - 	Нет
Сколько таблиц будет создано?	- 3 — orders, order_address, order_items
Зачем @IdClass, а не @EmbeddedId?	- Позволяет оставить ID-поля прямо в сущности
*/