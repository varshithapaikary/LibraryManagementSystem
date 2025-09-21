package org.example.strategy;



import org.example.library.Patron;
import org.example.library.Book;

import java.util.List;

public interface RecommendationStrategy {
    List<Book> recommend(Patron patron, int maxResults);
}

