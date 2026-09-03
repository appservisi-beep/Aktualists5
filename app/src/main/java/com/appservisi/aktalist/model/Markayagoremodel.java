package com.appservisi.aktalist.model;

public class Markayagoremodel {

    String aktuelid;
    String aktueladi;
    String resim;

    public Markayagoremodel(String aktuelid, String aktueladi, String resim) {
        this.aktuelid = aktuelid;
        this.aktueladi = aktueladi;
        this.resim = resim;
    }

    public String getAktuelid() {
        return aktuelid;
    }

    public void setAktuelid(String aktuelid) {
        this.aktuelid = aktuelid;
    }

    public String getAktueladi() {
        return aktueladi;
    }

    public void setAktueladi(String aktueladi) {
        this.aktueladi = aktueladi;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }
}
