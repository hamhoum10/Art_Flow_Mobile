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
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Client;
import com.mycompany.entities.Enchere;
import com.mycompany.services.EnchereServices;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import com.mycompany.entities.Enchere;
import com.mycompany.entities.Participant;
//import com.codename1.io.FileSystemStorage;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;



/**
 *
 * @author howell
 */
public class BidForm extends Form {

    // var
    Enchere en = new Enchere();
    Participant p = new Participant();
    EnchereServices es = EnchereServices.getInstance();
//String pdfFilePath = FileSystemStorage.getInstance().getAppHomePath() + "test.pdf";

    public BidForm() {
this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

    // Add a back button to the toolbar
    Command backCommand = new Command("Back");
    this.getToolbar().addCommandToLeftBar(backCommand);
    
    
//    
//    ConnectionRequest request = new ConnectionRequest();
//request.setUrl("https://www.example.com/test.pdf");
//request.setDestinationFile(pdfFilePath);
//NetworkManager.getInstance().addToQueueAndWait(request);


    // Create a container for the widgets
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    // Get the list of Enchere objects
  //  ArrayList<Enchere> enchereList = es.getAllEnchere();
    // Create a combo box for the enchere titles
    

    
  TextField enchereid = new TextField("", "Enter enchere id");
    container.add(enchereid);

    // Create a text field for the bid amount
   TextField montantField = new TextField("", "Enter bid amount");
montantField.setConstraint(TextField.NUMERIC);
container.add(montantField);

   


    // Create a text field for the client id
    TextField clienid = new TextField("", "Enter client id");
    container.add(clienid);

    // Create a button to submit the bid
    Button submitButton = new Button("Submit");
    container.add(submitButton);
    
    Button downloadButton = new Button("Download PDF");
    container.add(downloadButton);
    
    
    // Add an ActionListener to the submit button
    submitButton.addActionListener(evt -> {
        // Get the selected Enchere object from the combo box
       //enchere = enchereList.get((int) titleComboBox.getSelectedItem());
         Enchere enchere = new Enchere();
enchere.setIde(Integer.parseInt(enchereid.getText()));
        
Client client = new Client();
client.setId(Integer.parseInt(clienid.getText()));





double montant = Double.parseDouble(montantField.getText());
    if (montant <= 0) {
        Dialog.show("Error", "Bid amount must be positive", "OK", null);
        return;
    }

Participant p = new Participant (montant,client, enchere);
boolean success = es.ajoutParticipant(p);
        
        // Show a success message or an error message if the operation failed
        if (success) {
            Dialog.show("Success", "Participant added to Enchere", "OK", null);
        } else {
            Dialog.show("Error", "Failed to add participant", "OK", null);
        }
        
        
    });
    
//    downloadButton.addActionListener(evt -> {
//    if (FileSystemStorage.getInstance().exists(pdfFilePath)) {
//        Display.getInstance().execute(pdfFilePath);
//    } else {
//        Dialog.show("Error", "PDF file not found", "OK", null);
//    }
//});




//downloadButton.addActionListener(evt -> {
//    try {
//        // Create a new PDF document
//      Document document = new Document();
//
//            // Set the file name and location
//            String fileLocation = "C:/Users/amena/Documents/NetBeansProjects/example.pdf";
//
//            // Create a new FileOutputStream object
//            FileOutputStream fos = new FileOutputStream(fileLocation);
//
//            // Create a new PdfWriter instance
//            PdfWriter.getInstance(document, fos);
//        // Add a paragraph
//        Paragraph p = new Paragraph("This is a test paragraph.");
//        document.add(p);
//        // Close the document
//        document.close();
//        // Show a success message
//        Dialog.show("Success", "PDF downloaded", "OK", null);
//    } catch (Exception e) {
//        // Show an error message
//        Dialog.show("Error", "Failed to download PDF", "OK", null);
//        e.printStackTrace();
//    }
//});



    // Add the container to the form
    this.add(container);
    
}
    
    
    
    
    
    
    
    
    
    
}