package com.booknara.android.apps.patterns.model;

public class PhoneticModel extends ModelType {
    private String text;

    public PhoneticModel(String text) {
        super(ModelType.BODY);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "PhoneticModel{" +
                "text='" + text + '\'' +
                ", itemType=" + itemType +
                '}';
    }
}
