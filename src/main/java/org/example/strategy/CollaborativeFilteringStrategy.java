package org.example.strategy;


import org.example.library.Book;
import org.example.library.Patron;

import java.util.*;

public class CollaborativeFilteringStrategy implements RecommendationStrategy {
    private final Map<String, Integer> globalBorrowCount;
    private final Map<String, Book> catalog;

    public CollaborativeFilteringStrategy(Map<String, Integer> borrowCount,
                                          Map<String, Book> catalog) {
        this.globalBorrowCount = borrowCount;
        this.catalog = catalog;
    }

    @Override
    public List<Book> recommend(Patron patron, int maxResults) {
        Set<String> seen = new HashSet<>();
        patron.getHistory().forEach(r -> seen.add(r.getBook().getIsbn()));

        return globalBorrowCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .filter(e -> !seen.contains(e.getKey()))
                .map(e -> catalog.get(e.getKey()))
                .filter(Objects::nonNull)
                .limit(maxResults)
                .toList();
    }
}

