package com.smoothstack.december.orchestrationservice.entity;

import java.util.HashSet;
import java.util.Set;

public class Author {

    private Long id;
    private String name;
    private Set<Book> books = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

}