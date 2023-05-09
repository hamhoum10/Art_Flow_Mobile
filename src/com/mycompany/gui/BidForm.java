/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import com.codename1.ui.Form;

import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;

import com.codename1.ui.layouts.BoxLayout;

import com.itextpdf.text.BaseColor;
import com.mycompany.entities.Client;
import com.mycompany.entities.Enchere;
import com.mycompany.services.EnchereServices;

import com.mycompany.entities.Enchere;
import com.mycompany.entities.Participant;
//import com.codename1.io.FileSystemStorage;
import com.itextpdf.text.Document;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.util.Calendar;




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

   

   java.util.Date today = new java.util.Date();
    // Create a text field for the client id
    TextField clienid = new TextField("", "Enter client id");
    container.add(clienid);

    // Create a button to submit the bid
    Button submitButton = new Button("Submit");
    container.add(submitButton);
    
    
Button downloadButton = new Button("Download PDF");


// Set the date to compare with
Calendar targetDate = Calendar.getInstance();
targetDate.set(Calendar.YEAR, 2023);
targetDate.set(Calendar.MONTH, Calendar.MAY);
targetDate.set(Calendar.DAY_OF_MONTH, 8);

// Compare the current date with the target date
Calendar currentDate = Calendar.getInstance();
if (currentDate.before(targetDate)) {
    downloadButton.setHidden(true);
    container.addComponent(downloadButton);
    container.revalidate();
} else {
    container.addComponent(downloadButton);
}

    // Add an ActionListener to the submit button
    submitButton.addActionListener(evt -> {
        // Get the selected Enchere object from the combo box
       //enchere = enchereList.get((int) titleComboBox.getSelectedItem());
         Enchere enchere = new Enchere();
enchere.setIde(Integer.parseInt(enchereid.getText()));
        System.out.println("ENCHERE ID : "+enchere.getIde());
        
Client client = new Client();
client.setId(Integer.parseInt(clienid.getText()));
System.out.println("Client ID : "+client.getId());





double montant = Double.parseDouble(montantField.getText());

    if (montant <= 0) {
        Dialog.show("Error", "Bid amount must be positive", "OK", null);
       
        return;
    }

Participant p = new Participant (montant,client, enchere);

        System.out.println(p);
        // Show a success message or an error message if the operation failed
        if (es.ajoutParticipant(p)) {
           
            Dialog.show("Success", "Participant added to Enchere", "OK", null);
           
        } else {
            Dialog.show("Error", "The bid must higher than the current highest bid", "OK", null);
        }
         new BidForm().show();
        
    });
    
    downloadButton.addActionListener(evt -> {
   
    try {
       
    Enchere a= es.getONEEnchere1(Integer.parseInt(enchereid.getText()));
        downloadPDF(a.getTitre(),a.getDescription(),a.getPrixdepart(),"jhjhjhjhj");
    } catch (Exception ex) {
       
    }
        
        
});




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
    
    
    
    
   private void downloadPDF(String titre,String description,double prixdepart,String loc) throws Exception {
    // Set the file path
    String filePath = FileSystemStorage.getInstance().getAppHomePath() + "offre.pdf";
    // Create an output stream to write the PDF file
    OutputStream outputStream = FileSystemStorage.getInstance().openOutputStream(filePath);

    // Create a new document
    Document document = new Document();
    // Set the PDF writer to write to the output stream
    PdfWriter.getInstance(document, outputStream);

    // Add metadata to the document
    document.addAuthor("Your Name");
    document.addCreator("artflow");
    document.addSubject("winner");
    document.addTitle("winner");

    // Set the document margins
    document.setMargins(36, 36, 36, 36);

    // Open the document
    document.open();

    
Paragraph nickparagraph = new Paragraph("congradulation you won this auction : " + titre);
        
nickparagraph.add("\n"+description);
nickparagraph.add("Winner\n" +
"Name: malek\n" +
"Email: malek@esprit.tn\n" +
"phone Number: 88222366\n" +
"Address : hau215\n" +
"Conditions générales : Paiement à effectuer de préférence par virement bancaire. La facture\n" +
"doit être payée dans un délai de 30 jours après sa date démission. En cas de retard de\n" +
"paiement, les pénalités de retard sélèvent à 10% du montant total de la facture. Lindemnité\n" +
"forfaitaire pour frais de recouvrement est de 200 Dinars. Indemnisation complémentaire sur\n" +
"justification. Escompte pour paiement anticipé :néant\n" );


// Create a new cell to hold the paragraph
PdfPCell cell = new PdfPCell(nickparagraph);

// Set the background color of the cell
cell.setBackgroundColor(new BaseColor(128, 128, 128)); // Violet color

// Add the cell to a new table with one column and one row
PdfPTable table = new PdfPTable(1);
table.setWidthPercentage(100);

table.addCell(cell);

// Add the table to the document
document.add(table);

      
    
    
      
        // Set the font size and style for the response
      

        // Add some space between the questions
        document.add(new Paragraph(" "));
    

    // Close the document
    document.close();

    // Flush and close the output stream
    outputStream.flush();
    outputStream.close();

    if (Dialog.show("PDF Downloaded", "PDF file was saved to " + filePath + ". Do you want to open it?", "Open", "Cancel")) {
        // Open the PDF file
        Display.getInstance().execute(filePath);
    }
}  
    
    
    
    
    
}