/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.InfiniteProgress;
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
import java.util.Arrays;
import java.util.List;
import utilities.Stripeapi;

/**
 *
 * @author medya
 */
public class PaymentForm extends Form {
    
    public PaymentForm(Resources res) {
            setUIID("LoginForm");
        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Task");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new AddCommandeForm(res).show();
        });

        //Widgets
        TextField nameTF = new TextField("", "first name", 20, TextField.ANY);
        TextField numberTF = new TextField("", "code ", 20, TextField.ANY);
        TextField expmonthTF = new TextField("", "exp-month", 20, TextField.ANY);
        TextField expyearTF = new TextField("", "exp-year", 20, TextField.ANY);
        TextField cvcTF = new TextField("", "CVC", 20, TextField.ANY);
       
        Button submitBtn = new Button("Submit");
                submitBtn.setUIID("LoginButton");

        //actions
        submitBtn.addActionListener((evt) -> {
            try{

                if(nameTF.getText().isEmpty() || expmonthTF.getText().isEmpty() || expyearTF.getText().isEmpty()|| numberTF.getText().isEmpty()|| cvcTF.getText().isEmpty()){
                      
                    Dialog.show("Veuillez verifier les données","", "Annuler" , "OK");
                }
                else if (isValidName(nameTF.getText())==false||isValidName(expmonthTF.getText())==false||isValidName(expyearTF.getText())==false||isValidName(numberTF.getText())==false||isValidName(cvcTF.getText())==false){
                    Dialog.show("Please don't use special caracteres ","", null , "OK");
                }else if (numberTF.getText().length()!= 16 ) {
                 Dialog.show("code number must contain 16 numbers ","", null , "OK");
                } else if (cvcTF.getText().length()!=3) {
                 Dialog.show("Please CVC must contain 3 numbers ","", null , "OK" );
                }else if (expyearTF.getText().length()!=2) {
                 Dialog.show("Please expyear must contain 2 numbers ","", null , "OK" );
                }else if (expmonthTF.getText().length()!=2) {
                 Dialog.show("Please expmonth must contain 2 numbers ","", null , "OK" );
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();//Loading after insert data

                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    Stripeapi st =new Stripeapi();
                    boolean transaction = st.verifyCardAndPay(numberTF.getText(), Integer.valueOf(expmonthTF.getText()), Integer.valueOf(expmonthTF.getText()), String.valueOf(cvcTF.getText()), nameTF.getText(),String.valueOf(ShowUserPanier.montantT *100));

                    
                  
                    
                    iDialog.dispose(); //nahiw loading baad maamalna ajout 
                    if(transaction ==true){
                        new ShowUserPanier(res).show();
                    Dialog.show("Payment sucesseded !","", null, "OK" );
                    }else{
                    Dialog.show("check you're credientiels !","", null, "OK" );
                    }

                    
                    
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //end
            this.addAll(nameTF, numberTF, expmonthTF, expyearTF, cvcTF,submitBtn);
    
    }
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));

    }
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
