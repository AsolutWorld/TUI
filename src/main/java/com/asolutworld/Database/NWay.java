package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Objects.Transport;
import main.java.com.asolutworld.Objects.Way;
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
public class NWay {
    private String GET_WAYS="SELECT * FROM ways";
    private ArrayList<Way> ways;

    public ArrayList<Way> getWays(){
        collectWaysData();
        return ways;
    }

    public void setWays(ArrayList<Way> ways) {
        this.ways = ways;
    }

    private void collectWaysData(){
        ways = new ArrayList<>();
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(GET_WAYS);
                ResultSet resultSet=prep.executeQuery();

                connection.close();

                while (resultSet.next()){
                    ways.add(new Way(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                            resultSet.getInt(4),resultSet.getString(5)
                            ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
