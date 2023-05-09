/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Ligne_panier;
import java.io.IOException;
import java.util.List;
import services.LignePanierService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author achref
 */
public class ShowUserPanier  extends Form {
    public static int montantT=0;
    
    LignePanierService lps = LignePanierService.getInstance();
     

  public ShowUserPanier(Resources res) {

        //custom
        this.getToolbar().addCommandToRightBar("Add", null, (e) -> new AddCommandeForm(res).show());
        this.setLayout(BoxLayout.y());
        this.setTitle("Votre Panier");
        montantT=0;
        
        //calcule total
        List<Ligne_panier> lp = lps.affichageUserLp();
        for (Ligne_panier l : lp) {
        montantT+=l.getPrix_unitaire()*l.getQuantity();
        }
        
        //products card in panier
        for (Ligne_panier l : lp) {
            
//            String articleimage = lps.getArticleImagefromLP(l.getId());
//            
            
            System.out.println(lps.getArticleImagefromLP(l.getId()));
            
                    ImageViewer img = null;
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        EncodedImage enc = null;
        Image imgs;
        ImageViewer imgv;
        String url = lps.getArticleImagefromLP(l.getId());
            

       
        try {
            enc = EncodedImage.create("/art1.jpg");
            
        } catch (IOException ex) {

        }
       
        imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);

        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label title = new Label(l.getNom_article());
//        TextArea description = description = new TextArea(l.getDescription());
//        try{
//             description = new TextArea(l.getDescription().substring(0,20)+"...");
//        }catch(Exception e){};
//        try{
//             description = new TextArea(l.getDescription().substring(0,30)+"...");
//        }catch(Exception e){};
//        description.setEditable(false);
//        description.setFocusable(false);
//        description.setUIID("Label");
       
            TextField priceField = new TextField("Price: " + l.getPrix_unitaire());
        priceField.setEditable(false);
        priceField.setUIID("Label");

        // Add a TextField for quantity
        TextField quantityField = new TextField("Quantity: " + l.getQuantity());
        quantityField.setEditable(false);
        quantityField.setUIID("Label");
       
         Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       
        Button add = new Button();
    FontImage.setMaterialIcon(add, FontImage.MATERIAL_ADD);
    add.addActionListener(e->{
        System.out.println((int) l.getId());
        int id=(int) l.getId();
        lps.addQuantity(id); 
            System.out.println("ok add");
        Form newForm = new ShowUserPanier(res);
        newForm.show();
          
        //consumation api
    });
    Button minus = new Button();
    FontImage.setMaterialIcon(minus, FontImage.MATERIAL_REMOVE);
    minus.addActionListener(e->{
        //consumation api
        int id=(int) l.getId();
        lps.minusQuantity(id); 
            System.out.println("ok minus");
        Form newForm = new ShowUserPanier(res);
        newForm.show();

    });
    Button delete = new Button();
    FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
    delete.addActionListener(e->{
        //consumation api
        int id=(int) l.getId();
        lps.deleteQuantity(id); // jointure article id ligne panier jsp kifeh , lezem el fetch tjib kolshayyy
            System.out.println("ok delete");
        Form newForm = new ShowUserPanier(res);
        newForm.show();


    });
    
    
       
        img = new ImageViewer(imgs);
       
        c3.add(add);
        c3.add(minus);
        c3.add(delete);
        c3.getAllStyles().setMarginRight(10);;
        c2.add(title);
//        c2.add(description);
        c2.add(priceField);
        c2.add(quantityField);
        c2.add(c3);
        c1.add(img);
        c1.add(c2);
        this.add(c1);
        

        this.refreshTheme();
        
            
        }
        //total label
        Container c4= new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField Total = new TextField("ToTal: " + montantT);
        Total.setEditable(false);
        Total.setUIID("Label");
        c4.add(Total);
        this.add(c4);
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new WalkthruForm(res).showBack();
        });
    }

    

    
}