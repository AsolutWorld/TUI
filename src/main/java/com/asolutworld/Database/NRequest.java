package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Authorization.beans.Login;
import main.java.com.asolutworld.Authorization.beans.SessionBean;
import main.java.com.asolutworld.Authorization.dao.LoginDAO;
import main.java.com.asolutworld.Utils.DataConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
public class NRequest {
    private String address;
    private String resource;
    private String type="Перевыберите ресурс";
    private String ccount;
    private int count;

    private static final String GET_RESOURCES="SELECT resources.resource, resources.type FROM resources";
    private String ADD_REQUEST="INSERT INTO requests VALUES (DEFAULT ,?,?,?,?,?,current_date,DEFAULT )";

    private ArrayList<String> resources;
    private ArrayList<String> types;

    public ArrayList<String> getResources(){
        collectResources();

        return resources;
    }

    public String addRequest(){
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(ADD_REQUEST);
                prep.setInt(1, LoginDAO.getU_ID());
                prep.setString(2,address);
                prep.setString(3,resource);
                prep.setInt(4,Integer.parseInt(ccount));
                prep.setString(5,type);

                prep.execute();

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "requests";
    }

    public String getCcount() {
        return ccount;
    }

    public void setCcount(String ccount) {
        this.ccount = ccount;
    }

    private void collectResources(){
        resources=new ArrayList<>();
        types=new ArrayList<>();
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(GET_RESOURCES);
                ResultSet resultSet=prep.executeQuery();

                connection.close();

                while (resultSet.next()){
                    resources.add(resultSet.getString(1));
                    types.add(resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void onChangeListener(ValueChangeEvent e){
        resource=e.getNewValue().toString();
        type=types.get(resources.indexOf(resource));
    }

    public void setResources(ArrayList<String> resources) {
        this.resources = resources;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
