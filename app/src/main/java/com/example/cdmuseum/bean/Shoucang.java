package com.example.cdmuseum.bean;

import java.io.Serializable;

public class Shoucang implements Serializable {
    private int id;
    private String chanpinname;
    private String chanpinprice;
    private String path;
    private String xiangqing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChanpinname() {
        return chanpinname;
    }

    public void setChanpinname(String chanpinname) {
        this.chanpinname = chanpinname;
    }

    public String getChanpinprice() {
        return chanpinprice;
    }

    public void setChanpinprice(String chanpinprice) {
        this.chanpinprice = chanpinprice;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getXiangqing() {
        return xiangqing;
    }

    public void setXiangqing(String xiangqing) {
        this.xiangqing = xiangqing;
    }
}
