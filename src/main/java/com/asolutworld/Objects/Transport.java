package main.java.com.asolutworld.Objects;

import java.io.Serializable;

public class Transport implements Serializable {
    private int tr_id;
    private String number;
    private String descr;
    private String type;
    private String address;
    private String phone;
    private String work_time;

    public Transport(int tr_id, String number, String descr, String type, String address, String phone, String work_time) {
        this.tr_id = tr_id;
        this.number = number;
        this.descr = descr;
        this.type = type;
        this.address = address;
        this.phone = phone;
        this.work_time = work_time;
    }

    public int getTr_id() {
        return tr_id;
    }

    public void setTr_id(int tr_id) {
        this.tr_id = tr_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWork_time() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }
}
