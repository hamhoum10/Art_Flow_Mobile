/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Client;
import com.mycompany.entities.Enchere;
import com.mycompany.entities.Participant;
import com.mycompany.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author howell
 */
public class EnchereServices {

    public static boolean resultOK = true;
    private ConnectionRequest req;
    // private ArrayList<Enchere> encheres;
    private String json;
    public static EnchereServices instance = null;
    //  List<Enchere> encheress = new ArrayList<>();

    public static EnchereServices getInstance() {
        if (instance == null) {
            instance = new EnchereServices();
        }
        return instance;
    }

    public EnchereServices() {
        req = new ConnectionRequest();

    }

    //AJOUT 
    public void ajoutEnchere(Enchere e) {

        String url = Statics.BASE_URL + "/addenchere/new?titre=" + e.getTitre() + "&description=" + e.getDescription() + "&prixdepart=" + e.getPrixdepart() + "&datelimite=" + e.getDate_limite() + "&image=" + e.getImg();
        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener((t) -> {
            String str = new String(req.getResponseData());//reponse json 
            System.out.println("data ==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution du request 
    }

    //UPDATEEE
    public boolean modifierEnchere(Enchere e) {
        String url = Statics.BASE_URL + "updateenchere?ide=" + e.getIde() + "titre=" + e.getTitre() + "&description=" + e.getDescription() + "&prixdepart=" + e.getPrixdepart() + "&datelimite=" + e.getDate_limite() + "&image=" + e.getImg();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOK;

    }

    //Delete 
    public boolean deleteEnchere(int ide) {
        String url = Statics.BASE_URL + "deleteenchere?ide=" + ide;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }


    public ArrayList<Enchere> getAllEnchere() {
        ArrayList<Enchere> encheres = new ArrayList<>();
        String url = Statics.BASE_URL + "/affichage";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
                    System.out.println("Response JSON: " + list); // Debug output
// Debug output
                    for (Map<String, Object> obj : list) {
                        Enchere e = new Enchere();

                     
                    e.setTitre(obj.get("titre").toString());
                        e.setDescription(obj.get("description").toString());
                        e.setPrixdepart(Float.valueOf(obj.get("prixdepart").toString()));

// Parse dateajout
// Parse dateajout
                        String dateLimiteStr = obj.get("dateLimite").toString();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        Date dateLimite = dateFormat.parse(dateLimiteStr);
                        e.setDate_limite(dateLimite);

                        Object imgObj = obj.get("image");
                        if (imgObj != null) {
                            e.setImg(imgObj.toString());
                        }
                        System.out.println("Enchere object: " + e); // Debug output
                        encheres.add(e);

                    }
                    // Move the return statement inside the actionPerformed method
                } catch (ParseException ex) {
                    System.out.println("hhh");
                } catch (IOException ex) {
                    // Logger.getLogger(EnchereServices.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

      NetworkManager.getInstance().addToQueueAndWait(req);
        // Return an empty list here (will be ignored)
        return encheres;
    }

    
    
    
    
    
     //AJOUT 
    public boolean ajoutParticipant(Participant p) {

        String url = Statics.BASE_URL + "/addparticipant/new";
        req.setUrl(url);

        req.setPost(false);
        
        
          req.addArgument("montant",p.getMontant()+ "");
            req.addArgument("id",p.getClient().getId()+ "");
                req.addArgument("ide",p.getEnchere().getIde()+ "");
               
         
          //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        //   NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
        
        
        
        
      
    
    
    
    
    public Client DetailClient( int id , Client client) {
        
        String url = Statics.BASE_URL+"/clientById?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                client.setFirstname(obj.get("firstname").toString());
                client.setLastname(obj.get("lastname").toString());
                client.setAddress(obj.get("address").toString());
                client.setPhone(obj.get("phone").toString());
                client.setEmail(obj.get("email").toString());
                client.setUsername(obj.get("username").toString());
                client.setPwd(obj.get("password").toString());

                
            }catch(IOException ex) {
                System.out.println("error related to sql 🙁 "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return client;
    }
    
    
    
    
    
    
    
}

