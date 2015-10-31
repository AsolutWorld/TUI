package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Authorization.beans.SessionBean;
import main.java.com.asolutworld.Objects.Resource;
import main.java.com.asolutworld.Objects.Volunteer;
import main.java.com.asolutworld.Utils.DataConnection;
import main.java.com.asolutworld.Utils.ReportManager;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "databaseController",eager = true)
@SessionScoped
public class DatabaseController {
    private static final String CREATE_AVAILABLE_RESOURCES_TABLE="DROP TABLE IF EXISTS available_resources;\n" +
            "CREATE TABLE available_resources\n" +
            "AS (SELECT hres_id, u_id, stock_id, resource, count,type FROM hresources WHERE count>0 )";
    private static final String GET_VOLUNTEERS="SELECT volunteers.u_id,volunteers.sname,volunteers.fname,volunteers.location\n" +
            "  ,volunteers.phone, volunteers.access FROM volunteers";
    private static final String GET_VOLUNTEER_RESOURCES="SELECT available_resources.hres_id, available_resources.stock_id, \n" +
            "  stocks.st_name, available_resources.resource, available_resources.count, available_resources.type \n" +
            "  FROM available_resources,stocks WHERE (available_resources.u_id=?) AND (available_resources.stock_id=stocks.stock_id)";
    private static final String GET_STOCK_RESOURCES="SELECT available_resources.hres_id, available_resources.u_id,\n" +
            "  available_resources.resource, available_resources.count, available_resources.type\n" +
            "FROM available_resources WHERE available_resources.stock_id=?";

    private static final String GET_VOLUNTEER="SELECT volunteers.u_id,volunteers.sname,volunteers.fname,volunteers.location\n" +
            "  ,volunteers.phone, volunteers.access FROM volunteers WHERE volunteers.u_id=?";

    private static final String UPDATE_VOLUNTEERS="UPDATE volunteers SET sname=?,fname=?,location=?,phone=?,access=? WHERE u_id=?";
    private static final String UPDATE_VOLUNTEER_RESOURCES="UPDATE hresources SET resource=?,count=?,type=? WHERE u_id=?";
    private static final String UPDATE_STOCK_RESOURCES="UPDATE hresources SET resource=?,count=?,type=? WHERE stock_id=?";
    private ArrayList<Volunteer> volunteers;
    public ArrayList<Volunteer> getVolunteers(){
        collectVolunteerData();
        return volunteers;
    }

    private void collectVolunteerData(){

        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {
                volunteers=new ArrayList<>();
                PreparedStatement prep=connection.prepareStatement(CREATE_AVAILABLE_RESOURCES_TABLE);
                prep.execute();

                prep=connection.prepareStatement(GET_VOLUNTEERS);
                ResultSet resultSet=prep.executeQuery();

                connection.close();
                //ReportManager.makeCSV(resultSet);
                while (resultSet.next()){

                    volunteers.add(new Volunteer(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String saveVolunteersChanges(){
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {
                for (Volunteer volunteer:volunteers){
                    PreparedStatement prep=connection.prepareStatement(UPDATE_VOLUNTEERS);
                    prep.setString(1,volunteer.getSname());
                    prep.setString(2,volunteer.getFname());
                    prep.setString(3,volunteer.getLocation());
                    prep.setString(4,volunteer.getPhone());
                    prep.setString(5,volunteer.getRole());
                    prep.setInt(6, volunteer.getU_id());
                    prep.execute();
                }

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }



    private ArrayList<Resource> uresources;
    public ArrayList<Resource> getUresources(){
        String s=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("u_id");
        int u_id=Integer.valueOf(s);
        uresources=collectUserResourcesData(u_id);
        return uresources;
    }

    private ArrayList<Resource> collectUserResourcesData(int u_id){
        ArrayList<Resource> resources=new ArrayList<>();
        try {
            Connection connection=DataConnection.getConnecion();

            if(connection!=null){


                PreparedStatement prep=connection.prepareStatement(GET_VOLUNTEER_RESOURCES);

                prep.setInt(1,u_id);
                ResultSet resultSet=prep.executeQuery();

                connection.close();

                while (resultSet.next()){
                    resources.add(new Resource(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5),
                            resultSet.getString(6)
                    ));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resources;
    }



    private ArrayList<Resource> stockResources;
    public ArrayList<Resource> getStockResources(){
        collectStockResourcesData();
        return stockResources;
    }
    private void collectStockResourcesData(){
        try {
            Connection connection=DataConnection.getConnecion();
            if(connection!=null){
                stockResources=new ArrayList<>();

                PreparedStatement prep=connection.prepareStatement(GET_STOCK_RESOURCES);
                //prep.setInt(1,); TODO: stock_id get

                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setVolunteers(ArrayList<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    public void setUresources(ArrayList<Resource> uresources) {
        this.uresources = uresources;
    }

    public void setStockResources(ArrayList<Resource> stockResources) {
        this.stockResources = stockResources;
    }

    private Volunteer volunteer;
    public Volunteer getVolunteer() {
        String s=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("u_id");
        int u_id=Integer.valueOf(s);
        volunteer=collectUserData(u_id);
        return volunteer;
    }

    private Volunteer collectUserData(int u_id){
        Volunteer vol=new Volunteer(u_id,"","","","","");
        try {
            Connection connection=DataConnection.getConnecion();

            if(connection!=null){


                PreparedStatement prep=connection.prepareStatement(GET_VOLUNTEER);

                prep.setInt(1,u_id);
                ResultSet resultSet=prep.executeQuery();

                connection.close();

                while (resultSet.next()){
                    vol=new Volunteer(u_id,resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getString(5),resultSet.getString(6));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vol;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }




}
