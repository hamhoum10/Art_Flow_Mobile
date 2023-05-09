/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import services.*;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utilities.Statics;


/**
 *
 * @author medya
 */
public class LignePanierService {
    
    public static boolean resultOK = true;
    private ConnectionRequest req;
    private ArrayList<Ligne_panier> Lp;
    private String json;
     public static LignePanierService instance = null;
//        List<Client> encheress = new ArrayList<>();
    
   
    
     public static LignePanierService getInstance(){
        if (instance == null)
            instance = new LignePanierService();
        return instance;
    }
    
    
    
      public LignePanierService(){
        req = new ConnectionRequest(); 
    
}
      public void ajoutLignePanier(int id_article,int id_client,int quantity){
    
    String url = Statics.BASE_URL + "/newLP?id-client="+id_client+"&id-article="+id_article+"&quantity="+quantity;
    req.setUrl(url);
  
    req.setPost(false);
    req.addResponseListener((t) ->{
        String str = new String(req.getResponseData());//reponse json 
        System.out.println("data =="+str);
    });
    NetworkManager.getInstance().addToQueueAndWait(req);//execution du request 
}
      
      
      
      public ArrayList<Ligne_panier>affichageAllLp() {
        ArrayList<Ligne_panier> result = new ArrayList<>();
        Ligne_panier LP = new Ligne_panier();
        String url = Statics.BASE_URL+"/showLP";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Ligne_panier lp = new Ligne_panier();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        float prixUnitaire = Float.parseFloat(obj.get("prixUnitaire").toString());
                        float quantity = Float.parseFloat(obj.get("quantity").toString());
                        String nomArticle = obj.get("nomArticle").toString();
                        String description = obj.get("description").toString();
                        String nomArtiste = obj.get("nomArtiste").toString();
                        String prenomArtiste = obj.get("prenomArtiste").toString();
//                        Article idArticle =(Article) obj.get("idArticle");
//                        int id_article = Integer.valueOf(obj.get("id_article").toString());
//                        float idPanier = Float.parseFloat(obj.get("idPanier").toString());
                        
//                        lp.setId_article((int)idArticle);
//                        lp.setId_panier((int)idPanier);
                        lp.setId((int) id);
//                        lp.setId_article(4);
                        lp.setDescription(description);
                        lp.setNom_article(nomArticle);
                        lp.setQuantity((int) quantity);
                        lp.setPrix_unitaire((double)prixUnitaire);
                        
                     
                        
                        //insert data into ArrayList result
                        result.add(lp);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
    }
      
      public ArrayList<Ligne_panier>affichageUserLp() {
        ArrayList<Ligne_panier> result = new ArrayList<>();
        Ligne_panier LP = new Ligne_panier();
        String url = Statics.BASE_URL+"/showLP/"+3;   //hard coding for now
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Ligne_panier lp = new Ligne_panier();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        float prixUnitaire = Float.parseFloat(obj.get("prixUnitaire").toString());
                        float quantity = Float.parseFloat(obj.get("quantity").toString());
                        String nomArticle = obj.get("nomArticle").toString();
                        String description = obj.get("description").toString();
//                        String nomArtiste = obj.get("nomArtiste").toString();
//                        String prenomArtiste = obj.get("prenomArtiste").toString();
//                        Article idArticle =(Article) obj.get("idArticle");
//                        int id_article = Integer.valueOf(obj.get("id_article").toString());
//                        float idPanier = Float.parseFloat(obj.get("idPanier").toString());
                        
//                        lp.setId_article((int)idArticle);
//                        lp.setId_panier((int)idPanier);
                        lp.setId((int) id);
//                        lp.setId_article(4);
                        lp.setDescription(description);
                        lp.setNom_article(nomArticle);
                        lp.setQuantity((int) quantity);
                        lp.setPrix_unitaire((double)prixUnitaire);
                        
                     
                        
                        //insert data into ArrayList result
                        result.add(lp);
                        
             
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
    }
      public boolean addQuantity(int idlp) {

        //1
        String addURL = Statics.BASE_URL + "/"+idlp+"/plusLP";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        
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
      public boolean minusQuantity(int idlp) {

        //1
        String addURL = Statics.BASE_URL + "/"+idlp+"/minusLP";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        
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
      public boolean deleteQuantity(int idlp) {

        //1
        String addURL = Statics.BASE_URL + "/"+idlp+"/deleteLP";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        
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