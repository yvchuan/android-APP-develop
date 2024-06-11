package com.example.cdmuseum.bean;

import java.io.Serializable;

public class Qiye implements Serializable {
    private int id;
    private String caiming;
    private String path;
    private String price;
    private String jieshao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJieshao() {
        return jieshao;
    }

    public void setJieshao(String jieshao) {
        this.jieshao = jieshao;
    }

    public String getCaiming() {
        return caiming;
    }

    public void setCaiming(String caiming) {
        this.caiming = caiming;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
