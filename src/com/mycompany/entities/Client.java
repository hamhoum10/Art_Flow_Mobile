/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author howell
 */
public class Client {
    private int id;
   
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
    private String email;
   private String username;
   private String pwd;

    public Client() {
    }

    public Client(int id) {
        this.id = id;
    }

    
    
    public Client(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Client(int id, String firstname, String lastname, String address, String phone, String email, String username) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
    }

    
    
    
    

    public Client(int id, String firstname, String lastname, String address, String phone, String email, String username, String pwd) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.pwd = pwd;
    }

    public Client(String firstname, String lastname, String address, String phone, String email, String username, String pwd) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.pwd = pwd;
    }

    public Client(String firstname, String lastname, String address, String phone, String email, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
    }

  

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    
    
    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", address=" + address + ", phone=" + phone + ", email=" + email + ", username=" + username + ", pwd=" + pwd + '}';
    }

    
    
    
    
}
    
   

    
    
    