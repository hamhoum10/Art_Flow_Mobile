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

    List<Enchere> encheress = new ArrayList<>();

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
        req = new ConnectionRequest();
        ArrayList<Enchere> encheres = new ArrayList<>();
        String url = Statics.BASE_URL + "/affichage";
        req.setUrl(url);
        req.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser j = new JSONParser();
                Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
                System.out.println("Response JSON: " + list); // Debug output
// Debug output
                System.out.println("Response JSON: " + new String(req.getResponseData()));

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
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        // Return an empty list here (will be ignored)
        return encheres;
    }

    //AJOUT 
    public boolean ajoutParticipant(Participant p) {
        boolean etat = false;

        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/addparticipant/new";
        req.setUrl(url);

        req.setPost(false);

        req.addArgument("montant", p.getMontant() + "");
        req.addArgument("id", p.getClient().getId() + "");

        req.addArgument("ide", p.getEnchere().getIde() + "");

        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = false;
                if (req.getResponseCode() == 200) {
                    String response = new String(req.getResponseData());
                    if (response.contains("The bid must be at least")) {
                        resultOK = false;
                    } else {
                        resultOK = true;
                    }
                }
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    public Client DetailClient(int id, Client client) {

        String url = Statics.BASE_URL + "/clientById?" + id;
        req.setUrl(url);

        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {

            JSONParser jsonp = new JSONParser();
            try {

                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                client.setFirstname(obj.get("firstname").toString());
                client.setLastname(obj.get("lastname").toString());
                client.setAddress(obj.get("address").toString());
                client.setPhone(obj.get("phone").toString());
                client.setEmail(obj.get("email").toString());
                client.setUsername(obj.get("username").toString());
                client.setPwd(obj.get("password").toString());

            } catch (IOException ex) {
                System.out.println("error related to sql 🙁 " + ex.getMessage());
            }

            System.out.println("data === " + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return client;
    }

    //FETCH
    public List<Enchere> fetchEnchere() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/affichage";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    encheress = parseEnchere(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("hhh");
                }
                req.removeResponseListener(this);
            }

        });
        //parse

        NetworkManager.getInstance().addToQueueAndWait(req);
        return encheress;
    }

    public List<Enchere> parseEnchere(String jsonText) throws ParseException {
//
//        //var
        encheress = new ArrayList<>();
//
//        //DO
//        //1
        JSONParser j = new JSONParser();

        try {

            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Enchere en = new Enchere();

                en.setIde(((Number) obj.get("ide")).intValue());
                en.setTitre((String) obj.get("titre"));
                en.setDescription((String) obj.get("description"));
                en.setPrixdepart((double) obj.get("prixdepart"));
                String dateStr = obj.get("date_limite").toString();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                Date date_limite = dateFormat.parse(dateStr);
                en.setDate_limite(date_limite);
                en.setImg((String) obj.get("image"));

                encheress.add(en);

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return encheress;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*   public Enchere getONEEnchere(int ide) {
          Enchere e = new Enchere();
        req = new ConnectionRequest();
        Enchere encheres = new();
        String url = Statics.BASE_URL + "/encheres/"+ ide ;
        req.setUrl(url);
        req.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser j = new JSONParser();
                Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
                System.out.println("Response JSON: " + list); // Debug output
// Debug output
                System.out.println("Response JSON: " + new String(req.getResponseData()));

                for (Map<String, Object> obj : list) {
                   

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
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        // Return an empty list here (will be ignored)
        return e;
    }*/

     
     
     
     
     
     
     
     
     
     
     public Enchere getONEEnchere1(int ide) {
        final Enchere en = new Enchere();
         req = new ConnectionRequest();
         JSONParser jp = new JSONParser();
         String url = Statics.BASE_URL + "/encheres/"+ ide ;
         req.setUrl(url);
        req.setPost(false);
         
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(req.getResponseData());
                try {
                Map<String, Object> obj = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                en.setIde(ide);
                en.setTitre((String) obj.get("titre"));
                en.setDescription((String) obj.get("description"));
                en.setPrixdepart((double) obj.get("prixdepart"));
                String dateStr = obj.get("dateLimite").toString();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                Date date_limite = dateFormat.parse(dateStr);
                en.setDate_limite(date_limite);
                en.setImg((String) obj.get("image"));
                    
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException ex) {
                    
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

         
         
         
         
         return en;
     }
     
     
    
     
     
     
     
     
     
     
     
}
