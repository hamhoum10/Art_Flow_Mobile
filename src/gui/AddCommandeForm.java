/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.InfiniteProgress;
import services.ArticleServices;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Commande;
import java.util.Arrays;
import java.util.List;
import services.CommandeService;

/**
 *
 * @author MediaStudio
 */
public class AddCommandeForm extends Form {
    public AddCommandeForm(Resources res) {
        ArticleServices as = ArticleServices.getInstance();
        setUIID("LoginForm");
        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Commande");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ShowUserPanier(res).show();
        });

        //Widgets
        TextField firstnameTF = new TextField("", "first name", 20, TextField.ANY);
        TextField lastnameTF = new TextField("", "last name", 20, TextField.ANY);
        TextField emailTF = new TextField("", "email", 20, TextField.ANY);
        TextField addresseTF = new TextField("", "addresse ", 20, TextField.ANY);
        TextField codepostalTF = new TextField("", "code postal", 20, TextField.ANY);
        TextField numeroTF = new TextField("", "numero", 10, TextField.ANY );
        
       
        Button submitBtn = new Button("Submit");
                submitBtn.setUIID("LoginButton");

        //actions
        submitBtn.addActionListener((evt) -> {
            try{

                if(firstnameTF.getText().isEmpty() || lastnameTF.getText().isEmpty() || codepostalTF.getText().isEmpty()|| numeroTF.getText().isEmpty()|| addresseTF.getText().isEmpty()|| emailTF.getText().isEmpty() ){
                      
                    Dialog.show("Veuillez verifier les données","", "Annuler" , "OK");
                }
                else if (isValidName(firstnameTF.getText())==false||isValidName(lastnameTF.getText())==false||isValidName(numeroTF.getText())==false||isValidName(addresseTF.getText())==false||isValidName(codepostalTF.getText())==false){
                    Dialog.show("Please don't use special caracteres ","", "Annuler" , "OK");
                }else if (numeroTF.getText().length()!= 8 ) {
                 Dialog.show("Please Phone number must contain 8 numbers ","", "Annuler" , "OK");
                } else if (codepostalTF.getText().length()!=4) {
                 Dialog.show("Please Code postal must contain 4 numbers ","", "Annuler" , "OK" );
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();//Loading after insert data

                    final Dialog iDialog = ip.showInfiniteBlocking();

                    
                  
                    Commande c =new Commande(3, lastnameTF.getText(), firstnameTF.getText(),Integer.valueOf(numeroTF.getText()), Integer.valueOf(codepostalTF.getText()), addresseTF.getText(),emailTF.getText());
                    CommandeService.getInstance().addCommande(c, 3);
                    
                    iDialog.dispose(); //nahiw loading baad maamalna ajout 
                    new PaymentForm(res).show();
                    
                    Dialog.show("You're Order is created sucessfully ! ","", "Annuler" , "OK");

                    
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //end
            this.addAll(firstnameTF, lastnameTF, emailTF, numeroTF, addresseTF, codepostalTF, submitBtn);
    }
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));

    }
    
    //controle de saisae
    
    public static boolean isValidName(String name) {
         List<Character> specialCharacters = Arrays.asList(
                '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '{', '}', '[', ']', '\\', '|', ';', ':', '\'', '\"', ',', '.', '/', '?', '<', '>', '~', '`'
        );
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (specialCharacters.contains(c)) {
                return false;
            }
        }
        return true;
    }
    
}
