package org.example.library.service;

import org.example.observer.Observable;
import org.example.observer.Observer;

import java.util.*;

public class ReservationService implements Observable {
    private final Map<String, Queue<Observer>> reservations = new HashMap<>();

    public synchronized void reserve(String isbn, Observer patronObserver) {
        reservations.computeIfAbsent(isbn, k -> new LinkedList<>()).add(patronObserver);
    }

    public synchronized void notifyAvailable(String isbn) {
        Queue<Observer> q = reservations.get(isbn);
        if (q == null) return;
        Observer next = q.poll();
        if (next != null) {
            next.update("Book with ISBN " + isbn + " is now available.");
        }
    }

    @Override public void addObserver(Observer o) {}
    @Override public void removeObserver(Observer o) {}
    @Override public void notifyObservers(String message) {}
}
