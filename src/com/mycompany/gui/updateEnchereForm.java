///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.gui;
//
//import com.codename1.ui.Display;
//import com.codename1.ui.Form;
//import com.codename1.ui.TextField;
//import com.codename1.ui.Toolbar;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.spinner.Picker;
//import com.codename1.ui.util.Resources;
//import com.mycompany.entities.Enchere;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//
///**
// *
// * @author howell
// */
//public class updateEnchereForm extends BaseForm{
//  
//   public Enchere e;
//    Form current;
//    public updateEnchereForm(Resources res , Enchere e) {
//       
//    super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
//        Toolbar tb = new Toolbar(true);
//        current = this ;
//        setToolbar(tb);
//        getTitleArea().setUIID("Container");
//        setTitle("update auction");
//        getContentPane().setScrollVisible(false);
//        
//        
//        super.addSideMenu(res);
//    
//        TextField titre = new TextField(e.getTitre(), "Titre");
//TextField description = new TextField(e.getDescription(), "description");
//TextField prixdepart = new TextField(String.valueOf(e.getPrixdepart()), " prixdepart");
//
//
//       
//Picker date_limite = new Picker();
//date_limite.setType(Display.PICKER_TYPE_DATE);
//date_limite.setDate(new Date());
//
//// Get the selected date from the picker and convert it to a LocalDate object
//Date selectedDate = date_limite.getDate();
//
//
//       
//        TextField img = new TextField(e.getImg(), "entrer img");
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
//    }
//    
//    
//    
//    
//    
//}
