package org.example;

import org.example.branch.Inventory;
import org.example.library.Book;
import org.example.library.Patron;
import org.example.service.LibraryService;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        LibraryService library = new LibraryService(inventory);

        Book book1 = new Book("Effective Java", "John", "12345", 2021);
        Patron patron1 = new Patron("P1", "Nikhil");

        library.addBook(book1, 2);
        library.registerPatron(patron1);

        library.checkout("12345", "P1");
        System.out.println("Search result: " + library.searchByTitle("Effective"));
    }
}
