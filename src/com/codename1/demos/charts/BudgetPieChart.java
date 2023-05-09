/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.codename1.demos.charts;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;

/**
 * Budget demo pie chart.
 */
public class BudgetPieChart extends AbstractDemoChart {

    /**
     * Returns the chart name.
     *
     * @return the chart name
     */
    public String getName() {
        return "Budget chart";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    public String getDesc() {
        return "The budget per project for this year (pie chart)";
    }

    @Override
    public Component getChartModelEditor() {
        return null;
    }

    @Override
    public String getChartTitle() {
        return "Budget";
    }

    
    private int shiftColor(int color, double factor) {
        return ColorUtil.rgb(
                (int)Math.min(ColorUtil.red(color) * factor, 255) , 
                (int)Math.min(ColorUtil.green(color) * factor, 255) ,
                (int)Math.min(ColorUtil.blue(color) * factor, 255)
        );
    }
    
    @Override
    public Component execute() {
        double[] values = new double[]{12, 14, 11, 10, 19};
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        final DefaultRenderer renderer = buildCategoryRenderer(colors);
        for (SimpleSeriesRenderer r : renderer.getSeriesRenderers()) {
            r.setGradientEnabled(true);
            r.setGradientStart(0, shiftColor(r.getColor(), 0.8));
            r.setGradientStop(0, shiftColor(r.getColor(), 1.5));
        }
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextFont(largeFont);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        initRendererer(renderer);

        final CategorySeries seriesSet = buildCategoryDataset("Project budget", values);
        final PieChart chart = new PieChart(seriesSet, renderer);
        ChartComponent comp = new ChartComponent(chart) {

            private boolean inDrag = false;

            @Override
            public void pointerPressed(int x, int y) {
                inDrag = false;
                super.pointerPressed(x, y); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void pointerDragged(int x, int y) {
                inDrag = true;
                super.pointerDragged(x, y); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected void seriesReleased(SeriesSelection sel) {

                if (inDrag) {
                    // Don't do this if it was a drag operation
                    return;
                }

                for (SimpleSeriesRenderer r : renderer.getSeriesRenderers()) {
                    r.setHighlighted(false);
                }
                SimpleSeriesRenderer r = renderer.getSeriesRendererAt(sel.getPointIndex());
                r.setHighlighted(true);

                Shape seg = chart.getSegmentShape(sel.getPointIndex());
                Rectangle bounds = seg.getBounds();
                bounds = new Rectangle(
                        bounds.getX() - 40,
                        bounds.getY() - 40,
                        bounds.getWidth() + 80,
                        bounds.getHeight() + 80
                );

                this.zoomToShapeInChartCoords(bounds, 500);

            }

        };
        comp.setZoomEnabled(true);
        comp.setPanEnabled(true);
        comp.getStyle().setBgColor(0xff0000);
        comp.getStyle().setBgTransparency(255);

        return comp;

    }

}
