package com.example.fitnessandhealth.model;


public class Kullanici {
    private String kullaniciname, kullanicisurname,kullanicimail, kullaniciheight, kullaniciweight, kullaniciage, kullaniciUid, kullaniciweist, kullanicihip, kullanicineck, kullanicimale, kullanicifemale;

    public Kullanici(String kullaniciname, String kullanicisurname, String kullanicimail, String kullaniciheight, String kullaniciweight, String kullaniciage, String kullaniciUid
     ,String kullaniciweist,String kullanicihip, String kullanicineck, String kullanicimale, String kullanicifemale) {
        this.kullaniciname = kullaniciname;
        this.kullanicisurname = kullanicisurname;
        this.kullanicimail = kullanicimail;
        this.kullaniciheight = kullaniciheight;
        this.kullaniciweight = kullaniciweight;
        this.kullaniciage = kullaniciage;
        this.kullaniciUid = kullaniciUid;
        this.kullaniciweist = kullaniciweist;
        this.kullanicihip = kullanicihip;
        this.kullanicineck = kullanicineck;
        this.kullanicimale = kullanicimale;
        this.kullanicifemale = kullanicifemale;
    }

    public Kullanici() {
    }

    public String getKullaniciname() {
        return kullaniciname;
    }

    public void setKullaniciname(String kullaniciname) {
        this.kullaniciname = kullaniciname;
    }

    public String getKullanicisurname() {
        return kullanicisurname;
    }

    public void setKullanicisurname(String kullanicisurname) {
        this.kullanicisurname = kullanicisurname;
    }

    public String getKullanicimail() {
        return kullanicimail;
    }

    public void setKullanicimail(String kullanicimail) {
        this.kullanicimail = kullanicimail;
    }

    public String getKullaniciheight() {
        return kullaniciheight;
    }

    public void setKullaniciheight(String kullaniciheight) {
        this.kullaniciheight = kullaniciheight;
    }

    public String getKullaniciweight() {
        return kullaniciweight;
    }

    public void setKullaniciweight(String kullaniciweight) {
        this.kullaniciweight = kullaniciweight;
    }

    public String getKullaniciage() {
        return kullaniciage;
    }

    public void setKullaniciage(String kullaniciage) {
        this.kullaniciage = kullaniciage;
    }

    public String getKullaniciUid() {
        return kullaniciUid;
    }

    public void setKullaniciUid(String kullaniciUid) {
        this.kullaniciUid = kullaniciUid;
    }

    public String getKullanicihip() {
        return kullanicihip;
    }

    public void setKullanicihip(String kullanicihip) {
        this.kullanicihip = kullanicihip;
    }

    public String getKullaniciweist() {
        return kullaniciweist;
    }

    public void setKullaniciweist(String kullaniciweist) {
        this.kullaniciweist = kullaniciweist;
    }

    public String getKullanicineck() {
        return kullanicineck;
    }

    public void setKullanicineck(String kullanicineck) {
        this.kullanicineck = kullanicineck;
    }

    public String getKullanicimale() {
        return kullanicimale;
    }

    public void setKullanicimale(String kullanicimale) {
        this.kullanicimale = kullanicimale;
    }

    public String getKullanicifemale() {
        return kullanicifemale;
    }

    public void setKullanicifemale(String kullanicifemale) {
        this.kullanicifemale = kullanicifemale;
    }
}
