package org.example.library;

import java.time.LocalDate;

public class BorrowRecord {
    private final Book book;
    private final Patron patron;
    private final LocalDate checkoutDate;
    private LocalDate returnDate;

    public BorrowRecord(Book book, Patron patron, LocalDate checkoutDate, LocalDate returnDate) {
        this.book = book;
        this.patron = patron;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
    }

    public Book getBook() { return book; }
    public Patron getPatron() { return patron; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}

