# clojure-scripting

A Clojure scripting toolkit for web scraping and HTML parsing tasks.

## Overview

This project provides a foundation for writing Clojure scripts that fetch, parse, and extract data from web pages. It combines the power of Hickory for HTML parsing with Babashka's HTTP client for making web requests.

## Requirements

- Java 11 or later
- Clojure CLI (1.11+)

## Installation

Clone the repository:

```bash
git clone https://github.com/conao3/clojure-scripting.git
cd clojure-scripting
```

## Dependencies

| Library | Version | Purpose |
|---------|---------|---------|
| org.clojure/clojure | 1.12.0 | Core language |
| org.clj-commons/hickory | 0.7.7 | HTML parsing and manipulation |
| org.babashka/http-client | 0.4.23 | HTTP requests |

## Usage

Start a REPL with the project dependencies:

```bash
clj
```

Example of fetching and parsing HTML:

```clojure
(require '[hickory.core :as hickory]
         '[hickory.select :as s]
         '[babashka.http-client :as http])

;; Fetch and parse a webpage
(def page (-> "https://example.com"
              http/get
              :body
              hickory/parse
              hickory/as-hickory))

;; Select elements using CSS-like selectors
(s/select (s/tag :h1) page)
```

## Project Structure

```
.
├── deps.edn                          # Project dependencies
└── src/
    └── scripting/
        └── residia_scripting.clj     # Example scraping script
```

## License

Apache License 2.0. See [LICENSE](LICENSE) for details.
