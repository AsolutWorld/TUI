package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Authorization.dao.LoginDAO;
import main.java.com.asolutworld.Objects.*;
import main.java.com.asolutworld.Utils.DataConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

@ManagedBean(name = "databaseController",eager = true)
@SessionScoped
public class DatabaseController {

    private static final String GET_VOLUNTEERS="SELECT volunteers.u_id,volunteers.sname,volunteers.fname,volunteers.address\n" +
            "  ,volunteers.phone, volunteers.access FROM volunteers";
    private static final String GET_VOLUNTEER_RESOURCES="SELECT hresources.hres_id, hresources.stock_id, \n" +
            "  stocks.st_name, hresources.resource, hresources.count, hresources.type, hresources.date \n" +
            "  FROM hresources,stocks WHERE (hresources.u_id=?) AND (hresources.stock_id=stocks.stock_id)";

    private static final String GET_ASTOCK_RESOURCES="SELECT hresources.resource, hresources.count, hresources.type\n" +
            "FROM hresources WHERE (hresources.stock_id=?)";
    private static final String GET_NSTOCK_RESOURCES="SELECT nresources.resource, nresources.count, nresources.type\n" +
            "FROM nresources WHERE (nresources.stock_id=?)";

    private static final String GET_RESOURCES="SELECT resources.resource, resources.type FROM resources";
    private static final String GET_ARESOURCES="SELECT concat(volunteers.fname, ' ', volunteers.sname), hresources.resource, hresources.count,hresources.type,\n" +
            "  hresources.date FROM hresources,volunteers WHERE (volunteers.u_id=hresources.u_id) AND (hresources.stock_id=?)";
    private static final String GET_NRESOURCES ="SELECT concat(volunteers.fname, ' ', volunteers.sname), nresources.resource, nresources.count,nresources.type,\n" +
            "  nresources.date FROM nresources,volunteers WHERE (volunteers.u_id=nresources.u_id) AND (nresources.stock_id=?)";
    private static final String GET_REQUESTS="SELECT concat(volunteers.fname,' ',volunteers.sname),requests.address,requests.resource,requests.count,\n" +
            "  requests.type,requests.date,requests.active, requests.req_id FROM requests,volunteers WHERE volunteers.u_id=requests.u_id";

    private static final String GET_VOLUNTEER="SELECT volunteers.u_id,volunteers.sname,volunteers.fname,volunteers.address\n" +
            "  ,volunteers.phone, volunteers.access FROM volunteers WHERE volunteers.u_id=?";
    private static final String GET_STOCK="SELECT stocks.st_name,stocks.address,stocks.phone,stocks.work_time FROM stocks WHERE stocks.stock_id=?";
    private static final String GET_STOCKS="SELECT stocks.stock_id,stocks.st_name,stocks.address,stocks.phone,stocks.work_time FROM stocks";

    private ArrayList<Volunteer> volunteers;
    public ArrayList<Volunteer> getVolunteers(){
        collectVolunteerData();
        return volunteers;
    }

