package main.java.com.asolutworld.Database;

import main.java.com.asolutworld.Objects.Volunteer;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

@ManagedBean(name = "databaseController",eager = true)
@ApplicationScoped
public class DatabaseController {
    private static final String CREATE_AVAILABLE_RESOURCES_TABLE="DROP TABLE IF EXISTS availble_resources;\n" +
            "CREATE TABLE availble_resources\n" +
            "AS (SELECT u_id, string_agg(resource,',') AS resources FROM hresources WHERE available=TRUE \n" +
            "GROUP BY u_id);";
    private static final String GET_VOLUNTEERS="SELECT volunteer.u_id,volunteer.sname,volunteer.fname,volunteer.location\n" +
            "  ,availble_resources.string_agg ,volunteer.phone FROM availble_resources,volunteer \n" +
            "WHERE availble_resources.u_id IN (volunteer.u_id);";

    ArrayList<Volunteer> volunteers;
    public ArrayList<Volunteer> getVolunteers(){
        collectVolunteerData();
        return volunteers;
    }

    private void collectVolunteerData(){

    }
}
