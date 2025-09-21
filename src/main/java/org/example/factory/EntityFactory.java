package org.example.factory;

import org.example.library.Book;
import org.example.library.Patron;

public class EntityFactory {

    public static Book createBook(String title, String author, String isbn, int year) {
        return new Book(title, author, isbn, year);
    }

    public static Patron createPatron(String id, String name) {
        return new Patron(id, name);
    }
}

