package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Utils.DataConnection;
import main.java.com.asolutworld.Utils.SendMail;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ManagedBean
@SessionScoped
public class Register {
    private String login;
    private String pass;
    private String sname;
    private String fname;
    private String address;
    private String phone;

    private boolean usedl=false;

    public boolean isUsedl() {
        return usedl;
    }

    public void setUsedl(boolean usedl) {
        this.usedl = usedl;
    }

    private String ADD_USER="INSERT INTO volunteers VALUES (DEFAULT ,?,?,?,?,?,?,'public',?)";
    private String CHECK_USER="SELECT login FROM volunteers WHERE login=?";

    public String addUser(){
        try {
            if(usedl)return "register";
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {

                String hash=String.valueOf(login.hashCode());

                PreparedStatement prep=connection.prepareStatement(ADD_USER);
                prep.setString(1, login);
                prep.setString(2, pass);
                prep.setString(3,sname);
                prep.setString(4,fname);
                prep.setString(5,address);
                prep.setString(6,phone);
                prep.setString(7,hash);

                prep.execute();

                connection.close();
                SendMail.send(hash,login,address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "index";
    }

    public void onChangeListener(ValueChangeEvent e){
        login=e.getNewValue().toString();
        usedl=checkUser(login);
    }

    private boolean checkUser(String name){
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(CHECK_USER);
                prep.setString(1, name);

                ResultSet res=prep.executeQuery();
                while (res.next()){
                    return true;
                }

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
