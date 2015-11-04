package main.java.com.asolutworld.Objects;

import java.io.Serializable;

public class Way implements Serializable {
    private int way_id;
    private String address_1;
    private String address_2;
    private int length;
    private String type;

    public int getWay_id() {
        return way_id;
    }

    public void setWay_id(int way_id) {
        this.way_id = way_id;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Way(int way_id, String address_1, String address_2, int length, String type) {

        this.way_id = way_id;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.length = length;
        this.type = type;
    }
}
