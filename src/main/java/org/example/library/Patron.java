package org.example.library;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private final String id;
    private String name;
    private final List<BorrowRecord> history = new ArrayList<>();

    public Patron(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<BorrowRecord> getHistory() { return history; }

    public void addBorrowRecord(BorrowRecord r) {
        history.add(r);
    }
}
