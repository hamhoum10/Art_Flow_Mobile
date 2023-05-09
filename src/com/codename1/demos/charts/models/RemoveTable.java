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

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;

/**
 * Special table whose last column is a "remove" button
 *
 * @author Shai Almog
 */
public class RemoveTable extends Table {
    private FontImage removeImage;
    private OnRemove onRemove;
    public RemoveTable(TableModel m, OnRemove onRemove) {
        super(m);
        this.onRemove = onRemove;
    }
    
    private FontImage getRemoveImage() {
        if(removeImage == null) {
            Style s = UIManager.getInstance().getComponentStyle("TableCell");
            s.setFgColor(0xff0000);
            removeImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, s, 3.5f);
        }
        return removeImage;
    }
    
    public static interface OnRemove {
        public void removed(int row, int column);
    }

    @Override
    protected Component createCell(Object value, int row, int column, boolean editable) {
        if(column == getModel().getColumnCount() - 1) {
            Button removeButton = new Button(getRemoveImage());
            removeButton.addActionListener(e -> {
                onRemove.removed(row, column);
                setModel(getModel());
            });
            return removeButton;
        }
        return super.createCell(value, row, column, editable); 
    }

    
}
