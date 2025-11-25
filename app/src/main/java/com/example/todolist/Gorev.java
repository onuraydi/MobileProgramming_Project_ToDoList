package com.example.todolist;

public class Gorev {
    private String metin;
    private boolean tamamlandi;

    public Gorev(String metin, boolean tamamlandi) {
        this.metin = metin;
        this.tamamlandi = tamamlandi;
    }

    public String getMetin() {
        return metin;
    }

    public boolean isTamamlandi() {
        return tamamlandi;
    }

    public void setTamamlandi(boolean tamamlandi) {
        this.tamamlandi = tamamlandi;
    }
}