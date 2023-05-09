///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package gui;

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
import entities.Evenement;
//import entities.Categorie;
import services.EventService;
//import services.ArticleServices;

/**
 *
 * @author Lenovo
 */
public class modifyEventForm extends Form{
     public modifyEventForm(Evenement a ,Resources res) {
      
        EventService as = EventService.getInstance();
        setUIID("LoginForm");
        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Event");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ShowEvent(res).show();
        });

        //Widgets
        //sans oublier pour la date
//        TextField date = new TextField(a.getDate_evemt()+"", "description of event", 20, TextField.ANY);
        TextField name = new TextField(a.getName()+"", "name of event", 20, TextField.ANY);
        a.setName(name.getText());
        TextField prix = new TextField(a.getPrix()+"", "Prix", 20, TextField.ANY);
       // a.setPrix();
        TextField location = new TextField(a.getLocation()+"", "location of event", 20, TextField.ANY);
        a.setLocation(location.getText());
        TextField capacity = new TextField(a.getCapacity()+"", "Capacity", 20, TextField.ANY);
        a.setCapacity(capacity.getText());
//        TextField name = new TextField(a.getName()+"", "name of event", 20, TextField.ANY);
       // TextField prix = new TextField(a.getPrix()+"", "prix of event", 20, TextField.ANY);
  //      TextField username = new TextField(a.getUsername()+"", "prix of event", 20, TextField.ANY);
       
//        String[] characters = {"painture", "bsal" /* cropped */};
//        Picker categorie = new Picker();
//        categorie.setStrings(characters);
//        categorie.setSelectedString(characters[0]);
//        categorie.addActionListener(e -> ToastBar.showMessage("You picked " + categorie.getSelectedString(), FontImage.MATERIAL_INFO));
       
        Button selectImage = new Button("select Image");
        selectImage.setUIID("WalkthruTab2");
        selectImage.addActionListener((e) -> {
            Display.getInstance().openGallery((ActionListener) evt -> {
                String filePath = (String) evt.getSource();
                if (filePath != null) {
                 //   image.setText(filePath);
                }
            }, Display.GALLERY_IMAGE);
        });
        Button submitBtn = new Button("Submit");
                submitBtn.setUIID("LoginButton");
                
//                submitBtn.addActionListener((evt) -> {
//            if(as.modifyEvent(new Evenement (name.getText(),location.getText(),capacity.getText(),Double.parseDouble(prix.getText())))){
//            Dialog.show("Success", "Event  updated successfully", "Got it", null);
//            }else{
//                    Dialog.show("Success", "An error has occured", "Got it", null);
//            }
//            
//        });

        //actions
        submitBtn.addActionListener((evt) -> {
            if (as.modifyEvent(a)) {
                System.out.println(a.toString());
                Dialog.show("Success", "Event modified successfully", "Got it", null);
                new ShowEvent(res).showBack();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });
        //end
            this.addAll(name,prix,location,capacity, submitBtn);
    
        
                
                } }             
