/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.livraiosn;

import gui.livraiosn.modifyLivraisonForm;
import gui.livraiosn.AddLivraisonForm;
import com.codename1.components.ImageViewer;
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
import entities.guiStock.ShowStockForm;
import entities.livraison;
import gui.WalkthruForm;
import java.io.IOException;
import java.util.List;
import services.LivraisonServices;

/**
 *
 * @author omriy
 */
public class ShowLivraisonForm extends Form {
LivraisonServices as = LivraisonServices.getInstance();
   public ShowLivraisonForm(Resources res) {

        //custom
        this.getToolbar().addCommandToRightBar("Add", null, (e) -> new AddLivraisonForm(res).show());
         this.getToolbar().addCommandToLeftBar("Stock", null, (e) -> new ShowStockForm(res).show());
        this.setLayout(BoxLayout.y());
        this.setTitle("All livraison");
        List<livraison> livraison = as.fetchLivraison();
        for (int i = 0; i < livraison.size(); i++) {
            addItem(livraison.get(i), res);
        }
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new WalkthruForm(res).showBack();
        });
    }
   private void addItem(livraison a, Resources res) {
        
       
        
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
       
        Button affiche = new Button("Afficher");
        affiche.addActionListener(e->{
            //new ShowTutorielForm(a,res).show();
        });
       
         Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       
        Button modif = new Button();
        FontImage.setMaterialIcon(modif, FontImage.MATERIAL_MODE_EDIT);
        modif.addActionListener(e->{
            new modifyLivraisonForm(a,res).show();
        });
        Button delete = new Button();
        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
        delete.addActionListener(e->{
            if (as.deletelivraison(a)) {
                Dialog.show("Success", "Article deleted successfully", "Got it", null);
                new ShowLivraisonForm(res).show();
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
