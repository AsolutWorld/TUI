package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Authorization.dao.LoginDAO;
import main.java.com.asolutworld.Objects.SRequest;
import main.java.com.asolutworld.Objects.SResource;
import main.java.com.asolutworld.Utils.DataConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

@ManagedBean
@SessionScoped
public class NHResource {
    private String stock;
    private String resource;
    private String type="Rechoose resource";
    private String ccount;
    private boolean show=false;

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
        ArrayList<SResource> q=getSstockResources();
        SResource ss=new SResource("",0,"");
        for(int i=0;i<q.size();i++){
            if(q.get(i).getResource().equals(resource)){
                ss=q.get(i);
            }
        }
        if(ss.getCount()<Integer.parseInt(ccount)||ss.getCount()<1){
            show=true;
            return "home";
        }

        show=false;
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

    private static final String GET_ASTOCK_RESOURCES="SELECT hresources.resource, hresources.count, hresources.type\n" +
            "FROM hresources WHERE (hresources.stock_id=?)";
    private static final String GET_NSTOCK_RESOURCES="SELECT nresources.resource, nresources.count, nresources.type\n" +
            "FROM nresources WHERE (nresources.stock_id=?)";

    private ArrayList<SResource> sstockResources;

    public ArrayList<SResource> getSstockResources(){
        String s=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("stock_id");
        if(s==null)s="-1";
        int stock_id=Integer.valueOf(s);
        sstockResources=collectStockResourcesData(stock_id);

        return sstockResources;
    }
    private ArrayList<SResource> collectStockResourcesData(int stock_id){
        ArrayList<SResource> resources=new ArrayList<>();
        ArrayList<SResource> aresources=new ArrayList<>();
        ArrayList<SResource> nresources=new ArrayList<>();
        try {
            Connection connection=DataConnection.getConnecion();
            if(connection!=null){
                PreparedStatement prep=connection.prepareStatement(GET_ASTOCK_RESOURCES);
                prep.setInt(1,stock_id);

                ResultSet res=prep.executeQuery();
                while (res.next()) {
                    aresources.add(new SResource(
                                    res.getString(1),
                                    res.getInt(2),
                                    res.getString(3)
                            )
                    );
                }

                prep=connection.prepareStatement(GET_RESOURCES);
                res=prep.executeQuery();
                while (res.next()){
                    resources.add(new SResource(res.getString(1),0,res.getString(2)));
                }

                prep=connection.prepareStatement(GET_NSTOCK_RESOURCES);
                prep.setInt(1,stock_id);

                res=prep.executeQuery();
                while (res.next()){
                    nresources.add(new SResource(res.getString(1),res.getInt(2),res.getString(3)));
                }

                connection.close();

                resources=makeASResources(resources,aresources,nresources);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resources;
    }

    private ArrayList<SResource> makeASResources(ArrayList<SResource> resources,ArrayList<SResource> aresources,
                                                 ArrayList<SResource> nresources){
        for(SResource res:resources){
            for(SResource r:aresources){
                if(r.getResource().equals(res.getResource())){
                    res.setCount(res.getCount()+r.getCount());
                }
            }
            for(SResource r:nresources){
                if(r.getResource().equals(res.getResource())){
                    res.setCount(res.getCount()-r.getCount());
                }
            }
        }
        return resources;
    }

}
