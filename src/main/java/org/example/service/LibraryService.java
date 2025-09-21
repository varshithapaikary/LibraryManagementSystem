package org.example.service;

import org.example.branch.Inventory;
import org.example.library.Book;
import org.example.library.BorrowRecord;
import org.example.library.Patron;

import java.time.LocalDate;
import java.util.*;

public class LibraryService {
    private final Map<String, Book> catalog = new HashMap<>();
    private final Map<String, Patron> patrons = new HashMap<>();
    private final Inventory inventory;

    public LibraryService(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addBook(Book book, int count) {
        catalog.put(book.getIsbn(), book);
        inventory.addBook(book, count);
        System.out.println("Added " + count + " copies of " + book);
    }

    public void registerPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
        System.out.println("Registered patron " + patron.getName());
    }

    public Optional<BorrowRecord> checkout(String isbn, String patronId) {
        Book b = catalog.get(isbn);
        Patron p = patrons.get(patronId);
        if (b == null || p == null) return Optional.empty();

        BorrowRecord rec = new BorrowRecord(b, p, LocalDate.now(), null);
        boolean success = inventory.checkout(b, rec);

        if (success) {
            p.addBorrowRecord(rec);
            System.out.println(p.getName() + " checked out " + b);
            return Optional.of(rec);
        }
        System.out.println("Checkout failed — no copies available for ISBN " + isbn);
        return Optional.empty();
    }

    public void checkin(BorrowRecord rec) {
        inventory.checkin(rec.getBook(), rec);
        rec.setReturnDate(LocalDate.now());
        System.out.println("Returned: " + rec.getBook() + " by " + rec.getPatron().getName());
    }

    public List<Book> searchByTitle(String q) {
        String low = q.toLowerCase();
        return catalog.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(low))
                .toList();
    }
}
