/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Article;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Categorie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utilities.Statics;

/**
 *
 * @author MediaStudio
 */
public class ArticleServices {
    
    
    ConnectionRequest req;
    static ArticleServices instance = null;

    //util
    boolean resultOK = false;
    List<Article> tasks = new ArrayList<>();

    //Constructor
    private ArticleServices() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static ArticleServices getInstance() {
        if (instance == null) {
            instance = new ArticleServices();
        }

        return instance;
    }

    //ACTIONS
    //Add
    public boolean addArticle(Article a) {

        //1
        String addURL = Statics.BASE_URL + "addArticle";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("nom_article", a.getNom_article());
        req.addArgument("price", a.getPrice()+ "");
         req.addArgument("type", a.getType());
        req.addArgument("description", a.getDescription() + "");
         req.addArgument("image", a.getImage());
        req.addArgument("quantity", a.getQuantity() + "");
        req.addArgument("id_categorie", a.getCategorie().getName_categorie() + "");  
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

    public boolean modifyArticle(Article a) {

        //1
        String addURL = Statics.BASE_URL + "modifyArticle/"+a.getId_article();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("nom_article", a.getNom_article());
        req.addArgument("price", a.getPrice()+ "");
         req.addArgument("type", a.getType());
        req.addArgument("description", a.getDescription() + "");
         req.addArgument("image", a.getImage());
        req.addArgument("quantity", a.getQuantity() + "");
        req.addArgument("id_categorie", a.getCategorie().getName_categorie() + "");  
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
    public List<Article> fetchArticls() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "index";
        
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
    public List<Article> parseTasks(String jsonText) {

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
                
                Article a = new Article();
                Categorie c =new Categorie();
                 a.setId_article(((Double) item.get("idArticle")).intValue());
                a.setNom_article((String)item.get("nomArticle"));
                a.setPrice((double) item.get("price"));
                a.setType((String)item.get("type"));
                a.setDescription((String)item.get("description"));
                a.setImage((String)item.get("image"));
                a.setQuantity(((Double) item.get("quantity")).intValue());
                
             
                

                
                tasks.add(a);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return tasks;
    }

        public boolean deleteArticle(Article a) {

        //1
        String addURL = Statics.BASE_URL + "deletearticle/" + a.getId_article();

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
