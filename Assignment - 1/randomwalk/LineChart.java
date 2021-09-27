package edu.neu.coe.info6205.randomwalk;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

public class LineChart extends ApplicationFrame {

    public LineChart(String applicationTitle , String chartTitle, List<ListItem> randomWalkSet, int steps) {
        super(applicationTitle);

        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Steps"," Euclidean Distance",
                createDataset(randomWalkSet, steps),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private DefaultCategoryDataset createDataset(List<ListItem> randomWalkSet, int steps) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        for (ListItem randomWalkSetItem: randomWalkSet) {
            double euclideanDistance = randomWalkSetItem.getEuclideanDistance();
            int stepsInGraph = randomWalkSetItem.getSteps();
            dataset.addValue(euclideanDistance, "steps", Integer.toString(stepsInGraph));
        }

        for(int i=0;i<=steps;i=i+1){
            dataset.addValue(Math.sqrt(i), "root", Integer.toString(i));
        }

        return dataset;
    }

}
