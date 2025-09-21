package org.example.branch;

public class Branch {
    private final String id;
    private final String name;
    private final Inventory inventory;

    public Branch(String id, String name, Inventory inventory) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Inventory getInventory() { return inventory; }
}

