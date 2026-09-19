package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void testSearchWhenStorageIsEmpty() {
        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search("Test");

        assertThat(results).isEmpty();
    }

    @Test
    public void testSearchWhenNoMatchingObjects() {
        List<Searchable> searchables = List.of(new SimpleProduct(UUID.randomUUID(), "Хлеб", 50));
        when(storageService.getAllSearchable()).thenReturn(searchables);

        Collection<SearchResult> results = searchService.search("Test");

        assertThat(results).isEmpty();
    }

    @Test
    public void testSearchWhenMatchingObjectExists() {
        List<Searchable> searchables = List.of(
                new SimpleProduct(UUID.randomUUID(), "TestProduct", 90)
        );
        when(storageService.getAllSearchable()).thenReturn(searchables);

        Collection<SearchResult> results = searchService.search("Test");

        assertThat(results).hasSize(1);
    }
}
