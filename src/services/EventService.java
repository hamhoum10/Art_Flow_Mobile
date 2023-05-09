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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;

//import entities.Article;
import entities.Evenement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utilities.Statics;
import static services.EventService.instance;

/**
 *
 * @author Lenovo
 */
public class EventService {
     ConnectionRequest req;
    static EventService instance = null;

    //util
    boolean resultOK = false;
    List<Evenement> tasks = new ArrayList<>();

    //Constructor
    private EventService() {
        req = new ConnectionRequest();
    }

    //Singleton
     public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }

        return instance;
    }
//      public List<Evenement> affichageEvenement() {
//        
//        req = new ConnectionRequest();
//        
//        //1
//        String fetchURL = Statics.BASE_URL + "/evemt/allevemt";
//        
//        //2
//        req.setUrl(fetchURL);
//        
//        //3
//        req.setPost(false);
//        
//        //4
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                tasks = parseTasks(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return tasks;
//    }
//       public List<Evenement> parseTasks(String jsonText) {
//
//        //var
//        tasks = new ArrayList<>();
//        
//        //DO
//        //1
//        JSONParser jp = new JSONParser();
//        
//        try {
//            
//            //2
//            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//        
//            //3
//            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");
//        
//            //4
//            for (Map<String, Object> item : list) {
//                
//                Evenement a = new Evenement();
//               // Categorie c =new Categorie();
//                // a.setArtiste(((Double) item.get("idArticle")).intValue());
//                a.setDescription((String)item.get("description"));
//                a.setPrix((double) item.get("prix"));
//                a.setName((String)item.get("name"));
//                a.setLocation((String)item.get("location"));
//                a.setImage((String)item.get("image"));
//                a.setStart_hour((String)item.get("starthour"));
//                a.setCapacity((String)item.get("capacity"));
//                a.setUsername((String)item.get("username"));
//                  //a.setDate_evemt((Date)item.get("date"));
//                //  a.setFinish_hour((String)item.get("finish_hour"));
//                
//                
//                
//             
//                
//
//                
//                tasks.add(a);
//            }
//        
//        } catch (IOException ex) {
//        }
//        
//        
//        
//        //End
//        return tasks;
//    }
//     
     
     
//     public ArrayList<Evenement> affichageEvenement() {
//        ArrayList<Evenement> result = new ArrayList<>();
//        Evenement re = new Evenement();
//        String url = Statics.BASE_URL + "/evemt/allevemt";
//        req.setUrl(url);
//
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonp;
//                jsonp = new JSONParser();
//
//                try {
//                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//
//                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("root");
//
//                    for (Map<String, Object> obj : listOfMaps) {
//                        Evenement re = new Evenement();
//
//                        //dima id fi codename one float 5outhouha
//                      //  float id = Float.parseFloat(obj.get("id").toString());
//
//                        String name = obj.get("name").toString();
//                        
//                          String description = obj.get("description").toString();
//                       // String finish_hour = obj.get("finish_hour").toString();
//                        String location = obj.get("location").toString();
//                        String start_hour = obj.get("startHour").toString();
//                         
//
//                       
//                        String capacity = obj.get("capacity").toString();
//                        String image = obj.get("image").toString();
//                        //  Double prix = obj.get("prix").toString();
//                        //Date date_evemt  = obj.get("date_evemt").toString();
//                       String date_evemtStr = obj.get("dateEvemt").toString();
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//                      
//                        Date date_evemt = dateFormat.parse(date_evemtStr);
//                        //re.setDate_limite(dateLimite);
//                        
//                            
//
//                       
//
//                        double prix = Double.parseDouble(obj.get("prix").toString());
//                         String username = obj.get("username").toString();
//                        
//
//                      //  re.setId((int) id);
//                        re.setName(name);
//                        re.setCapacity(capacity);
//                        re.setImage(image);
//                            
//                        re.setStart_hour(start_hour);
//                       // re.getDate_evemt(dateEvemt);
//                       // re.setDate_evemt(date_evemt);
//                       re.setDate_evemt(date_evemt);
//                     //  Object dateEvemt = re.get("date_evemt");
//
//                         re.setDescription(description);
//                       //  re.setFinish_hour(finish_hour);
//                        re.setLocation(location);
//                        re.setPrix(prix);
//                        re.setUsername(username);
//
//                        //insert data into ArrayList result
//                        result.add(re);
//
//                    }
//
//                } catch (Exception ex) {
//
//                    ex.printStackTrace();
//                }
//
//            }
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//
//        return result;
//    }
      public List<Evenement> affichageEvenement() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/evemt/allevemt";
        
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
      
      public List<Evenement> parseTasks(String jsonText) {

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
                
                Evenement a = new Evenement();
               // Categorie c =new Categorie();
                 a.setId(((Double) item.get("id")).intValue());
               //a.setId((int)item.get("id"));
                a.setDescription((String)item.get("nomArticle"));
                
                a.setPrix((double) item.get("prix"));
                a.setImage((String)item.get("image"));
                a.setName((String)item.get("name"));
                a.setStart_hour((String)item.get("start_hour"));
                a.setCapacity((String)item.get("capacity"));
                 a.setLocation((String)item.get("location"));
                  a.setUsername((String)item.get("username"));
                
             
                

                
                tasks.add(a);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return tasks;
    }
      
      
      
      
      
      public boolean deleteEvent(Evenement a) {

        //1
        String addURL = Statics.BASE_URL + "/evemt/ "+ a.getId()+"/Evemtt" ;

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
       public boolean modifyEvent(Evenement a) {
          // System.out.println(a.toString());
        //1
        String addURL = Statics.BASE_URL + "/evemt/updateEvemtJSON/"+a.getId();
        req = new ConnectionRequest();
        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        
        //req.addArgument("nom_article", a.getDescription());
        req.addArgument("location", a.getLocation()+ "");
        req.addArgument("capacity", a.getCapacity()+ "");
         req.addArgument("name", a.getName());
        req.addArgument("prix", a.getPrix()+"");
        
        
      //   req.addArgument("image", a.getImage());
      //  req.addArgument("quantity", a.getDate_evemt()+ "");
      //  req.addArgument("id", a.getArtiste().getClass()+ "");  
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

//    public boolean modifyEvent(String text, float parseFloat, String text0, String text1) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
