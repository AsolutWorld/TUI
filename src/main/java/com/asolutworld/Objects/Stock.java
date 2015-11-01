package main.java.com.asolutworld.Objects;

import java.io.Serializable;

public class Stock implements Serializable {
    private int stock_id;
    private String st_name;
    private String address;
    private String phone;
    private String work_time;

    public Stock(int stock_id,String st_name,String address,String phone,String work_time){
        this.stock_id=stock_id;
        this.st_name=st_name;
        this.address=address;
        this.phone=phone;
        this.work_time=work_time;

    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
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
