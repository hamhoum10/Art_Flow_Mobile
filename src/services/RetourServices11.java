/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.livraison;
import entities.retour;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utilities.Statics;

/**
 *
 * @author omriy
 */
public class RetourServices11 {
    
    //Singleton
    public static RetourServices11 getInstance() {
        if (instance == null) {
            instance = new RetourServices11();
        }

        return instance;
    }
 ConnectionRequest req;
    static RetourServices11 instance = null;   
     //util
    boolean resultOK = false;
    List<retour> tasks = new ArrayList<>();
//Constructor
    public RetourServices11() {
        req = new ConnectionRequest();
    }

 //ACTIONS
    //Add
    public boolean addRetour(retour a) {

        //1
        String addURL = Statics.BASE_URL + "jyson/addret/new";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("nom", a.getName());
        req.addArgument("art", a.getArtiste()+ "");
         req.addArgument("adress", a.getAddres());
        req.addArgument("dat", a.getDate_sort()+ "");
         req.addArgument("usr", a.getUser_name()+ "");
        req.addArgument("idcom", a.getId_commende()+ "");  
        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
    //FETCH
    public List<retour> fetchRetour() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "jyson/retour";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
 //Parse
    public List<retour> parseTasks(String jsonText) {

        //var
        tasks = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
                retour a = new retour();
               
                 a.setId(((Double ) item.get("id")).intValue());
                a.setAddres((String)item.get("addres"));
                a.setArtiste((String)item.get("artiste"));
//                 a.setId_commende(((Double )item.get("idcom")).intValue());
             a.setId_commende(14);
                a.setDate_retour((String)item.get("date"));
                a.setName((String)item.get("name_produit"));
                a.setUser_name((String)item.get("user_name"));
                
            
        
                

                
                tasks.add(a);
            }
        
        } catch (IOException ex) {
        }
       
        
        //End
        return tasks;
    }
  public boolean deleteretour(retour  a) {

        //1
        String addURL = Statics.BASE_URL + "jyson/deleteret/" + a.getId();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
   public boolean modifyretour(retour a) {

        //1
        String addURL = Statics.BASE_URL + "jyson/updatret/"+a.getId();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
         
        req.addArgument("idcom",String.valueOf(a.getId_commende()));
        req.addArgument("nom", a.getName()+ "");
         req.addArgument("art", a.getArtiste());
        req.addArgument("adress", a.getAddres()+ "");
         req.addArgument("dat", a.getDate_retour().toString());
        req.addArgument("usr", a.getUser_name()+ "");
       
        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
    public boolean SMSlivraison() {

        //1
        String addURL = Statics.BASE_URL + "sms3" ;

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
    public boolean ShiftToLiv(retour a) {

        //1
        String addURL = Statics.BASE_URL + "retour/shift-row/"+a.getId() ;

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
}
