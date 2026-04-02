# Simple Java Search Engine

A small Java CLI project that crawls web pages, stores page titles in MySQL, and lets you search saved pages by keyword.

## Features

- Crawl a starting URL to a chosen depth
- Extract page titles with JSoup
- Save crawled pages into MySQL
- Search saved pages by keyword from the terminal

## Tech Stack

- Java
- Maven
- JSoup
- MySQL

## Project Structure

```text
.
├── pom.xml
├── README.md
├── schema.sql
└── src
    └── main
        └── java
            └── org
                └── example
                    ├── DatabaseManager.java
                    ├── PageData.java
                    ├── SearchEngine.java
                    ├── WebCrawler.java
                    └── WebScraper.java
```

## Can You Run This On macOS?

Yes. This project is standard Java and Maven, so it can run on macOS as long as you have:

- Java installed
- Maven installed
- MySQL running locally or reachable from your Mac

This project was verified to build successfully on macOS with Maven.

## Prerequisites

- Java 8 or newer
- Maven 3+
- MySQL 8+

Check your tools:

```bash
java -version
mvn -version
mysql --version
```

## Setup

### 1. Create the database

Run the schema file in MySQL:

```bash
mysql -u root -p < schema.sql
```

### 2. Configure database connection

The app reads database settings from environment variables.

```bash
export SEARCH_ENGINE_DB_URL="jdbc:mysql://localhost:3306/search_engine"
export SEARCH_ENGINE_DB_USER="root"
export SEARCH_ENGINE_DB_PASSWORD="your_password"
```

If you do not set them, the app defaults to:

- URL: `jdbc:mysql://localhost:3306/search_engine`
- User: `root`
- Password: empty string

### 3. Build the project

```bash
mvn clean package
```

### 4. Run the application

```bash
mvn exec:java -Dexec.mainClass=org.example.SearchEngine
```

If Maven needs to download the `exec` plugin the first time, that is normal.

## How It Works

### Crawl mode

1. Enter option `1`
2. Provide a starting URL
3. Provide a crawl depth
4. The crawler visits pages and stores each page URL and title in MySQL

### Search mode

1. Enter option `2`
2. Provide a keyword
3. The app searches page titles in MySQL and prints matching URLs

## Notes For Developers

- `WebCrawler` handles recursive crawling and link discovery.
- `DatabaseManager` manages MySQL reads and writes.
- `SearchEngine` is the CLI entry point.
- `WebScraper` and `PageData` are simple helper classes that can be extended for richer indexing later.

## Suggested Improvements

- Avoid duplicate URL inserts with a unique constraint
- Store page content, not only titles
- Add unit tests
- Add logging instead of `System.out.println`
- Respect `robots.txt` and add crawl limits/timeouts

## GitHub Push Checklist

- Initialize or clean up the git history if needed
- Commit the current source layout
- Do not commit `.idea`, `target`, `.DS_Store`, or local data files
- Do not commit real database passwords

## License

Add a license before publishing publicly on GitHub if you want others to reuse the code.
