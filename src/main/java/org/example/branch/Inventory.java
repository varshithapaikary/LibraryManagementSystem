package org.example.branch;

import org.example.library.Book;
import org.example.library.BorrowRecord;

import java.util.*;

public class Inventory {
    private final Map<String, Integer> copiesAvailable = new HashMap<>();
    private final Map<String, List<BorrowRecord>> borrowed = new HashMap<>();

    public synchronized void addBook(Book book, int count) {
        copiesAvailable.put(book.getIsbn(),
                copiesAvailable.getOrDefault(book.getIsbn(), 0) + count);
    }

    public synchronized boolean checkout(Book book, BorrowRecord record) {
        String isbn = book.getIsbn();
        int avail = copiesAvailable.getOrDefault(isbn, 0);
        if (avail <= 0) return false;
        copiesAvailable.put(isbn, avail - 1);
        borrowed.computeIfAbsent(isbn, k -> new ArrayList<>()).add(record);
        return true;
    }

    public synchronized void checkin(Book book, BorrowRecord record) {
        String isbn = book.getIsbn();
        copiesAvailable.put(isbn, copiesAvailable.getOrDefault(isbn, 0) + 1);
        List<BorrowRecord> list = borrowed.getOrDefault(isbn, Collections.emptyList());
        list.remove(record);
    }

    public int availableCopies(String isbn) {
        return copiesAvailable.getOrDefault(isbn, 0);
    }
}
