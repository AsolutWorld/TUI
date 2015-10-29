package main.java.com.asolutworld.Objects;

import java.io.Serializable;
import java.util.StringTokenizer;

public class Resource implements Serializable {
    private int hres_id;
    private int stock_id;
    private String stock_name;
    private String resource;
    private int count;
    private String type;

    public Resource(int hres_id,int stock_id,String stock_name, String resource,int count, String type){
        this.hres_id=hres_id;
        this.stock_id=stock_id;
        this.stock_name=stock_name;

        this.resource=resource;
        this.count=count;
        this.type=type;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public int getHres_id() {
        return hres_id;
    }

    public void setHres_id(int hres_id) {
        this.hres_id = hres_id;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
