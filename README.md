# Simple Java Search Engine

A beginner-friendly Java project that crawls web pages, stores page data in MySQL, and lets you search saved results from the terminal.

This project is built with Java, Maven, JSoup, and MySQL, and it runs on macOS, Windows, and Linux as long as Java, Maven, and MySQL are installed.

## Features

- Crawl a website starting from a given URL
- Follow links up to a selected depth
- Extract page titles using JSoup
- Save crawled pages into MySQL
- Search stored pages by keyword
- Run from a simple command-line menu

## Tech Stack

- Java 8+
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

## How It Works

The application starts with a terminal menu:

1. Crawl a website and save data to the database
2. Search saved data by keyword

During crawling, the app:

- visits the starting page
- extracts the page title
- stores the URL and title in MySQL
- follows links until the depth limit is reached

During search, the app:

- asks for a keyword
- checks saved titles in the database
- prints matching URLs

## Prerequisites

Make sure these are installed:

- Java
- Maven
- MySQL

You can verify with:

```bash
java -version
mvn -version
mysql --version
```

## Database Setup

Create the database and table with:

```bash
mysql -u root -p < schema.sql
```

This creates:

- database: `search_engine`
- table: `pages`

## Configuration

The application reads MySQL settings from environment variables.

```bash
export SEARCH_ENGINE_DB_URL="jdbc:mysql://localhost:3306/search_engine"
export SEARCH_ENGINE_DB_USER="root"
export SEARCH_ENGINE_DB_PASSWORD="your_password"
```

Default values if not provided:

- `SEARCH_ENGINE_DB_URL=jdbc:mysql://localhost:3306/search_engine`
- `SEARCH_ENGINE_DB_USER=root`
- `SEARCH_ENGINE_DB_PASSWORD=` empty

## Build

From the project folder:

```bash
mvn clean package
```

## Run

Run the application with:

```bash
mvn exec:java
```

You will see a menu like this:

```text
===== Simple Java Search Engine =====
1. Crawl a website and save to database
2. Search for a keyword in the database
```

## Example Workflow

1. Start the app
2. Choose option `1`
3. Enter a URL like `https://example.com`
4. Enter crawl depth like `2`
5. Let the crawler store results in MySQL
6. Run the app again
7. Choose option `2`
8. Search using a word from a saved page title

## Important Notes

- Some websites block crawlers or show bot-verification pages
- Reddit, for example, may return a verification page instead of normal content
- The current crawler may insert duplicate URLs
- This project stores page titles only, not full page content

## Known Limitations

- No duplicate protection in the database yet
- No crawl delay or rate limiting
- No `robots.txt` handling
- No unit tests yet
- Basic console logging only

## Suggested Improvements

- Add a unique constraint for URLs
- Store full page text for better searching
- Add error logging
- Add tests
- Add URL normalization
- Skip bot-check or verification pages

## GitHub Tips

Before pushing this project:

- keep `.idea`, `target`, `.DS_Store`, and local crawl output out of git
- never commit real database passwords
- add a license if you want others to reuse the code
- add screenshots or a demo GIF if you want a stronger portfolio project

## Author

Created by Deep.
