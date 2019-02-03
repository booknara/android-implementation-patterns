package com.booknara.android.apps.patterns.model;

public class HeaderModel extends ModelType {
    private String header;

    public HeaderModel(String header) {
        super(ModelType.HEADER);
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    @Override
    public String toString() {
        return "HeaderModel{" +
                "header='" + header + '\'' +
                ", itemType=" + itemType +
                '}';
    }
}
