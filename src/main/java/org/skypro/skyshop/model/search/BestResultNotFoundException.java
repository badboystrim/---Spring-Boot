package org.skypro.skyshop.model.search;

public class BestResultNotFoundException extends Exception {
    public BestResultNotFoundException(String query) {
        super("Для поискового запроса \"" + query + "\" не нашлось подходящего элемента.");
    }
}
