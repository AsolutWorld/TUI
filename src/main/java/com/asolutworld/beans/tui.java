package main.java.com.asolutworld.beans;

import main.java.com.asolutworld.Authorization.beans.SessionBean;
import main.java.com.asolutworld.Constants.Strings;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class tui implements Serializable {

    public String user(){
        return "user";
    }
}
