/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

import Worker.Developer;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
 
import javax.swing.JFrame;
 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Kevin
 */
public class Grafico extends Thread{
   private JFrame ventana = new JFrame("Grafica");
    @Override
    public void run() {
        XYSeries disney = new XYSeries("DISNEY");
        disney.add(0, 0);
        XYSeries cartoonNetwork = new XYSeries("CARTOON NETWORK");
        cartoonNetwork.add(0, 0);
        //moto.add(Global.getDisney().getDrive().getContadorPasoDeLosDias()+1, Global.getDisney().getUtilidades() + 500);
        
       
     
 
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(disney);
        dataset.addSeries(cartoonNetwork);
 
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Utilidad en tiempo real", 
                "DIA", 
                "UTILIDAD", 
                dataset,
                PlotOrientation.VERTICAL, true, true, false);
 
        
        XYPlot plot = xylineChart.getXYPlot();
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        plot.setRenderer(renderer);
        
        ChartPanel panel = new ChartPanel(xylineChart);
        this.getVentana().setSize(800, 600);
 
        this.getVentana().add(panel);
        int x = 5;
        int y = 7;
        while (true) {
            x = x +5;
            y = y +7;
            try {
                this.agregarNuevoValor(disney, cartoonNetwork,(int)Global.getDisney().getDrive().getUtilidades(),Global.getDisney().getDrive().getContadorPasoDeLosDias(),(int)Global.getCartoonNetwork().getDrive().getUtilidades(),Global.getCartoonNetwork().getDrive().getContadorPasoDeLosDias());
                //this.agregarNuevoValor(disney, cartoonNetwork,x,Global.getDisney().getDrive().getContadorPasoDeLosDias(),y,Global.getCartoonNetwork().getDrive().getContadorPasoDeLosDias());
                

                //sleep(Global.getDisney().getDuracionDia());
                sleep(Global.getDisney().getDuracionDia());
            } catch (InterruptedException ex) {
                Logger.getLogger(Developer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void agregarNuevoValor(XYSeries disney,XYSeries cn,int yDisney,int xDisney,int yCN,int xCN){
        
        disney.add(xDisney, yDisney);
        cn.add(xCN, yCN);
    }

    /**
     * @return the ventana
     */
    public JFrame getVentana() {
        return ventana;
    }

    /**
     * @param ventana the ventana to set
     */
    public void setVentana(JFrame ventana) {
        this.ventana = ventana;
    }
}
