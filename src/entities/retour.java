/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author omriy
 */
public class retour {
     private int id;
    private Date date_retour;

    public Date getDate_sort() {
        return date_retour;
    }

    public void setDate_entr(Date date_sort) {
        this.date_retour = date_sort;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    @Override
    public String toString() {
        return "Livraison{" +
//                "id=" + id +
                "user_name='" + user_name + '\'' + 
                ",date_entr=" + date_retour + 
                ", name='" + name + '\'' + 
                ", addres='" + addres + '\'' + 
                ", artiste='" + artiste + '\'' +

                '}';
    }

    private String name,user_name,addres;

    private String artiste;
    private int id_commende;
    //constructeure
    public retour(){}

    public retour(int id, String name, String artiste, String addres , Date date_retour, int id_commende, String user_name) {
        this.id = id;
        this.name = name;
        this.artiste = artiste;
        this.id_commende = id_commende;
        this.addres=addres;
        this.date_retour=date_retour;
        this.user_name=user_name;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public int getId_commende() {
        return id_commende;
    }

    public void setId_commende(int id_commende) {
        this.id_commende = id_commende;
    }
    //affichage du stock
}
