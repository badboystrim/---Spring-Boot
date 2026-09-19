package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String title;
    private final String text;
    public Article(UUID id, String title, String text) {
        if (id == null) {
            throw new IllegalArgumentException("Идентификатор не может быть null");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название статьи не может быть пустым");
        }
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Текст статьи не может быть пустым");
        }
        this.id = id;
        this.title = title;
        this.text = text;
    }
    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return title + " " + text;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }
}
