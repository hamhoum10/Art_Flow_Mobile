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
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.charts.views.PointStyle;
import com.codename1.ui.Component;
import com.codename1.ui.Form;

import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import com.codename1.demos.charts.models.XYMultipleSeriesEditor;
import com.codename1.demos.charts.models.XYSeriesEditor;
import com.codename1.ui.Display;
import com.codename1.ui.Font;

/**
 * Average temperature demo chart.
 */
public class AverageCubicTemperatureChart extends AbstractDemoChart {
    private XYMultipleSeriesDataset dataSet;
    /**
     * Returns the chart name.
     *
     * @return the chart name
     */
    public String getName() {
        return "Average temperature";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    public String getDesc() {
        return "The average temperature in 4 Greek islands (cubic line chart)";
    }

    @Override
    public Component getChartModelEditor() {
        XYMultipleSeriesEditor x = new XYMultipleSeriesEditor();
        x.init(getDataSet());
        return x;
    }

    @Override
    public String getChartTitle() {
        return "Avg. Cubic Temperature";
    }
    
    private XYMultipleSeriesDataset getDataSet() {
        if(dataSet == null) {
            dataSet = createTemperatureDataset();
        }
        return dataSet;
    }

    @Override
    public Component execute() {
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.CYAN, ColorUtil.MAGENTA};
        PointStyle[] styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,
            PointStyle.TRIANGLE, PointStyle.SQUARE};
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
        }
        setChartSettings(renderer, "Average temperature", "Month", "Temperature", 0.5, 12.5, 0, 32,
                ColorUtil.LTGRAY, ColorUtil.LTGRAY);
        renderer.setXLabels(12);
        renderer.setYLabels(10);
        renderer.setShowGrid(true);
        renderer.setXLabelsAlign(Component.RIGHT);
        renderer.setYLabelsAlign(Component.RIGHT);
        renderer.setZoomButtonsVisible(true);
        renderer.setPanLimits(new double[]{-10, 20, -10, 40});
        renderer.setPanEnabled(true);
        renderer.setZoomEnabled(true);
        renderer.setZoomLimits(new double[]{-10, 20, -10, 40});
        initRendererer(renderer);
        CubicLineChart chart = new CubicLineChart(
                getDataSet(),
                renderer,
                0.33f
        );
        ChartComponent c = newChart(chart);
        return c;

    }

}
