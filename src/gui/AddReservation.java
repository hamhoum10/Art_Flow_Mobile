/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
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
import entities.Article;
import entities.Categorie;
//import entities.Article;
//import entities.Categorie;
import entities.Evenement;
import entities.Reservation;
import java.util.Date;
//import services.ArticleServices;
import services.ReservationService;
import utilities.Statics;

/**
 *
 * @author Lenovo
 */
public class AddReservation extends Form{
    
    public AddReservation(Resources res, int id){
        
      ReservationService as = ReservationService.getInstance();
        setUIID("LoginForm");
        
        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Reserved a event");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ListEventForm(res).show();
        });

        //Widgets
        TextField nombredeplace = new TextField("", "Nombre de place ", 20, TextField.ANY);
//      

         Picker dateres = new Picker();
         dateres.setType(Display.PICKER_TYPE_DATE);
         dateres.setDate(new Date());
        // addStringValue("date_limite e", dateres);
//       
       
        Button selectImage = new Button("select Image");
        selectImage.setUIID("WalkthruTab2");
        selectImage.addActionListener((e) -> {
            Display.getInstance().openGallery((ActionListener) evt -> {
                String filePath = (String) evt.getSource();
                if (filePath != null) {
                    //imageTF.setText(filePath);
                }
            }, Display.GALLERY_IMAGE);
        });
        Button submitBtn = new Button("Submit");
                submitBtn.setUIID("LoginButton");

        //actions
        Reservation r = new Reservation();
        
        submitBtn.addActionListener((evt) -> {
           
           if (as.AddReservation(new Reservation (dateres.getText(),Integer.parseInt(nombredeplace.getText())),id)) {
                Dialog.show("Success", "Article Inserted successfully", "Got it", null);
                new ListEventForm(res).showBack();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
            this.addAll(nombredeplace, dateres, submitBtn);
    }
    
}

//
//public class AddReservation extends Form {
//
//    Form current;
//
//    public AddReservation(Resources res) {
//
//        super("add auction", BoxLayout.y());// change to BoxLayout.y() to make the form vertical
//
//        Toolbar tb = new Toolbar(true);
//        current = this;
//        setToolbar(tb);
//        getTitleArea().setUIID("Container");
//      
//        getContentPane().setScrollVisible(false);
//
//       TextField nombre de place = new TextField("", "entrer Titre");
     //  nbplace.setUIID("TextFieldBlack");
//        addStringValue("Titre", titre);
//
//       
//
//        Picker date_limite = new Picker();
//        dateres.setType(Display.PICKER_TYPE_DATE);
//        dateres.setDate(new Date());
//        addStringValue("dateres e", dateres);
//
//      
//        
//         Button btnAjouter=new Button("Ajouter");
//        addStringValue("",btnAjouter);
//
////        
////         Button btnShowevents=new Button("ShowEvents");
////        btnShowevents.addActionListener(e -> new ShowEventForm(current).show());
////
////        
//        
//        
//   //onclick button event 
//        
//        btnAjouter.addActionListener((ActionEvent e)-> {
//            try{
//
//                if( Integer.parseInt(nombredeplace.getText()){
//
//                    Dialog.show("Veuillez verifier les données","", "Annuler" , "OK");
//                }
//                else {
//                    InfiniteProgress ip = new InfiniteProgress();//Loading after insert data
//
//                    final Dialog iDialog = ip.showInfiniteBlocking();
//
//
//SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy"); // specify the format of the date string
//Date date;
//
//    date = dateFormat.parse(String.valueOf(date_limite.getText())); // parse the string to a Date object
//
//Reservation en = new Reservation(String.valueOf(titre.getText()), String.valueOf(description.getText()),  parseDouble(String.valueOf(prixdepart.getText())), date, String.valueOf(img.getText()));
//
//
//                    System.out.println("data Event == "+en);
//
//                    //appelle methode ajouterReclamation tee service Reclamation bech nizdou données te3na fl base 
//                    EnchereServices.getInstance().ajoutEnchere(en);
//
//                    iDialog.dispose(); //nahiw loading baad maamalna ajout 
//
//                    refreshTheme(); //Actualisation
//                }
//                
//                
//                
//                
//                
//                
//                
//                
//                
//                
//                
//                
//                
//                
//                
//                
//                
//            }catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });
//    }
//
//    private void addStringValue(String s, Component v) {
//        add(BorderLayout.west(new Label(s, "PaddedLabel"))
//                .add(BorderLayout.CENTER, v));
//
//    }
//
//}
