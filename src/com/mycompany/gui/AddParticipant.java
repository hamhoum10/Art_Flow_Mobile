/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.services.EnchereServices;
import com.mycompany.entities.Participant;
import static java.lang.Double.parseDouble;
import java.util.Date;

/**
 *
 * @author howell
 */
public class AddParticipant  extends Form {
    
    Form current;
   TextField montantField;
 TextField clientIdField;
   TextField enchereIdField;

    
    public AddParticipant(Resources res) {
        super("add participant", BoxLayout.y());// change to BoxLayout.y() to make the form vertical

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
    
     
        
    
        TextField montant = new TextField("", "entrer montant");
        montant.setUIID("TextFieldBlack");
        addStringValue("montant", montant);

        TextField id = new TextField("", "entrer id");
        id.setUIID("TextFieldBlack");
        addStringValue("id", id);

      

        TextField ide = new TextField("", "entrer ide");
        ide.setUIID("TextFieldBlack");
        addStringValue("ide", ide);
        
         Button btnAjouter=new Button("Ajouter");
        addStringValue("",btnAjouter);

         btnAjouter.addActionListener(e -> {
       // Participant p=new Participant(client, Enchere, parseDouble(String.valueOf(prixdepart.getText())))
    
         
       
       
       
       
         });
        
        
        
        
        
        
//        submitButton.addActionListener(e -> {
//            try {
//                int montant = Integer.parseInt(montantField.getText());
//                int clientId = Integer.parseInt(clientIdField.getText());
//                int enchereId = Integer.parseInt(enchereIdField.getText());
//                Participant p = new Participant(montant, new Client(clientId), new Enchere(enchereId));
//                p.ajoutParticipant(p);
//                Dialog.show("Success", "Participant added successfully", "OK", null);
//            } catch (NumberFormatException ex) {
//                Dialog.show("Error", "Invalid input", "OK", null);
//            }
//        });
        
   


}

   private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));

    }
   
   
   
   
   
   
}
