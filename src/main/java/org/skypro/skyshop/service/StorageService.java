package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        initStorage();
    }

    private void initStorage() {
        Product apple = new SimpleProduct(UUID.randomUUID(), "Яблоко", 160);
        Product milk = new DiscountedProduct(UUID.randomUUID(), "Молоко", 125, 20);
        Product bread = new SimpleProduct(UUID.randomUUID(), "Хлеб", 90);
        Product cheese = new FixPriceProduct(UUID.randomUUID(), "Сыр");
        Product meat = new SimpleProduct(UUID.randomUUID(), "Мясо", 950);
        Product juice = new DiscountedProduct(UUID.randomUUID(), "Сок", 220, 10);

        products.put(apple.getId(), apple);
        products.put(milk.getId(), milk);
        products.put(bread.getId(), bread);
        products.put(cheese.getId(), cheese);
        products.put(meat.getId(), meat);
        products.put(juice.getId(), juice);

        Article appleArticle = new Article(UUID.randomUUID(), "Польза яблок", "Яблоко содержит много витаминов.");
        Article milkArticle = new Article(UUID.randomUUID(), "Свежее молоко", "Молоко — отличный источник кальция.");
        Article healthyDiet = new Article(UUID.randomUUID(), "Здоровое питание", "Включите в рацион свежий сок и мясо.");

        articles.put(appleArticle.getId(), appleArticle);
        articles.put(milkArticle.getId(), milkArticle);
        articles.put(healthyDiet.getId(), healthyDiet);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public Collection<Searchable> getAllSearchable() {
        Collection<Searchable> allSearchable = new ArrayList<>();
        allSearchable.addAll(products.values());
        allSearchable.addAll(articles.values());
        return allSearchable;
    }
    public java.util.Optional<Product> getProductById(UUID id) {
        return java.util.Optional.ofNullable(products.get(id));
    }
}
