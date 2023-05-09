/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MediaPlayer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entities.Article;
import entities.Categorie;
import java.io.IOException;
import java.util.List;
import services.ArticleServices;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author achref
 */
public class ShowArticlesForm  extends Form {
    
    
     ArticleServices as = ArticleServices.getInstance();

  public ShowArticlesForm(Resources res) {

        //custom
        this.getToolbar().addCommandToRightBar("Add", null, (e) -> new AddArticleForm(res).show());
        this.setLayout(BoxLayout.y());
        this.setTitle("All Articles");
        List<Article> Articles = as.fetchArticls();
        for (int i = 0; i < Articles.size(); i++) {
            addItem(Articles.get(i), res);
        }
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new WalkthruForm(res).showBack();
        });
    }

    private void addItem(Article a, Resources res) {
        ImageViewer img = null;
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        EncodedImage enc = null;
        Image imgs;
        ImageViewer imgv;
        String url = "http://localhost/img/"+a.getImage();
       
       
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {

        }
       
        imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);

        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label title = new Label(a.getNom_article());
        TextArea description = description = new TextArea(a.getDescription());
        try{
             description = new TextArea(a.getDescription().substring(0,20)+"...");
        }catch(Exception e){};
        try{
             description = new TextArea(a.getDescription().substring(0,30)+"...");
        }catch(Exception e){};
        description.setEditable(false);
        description.setFocusable(false);
        description.setUIID("Label");
       
        Button affiche = new Button("Afficher");
        affiche.addActionListener(e->{
            //new ShowTutorielForm(a,res).show();
        });
       
         Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       
        Button modif = new Button();
        FontImage.setMaterialIcon(modif, FontImage.MATERIAL_MODE_EDIT);
        modif.addActionListener(e->{
            new modifyArticleForm(a,res).show();
        });
        Button delete = new Button();
        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
        delete.addActionListener(e->{
            if (as.deleteArticle(a)) {
                Dialog.show("Success", "Article deleted successfully", "Got it", null);
                new ShowArticlesForm(res).show();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });
       
        img = new ImageViewer(imgs);
       
        c3.add(modif);
        c3.add(delete);
        c3.getAllStyles().setMarginRight(10);;
        c2.add(title);
        c2.add(description);
        c2.add(affiche);
        c2.add(c3);
        c1.add(img);
        c1.add(c2);
        this.add(c1);

        this.refreshTheme();
    }
}