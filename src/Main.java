/**
 * @author Adam Long
 */

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;

import javax.swing.*;
import java.util.Map;

public class Main
{
   public static void main(String[] args)
   {
      System.out.println("Process Running Simulator -- Adam Long");

      final double SERVICE_TIME = 1/0.04;

      XYSeries cpuSeries = new XYSeries("cpuSeries");
      XYSeries turnaroundSeries = new XYSeries("turnaroundSeries");
      XYSeries throughputSeries = new XYSeries("throughputSeries");
      XYSeries readyQueueSeries = new XYSeries("readyQueueSeries");

      for(double k=10.0; k<=30; k++)
      {
         Simulator sim = new Simulator(k, SERVICE_TIME);
         sim.runSimulation();

         Map<String, Double> simMap = sim.retrieveMetrics();

         cpuSeries.add(k, simMap.get("cpuUtil"));
         turnaroundSeries.add(k, simMap.get("avgTurnaroundTime"));
         throughputSeries.add(k, simMap.get("totalThroughput"));
         readyQueueSeries.add(k, simMap.get("avgReadyQueueLength"));
      }

      //JFreeChart Graphs
      //These are attached in the report. Without admin access I cannot add external JAR files to the Linux servers.
      XYDataset cpuDataset = new XYSeriesCollection(cpuSeries);
//      XYDataset turnaroundDataset = new XYSeriesCollection(turnaroundSeries);
//      XYDataset throughputDataset = new XYSeriesCollection(throughputSeries);
//      XYDataset readyQueueDataset = new XYSeriesCollection(readyQueueSeries);


      JFreeChart cpuChart = ChartFactory.createXYLineChart(
            "CPU Utilization",
            "Lambda",
            "Utilization",
            cpuDataset,
            PlotOrientation.VERTICAL,
            false,
            false,
            false
      );

//      JFreeChart turnaroundChart = ChartFactory.createXYLineChart(
//            "Average Turnaround Time",
//            "Lambda",
//            "Time",
//            turnaroundDataset,
//            PlotOrientation.VERTICAL,
//            false,
//            false,
//            false
//      );
//
//      JFreeChart throughputChart = ChartFactory.createXYLineChart(
//            "Total Throughput",
//            "Lambda",
//            "Process Count",
//            throughputDataset,
//            PlotOrientation.VERTICAL,
//            false,
//            false,
//            false
//      );
//
//      JFreeChart readyQueueChart = ChartFactory.createXYLineChart(
//            "Average Ready Queue Length",
//            "Lambda",
//            "Length",
//            readyQueueDataset,
//            PlotOrientation.VERTICAL,
//            false,
//            false,
//            false
//      );

      ChartPanel cpuChartPanel = new ChartPanel(cpuChart);
//      ChartPanel turnaroundChartPanel = new ChartPanel(turnaroundChart);
//      ChartPanel throughputChartPanel = new ChartPanel(throughputChart);
//      ChartPanel readyQueueChartPanel = new ChartPanel(readyQueueChart);

      cpuChartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
//      turnaroundChartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
//      throughputChartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
//      readyQueueChartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

      JFrame cpuFrame = new JFrame("CPU Utilization");
      cpuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      cpuFrame.setContentPane(cpuChartPanel);
      cpuFrame.pack();
      cpuFrame.setVisible(true);

//      JFrame turnaroundFrame = new JFrame("Average Turnaround Time");
//      turnaroundFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      turnaroundFrame.setContentPane(cpuChartPanel);
//      turnaroundFrame.pack();
//      turnaroundFrame.setVisible(true);
//
//      JFrame totalThroughputFrame = new JFrame("Throughput Numbers");
//      totalThroughputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      totalThroughputFrame.setContentPane(cpuChartPanel);
//      totalThroughputFrame.pack();
//      totalThroughputFrame.setVisible(true);
//
//      JFrame readyQueueFrame = new JFrame("Ready Queue Process Count");
//      readyQueueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      readyQueueFrame.setContentPane(cpuChartPanel);
//      readyQueueFrame.pack();
//      readyQueueFrame.setVisible(true);
   }
}