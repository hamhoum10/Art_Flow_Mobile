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
import entities.Article;
//import entities.Evenement;
import entities.Reservation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utilities.Statics;

/**
 *
 * @author Lenovo
 */
public class ReservationService {
    private ConnectionRequest req;
     static ReservationService instance = null;
     
      boolean resultOK = false;
    List<Reservation> tasks = new ArrayList<>();

    
//    public static ReservationService getInstance(){
//        if (instance ==null)
//            instance = new ReservationService();
//        return instance ;
//        
//    }
      public ReservationService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }
     public boolean AddReservation(Reservation a,int id){
        String url = Statics.BASE_URL+"/reservation/addRes?nbplace="+a.getNb_place()+"&&dateres="+a.getDateres()+"&id="+id;             
        //String url = Statics.Base_URL+"/category/newcategoryjson?name_category="+category.getName_category();
          req.setUrl(url);

        //3
        req.setPost(false);

        //4
       
        req.addArgument("art", a.getDateres()+ "");
         ///req.addArgument("nbplace", a.getNb_place().toS);
           req.addArgument("idcom", a.getNb_place()+ ""); 
         
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
     
      public boolean deleteReservation(Reservation a) {

        //1
        String addURL = Statics.BASE_URL + "/reservation/ "+ a.getId()+"/ResDeleted" ;      

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
      
      
       public List<Reservation> affichageReservation() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/reservation/allres";   
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = Resrvationslist(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
      
      public List<Reservation> Resrvationslist(String jsonText) {

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
                
                Reservation a = new Reservation();
                System.out.println(item);
                
                 a.setId(((Double) item.get("idRes")).intValue());
                 a.setNb_place(((Double)item.get("nbPlace")).intValue());
//               a.setId((int)item.get("id"));
                
                a.setDateres((String)item.get("dateres"));
                
                
             
                

                
                tasks.add(a);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return tasks;
    }
     
}
