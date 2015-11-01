package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Authorization.dao.LoginDAO;
import main.java.com.asolutworld.Utils.DataConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ManagedBean
@SessionScoped
public class NResource {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String name;
    private String type;

    private String ADD_RESOURCE="INSERT INTO resources VALUES (DEFAULT ,?,?)";

    public String addResource(){
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(ADD_RESOURCE);
                prep.setString(1, name);
                prep.setString(2, type);

                prep.execute();

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "home";
    }
}
