package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> basket = new HashMap<>();

    public void addProduct(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        basket.put(id, basket.computeIfAbsent(id, k -> 0) + 1);
    }

    public Map<UUID, Integer> getBasketMap() {
        return Collections.unmodifiableMap(basket);
    }
}
