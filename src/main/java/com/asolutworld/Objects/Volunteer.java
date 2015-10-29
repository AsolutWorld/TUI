package main.java.com.asolutworld.Objects;

import main.java.com.asolutworld.Constants.Strings;

import java.io.Serializable;
import java.io.StringReader;

public class Volunteer implements Serializable {
    private int u_id;
    private String sname;
    private String fname;
    private String location;
    private String phone;
    private String role;

    public Volunteer(int u_id,String sname,String fname,String location,String phone,String role){
        this.u_id=u_id;
        this.sname=sname;
        this.fname=fname;
        this.location=location;
        this.phone=phone;
        this.role=role;
    }

    public boolean getCanEdit(){
        if(role.equals(Strings.ADMIN))return true;
        else return false;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
