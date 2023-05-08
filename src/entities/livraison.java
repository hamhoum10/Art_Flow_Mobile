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
public class livraison {
    private String name,user_name,addres;

    private String artiste;
    private int id_commende;
    private int id;
    private String date_sort;

    

    public String getDate_sort() {
        return date_sort;
    }

    public void setDate_entr(String date_sort) {
        this.date_sort = date_sort;
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
                " user_name='" + user_name + 
                ", date_entr=" + date_sort + 
                ", name='" + name + '\'' + 
                ", addres='" + addres + '\'' + 
                ", artiste='" + artiste + '\'' +

                '}';
    }


    //constructeure
    public livraison(){}

    public livraison(String name, String user_name, String addres, String artiste, int id_commende, String date_sort) {
        this.name = name;
        this.user_name = user_name;
        this.addres = addres;
        this.artiste = artiste;
        this.id_commende = id_commende;
        this.date_sort = date_sort;
    }
    

    public livraison(int id, String name, String artiste, String addres , String date_sort, int id_commende, String user_name) {
        this.id = id;
        this.name = name;
        this.artiste = artiste;
        this.id_commende = id_commende;
        this.addres=addres;
        this.date_sort=date_sort;
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
