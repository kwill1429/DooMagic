package com.psutton.utilities.objects;

public class PSItem {
    private int itemID;
    private int notedID;
    private boolean isAlchable;
    private String itemName;

    public PSItem(String itemName, int itemID) {
        this.setItemName(itemName);
        this.setItemID(itemID);
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getNotedID() {
        return notedID;
    }

    public void setNotedID(int notedID) {
        this.notedID = notedID;
    }

    public boolean isAlchable() {
        return isAlchable;
    }

    public void setAlchable(boolean isAlchable) {
        this.isAlchable = isAlchable;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return this.itemName;
    }
}
