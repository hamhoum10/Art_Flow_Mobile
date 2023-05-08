package com.mycompany.myapp;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;


public class livraison extends Form  {
    public livraison(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Container gui_Box_Layout_Y = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    protected com.codename1.ui.Label gui_Label = new com.codename1.ui.Label();
    protected com.codename1.ui.TextField gui_TextField = new com.codename1.ui.TextField();
    protected com.codename1.ui.TextField gui_TextField_2 = new com.codename1.ui.TextField();
    protected com.codename1.ui.TextField gui_TextField_3 = new com.codename1.ui.TextField();
    protected com.codename1.ui.Button gui_Button = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("livraison");
        setName("livraison");
        gui_Box_Layout_Y.setPreferredSizeStr("59.271805mm 65.83404mm");
                gui_Box_Layout_Y.setInlineStylesTheme(resourceObjectInstance);
        gui_Box_Layout_Y.setName("Box_Layout_Y");
        addComponent(gui_Box_Layout_Y);
        gui_Label.setText("Search");
                gui_Label.setInlineStylesTheme(resourceObjectInstance);
        gui_Label.setName("Label");
                gui_TextField.setInlineStylesTheme(resourceObjectInstance);
        gui_TextField.setName("TextField");
        gui_TextField_2.setPreferredSizeStr("inherit inherit");
                gui_TextField_2.setInlineStylesTheme(resourceObjectInstance);
        gui_TextField_2.setName("TextField_2");
        gui_TextField_3.setPreferredSizeStr("76.206604mm 5.7154956mm");
                gui_TextField_3.setInlineStylesTheme(resourceObjectInstance);
        gui_TextField_3.setName("TextField_3");
        gui_Button.setText("Submit");
                gui_Button.setInlineStylesTheme(resourceObjectInstance);
        gui_Button.setName("Button");
        gui_Box_Layout_Y.addComponent(gui_Label);
        gui_Box_Layout_Y.addComponent(gui_TextField);
        gui_Box_Layout_Y.addComponent(gui_TextField_2);
        gui_Box_Layout_Y.addComponent(gui_TextField_3);
        gui_Box_Layout_Y.addComponent(gui_Button);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Box_Layout_Y.getParent().getLayout()).setInsets(gui_Box_Layout_Y, "13.982522% auto auto 33.192047%").setReferenceComponents(gui_Box_Layout_Y, "-1 -1 -1 -1").setReferencePositions(gui_Box_Layout_Y, "0.0 0.0 0.0 0.0");
    }// </editor-fold>
//-- DON'T EDIT ABOVE THIS LINE!!!
}
