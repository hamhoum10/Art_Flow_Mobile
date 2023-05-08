/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.guiStock;

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
import entities.stock;
import java.util.Date;
import services.LivraisonServices;
import services.StockServices1;

/**
 *
 * @author omriy
 */
public class AddStockForm extends Form {
    public AddStockForm(Resources res) {
        StockServices1 as = StockServices1.getInstance();
        setUIID("LoginForm");
        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add stock");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ShowStockForm(res).show();
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
            if (as.addStock(new stock (nomTF.getText(),artTF.getText(),adressTF.getText(),"2000-10-10",Integer.valueOf(idcomTF.getText()),usrTF.getText()))) {
                Dialog.show("Success", "stock Inserted successfully", "Got it", null);
                new ShowStockForm(res).showBack();
                as.SMSlivraison();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
            this.addAll(nomTF, artTF, adressTF, datTF, usrTF, idcomTF,submitBtn);
    }
}
