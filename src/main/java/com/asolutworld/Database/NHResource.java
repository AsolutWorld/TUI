package main.java.com.asolutworld.Database;

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
public class NHResource {
    private String stock;
    private String resource;
    private String type="Rechoose resource";
    private String ccount;

    private static final String GET_RESOURCES="SELECT resources.resource, resources.type FROM resources";
    private static final String GET_STOCKS="SELECT stocks.st_name FROM stocks";
    private static final String ADD_HRESOURCE="INSERT INTO hresources VALUES (DEFAULT ,?,?,?,?,?,current_date)";
    private static final String SUB_HRESOURCE="INSERT INTO nresources VALUES (DEFAULT ,?,?,?,?,?,current_date)";
    private static final String FIND_STOCK="SELECT stock_id FROM stocks WHERE st_name=?;";

    private ArrayList<String> resources;
    private ArrayList<String> types;

    public ArrayList<String> getResources(){
        collectResources();

        return resources;
    }

    private ArrayList<String> stocks;
    public ArrayList<String> getStocks(){
        collectStocks();
        return stocks;
    }

    public void setStocks(ArrayList<String> stocks) {
        this.stocks = stocks;
    }

    private void collectStocks(){
        stocks=new ArrayList<>();
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(GET_STOCKS);
                ResultSet resultSet=prep.executeQuery();

                connection.close();

                while (resultSet.next()){
                    stocks.add(resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String addHresource(){
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(ADD_HRESOURCE);
                prep.setInt(1, LoginDAO.getU_ID());
                prep.setInt(2, getStockID());
                prep.setString(3,resource);
                prep.setInt(4,Integer.parseInt(ccount));
                prep.setString(5,type);

                prep.execute();

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "home";
    }

    public String subHresource(){
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(SUB_HRESOURCE);
                prep.setInt(1, LoginDAO.getU_ID());
                prep.setInt(2, getStockID());
                prep.setString(3,resource);
                prep.setInt(4,Integer.parseInt(ccount));
                prep.setString(5,type);

                prep.execute();

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "home";
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    private int getStockID(){
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {


                PreparedStatement prep=connection.prepareStatement(FIND_STOCK);
                prep.setString(1,stock);

                ResultSet res=prep.executeQuery();

                while (res.next()){
                    return res.getInt(1);

                }
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

}
