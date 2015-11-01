package main.java.com.asolutworld.Objects;

import java.io.Serializable;
import java.util.Date;

public class SRequest implements Serializable,Comparable {
    private String in_out="Out";
    private String name;
    private String resource;
    private int count;
    private String type;
    private Date date;
    private boolean active=true;
    private String address="";

    public SRequest(String in_out,String name,String resource,int count,String type, Date date){
        this.in_out=in_out;
        this.name=name;
        this.resource=resource;
        this.count=count;
        this.type=type;
        this.date=date;

    }

    public SRequest(String name,String address,String resource,int count,String type, Date date, boolean active){
        this.address=address;
        this.name=name;
        this.resource=resource;
        this.count=count;
        this.type=type;
        this.date=date;
        this.active=active;

    }

    public boolean isActive() {
        return active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    @Override
    public int compareTo(Object o) {
        return this.date.compareTo(((SRequest) o).date);
    }

    public String getIn_out() {
        return in_out;
    }

    public void setIn_out(String in_out) {
        this.in_out = in_out;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
