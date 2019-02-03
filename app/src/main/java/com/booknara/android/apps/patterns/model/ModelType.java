package com.booknara.android.apps.patterns.model;

public class ModelType {
    public static final int HEADER = 0;
    public static final int BODY = 1;

    protected int itemType;

    public ModelType(int itemType) {
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }
}
