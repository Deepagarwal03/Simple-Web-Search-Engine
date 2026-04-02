package org.example;

import java.util.List;
import java.util.Scanner;

public class SearchEngine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WebCrawler crawler = new WebCrawler();
        DatabaseManager dbManager = new DatabaseManager();

        System.out.println("\n===== Simple Java Search Engine =====");
        System.out.println("1. Crawl a website and save to database");
        System.out.println("2. Search for a keyword in the database");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            // Option 1: Crawling
            System.out.println("\nEnter the starting URL for crawling:");
            String startUrl = scanner.nextLine();

            System.out.println("Enter the depth for crawling:");
            int depth = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.println("\nStarting crawl...");
            crawler.crawl(startUrl, depth);
            System.out.println("Crawling completed! Data saved to the database.");

        } else if (choice == 2) {
            // Option 2: Searching
            System.out.println("\nEnter a keyword to search in the database:");
            String keyword = scanner.nextLine();

            List<String> results = dbManager.search(keyword);

            System.out.println("\nSearch Results (Matching URLs):");
            if (results.isEmpty()) {
                System.out.println("No results found for keyword: " + keyword);
            } else {
                results.forEach(System.out::println);
            }

            System.out.println("\nExiting program. Goodbye!");
            System.exit(0); // Exit the program automatically after search

        } else {
            System.out.println("Invalid choice! Please enter 1 or 2.");
        }

        scanner.close();
    }
}
