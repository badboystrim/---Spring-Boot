package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    public void testAddNonExistingProductThrowsException() {
        UUID invalidId = UUID.randomUUID();
        when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> {
            basketService.addProductToBasket(invalidId);
        });

        verify(productBasket, never()).addProduct(any());
    }

    @Test
    public void testAddExistingProductCallsBasketAdd() {
        UUID validId = UUID.randomUUID();
        Product product = new SimpleProduct(validId, "TestProduct", 120);
        when(storageService.getProductById(validId)).thenReturn(Optional.of(product));

        basketService.addProductToBasket(validId);

        verify(productBasket, times(1)).addProduct(validId);
    }

    @Test
    public void testGetUserBasketWhenBasketIsEmpty() {
        when(productBasket.getBasketMap()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        assertThat(userBasket.getItems()).isEmpty();
        assertThat(userBasket.getTotal()).isZero();
    }

    @Test
    public void testGetUserBasketWithProducts() {
        UUID id = UUID.randomUUID();
        Product product = new SimpleProduct(id, "TestProduct", 100);

        when(productBasket.getBasketMap()).thenReturn(Map.of(id, 2));
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));

        UserBasket userBasket = basketService.getUserBasket();

        assertThat(userBasket.getItems()).hasSize(1);
        assertThat(userBasket.getTotal()).isEqualTo(200);
    }
}
