package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Utils.DataConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
public class NNWay {
    private String add_1;
    private String add_2;
    private double length;
    private String type;

    private String ADD_WAY="INSERT INTO ways VALUES (DEFAULT ,?,?,?,?)";

    public String addWay(){

        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(ADD_WAY);
                prep.setString(1,add_1);
                prep.setString(2,add_2);
                prep.setDouble(3, length);
                prep.setString(4,type);
                prep.execute();
                connection.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return "ways";
    }

    public String getAdd_1() {
        return add_1;
    }

    public void setAdd_1(String add_1) {
        this.add_1 = add_1;
    }

    public String getAdd_2() {
        return add_2;
    }

    public void setAdd_2(String add_2) {
        this.add_2 = add_2;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
