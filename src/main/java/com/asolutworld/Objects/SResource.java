package main.java.com.asolutworld.Objects;

public class SResource {
    private String resource;
    private int count;
    private String type;

    public SResource(String resource,int count,String type){
        this.resource=resource;
        this.count=count;
        this.type=type;
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
}
