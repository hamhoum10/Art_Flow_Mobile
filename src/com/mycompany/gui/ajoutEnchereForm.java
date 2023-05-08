/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Enchere;
import com.mycompany.services.EnchereServices;
import static java.lang.Double.parseDouble;
import java.util.Date;

/**
 *
 * @author howell
 */
public class ajoutEnchereForm extends Form {

    Form current;

    public ajoutEnchereForm(Resources res) {

        super("add auction", BoxLayout.y());// change to BoxLayout.y() to make the form vertical

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      
        getContentPane().setScrollVisible(false);

        TextField titre = new TextField("", "entrer Titre");
        titre.setUIID("TextFieldBlack");
        addStringValue("Titre", titre);

        TextField description = new TextField("", "entrer description");
        description.setUIID("TextFieldBlack");
        addStringValue("description", description);

        TextField prixdepart = new TextField("", "entrer prixdepart");
        prixdepart.setUIID("TextFieldBlack");
        addStringValue("prixdepart", prixdepart);

        Picker date_limite = new Picker();
        date_limite.setType(Display.PICKER_TYPE_DATE);
        date_limite.setDate(new Date());
        addStringValue("date_limite e", date_limite);

        TextField img = new TextField("", "entrer img");
        img.setUIID("TextFieldBlack");
        addStringValue("img", img);
        
         Button btnAjouter=new Button("Ajouter");
        addStringValue("",btnAjouter);

//        
//         Button btnShowevents=new Button("ShowEvents");
//        btnShowevents.addActionListener(e -> new ShowEventForm(current).show());
//
//        
        
        
   //onclick button event 
        
        btnAjouter.addActionListener((ActionEvent e)-> {
            try{

                if(titre.getText().isEmpty() || description.getText().isEmpty() || prixdepart.getText().isEmpty() || img.getText().isEmpty()){

                    Dialog.show("Veuillez verifier les données","", "Annuler" , "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();//Loading after insert data

                    final Dialog iDialog = ip.showInfiniteBlocking();


SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy"); // specify the format of the date string
Date date;

    date = dateFormat.parse(String.valueOf(date_limite.getText())); // parse the string to a Date object

Enchere en = new Enchere(String.valueOf(titre.getText()), String.valueOf(description.getText()),  parseDouble(String.valueOf(prixdepart.getText())), date, String.valueOf(img.getText()));


                    System.out.println("data Event == "+en);

                    //appelle methode ajouterReclamation tee service Reclamation bech nizdou données te3na fl base 
                    EnchereServices.getInstance().ajoutEnchere(en);

                    iDialog.dispose(); //nahiw loading baad maamalna ajout 

                    refreshTheme(); //Actualisation
                }
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));

    }

}
