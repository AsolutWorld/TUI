package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Utils.DataConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ManagedBean
@SessionScoped
public class NNStock {
    private String name;
    private String address;
    private String phone;
    private String work_time;

    private String ADD_STOCK="INSERT INTO stocks VALUES (DEFAULT ,?,?,?,?)";

    public String addStock(){
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(ADD_STOCK);
                prep.setString(1,name);
                prep.setString(2,address);
                prep.setString(3,phone);
                prep.setString(4,work_time);

                prep.execute();
                connection.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "stocks";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
