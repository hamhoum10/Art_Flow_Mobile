/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.retour;

import gui.Stock.*;
import gui.livraiosn.*;
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
import entities.retour;
import entities.stock;
import java.util.Date;
import services.LivraisonServices;
import services.RetourServices11;
import services.StockServices1;

/**
 *
 * @author omriy
 */
public class AddRetourForm extends Form {
    public AddRetourForm(Resources res) {
        RetourServices11 as = RetourServices11.getInstance();
        setUIID("LoginForm");
        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Retour");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ShowRetourForm(res).show();
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
            if (as.addRetour(new retour (nomTF.getText(),artTF.getText(),adressTF.getText(),"2000-10-10",Integer.valueOf(idcomTF.getText()),usrTF.getText()))) {
                Dialog.show("Success", "retour Inserted successfully", "Got it", null);
                new ShowRetourForm(res).showBack();
                as.SMSlivraison();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
            this.addAll(nomTF, artTF, adressTF, datTF, usrTF, idcomTF,submitBtn);
    }
}
