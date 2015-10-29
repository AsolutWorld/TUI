package main.java.com.asolutworld;

import main.java.com.asolutworld.Authorization.beans.SessionBean;
import main.java.com.asolutworld.Constants.Strings;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.HttpSession;

@ManagedBean(name="tui",eager=true)
@SessionScoped
public class tui implements Serializable {
    private String name;
    private String pass;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String login()
    {
        return "database";
    }
}
