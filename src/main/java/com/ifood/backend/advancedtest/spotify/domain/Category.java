package com.ifood.backend.advancedtest.spotify.domain;

public enum Category {
    CLASSICAL("classical"),
    PARTY("party"),
    POP("pop"),
    ROCK("rock");

    private String categoryId;

    Category(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return this.categoryId;
    }
}
