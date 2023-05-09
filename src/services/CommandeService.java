/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import entities.Commande;
import java.util.ArrayList;
import java.util.List;
import utilities.Statics;

/**
 *
 * @author medya
 */
public class CommandeService {
    //var
    public ConnectionRequest req;
    public static CommandeService instance = null;

    //util
    boolean resultOK = false;
    List<Commande> cd = new ArrayList<>();

    //Constructor
    public CommandeService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static CommandeService getInstance() {
        if (instance == null) {
            instance = new CommandeService();
        }

        return instance;
    }
    

    //ACTIONS
    //Add
    public void addCommande(Commande c,int id) {
        
        //1
        String addURL = Statics.BASE_URL+"/newC?id_client="+id+"&firstname="+c.getNomClientCommande()+"&lastname="+c.getPrénomClientCommande()+"&codepostal="+c.getCodepostal()+"&address="+c.getAdresse()+"&number="+c.getNumeroPhoneclient()+"&email="+c.getEmail();
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
