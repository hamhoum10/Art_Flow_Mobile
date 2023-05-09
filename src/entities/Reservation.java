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
public class Reservation {

    private int id;
    private Evenement event;
    private Client client;
    private int nb_place;
    //private double price;
    private String dateres;

    public Reservation() {
    }

    public Reservation(int id, Evenement event, Client client, int nb_place, String dateres) {
        this.id = id;
        this.event = event;
        this.client = client;
        this.nb_place = nb_place;
        this.dateres = dateres;
    }

    public Reservation(Evenement event, Client client, int nb_place, String dateres) {
        this.event = event;
        this.client = client;
        this.nb_place = nb_place;
        this.dateres = dateres;
    }

    public Reservation( String dateres,int nb_place) {
        this.nb_place = nb_place;
        this.dateres = dateres;
    }

    
    

   

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evenement getEvent() {
        return event;
    }

    public void setEvent(Evenement event) {
        this.event = event;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public String getDateres() {
        return dateres;
    }

    public void setDateres(String dateres) {
        this.dateres = dateres;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", event=" + event + ", client=" + client + ", nb_place=" + nb_place + ", dateres=" + dateres + '}';
    }

   
    
    
}
