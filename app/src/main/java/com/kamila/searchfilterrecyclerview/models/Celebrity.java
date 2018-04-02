package com.kamila.searchfilterrecyclerview.models;

public class Celebrity {

    private String name;
    private String imageUrl;

    public Celebrity(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}