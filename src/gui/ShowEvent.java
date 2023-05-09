/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Article;
import entities.Evenement;
import java.io.IOException;
import java.util.List;
import services.EventService;

/**
 *
 * @author Lenovo
 */
public class ShowEvent extends Form {
    EventService as = EventService.getInstance();
    
     public ShowEvent(Resources res){
         // this.getToolbar().addCommandToRightBar("Add", null, (e) -> new AddReservation(res).show());
        this.setLayout(BoxLayout.y());
        this.setTitle("All events");
        List<Evenement> evenements = as.affichageEvenement();
       // for (int i = 0; i < evenements.size(); i++) {
       for (Evenement l : evenements) {
            //addItem(evenements.get(1), res);
             ImageViewer img = null;
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        EncodedImage enc = null;
        Image imgs;
        ImageViewer imgv;
        //pour afficher l'image sur l'emilateur
        String url = l.getImage();
       
       
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {

        }
       
        imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);

        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label title = new Label(l.getName());
           System.out.println(l.getId());
       TextField prix = new TextField("Price: " + l.getPrix());
        prix.setEditable(false);
        prix.setUIID("Label");
         TextField location = new TextField("location: " + l.getLocation());
        location.setEditable(false);
        location.setUIID("Label");
         TextField capacity = new TextField("capacity: " + l.getCapacity());
        capacity.setEditable(false);
        capacity.setUIID("Label");
//         TextField dateEvent = new TextField("dateEvent: " + l.getDate_evemt());
//        dateEvent.setEditable(false);
//        dateEvent.setUIID("Label");
         
         
        TextArea description = description = new TextArea(l.getDescription());
        try{
             description = new TextArea(l.getDescription().substring(0,20)+"...");
        }catch(Exception e){};
        try{
             description = new TextArea(l.getDescription().substring(0,30)+"...");
        }catch(Exception e){};
        description.setEditable(false);
        description.setFocusable(false);
        description.setUIID("Label");
       
        Button affiche = new Button("Afficher");
        affiche.addActionListener(e->{
            //new ShowTutorielForm(a,res).show();
        });
       
         Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       
        Button modif = new Button();
        FontImage.setMaterialIcon(modif, FontImage.MATERIAL_MODE_EDIT);
        modif.addActionListener(e->{
            new modifyEventForm(l,res).show();
        });
        Button delete = new Button();
        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
        delete.addActionListener(e->{
            if (as.deleteEvent(l)) {
                Dialog.show("Success", "Article deleted successfully", "Got it", null);
                
                new ShowEvent(res).show();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });
       
        img = new ImageViewer(imgs);
       
        c3.add(modif);
        c3.add(delete);
        c3.getAllStyles().setMarginRight(10);;
        c2.add(title);
        c2.add(description);
        c2.add(prix);
        c2.add(location);
        c2.add(capacity);
       // c2.add(dateEvent);
        c2.add(affiche);
        c2.add(c3);
        c1.add(img);
        c1.add(c2);
        this.add(c1);

        this.refreshTheme();
    
        }
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new WalkthruForm(res).showBack();
        });
     
//         this.getToolbar().addCommandToRightBar("Add", null, (e) -> new AddReservation(res).show());
////       
//
// this.setLayout(BoxLayout.y());
//        this.setTitle("View all Events");
//        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
//          // new HomeForm().showBack();
//        });
//
//        //widgets
//        SpanLabel sl = new SpanLabel();
//        sl.setText(as.affichageEvenement().toString());
//
//        //end
//        this.add(sl);

    }
     
//      private void addItem(Evenement a, Resources res){
//          ImageViewer img = null;
//        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
//        EncodedImage enc = null;
//        Image imgs;
//        ImageViewer imgv;
//        String url = "http://localhost/img/"+a.getImage();
//       
//       
//        try {
//            enc = EncodedImage.create("/load.png");
//        } catch (IOException ex) {
//
//        }
//       
//        imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
//
//        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        Label title = new Label(a.getName());
//       TextField prix = new TextField("Price: " + a.getPrix());
//        prix.setEditable(false);
//        prix.setUIID("Label");
//         TextField location = new TextField("location: " + a.getLocation());
//        location.setEditable(false);
//        location.setUIID("Label");
//         TextField capacity = new TextField("capacity: " + a.getCapacity());
//        capacity.setEditable(false);
//        capacity.setUIID("Label");
//         TextField dateEvent = new TextField("dateEvent: " + a.getDate_evemt());
//        dateEvent.setEditable(false);
//        dateEvent.setUIID("Label");
//         
//        TextArea description = description = new TextArea(a.getDescription());
//        try{
//             description = new TextArea(a.getDescription().substring(0,20)+"...");
//        }catch(Exception e){};
//        try{
//             description = new TextArea(a.getDescription().substring(0,30)+"...");
//        }catch(Exception e){};
//        description.setEditable(false);
//        description.setFocusable(false);
//        description.setUIID("Label");
//       
//        Button affiche = new Button("Afficher");
//        affiche.addActionListener(e->{
//           // new ShowTutorielForm(a,res).show();
//        });
//       
//         Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
//       
//        Button modif = new Button();
//        FontImage.setMaterialIcon(modif, FontImage.MATERIAL_MODE_EDIT);
//        modif.addActionListener(e->{
//            new modifyEventForm(a,res).show();
//        });
//        Button delete = new Button();
//        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
//        delete.addActionListener(e->{
//            if (as.deleteEvent(a)) {
//                Dialog.show("Success", "Article deleted successfully", "Got it", null);
//                
//               // new ShowEvent(res).show();
//            } else {
//                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
//            }
//        });
//       
//        img = new ImageViewer(imgs);
//       
//        c3.add(modif);
//        c3.add(delete);
//        c3.getAllStyles().setMarginRight(10);;
//        c2.add(title);
//        c2.add(description);
//        c2.add(prix);
//        c2.add(location);
//        c2.add(capacity);
//        c2.add(dateEvent);
//        c2.add(affiche);
//        c2.add(c3);
//        c1.add(img);
//        c1.add(c2);
//        this.add(c1);
//
//        this.refreshTheme();
//    }
          
      
       
      }
       
        


