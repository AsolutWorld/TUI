package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Objects.Transport;
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
public class NTransport {
    private String GET_TRANSPORT="SELECT * FROM transport";
    private ArrayList<Transport> transports;

    public void setTransports(ArrayList<Transport> transports) {
        this.transports = transports;
    }

    public ArrayList<Transport> getTransports(){
        collectTransportsData();
        return transports;
    }

    private void collectTransportsData(){
        transports = new ArrayList<>();
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(GET_TRANSPORT);
                ResultSet resultSet=prep.executeQuery();

                connection.close();

                while (resultSet.next()){
                    transports.add(new Transport(resultSet.getInt(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                            resultSet.getString(6),resultSet.getString(7)
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
