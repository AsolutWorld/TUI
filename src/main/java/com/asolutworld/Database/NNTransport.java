package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Utils.DataConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ManagedBean
@SessionScoped
public class NNTransport {
    private String number;
    private String descr;
    private String type;
    private String address;
    private String phone;
    private String work_time;

    private String ADD_TRANSPORT="INSERT INTO transport VALUES (DEFAULT ,?,?,?,?,?,?)";

    public String addTransport(){
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(ADD_TRANSPORT);
                prep.setString(1,number);
                prep.setString(2,descr);
                prep.setString(3,type);
                prep.setString(4,address);
                prep.setString(5,phone);
                prep.setString(6,work_time);
                prep.execute();
                connection.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "transport";
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
