package main.java.com.asolutworld.Objects;

import main.java.com.asolutworld.Authorization.beans.SessionBean;
import main.java.com.asolutworld.Constants.Strings;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.io.StringReader;

public class Volunteer implements Serializable {
    private int u_id;
    private String sname;
    private String fname;
    private String address;
    private String phone;
    private String role;

    public Volunteer(int u_id,String sname,String fname,String address,String phone,String role){
        this.u_id=u_id;
        this.sname=sname;
        this.fname=fname;
        this.address=address;
        this.phone=phone;
        this.role=role;
    }

    public boolean getCanEdit(){
        if(role.equals(Strings.ADMIN))return true;
        else return false;
    }

    public boolean getMyProfile(){
        if(u_id== (int)SessionBean.getSession().getAttribute("u_id")){
            return true;
        }
        return false;
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
}
