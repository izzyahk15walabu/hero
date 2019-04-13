package com.walaabu.gadalaw;

public class PDFfile {

    String name;
    String Uri;

    public PDFfile(String name, String uri) {
        this.name = name;
        Uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }
}
