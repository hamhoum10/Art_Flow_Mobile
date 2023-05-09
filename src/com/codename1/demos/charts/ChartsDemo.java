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
package com.codename1.demos.charts;


import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 * This is the main demo for the Codename One charts API which derives from the Android aChatEngine
 * open source project. This demos tries to give a birds eye view of the possibilities for charts in Codename One.
 * 
 * @author Steve Hannah
 */

public class ChartsDemo {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);
        Toolbar.setPermanentSideMenu(Display.getInstance().isTablet());
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        ChartDemosForm demos = new ChartDemosForm();
        current = demos;
        demos.show();  
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }
}
