/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import entities.Panier;
import java.util.ArrayList;
import java.util.List;
import utilities.Statics;


/**
 *
 * @author medya
 */
public class PanierService {
    //var
    public ConnectionRequest req;
    public static PanierService instance = null;

    //util
    boolean resultOK = false;
    List<Panier> tasks = new ArrayList<>();

    //Constructor
    public PanierService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static PanierService getInstance() {
        if (instance == null) {
            instance = new PanierService();
        }

        return instance;
    }
    

    //ACTIONS
    //Add
    public void addPanier(int id_client) {
        
        //1
        String addURL = Statics.BASE_URL+"/mobile/new?id_client="+id_client;

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
//        req.addArgument("id-client", String.valueOf(id_client)); //t.getId_client()
        

        //5
         req.addResponseListener((e)->{
            
            String str = new String (req.getResponseData());//Response JSON THAT WE SAW IN THE NAVIGATOR 
            System.out.println("data=="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
  
    
}
