/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.livraiosn;

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
import java.util.Date;
import services.LivraisonServices;

/**
 *
 * @author omriy
 */
public class AddLivraisonForm extends Form {
    public AddLivraisonForm(Resources res) {
        LivraisonServices as = LivraisonServices.getInstance();
        setUIID("LoginForm");
        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Livraison");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ShowLivraisonForm(res).show();
        });

        //Widgets
        TextField nomTF = new TextField("", "Nom", 20, TextField.ANY);
        TextField artTF = new TextField("", "Artiste", 20, TextField.ANY);
        TextField adressTF = new TextField("", "adress", 20, TextField.ANY);
        TextField datTF = new TextField("", "dat", 20, TextField.ANY);
        TextField usrTF = new TextField("", "user", 20, TextField.ANY);
        TextField idcomTF = new TextField("", "ID commende", 20, TextField.ANY);  
        Button submitBtn = new Button("Submit");
                submitBtn.setUIID("LoginButton");
                

        //actions
        submitBtn.addActionListener((evt) -> {
            if (as.addLivraison(new livraison (nomTF.getText(),usrTF.getText(),adressTF.getText(),artTF.getText(),Integer.valueOf(idcomTF.getText()),"2000-10-10"))) {
                Dialog.show("Success", "Livraison Inserted successfully", "Got it", null);
                new ShowLivraisonForm(res).showBack();
                as.SMSlivraison();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
            this.addAll(nomTF, artTF, adressTF, datTF, usrTF, idcomTF,submitBtn);
    }
}
