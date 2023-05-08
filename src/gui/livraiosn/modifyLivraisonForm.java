/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.livraiosn;

import gui.livraiosn.AddLivraisonForm;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entities.livraison;
import services.LivraisonServices;

/**
 *
 * @author omriy
 */
public class modifyLivraisonForm   extends Form{
    
    
     public modifyLivraisonForm(livraison a,Resources res) {
        LivraisonServices as = LivraisonServices.getInstance();
        setUIID("LoginForm");
        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("UPDATE livraison");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ShowLivraisonForm(res).show();
        });

        //Widgets
        
        TextField nomTF = new TextField(a.getName()+"", "nom ", 20, TextField.ANY);
        TextField artTF = new TextField(a.getArtiste()+"", "art", 20, TextField.ANY);
        TextField adressTF = new TextField(a.getAddres()+"", "adress", 20, TextField.ANY);
        TextField datTF = new TextField(a.getDate_sort()+"", "dat", 20, TextField.ANY);        
        TextField idcomTF = new TextField(a.getId_commende()+"", "idcom", 20, TextField.ANY);
        TextField usrTF = new TextField(a.getUser_name()+"", "usr", 20, TextField.ANY);
       
       
       
   
        
        
        
        
        Button submitBtn = new Button("Submit");
                submitBtn.setUIID("LoginButton");

        //actions
        submitBtn.addActionListener((evt) -> {
            if (as.modifyLivraison(new livraison(a.getId(),nomTF.getText(), artTF.getText(),adressTF.getText(), "2000-10-10",Integer.valueOf(idcomTF.getText()),usrTF.getText()))) {
                Dialog.show("Success", "Article updated successfully", "Got it", null);
                new AddLivraisonForm(res).showBack();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
            this.addAll(nomTF, artTF, adressTF, datTF, usrTF, idcomTF, submitBtn);
    }
    
}
