/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Evenement {
    private int id;
    private String name;
   // private Date date_evemt;
    private String start_hour;
    private String finish_hour;
    private String capacity;
    private String description;
    private String image;
    private String location;
    private Artiste artiste;
    private Date date_evemt;
    private Double prix;
    private String username;

    public Evenement() {
    }

    public Evenement(int id, String name, String start_hour, String finish_hour, String capacity, String description, String image, String location, Artiste artiste, Date date_evemt, Double prix, String username) {
        this.id = id;
        this.name = name;
        this.start_hour = start_hour;
        this.finish_hour = finish_hour;
        this.capacity = capacity;
        this.description = description;
        this.image = image;
        this.location = location;
        this.artiste = artiste;
        this.date_evemt = date_evemt;
        this.prix = prix;
        this.username = username;
    }

    public Evenement(String name, String capacity, String location, Double prix) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.prix = prix;
    }
    
 public Evenement(String name,double p, String loc,String cap)
 {this.name=name;
 this.capacity = cap;
 this.location=loc;
 this.prix=p;
 
 
 }
    public Evenement(String name, String start_hour, String finish_hour, String capacity, String description, String image, String location, Artiste artiste, Date date_evemt, Double prix, String username) {
        this.name = name;
        this.start_hour = start_hour;
        this.finish_hour = finish_hour;
        this.capacity = capacity;
        this.description = description;
        this.image = image;
        this.location = location;
        this.artiste = artiste;
        this.date_evemt = date_evemt;
        this.prix = prix;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public String getFinish_hour() {
        return finish_hour;
    }

    public void setFinish_hour(String finish_hour) {
        this.finish_hour = finish_hour;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public Date getDate_evemt() {
        return date_evemt;
    }

    public void setDate_evemt(Date date_evemt) {
        this.date_evemt = date_evemt;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", name=" + name + ", start_hour=" + start_hour + ", capacity=" + capacity + ", description=" + description + ", image=" + image + ", location=" + location + ", artiste=" + artiste + ", date_evemt=" + date_evemt + ", prix=" + prix + ", username=" + username + '}';
    }

   

    
   

    

    
  
    
    
    
    
}
