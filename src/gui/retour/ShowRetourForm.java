/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.retour;

import gui.Stock.*;
import gui.livraiosn.*;
import gui.livraiosn.modifyLivraisonForm;
import gui.livraiosn.AddLivraisonForm;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.livraison;
import entities.retour;
import entities.stock;
import gui.WalkthruForm;
import java.io.IOException;
import java.util.List;
import services.LivraisonServices;
import services.RetourServices11;
import services.StockServices1;

/**
 *
 * @author omriy
 */
public class ShowRetourForm extends Form {
    RetourServices11 as = RetourServices11.getInstance();
    LivraisonServices ll = LivraisonServices.getInstance();
   public ShowRetourForm(Resources res) {

        //custom
        
         this.getToolbar().addCommandToLeftBar("Livraison", null, (e) -> new ShowLivraisonForm(res).show());
          this.getToolbar().addCommandToLeftBar("Stock", null, (e) -> new ShowStockForm(res).show());
          this.getToolbar().addCommandToRightBar("Add", null, (e) -> new AddRetourForm(res).show());
        this.setLayout(BoxLayout.y());
        this.setTitle("All Retour");
        List<retour> retour = as.fetchRetour();
        for (int i = 0; i < retour.size(); i++) {
            addItem(retour.get(i), res);
        }
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new WalkthruForm(res).showBack();
        });
    }
   private void addItem(retour a, Resources res) {
        
       
        
ImageViewer img = null;
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        EncodedImage enc = null;
        
       
        

        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label title = new Label(a.getName());
        Label title1 = new Label(String.valueOf(a.getId()));
        TextArea description = description = new TextArea(a.getAddres());
        try{
             description = new TextArea(a.getAddres().substring(0,20)+"...");
        }catch(Exception e){};
        try{
             description = new TextArea(a.getAddres().substring(0,30)+"...");
        }catch(Exception e){};
        description.setEditable(false);
        description.setFocusable(false);
        description.setUIID("Label");
       
        Button affiche = new Button("mvToLiv");
        affiche.addActionListener(e->{
            //new ShowTutorielForm(a,res).show();
SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
           
            System.out.println(a.getDate_sort());
             livraison l = new livraison(a.getName(), a.getUser_name(), a.getAddres(), a.getArtiste(), a.getId_commende(),"2023-8-6");
             ll.addLivraison(l);
            as.deleteretour(a);
            Dialog.show("Success", "commande moved to livraison", "Got it", null);
                new ShowRetourForm(res).show();
        });
       
         Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       
        Button modif = new Button();
        FontImage.setMaterialIcon(modif, FontImage.MATERIAL_MODE_EDIT);
        modif.addActionListener(e->{
            new modifyRetourForm(a,res).show();
        });
        Button delete = new Button();
        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
        delete.addActionListener(e->{
            if (as.deleteretour(a)) {
                Dialog.show("Success", "Retour deleted successfully", "Got it", null);
                new ShowRetourForm(res).show();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });
       
        
       
        c3.add(modif);
        c3.add(delete);
        c3.getAllStyles().setMarginRight(10);;
        c2.add(title);
        c2.add(description);
        c2.add(affiche);
        c2.add(c3);
        
        c1.add(c2);
        this.add(c1);

        this.refreshTheme();
    }
}
