package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WebCrawler {
    private Set<String> visitedLinks = new HashSet<>();
    private DatabaseManager dbManager = new DatabaseManager();

    public void crawl(String url, int depth) {
        if (depth == 0 || visitedLinks.contains(url)) {
            return;
        }

        visitedLinks.add(url);
        System.out.println("Crawling: " + url);

        try {
            Document document = Jsoup.connect(url).get();
            String title = document.title();

            // Save to MySQL database
            dbManager.savePage(url, title);

            Elements links = document.select("a[href]");
            for (Element link : links) {
                String linkUrl = link.attr("abs:href");
                crawl(linkUrl, depth - 1);
            }
        } catch (IOException e) {
            System.out.println("Error while crawling: " + e.getMessage());
        }
    }
}
