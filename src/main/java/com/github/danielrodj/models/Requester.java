package com.github.danielrodj.models;
/**
 *
 * @author Angel Daniel Rodríguez Juárez
 */
public class Requester {
    
    private int requesterId;
    private String firstName;
    private String lastName;
    private String position;
    private int area;
    
    public Requester (){}
    
    public Requester (int requesterId, String firstName, String lastName, String position, int area){
        this.requesterId = requesterId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.area = area;
    }
    
    public Integer getRequesterId(){
        return requesterId;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getPosition(){
        return position;
    }
    
    public int getArea(){
        return area;
    }
    
}
