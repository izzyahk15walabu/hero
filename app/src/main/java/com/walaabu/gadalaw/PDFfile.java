package com.walaabu.gadalaw;

import android.net.Uri;

public class PDFfile {

    String name;
    String adress;

    public PDFfile() {
    }

    public PDFfile(String name, String adress) {
        this.name = name;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
