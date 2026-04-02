package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebScraper {
    public PageData scrape(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String title = document.title();

            return new PageData(url, title);
        } catch (IOException e) {
            System.out.println("Error while scraping: " + e.getMessage());
            return null;
        }
    }
}
