package com.appservisi.aktalist.data;

public class Markalar {
    String markaID;
    String markaAdi;
    String resim;

    public Markalar(String markaID, String markaAdi,String resim) {
        this.markaID = markaID;
        this.markaAdi = markaAdi;
        this.resim = resim;
    }

    public String getMarkaID() {
        return markaID;
    }

    public void setMarkaID(String markaID) {
        this.markaID = markaID;
    }

    public String getMarkaAdi() {
        return markaAdi;
    }

    public void setMarkaAdi(String markaAdi) {
        this.markaAdi = markaAdi;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }
}
