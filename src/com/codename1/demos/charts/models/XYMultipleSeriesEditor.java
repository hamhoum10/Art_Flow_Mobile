/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Please contact Codename One through https://www.codenameone.com/ if you 
 * need additional information or have any questions.
 */

package com.codename1.demos.charts.models;

import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.components.Accordion;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

/**
 * Allows editing a multiple series set
 *
 * @author Shai Almog
 */
public class XYMultipleSeriesEditor extends Container {
    private XYMultipleSeriesDataset xy;
    public XYMultipleSeriesEditor() {
        super(new BorderLayout());
    }
    
    public void init(XYMultipleSeriesDataset xy) {
        Accordion acc = new Accordion();
        add(BorderLayout.CENTER, acc);
        this.xy = xy;
        Style s = UIManager.getInstance().getComponentStyle("Button");
        s.setFgColor(0xff0000);
        FontImage removeImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, s, 3.5f);
        FontImage addImage = FontImage.createMaterial(FontImage.MATERIAL_ADD, s, 3.5f);
        for(XYSeries xx : xy.getSeries()) {
            addSeries(xx, acc, removeImage);
        }
        Button add = new Button(addImage);
        add(BorderLayout.SOUTH, add);
        add.addActionListener(e -> {
            XYSeries x  = new XYSeries("New Series");
            addSeries(x, acc, removeImage);
            acc.animateLayout(200);
        });
    }

    private void addSeries(XYSeries xx, Accordion acc, FontImage removeImage) {
        XYSeriesEditor edit = new XYSeriesEditor();
        edit.init(xx, title -> acc.setHeader(title, edit));
        edit.setScrollable(false);
        Button remove = new Button(removeImage);
        remove.addActionListener((e) -> {
            xy.removeSeries(xx);
            acc.removeContent(edit);
            acc.animateLayout(200);
        });
        acc.addContent(xx.getTitle(), edit);
    }
}
