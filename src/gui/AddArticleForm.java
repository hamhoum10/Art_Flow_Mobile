/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.ToastBar;
import com.codename1.io.MultipartRequest;
import services.ArticleServices;
import entities.Article;
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
import entities.Categorie;

/**
 *
 * @author MediaStudio
 */
public class AddArticleForm extends Form {
    public AddArticleForm(Resources res) {
        ArticleServices as = ArticleServices.getInstance();
        setUIID("LoginForm");
        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Task");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ShowArticlesForm(res).show();
        });

        //Widgets
        TextField nomArticleTF = new TextField("", "Nom Article", 20, TextField.ANY);
        TextField descriptionTF = new TextField("", "Article description", 20, TextField.ANY);
        TextField priceTF = new TextField("", "Article price", 20, TextField.ANY);
        TextField typeTF = new TextField("", "Article type", 20, TextField.ANY);
        TextField imageTF = new TextField("", "Article image", 20, TextField.ANY);
        TextField quantityTF = new TextField("", "Article quantity", 20, TextField.ANY);
       
        String[] characters = {"painture", "bsal" /* cropped */};
        Picker categorie = new Picker();
        categorie.setStrings(characters);
        categorie.setSelectedString(characters[0]);
        categorie.addActionListener(e -> ToastBar.showMessage("You picked " + categorie.getSelectedString(), FontImage.MATERIAL_INFO));
       
        Button selectImage = new Button("select Image");
        selectImage.setUIID("WalkthruTab2");
        selectImage.addActionListener((e) -> {
            Display.getInstance().openGallery((ActionListener) evt -> {
                String filePath = (String) evt.getSource();
                if (filePath != null) {
                    imageTF.setText(filePath);
                }
            }, Display.GALLERY_IMAGE);
        });
        Button submitBtn = new Button("Submit");
                submitBtn.setUIID("LoginButton");

        //actions
        submitBtn.addActionListener((evt) -> {
            if (as.addArticle(new Article (Double.parseDouble(priceTF.getText()), typeTF.getText(), imageTF.getText(), descriptionTF.getText(), nomArticleTF.getText(),new Categorie(1,categorie.getText(),""),Integer.parseInt(quantityTF.getText())))) {
                Dialog.show("Success", "Article Inserted successfully", "Got it", null);
                new ShowArticlesForm(res).showBack();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
            this.addAll(nomArticleTF, descriptionTF, priceTF, quantityTF, typeTF, selectImage, imageTF, categorie, submitBtn);
    }
    
}
