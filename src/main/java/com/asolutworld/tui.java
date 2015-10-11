package main.java.com.asolutworld;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="tui",eager=true)
@SessionScoped
public class tui {

    public String string;
    public tui(){
        System.out.println("App started");
        string="adflgjrofdigl";
    }


    public String getGessage(){
        return "hello yordle";
    }
}
