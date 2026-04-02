package org.example;

public class PageData {
    private String url;
    private String title;

    public PageData(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return url;
    }
}
