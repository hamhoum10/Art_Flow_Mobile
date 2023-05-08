/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Enchere;
import com.mycompany.services.EnchereServices;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import com.mycompany.entities.Enchere;

/**
 *
 * @author howell
 */
public class ShowEnchereForm extends Form {

    //var
    Enchere en = new Enchere();
    EnchereServices es = EnchereServices.getInstance();

    public ShowEnchereForm() {

        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("All Auctions");

        // Create a new Container to hold the buttons and labels
        Container enchereContainer = new Container(BoxLayout.y());

        // Get the list of Enchere objects
        ArrayList<Enchere> enchereList = es.getAllEnchere();

        // Iterate through the list of Enchere objects and add a button and a label for each object
        for (Enchere enchere : enchereList) {
            Button enchereButton = new Button("Bid");
            SpanLabel enchereLabel = new SpanLabel(enchere.toString());

            enchereContainer.add(enchereButton);
            enchereContainer.add(enchereLabel);

            // Add an ActionListener to the button to handle the click event
         
    // Create a new form for the bid
    enchereButton.addActionListener(e -> {
    // Create a new form for the bid
    

  Form bidForm = new BidForm();


  
     


    // Show the bid form
    bidForm.show();
});

        }

        // Add the Container to the form
        this.add(enchereContainer);
    }
}
