package com.crowderia.udayasri.androidhivetest.model;

/**
 * Customer Model Class
 * Created by UdayaSri on 12/21/15.
 */

public class Customer {

    private String first_name, last_name,email,thumbNail;


    public Customer(String first_name, String last_name,String email,String thumbNail) {

        super();
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.thumbNail = thumbNail;
    }

    public Customer() {

    }

    // Getters & setters for the customer object
    public String getFirst_name(){
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getThumbNail(){
        return thumbNail;
    }

    public void setThumbNail(String thumbNail){
        this.thumbNail = thumbNail;
    }
}