    public void downloadVolunteersFile() {
        ReportManager.makeCSV(volunteers);
        File file = new File(String.valueOf(LoginDAO.getU_ID())+"-volunteers.csv");
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        response.setHeader("Content-Disposition", "attachment;filename="+String.valueOf(LoginDAO.getU_ID())+"-volunteers.csv");
        response.setContentLength((int) file.length());
        ServletOutputStream out = null;
        try {
            FileInputStream input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            int i = 0;
            while ((i = input.read(buffer)) != -1) {
                out.write(buffer);
                out.flush();
            }
            FacesContext.getCurrentInstance().getResponseComplete();
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

    }

    private void collectVolunteerData(){

        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {
                volunteers=new ArrayList<>();

                PreparedStatement prep=connection.prepareStatement(GET_VOLUNTEERS);
                ResultSet resultSet=prep.executeQuery();

                connection.close();

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

    public void downloadUresourcesFile() {
        ReportManager.makeurCSV(uresources);
        File file = new File(String.valueOf(LoginDAO.getU_ID())+"-uresources.csv");
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        response.setHeader("Content-Disposition", "attachment;filename="+String.valueOf(LoginDAO.getU_ID())+"-uresources.csv");
        response.setContentLength((int) file.length());
        ServletOutputStream out = null;
        try {
            FileInputStream input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            int i = 0;
            while ((i = input.read(buffer)) != -1) {
                out.write(buffer);
                out.flush();
            }
            FacesContext.getCurrentInstance().getResponseComplete();
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

    }

    public void downloadRequestsFile() {
        ReportManager.makereqCSV(requests);
        File file = new File(String.valueOf(LoginDAO.getU_ID())+"-requests.csv");
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        response.setHeader("Content-Disposition", "attachment;filename="+String.valueOf(LoginDAO.getU_ID())+"-requests.csv");
        response.setContentLength((int) file.length());
        ServletOutputStream out = null;
        try {
            FileInputStream input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            int i = 0;
            while ((i = input.read(buffer)) != -1) {
                out.write(buffer);
                out.flush();
            }
            FacesContext.getCurrentInstance().getResponseComplete();
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

    }

        public void downloadStocksFile() {
            ReportManager.makestsCSV(stocks);
            File file = new File(String.valueOf(LoginDAO.getU_ID())+"-stocks.csv");
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            response.setHeader("Content-Disposition", "attachment;filename="+String.valueOf(LoginDAO.getU_ID())+"-stocks.csv");
            response.setContentLength((int) file.length());
            ServletOutputStream out = null;
            try {
                FileInputStream input = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                out = response.getOutputStream();
                int i = 0;
                while ((i = input.read(buffer)) != -1) {
                    out.write(buffer);
                    out.flush();
                }
                FacesContext.getCurrentInstance().getResponseComplete();
            } catch (IOException err) {
                err.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

    }

    private String UPDATE_REQUESTS="UPDATE requests SET active=? WHERE req_id=?";
    public void updateRequests(){
        try {
            Connection connection= DataConnection.getConnecion();
            if (connection != null) {

                for (SRequest req:requests){

                    PreparedStatement prep=connection.prepareStatement(UPDATE_REQUESTS);
                    prep.setInt(1, LoginDAO.getU_ID());
                    prep.setInt(2, req.getReq_id());

                    prep.execute();
                }

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void downloadStockFile() {
        String s=(String.valueOf(LoginDAO.getU_ID()) + "-" + stock.getSt_name() + "-stock.csv").replace(' ','_');
        ReportManager.makestCSV(sstockResources, s);
        File file = new File(s);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        response.setHeader("Content-Disposition", "attachment;filename="+s);
        response.setContentLength((int) file.length());
        ServletOutputStream out = null;
        try {
            FileInputStream input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            int i = 0;
            while ((i = input.read(buffer)) != -1) {
                out.write(buffer);
                out.flush();
            }
            FacesContext.getCurrentInstance().getResponseComplete();
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

    }

    public void downloadStockReqFile() {
        String s=(String.valueOf(LoginDAO.getU_ID())+"-"+stock.getSt_name()+"-stock_req.csv").replace(' ', '_');
        ReportManager.makestrCSV(allRequests, s);
        File file = new File(s);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        response.setHeader("Content-Disposition", "attachment;filename="+s);
        response.setContentLength((int) file.length());
        ServletOutputStream out = null;
        try {
            FileInputStream input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            int i = 0;
            while ((i = input.read(buffer)) != -1) {
                out.write(buffer);
                out.flush();
            }
            FacesContext.getCurrentInstance().getResponseComplete();
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

    }

    private ArrayList<Resource> uresources;
    public ArrayList<Resource> getUresources(){
        String s=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("u_id");
        if(s==null)s="-1";
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
                            resultSet.getString(6),
                            resultSet.getDate(7)
                    ));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resources;
    }



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


    public void setVolunteers(ArrayList<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    public void setUresources(ArrayList<Resource> uresources) {
        this.uresources = uresources;
    }


    private Volunteer volunteer;
    public Volunteer getVolunteer() {
        String s=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("u_id");
        if(s==null)s="-1";
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

    private Stock stock;

    public Stock getStock(){
        String s=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("stock_id");
        if(s==null)s="-1";
        int stock_id=Integer.valueOf(s);
        stock=collectStockData(stock_id);
        return stock;
    }

    private Stock collectStockData(int stock_id){
        Stock sto=new Stock(stock_id,"","","","");
        try {
            Connection connection=DataConnection.getConnecion();

            if(connection!=null){


                PreparedStatement prep=connection.prepareStatement(GET_STOCK);

                prep.setInt(1,stock_id);
                ResultSet resultSet=prep.executeQuery();

                connection.close();

                while (resultSet.next()){
                    sto=new Stock(stock_id,resultSet.getString(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getString(4));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sto;
    }

    private ArrayList<Stock> stocks;

    public ArrayList<Stock> getStocks(){
        stocks=collectAllStocksData();
        return stocks;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }


    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    private ArrayList<Stock> collectAllStocksData(){
        ArrayList<Stock> sto=new ArrayList<>();
        try {
            Connection connection=DataConnection.getConnecion();

            if(connection!=null){


                PreparedStatement prep=connection.prepareStatement(GET_STOCKS);
                ResultSet res=prep.executeQuery();

                connection.close();

                while (res.next()){
                    sto.add(new Stock(res.getInt(1),res.getString(2),res.getString(3),
                            res.getString(4),res.getString(5)
                    ));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sto;
    }


    private ArrayList<SRequest> allRequests;
    public ArrayList<SRequest> getAllRequests(){
        allRequests=new ArrayList<>();
        String s=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("stock_id");
        if(s==null)s="-1";
        int stock_id=Integer.valueOf(s);
        ArrayList<SRequest> arequests=collectARequests(stock_id);
        ArrayList<SRequest> rrequests=collectRRequests(stock_id);
        for (SRequest req:arequests){
            allRequests.add(req);
        }
        for (SRequest req:rrequests){
            allRequests.add(req);
        }
        Collections.sort(allRequests);
        return allRequests;
    }

    private ArrayList<SRequest> collectARequests(int stock_id){
        ArrayList<SRequest> req=new ArrayList<>();
        try {
            Connection connection=DataConnection.getConnecion();

            if(connection!=null){


                PreparedStatement prep=connection.prepareStatement(GET_ARESOURCES);
                prep.setInt(1,stock_id);
                ResultSet res=prep.executeQuery();

                connection.close();

                while (res.next()){
                    req.add(new SRequest("In",res.getString(1),res.getString(2),res.getInt(3),res.getString(4),
                            res.getDate(5)));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return req;
    }

    private ArrayList<SRequest> collectRRequests(int stock_id){
        ArrayList<SRequest> req=new ArrayList<>();
        try {
            Connection connection=DataConnection.getConnecion();

            if(connection!=null){


                PreparedStatement prep=connection.prepareStatement(GET_NRESOURCES);
                prep.setInt(1,stock_id);
                ResultSet res=prep.executeQuery();

                connection.close();

                while (res.next()){
                    req.add(new SRequest("Out",res.getString(1),res.getString(2),res.getInt(3),res.getString(4),
                            res.getDate(5)));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return req;
    }

    private ArrayList<SRequest> requests;
    public ArrayList<SRequest> getRequests(){
        requests=collectRequests();
        return requests;
    }

    private ArrayList<SRequest> collectRequests(){
        ArrayList<SRequest> req=new ArrayList<>();
        try {
            Connection connection=DataConnection.getConnecion();

            if(connection!=null){


                PreparedStatement prep=connection.prepareStatement(GET_REQUESTS);
                ResultSet res=prep.executeQuery();

                connection.close();

                while (res.next()){
                    req.add(new SRequest(res.getString(1),res.getString(2),res.getString(3),res.getInt(4),
                            res.getString(5),res.getDate(6),res.getBoolean(7),res.getInt(8)));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return req;
    }



}
