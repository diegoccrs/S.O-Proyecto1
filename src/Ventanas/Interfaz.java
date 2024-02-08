/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

import Companies.Company;
import Drive.Drive;
import Worker.Assembler;
import Worker.Developer;
import Worker.ProjectManager;
import java.awt.BasicStroke;
import java.awt.Color;
import static java.lang.Integer.parseInt;
import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Diego
 */
public class Interfaz extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    
    
    //ESTE METODO DEVUELVE TRUE SI SE PUEDE AUMENTAR Y DEVUELVE FALSE SI NO SE PUEDE. Solo funciona para disney
    public boolean permisoDeAumentoDeTrabajadorDisney(){
        boolean respuesta = false;
        int totalTrabajadores = Global.getDisney().cantidadTrabajadores(); 
        if(totalTrabajadores < 16){  //CARNET DE KEVIN TERMINA EN 4 POR LO TANTO -> 12+4=16
            respuesta = true;
        }
        return respuesta;
    }
    
    //ESTE METODO DEVUELVE TRUE SI SE PUEDE AUMENTAR Y DEVUELVE FALSE SI NO SE PUEDE. Solo funciona para cartoon network
    public boolean permisoDeAumentoDeTrabajadorCartoonNetwork(){
        boolean respuesta = false;
        int totalTrabajadores = Global.getCartoonNetwork().cantidadTrabajadores();
        if(totalTrabajadores < 18){ //CARNET DE DIEGO TERMINA EN 6 POR LO TANTO -> 12+6=18
            respuesta = true;
        }
        return respuesta;
    }
    
    public boolean permisoDeAumentoDeTrabajadorDisneyEnDatosIniciales(){
        boolean respuesta = false;
        int totalTrabajadores = Integer.parseInt(guionistasDisney.getText())+
                                Integer.parseInt(escenariosDisney.getText())+
                                Integer.parseInt(animadoresDisney.getText())+
                                Integer.parseInt(doblajeDisney.getText())+
                                Integer.parseInt(plottwistDisney.getText())+
                                Integer.parseInt(ensambladoresDisney.getText()); 
        if(totalTrabajadores < 16){  //CARNET DE KEVIN TERMINA EN 4 POR LO TANTO -> 12+4=16
            respuesta = true;
        }
        return respuesta;
    }
    
    public boolean permisoDeAumentoDeTrabajadorCNEnDatosIniciales(){
        boolean respuesta = false;
        int totalTrabajadores = Integer.parseInt(guionistasCN.getText())+
                                Integer.parseInt(escenariosCN.getText())+
                                Integer.parseInt(animadoresCN.getText())+
                                Integer.parseInt(doblajeCN.getText())+
                                Integer.parseInt(plottwistCN.getText())+
                                Integer.parseInt(ensambladoresCN.getText()); 
        if(totalTrabajadores < 18){  //CARNET DE DIEGO TERMINA EN 6 POR LO TANTO -> 12+6=18
            respuesta = true;
        }
        return respuesta;
    }
    
    public void cambiarPasoDeDias(int t){
        diasInicio.setText(Integer.toString(t));
        diasDisney.setText(Integer.toString(t));
        diasCN.setText(Integer.toString(t));
    }
    
    public void cambiarDiasLanzamiento(int t,int tipoCompania){
        if(tipoCompania == 0){
            diasEntregaDisney.setText(Integer.toString(t));
        }else{
            diasEntregaCartoonNetwork.setText(Integer.toString(t));
        }
    }
    
    public void cambiarPMActividad(String t,int tipoCompania){
        if(tipoCompania == 0){
            pmActividadDisney.setText(t);
        }else{
            pmCNActividadd.setText(t);
        }
    }
    
    public void cambiarFaltasPM(int t,int tipoCompania){
        if(tipoCompania == 0){
            faltasPMDisney.setText(Integer.toString(t));
        }else{
            faltasPMCN.setText(Integer.toString(t));
        }
    }
    
    public void cambiarSalarioDescontadoPM(int t,int tipoCompania){
        if(tipoCompania == 0){
            salarioDescontadoDisney.setText("$"+Integer.toString(t));
        }else{
            salarioDescontadocn23.setText("$"+Integer.toString(t));
        }
    }
    
    public void cambiarActividadDirector(String t,int tipoCompania){
        if(tipoCompania == 0){
            actividadDirectorDisney.setText(t);
        }else{
            actividadDirectorCN.setText(t);
        }
    }
    
    public void cambiarCapitulosListos(int t,int tipoCompania){
        if(tipoCompania == 0){
            capitulosListosDisney.setText(Integer.toString(t));
            estandardcinicio.setText(Integer.toString(t));
        }else{
            capitulosListosCN.setText(Integer.toString(t));
            estandarcninicio.setText(Integer.toString(t));
        }
    }
    
    public void cambiarCapitulosPlotTwistListos(int t,int tipoCompania){
        if(tipoCompania == 0){
            capitulosPlotTwistListosDisney.setText(Integer.toString(t));
            plottwistdcinicio.setText(Integer.toString(t));
        }else{
            //System.out.println(t+"hj");
            capitulosPlotTwistListosCN.setText(Integer.toString(t));
            plotwistcninicio.setText(Integer.toString(t));
        }
    }
    
    public void cambiarCostosOperativos(float t,int tipoCompania){
        DecimalFormat formato = new DecimalFormat("#,###.##");
        String numeroFormateado = formato.format((int)t);
        
        if(tipoCompania == 0){
            costosOperativosDisney.setText("$"+numeroFormateado);
            operativosdcinicio.setText("$"+numeroFormateado);
        }else{
            costosOperativosCN.setText("$"+numeroFormateado);
            operativoscninicio.setText("$"+numeroFormateado);
        }
    }
    
    public void cambiarGanancias(float t,int tipoCompania){
        DecimalFormat formato = new DecimalFormat("#,###.##");
        String numeroFormateado = formato.format((int)t);
        if(tipoCompania == 0){
            gananciasDisney.setText("$"+numeroFormateado);
            gananciasdcinicio.setText("$"+numeroFormateado);
        }else{
            gananciasCN.setText("$"+numeroFormateado);
            gananciascninicio.setText("$"+numeroFormateado);
        }
    }
    
    public void cambiarUtilidades(float t,int tipoCompania){
        DecimalFormat formato = new DecimalFormat("#,###.##");
        String numeroFormateado = formato.format((int)t);
        if(tipoCompania == 0){
            utilidadesDisney.setText("$"+numeroFormateado);
            utilidadesdcinicio.setText("$"+numeroFormateado);
        }else{
            utilidadesCN.setText("$"+numeroFormateado);
            utilidadescninicio.setText("$"+numeroFormateado);
        }
    }
    
    //DEVUELVE DATOS INICIALES CARTONNETWORK
    public String getGuionistasCN(){
        return guionistasCN.getText();
    }
    public String getEscenariosCN(){
        return escenariosCN.getText();
    }
    public String getdoblajeCN(){
        return doblajeCN.getText();
    }
    public String getAnimadoresCN(){
        return animadoresCN.getText();
    }
    public String getPlottwistCN(){
        return plottwistCN.getText();
    }
    public String getEnsambladoresCN(){
        return ensambladoresCN.getText();
    }
    
    //DEVUELVE DATOS INICIALES DISNEY
    public String getGuionistasDisney(){
        return guionistasDisney.getText();
    }
    public String getEscenariosDisney(){
        return escenariosDisney.getText();
    }
    public String getdoblajeDisney(){
        return doblajeDisney.getText();
    }
    public String getAnimadoresDisney(){
        return animadoresDisney.getText();
    }
    public String getPlottwistDisney(){
        return plottwistDisney.getText();
    }
    public String getEnsambladoresDisney(){
        return ensambladoresDisney.getText();
    }
    
    //DEVUELVE LOS DATOS ESCRITOS EN CONFIGURACION
    public String getDuracionDias(){
        return duracionDias.getText();
    }
    public String getDiasEntrega(){
        return diasEntrega.getText();
    }
    
    
    public void cambiarGuionesDisney(int t){
        GuionesDisney.setText(Integer.toString(t)); 
    }
    
    public void cambiarEscenariosDisney(int t){
        EscenariosDisney.setText(Integer.toString(t)); 
    }
    
    public void cambiarAnimacionesDisney(int t){
        AnimacionesDisney.setText(Integer.toString(t)); 
    }
    
    public void cambiarDoblajesDisney(int t){
        DoblajesDisney.setText(Integer.toString(t)); 
    }
    
    public void cambiarGuionesPlowtTistDisney(int t){
        PlotTwistDisney.setText(Integer.toString(t)); 
    }
    
    public void cambiarGuionesCartoonNetwork(int t){
        GuionesCN.setText(Integer.toString(t)); 
    }
    
    public void cambiarEscenariosCartoonNetwork(int t){
        EscenariosCN.setText(Integer.toString(t)); 
    }
    
    public void cambiarAnimacionesCartoonNetwork(int t){
        AnimacionesCN.setText(Integer.toString(t)); 
    }
    
    public void cambiarDoblajesCartoonNetwork(int t){
        DoblajesCN.setText(Integer.toString(t)); 
    }
    
    public void cambiarGuionesPlowtTistCartoonNetwork(int t){
        PlotTwistCN.setText(Integer.toString(t)); 
    }
    
    
    //Metodos que cambian la cantidad de trabajadores activos que hay en los label
    public void setGuionistasDisney(int t){
        GuionistasDisneyActivos.setText(Integer.toString(t)); 
    }
    
    public void setEscenariosDisney(int t){
        EscenariosDisneyActivos.setText(Integer.toString(t)); 
    }
    
    public void setAnimadoresDisney(int t){
        AnimadoresDisneyActivos.setText(Integer.toString(t)); 
    }
    
    public void setDoblajesDisney(int t){
        DoblajesDisneyActivos.setText(Integer.toString(t)); 
    }
    
    public void setGuionistasPlowtTistDisney(int t){
        PlotTwistDisneyActivos.setText(Integer.toString(t)); 
    }
    
    public void setEnsambladoresDisney(int t){
        EnsambladoresDisneyActivos.setText(Integer.toString(t)); 
    }
    
    public void setGuionistasCartoonNetwork(int t){
        GuionistasCartoonNetworkActivos.setText(Integer.toString(t)); 
    }
    
    public void setEscenariosCartoonNetwork(int t){
        EscenariosCartoonNetworkActivos.setText(Integer.toString(t)); 
    }
    
    public void setAnimadoresCartoonNetwork(int t){
        AnimadoresCartoonNetworkActivos.setText(Integer.toString(t)); 
    }
    
    public void setDoblajesCartoonNetwork(int t){
        DoblajesCartoonNetworkActivos.setText(Integer.toString(t)); 
    }
    
    public void setGuionesPlowtTistCartoonNetwork(int t){
        PlotTwistCartoonNetworkActivos.setText(Integer.toString(t)); 
    }
    
    public void setEnsambladoresCartoonNetwork(int t){
        EnsambladoresCartoonNetworkActivos.setText(Integer.toString(t)); 
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        diasInicio = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        utilidadescninicio = new javax.swing.JLabel();
        utilidadesdcinicio = new javax.swing.JLabel();
        operativoscninicio = new javax.swing.JLabel();
        operativosdcinicio = new javax.swing.JLabel();
        gananciascninicio = new javax.swing.JLabel();
        gananciasdcinicio = new javax.swing.JLabel();
        estandarcninicio = new javax.swing.JLabel();
        estandardcinicio = new javax.swing.JLabel();
        plotwistcninicio = new javax.swing.JLabel();
        plottwistdcinicio = new javax.swing.JLabel();
        jButton54 = new javax.swing.JButton();
        salarioDescontadoCN2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        PlotTwistCartoonNetworkActivos = new javax.swing.JLabel();
        AnimadoresCartoonNetworkActivos = new javax.swing.JLabel();
        EscenariosCartoonNetworkActivos = new javax.swing.JLabel();
        DoblajesCartoonNetworkActivos = new javax.swing.JLabel();
        GuionistasCartoonNetworkActivos = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        EnsambladoresCartoonNetworkActivos = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        diasEntregaCartoonNetwork = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        GuionesCN = new javax.swing.JLabel();
        EscenariosCN = new javax.swing.JLabel();
        PlotTwistCN = new javax.swing.JLabel();
        DoblajesCN = new javax.swing.JLabel();
        AnimacionesCN = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        costosOperativosCN = new javax.swing.JLabel();
        utilidadesCN = new javax.swing.JLabel();
        gananciasCN = new javax.swing.JLabel();
        pmCNActividad = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        actividadDirectorCN = new javax.swing.JLabel();
        capitulosListosCN = new javax.swing.JLabel();
        faltasPMCN = new javax.swing.JLabel();
        pmCNActividadd = new javax.swing.JLabel();
        salarioDescontadocn23 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        capitulosPlotTwistListosCN = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        diasCN = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pmDisneyActividad2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        PlotTwistDisneyActivos = new javax.swing.JLabel();
        AnimadoresDisneyActivos = new javax.swing.JLabel();
        EscenariosDisneyActivos = new javax.swing.JLabel();
        DoblajesDisneyActivos = new javax.swing.JLabel();
        GuionistasDisneyActivos = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        EnsambladoresDisneyActivos = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        diasEntregaDisney = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        GuionesDisney = new javax.swing.JLabel();
        EscenariosDisney = new javax.swing.JLabel();
        PlotTwistDisney = new javax.swing.JLabel();
        DoblajesDisney = new javax.swing.JLabel();
        AnimacionesDisney = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        costosOperativosDisney = new javax.swing.JLabel();
        gananciasDisney = new javax.swing.JLabel();
        utilidadesDisney = new javax.swing.JLabel();
        pmActividadDisney = new javax.swing.JLabel();
        faltasPMDisney = new javax.swing.JLabel();
        salarioDescontadoDisney = new javax.swing.JLabel();
        actividadDirectorDisney = new javax.swing.JLabel();
        capitulosListosDisney = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        capitulosPlotTwistListosDisney = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        diasDisney = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        plottwistCN = new javax.swing.JLabel();
        animadoresCN = new javax.swing.JLabel();
        escenariosCN = new javax.swing.JLabel();
        doblajeCN = new javax.swing.JLabel();
        guionistasCN = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        ensambladoresCN = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        plottwistDisney = new javax.swing.JLabel();
        animadoresDisney = new javax.swing.JLabel();
        escenariosDisney = new javax.swing.JLabel();
        doblajeDisney = new javax.swing.JLabel();
        guionistasDisney = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        ensambladoresDisney = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        diasEntrega = new javax.swing.JLabel();
        duracionDias = new javax.swing.JLabel();
        jButton53 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel48.setText("DIAS:");
        jPanel1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        diasInicio.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        diasInicio.setText("0");
        jPanel1.add(diasInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        jLabel70.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel70.setText("ESTUDIOS DE ANIMACION EN VENEZUELA");
        jPanel1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 990, 70));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel54.setText("CARTOON NETWORK");
        jPanel1.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel64.setText("DISNEY CHANNEL");
        jPanel1.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel65.setText("Utilidades");
        jPanel1.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, -1, -1));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel67.setText("Costos Operativos");
        jPanel1.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, -1, -1));

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel68.setText("Ganancias en bruto");
        jPanel1.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 180, -1, -1));

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel74.setText("Capitulos Estandar ");
        jPanel1.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, -1, -1));

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel75.setText("Capitulos Plot Twist");
        jPanel1.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 180, -1, -1));

        utilidadescninicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        utilidadescninicio.setText("0");
        jPanel1.add(utilidadescninicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, -1, -1));

        utilidadesdcinicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        utilidadesdcinicio.setText("0");
        jPanel1.add(utilidadesdcinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, -1, -1));

        operativoscninicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        operativoscninicio.setText("0");
        jPanel1.add(operativoscninicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, -1, -1));

        operativosdcinicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        operativosdcinicio.setText("0");
        jPanel1.add(operativosdcinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, -1, -1));

        gananciascninicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gananciascninicio.setText("0");
        jPanel1.add(gananciascninicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, -1, -1));

        gananciasdcinicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gananciasdcinicio.setText("0");
        jPanel1.add(gananciasdcinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, -1, -1));

        estandarcninicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        estandarcninicio.setText("0");
        jPanel1.add(estandarcninicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 240, -1, -1));

        estandardcinicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        estandardcinicio.setText("0");
        jPanel1.add(estandardcinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 370, -1, -1));

        plotwistcninicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        plotwistcninicio.setText("0");
        jPanel1.add(plotwistcninicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 240, -1, -1));

        plottwistdcinicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        plottwistdcinicio.setText("0");
        jPanel1.add(plottwistdcinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 370, -1, -1));

        jButton54.setText("GRAFICO UTILIDAD EN TIEMPO REAL");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton54, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 500, -1, -1));

        jTabbedPane1.addTab("Inicio", jPanel1);

        salarioDescontadoCN2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("TRABAJADORES");
        salarioDescontadoCN2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel5.setText("GUIONISTAS ");
        salarioDescontadoCN2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel7.setText("DISEÑADORES DE ESCENARIOS");
        salarioDescontadoCN2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel9.setText("ANIMADORES DE PERSONAJES");
        salarioDescontadoCN2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel11.setText("ACTORES DE DOBLAJE");
        salarioDescontadoCN2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel13.setText("GUIONISTAS DE PLOT TWIST");
        salarioDescontadoCN2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jButton1.setText("-");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        jButton2.setText("-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        jButton3.setText("-");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, -1, -1));

        jButton5.setText("-");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, -1, -1));

        jButton11.setText("+");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, -1));

        jButton12.setText("+");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        jButton13.setText("+");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, -1));

        jButton14.setText("+");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, -1, -1));

        jButton15.setText("+");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, -1, -1));

        jLabel43.setText("ACTIVOS");
        salarioDescontadoCN2.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        PlotTwistCartoonNetworkActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PlotTwistCartoonNetworkActivos.setText("0");
        salarioDescontadoCN2.add(PlotTwistCartoonNetworkActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 30, -1));

        AnimadoresCartoonNetworkActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AnimadoresCartoonNetworkActivos.setText("0");
        salarioDescontadoCN2.add(AnimadoresCartoonNetworkActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 30, -1));

        EscenariosCartoonNetworkActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EscenariosCartoonNetworkActivos.setText("0");
        salarioDescontadoCN2.add(EscenariosCartoonNetworkActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 30, -1));

        DoblajesCartoonNetworkActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        DoblajesCartoonNetworkActivos.setText("0");
        salarioDescontadoCN2.add(DoblajesCartoonNetworkActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 30, -1));

        GuionistasCartoonNetworkActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        GuionistasCartoonNetworkActivos.setText("0");
        salarioDescontadoCN2.add(GuionistasCartoonNetworkActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 30, -1));

        jLabel14.setText("ENSAMBLADORES");
        salarioDescontadoCN2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jButton6.setText("-");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, -1, -1));

        jButton16.setText("+");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        salarioDescontadoCN2.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, -1, -1));

        EnsambladoresCartoonNetworkActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EnsambladoresCartoonNetworkActivos.setText("0");
        salarioDescontadoCN2.add(EnsambladoresCartoonNetworkActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 30, -1));

        jLabel2.setText("CAPACIDAD DE DRIVE");
        salarioDescontadoCN2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jLabel4.setText("ACTUAL");
        salarioDescontadoCN2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, -1, -1));

        jLabel6.setText("MAXIMA");
        salarioDescontadoCN2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        jLabel32.setText("PROJECT MANAGER");
        salarioDescontadoCN2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel35.setText("SALARIO DESCONTADO");
        salarioDescontadoCN2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

        jLabel36.setText("FALTAS");
        salarioDescontadoCN2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, -1, -1));

        jLabel37.setText("ACTIVIDAD");
        salarioDescontadoCN2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        jLabel12.setText("DIAS PARA LA ENTREGA");
        salarioDescontadoCN2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 480, -1, -1));

        diasEntregaCartoonNetwork.setText("0");
        salarioDescontadoCN2.add(diasEntregaCartoonNetwork, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 500, 50, 20));

        jLabel98.setText("25");
        salarioDescontadoCN2.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 20, 20));

        jLabel99.setText("20");
        salarioDescontadoCN2.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 20, 20));

        jLabel100.setText("10");
        salarioDescontadoCN2.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 20, 20));

        jLabel101.setText("35");
        salarioDescontadoCN2.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 20, 20));

        jLabel102.setText("55");
        salarioDescontadoCN2.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 20, 20));

        GuionesCN.setText("0");
        salarioDescontadoCN2.add(GuionesCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 20, 20));

        EscenariosCN.setText("0");
        salarioDescontadoCN2.add(EscenariosCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 20, 20));

        PlotTwistCN.setText("0");
        salarioDescontadoCN2.add(PlotTwistCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, 20, 20));

        DoblajesCN.setText("0");
        salarioDescontadoCN2.add(DoblajesCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 20, 20));

        AnimacionesCN.setText("0");
        salarioDescontadoCN2.add(AnimacionesCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 20, 20));

        jLabel55.setText("COSTOS OPERATIVOS");
        salarioDescontadoCN2.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, -1, -1));

        jLabel56.setText("GANANCIAS EN BRUTO");
        salarioDescontadoCN2.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 530, -1, -1));

        jLabel61.setText("UTILIDADES");
        salarioDescontadoCN2.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 480, -1, -1));

        costosOperativosCN.setText("0");
        salarioDescontadoCN2.add(costosOperativosCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 450, -1, -1));

        utilidadesCN.setText("0");
        salarioDescontadoCN2.add(utilidadesCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 500, -1, -1));

        gananciasCN.setText("0");
        salarioDescontadoCN2.add(gananciasCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 550, -1, -1));
        salarioDescontadoCN2.add(pmCNActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, -1, -1));

        jLabel71.setText("ACTIVIDAD");
        salarioDescontadoCN2.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, -1, -1));

        jLabel72.setText("DIRECTOR");
        salarioDescontadoCN2.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, -1, -1));

        jLabel73.setText("CAPITULOS ESTANDAR LISTOS");
        salarioDescontadoCN2.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, -1, -1));

        actividadDirectorCN.setText("0");
        salarioDescontadoCN2.add(actividadDirectorCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, -1, -1));

        capitulosListosCN.setText("0");
        salarioDescontadoCN2.add(capitulosListosCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, -1, -1));

        faltasPMCN.setText("0");
        salarioDescontadoCN2.add(faltasPMCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, -1, -1));

        pmCNActividadd.setText("0");
        salarioDescontadoCN2.add(pmCNActividadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, -1));

        salarioDescontadocn23.setText("0");
        salarioDescontadoCN2.add(salarioDescontadocn23, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, -1, -1));

        jLabel34.setText("CAPITULOS PLOTTWIST  LISTOS");
        salarioDescontadoCN2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, -1, -1));

        capitulosPlotTwistListosCN.setText("0");
        salarioDescontadoCN2.add(capitulosPlotTwistListosCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 450, -1, -1));

        jLabel1.setText("DIAS:");
        salarioDescontadoCN2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 540, -1, -1));

        diasCN.setText("0");
        salarioDescontadoCN2.add(diasCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 560, -1, -1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/adventure-time-cartoon-network-characters-5wjyuuwzp2po3amt.jpg"))); // NOI18N
        salarioDescontadoCN2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1150, 620));

        jTabbedPane1.addTab("Cartoon Network", salarioDescontadoCN2);

        pmDisneyActividad2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("TRABAJADORES");
        pmDisneyActividad2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, -1, -1));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("GUIONISTAS ");
        pmDisneyActividad2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, -1, -1));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("DISEÑADORES DE ESCENARIOS");
        pmDisneyActividad2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, -1, -1));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("ANIMADORES DE PERSONAJES");
        pmDisneyActividad2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, -1, -1));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("ACTORES DE DOBLAJE");
        pmDisneyActividad2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, -1, -1));

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("GUIONISTAS DE PLOT TWIST");
        pmDisneyActividad2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, -1, -1));

        jButton7.setText("-");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, -1, -1));

        jButton8.setText("-");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 120, -1, -1));

        jButton9.setText("-");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, -1, -1));

        jButton10.setText("-");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, -1, -1));

        jButton17.setText("-");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, -1, -1));

        jButton18.setText("+");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, -1, -1));

        jButton19.setText("+");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, -1, -1));

        jButton20.setText("+");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, -1, -1));

        jButton21.setText("+");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 200, -1, -1));

        jButton22.setText("+");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, -1, -1));

        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("ACTIVOS");
        pmDisneyActividad2.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, -1, -1));

        PlotTwistDisneyActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PlotTwistDisneyActivos.setForeground(new java.awt.Color(255, 255, 255));
        PlotTwistDisneyActivos.setText("0");
        pmDisneyActividad2.add(PlotTwistDisneyActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, 20, -1));

        AnimadoresDisneyActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AnimadoresDisneyActivos.setForeground(new java.awt.Color(255, 255, 255));
        AnimadoresDisneyActivos.setText("0");
        pmDisneyActividad2.add(AnimadoresDisneyActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 160, 20, -1));

        EscenariosDisneyActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EscenariosDisneyActivos.setForeground(new java.awt.Color(255, 255, 255));
        EscenariosDisneyActivos.setText("0");
        pmDisneyActividad2.add(EscenariosDisneyActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 120, 20, -1));

        DoblajesDisneyActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        DoblajesDisneyActivos.setForeground(new java.awt.Color(255, 255, 255));
        DoblajesDisneyActivos.setText("0");
        pmDisneyActividad2.add(DoblajesDisneyActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 200, 20, -1));

        GuionistasDisneyActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        GuionistasDisneyActivos.setForeground(new java.awt.Color(255, 255, 255));
        GuionistasDisneyActivos.setText("0");
        pmDisneyActividad2.add(GuionistasDisneyActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 20, -1));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("ENSAMBLADORES");
        pmDisneyActividad2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, -1, -1));

        jButton23.setText("-");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 300, -1, -1));

        jButton24.setText("+");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        pmDisneyActividad2.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 300, -1, -1));

        EnsambladoresDisneyActivos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EnsambladoresDisneyActivos.setForeground(new java.awt.Color(255, 255, 255));
        EnsambladoresDisneyActivos.setText("0");
        pmDisneyActividad2.add(EnsambladoresDisneyActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, 20, -1));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("COSTOS OPERATIVOS");
        pmDisneyActividad2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 310, -1, -1));

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ACTUAL");
        pmDisneyActividad2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 50, -1, -1));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("MAXIMA");
        pmDisneyActividad2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 50, -1, -1));

        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("PROJECT MANAGER");
        pmDisneyActividad2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, -1, -1));

        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("ACTIVIDAD");
        pmDisneyActividad2.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 390, -1, -1));

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("SALARIO DESCONTADO");
        pmDisneyActividad2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 500, -1, -1));

        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("FALTAS");
        pmDisneyActividad2.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 440, -1, -1));

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("DIRECTOR");
        pmDisneyActividad2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 360, -1, -1));

        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("ACTIVIDAD");
        pmDisneyActividad2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, -1, -1));

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("CAPITULOS ESTANDAR  LISTOS");
        pmDisneyActividad2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 440, -1, -1));

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("DIAS PARA LA ENTREGA");
        pmDisneyActividad2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 490, -1, -1));

        diasEntregaDisney.setForeground(new java.awt.Color(255, 255, 255));
        diasEntregaDisney.setText("0");
        pmDisneyActividad2.add(diasEntregaDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 510, 50, 20));

        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("25");
        pmDisneyActividad2.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 80, 20, 20));

        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("20");
        pmDisneyActividad2.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 120, 20, 20));

        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("10");
        pmDisneyActividad2.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 240, 20, 20));

        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("35");
        pmDisneyActividad2.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 200, 20, 20));

        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("55");
        pmDisneyActividad2.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 160, 20, 20));

        GuionesDisney.setForeground(new java.awt.Color(255, 255, 255));
        GuionesDisney.setText("0");
        pmDisneyActividad2.add(GuionesDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 80, 20, 20));

        EscenariosDisney.setForeground(new java.awt.Color(255, 255, 255));
        EscenariosDisney.setText("0");
        pmDisneyActividad2.add(EscenariosDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 120, 20, 20));

        PlotTwistDisney.setForeground(new java.awt.Color(255, 255, 255));
        PlotTwistDisney.setText("0");
        pmDisneyActividad2.add(PlotTwistDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 240, 20, 20));

        DoblajesDisney.setForeground(new java.awt.Color(255, 255, 255));
        DoblajesDisney.setText("0");
        pmDisneyActividad2.add(DoblajesDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 200, 20, 20));

        AnimacionesDisney.setForeground(new java.awt.Color(255, 255, 255));
        AnimacionesDisney.setText("0");
        pmDisneyActividad2.add(AnimacionesDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 160, 20, 20));

        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("GANANCIAS EN BRUTO");
        pmDisneyActividad2.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 420, -1, -1));

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("UTILIDADES");
        pmDisneyActividad2.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 370, -1, -1));

        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("CAPACIDAD DE DRIVE");
        pmDisneyActividad2.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, -1, -1));

        costosOperativosDisney.setForeground(new java.awt.Color(255, 255, 255));
        costosOperativosDisney.setText("0");
        pmDisneyActividad2.add(costosOperativosDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 330, -1, -1));

        gananciasDisney.setForeground(new java.awt.Color(255, 255, 255));
        gananciasDisney.setText("0");
        pmDisneyActividad2.add(gananciasDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 450, 100, -1));

        utilidadesDisney.setForeground(new java.awt.Color(255, 255, 255));
        utilidadesDisney.setText("0");
        pmDisneyActividad2.add(utilidadesDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 390, -1, -1));

        pmActividadDisney.setForeground(new java.awt.Color(255, 255, 255));
        pmActividadDisney.setText("0");
        pmDisneyActividad2.add(pmActividadDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 410, -1, -1));

        faltasPMDisney.setForeground(new java.awt.Color(255, 255, 255));
        faltasPMDisney.setText("0");
        pmDisneyActividad2.add(faltasPMDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, -1, -1));

        salarioDescontadoDisney.setForeground(new java.awt.Color(255, 255, 255));
        salarioDescontadoDisney.setText("0");
        pmDisneyActividad2.add(salarioDescontadoDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 530, -1, -1));

        actividadDirectorDisney.setForeground(new java.awt.Color(255, 255, 255));
        actividadDirectorDisney.setText("0");
        pmDisneyActividad2.add(actividadDirectorDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 410, -1, -1));

        capitulosListosDisney.setForeground(new java.awt.Color(255, 255, 255));
        capitulosListosDisney.setText("0");
        pmDisneyActividad2.add(capitulosListosDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, -1, -1));

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("CAPITULOS PLOTTWIST  LISTOS");
        pmDisneyActividad2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 500, -1, -1));

        capitulosPlotTwistListosDisney.setForeground(new java.awt.Color(255, 255, 255));
        capitulosPlotTwistListosDisney.setText("0");
        pmDisneyActividad2.add(capitulosPlotTwistListosDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 530, 10, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("DIAS:");
        pmDisneyActividad2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 550, -1, -1));

        diasDisney.setForeground(new java.awt.Color(255, 255, 255));
        diasDisney.setText("0");
        pmDisneyActividad2.add(diasDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 570, -1, -1));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disney fondo.jpeg"))); // NOI18N
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pmDisneyActividad2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1110, 610));

        jTabbedPane1.addTab("Disney Channel ", pmDisneyActividad2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setText("TRABAJADORES");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, -1));

        jLabel31.setText("GUIONISTAS ");
        jPanel5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, -1, -1));

        jLabel42.setText("DISEÑADORES DE ESCENARIOS");
        jPanel5.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, -1, -1));

        jLabel57.setText("ANIMADORES DE PERSONAJES");
        jPanel5.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, -1, -1));

        jLabel58.setText("ACTORES DE DOBLAJE");
        jPanel5.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, -1, -1));

        jLabel59.setText("GUIONISTAS DE PLOT TWIST");
        jPanel5.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, -1, -1));

        jButton25.setText("-");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, -1, -1));

        jButton26.setText("-");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, -1, -1));

        jButton27.setText("-");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, -1, -1));

        jButton28.setText("-");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, -1, -1));

        jButton29.setText("-");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, -1, -1));

        jButton30.setText("+");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, -1, -1));

        jButton31.setText("+");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, -1, -1));

        jButton32.setText("+");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 340, -1, -1));

        jButton33.setText("+");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, -1, -1));

        jButton34.setText("+");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, -1, -1));

        jLabel60.setText("ACTIVOS");
        jPanel5.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, -1, -1));

        plottwistCN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        plottwistCN.setText("1");
        jPanel5.add(plottwistCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 420, 20, -1));

        animadoresCN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        animadoresCN.setText("1");
        jPanel5.add(animadoresCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 20, -1));

        escenariosCN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        escenariosCN.setText("1");
        jPanel5.add(escenariosCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 20, -1));

        doblajeCN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        doblajeCN.setText("1");
        jPanel5.add(doblajeCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 380, 20, -1));

        guionistasCN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        guionistasCN.setText("1");
        jPanel5.add(guionistasCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 20, -1));

        jLabel66.setText("ENSAMBLADORES");
        jPanel5.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 480, -1, -1));

        jButton35.setText("-");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 480, -1, -1));

        jButton36.setText("+");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, -1, -1));

        ensambladoresCN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ensambladoresCN.setText("1");
        jPanel5.add(ensambladoresCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 480, 20, -1));

        jLabel78.setText("DIAS PARA LA ENTREGA");
        jPanel5.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 570, -1, -1));

        jLabel80.setText("TRABAJADORES");
        jPanel5.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, -1, -1));

        jLabel81.setText("GUIONISTAS ");
        jPanel5.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 260, -1, -1));

        jLabel82.setText("DISEÑADORES DE ESCENARIOS");
        jPanel5.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, -1, -1));

        jLabel83.setText("ANIMADORES DE PERSONAJES");
        jPanel5.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 340, -1, -1));

        jLabel84.setText("ACTORES DE DOBLAJE");
        jPanel5.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 380, -1, -1));

        jLabel85.setText("GUIONISTAS DE PLOT TWIST");
        jPanel5.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, -1, -1));

        jButton37.setText("-");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 260, -1, -1));

        jButton38.setText("-");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 300, -1, -1));

        jButton39.setText("-");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 340, -1, -1));

        jButton40.setText("-");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 380, -1, -1));

        jButton41.setText("-");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 420, -1, -1));

        jButton42.setText("+");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 260, -1, -1));

        jButton43.setText("+");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 300, -1, -1));

        jButton44.setText("+");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 340, -1, -1));

        jButton45.setText("+");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 380, -1, -1));

        jButton46.setText("+");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 420, -1, -1));

        jLabel86.setText("ACTIVOS");
        jPanel5.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 220, -1, -1));

        plottwistDisney.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        plottwistDisney.setText("1");
        jPanel5.add(plottwistDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 420, 20, -1));

        animadoresDisney.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        animadoresDisney.setText("1");
        jPanel5.add(animadoresDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 340, 20, -1));

        escenariosDisney.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        escenariosDisney.setText("1");
        jPanel5.add(escenariosDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 300, 20, -1));

        doblajeDisney.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        doblajeDisney.setText("1");
        jPanel5.add(doblajeDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 380, 20, -1));

        guionistasDisney.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        guionistasDisney.setText("1");
        jPanel5.add(guionistasDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 260, 20, -1));

        jLabel92.setText("ENSAMBLADORES");
        jPanel5.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 480, -1, -1));

        jButton47.setText("-");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 480, -1, -1));

        jButton48.setText("+");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton48, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 480, -1, -1));

        ensambladoresDisney.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ensambladoresDisney.setText("1");
        jPanel5.add(ensambladoresDisney, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 480, 20, -1));

        jLabel94.setText("DURACION DE DIAS  EN SEGUNDOS");
        jPanel5.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 570, -1, -1));

        jLabel69.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel69.setText("CONFIGURACION");
        jPanel5.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 470, 70));

        jButton49.setText("-");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton49, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 570, -1, -1));

        jButton50.setText("-");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton50, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 570, -1, -1));

        jButton51.setText("+");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton51, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 570, -1, -1));

        jButton52.setText("+");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton52, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 570, -1, -1));

        diasEntrega.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        diasEntrega.setText("0");
        jPanel5.add(diasEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 570, 20, -1));

        duracionDias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        duracionDias.setText("0");
        jPanel5.add(duracionDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 570, 20, -1));

        jButton53.setText("GUARDAR");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton53, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 550, 120, 40));

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disney logo 1.jpg"))); // NOI18N
        jPanel5.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 400, 200));

        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cartoon n logo.png"))); // NOI18N
        jPanel5.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 340, 160));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Configuracion", jPanel4);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
        Global.getFunciones().escribir_txt();
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // TODO add your handling code here:
        diasEntrega.setText(Integer.toString(Integer.parseInt(diasEntrega.getText()) + 1));
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        // TODO add your handling code here:
        duracionDias.setText(Integer.toString(Integer.parseInt(duracionDias.getText()) + 1));
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(diasEntrega.getText()) > 1){
            diasEntrega.setText(Integer.toString(Integer.parseInt(diasEntrega.getText()) - 1));
        }else{
            JOptionPane.showMessageDialog(null, "La duracion de dias no puede ser 0");
        }
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(duracionDias.getText()) > 1){
            duracionDias.setText(Integer.toString(Integer.parseInt(duracionDias.getText()) - 1));
        }else{
            JOptionPane.showMessageDialog(null, "La duracion de dias no puede ser 0");
        }
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisneyEnDatosIniciales() == true){
            ensambladoresDisney.setText(Integer.toString(Integer.parseInt(ensambladoresDisney.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Disney es 16");
        }
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(ensambladoresDisney.getText()) > 1){
            ensambladoresDisney.setText(Integer.toString(Integer.parseInt(ensambladoresDisney.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisneyEnDatosIniciales() == true){
            plottwistDisney.setText(Integer.toString(Integer.parseInt(plottwistDisney.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Disney es 16");
        }
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisneyEnDatosIniciales()== true){
            doblajeDisney.setText(Integer.toString(Integer.parseInt(doblajeDisney.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Disney es 16");
        }
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisneyEnDatosIniciales() == true){
            animadoresDisney.setText(Integer.toString(Integer.parseInt(animadoresDisney.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Disney es 16");
        }
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisneyEnDatosIniciales() == true){
            escenariosDisney.setText(Integer.toString(Integer.parseInt(escenariosDisney.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Disney es 16");
        }
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisneyEnDatosIniciales() == true){
            guionistasDisney.setText(Integer.toString(Integer.parseInt(guionistasDisney.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Disney es 16");
        }
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(plottwistDisney.getText()) > 1){
            plottwistDisney.setText(Integer.toString(Integer.parseInt(plottwistDisney.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(doblajeDisney.getText()) > 1){
            doblajeDisney.setText(Integer.toString(Integer.parseInt(doblajeDisney.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(animadoresDisney.getText()) > 1){
            animadoresDisney.setText(Integer.toString(Integer.parseInt(animadoresDisney.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(escenariosDisney.getText()) > 1){
            escenariosDisney.setText(Integer.toString(Integer.parseInt(escenariosDisney.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(guionistasDisney.getText()) > 1){
            guionistasDisney.setText(Integer.toString(Integer.parseInt(guionistasDisney.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCNEnDatosIniciales() == true){
            ensambladoresCN.setText(Integer.toString(Integer.parseInt(ensambladoresCN.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Cartoon Network es 18");
        }
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(ensambladoresCN.getText()) > 1){
            ensambladoresCN.setText(Integer.toString(Integer.parseInt(ensambladoresCN.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCNEnDatosIniciales() == true){
            plottwistCN.setText(Integer.toString(Integer.parseInt(plottwistCN.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Cartoon Network es 18");
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCNEnDatosIniciales() == true){
            doblajeCN.setText(Integer.toString(Integer.parseInt(doblajeCN.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Cartoon Network es 18");
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCNEnDatosIniciales() == true){
            animadoresCN.setText(Integer.toString(Integer.parseInt(animadoresCN.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Cartoon Network es 18");
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCNEnDatosIniciales() == true){
            escenariosCN.setText(Integer.toString(Integer.parseInt(escenariosCN.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Cartoon Network es 18");
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCNEnDatosIniciales() == true){
            guionistasCN.setText(Integer.toString(Integer.parseInt(guionistasCN.getText())+1));
        }else{
            JOptionPane.showMessageDialog(null, "El maximo de trabajadores para Cartoon Network es 18");
        }
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(plottwistCN.getText()) > 1){
            plottwistCN.setText(Integer.toString(Integer.parseInt(plottwistCN.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(doblajeCN.getText()) > 1){
            doblajeCN.setText(Integer.toString(Integer.parseInt(doblajeCN.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(animadoresCN.getText()) > 1){
            animadoresCN.setText(Integer.toString(Integer.parseInt(animadoresCN.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(escenariosCN.getText()) > 1){
            escenariosCN.setText(Integer.toString(Integer.parseInt(escenariosCN.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(guionistasCN.getText()) > 1){
            guionistasCN.setText(Integer.toString(Integer.parseInt(guionistasCN.getText())-1));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que tener al menos 1 trabajador de cada tipo");
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisney() == true){
            Assembler ensambladorDCNuevo = new Assembler(5,50, Global.getMutexDisney(),Global.getDisney()); //creas al nuevo ensamblador
            Global.getDisney().getEnsambladores().insertFinal(ensambladorDCNuevo);//lo agregas a la lista de guionistas de disney
            EnsambladoresDisneyActivos.setText(Integer.toString(Global.getDisney().getEnsambladores().getSize()));//actualizamos el label de la interfaz
            ensambladorDCNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro ensamblador. El total de trabajadores es 16(el maximo)");
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        if(Global.getDisney().getEnsambladores().getSize() > 1){
            Assembler trabajadorDespedido = (Assembler) Global.getDisney().getEnsambladores().getTail().getElement();//agarramos al ultimo ensamblador
            Global.getDisney().getEnsambladores().deleteFinal();//eliminamos al ultimo ensamblador
            trabajadorDespedido.stop();//detenemos el hilo del ultimo ensamblador
            EnsambladoresDisneyActivos.setText(Integer.toString(Global.getDisney().getEnsambladores().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un ensamblador.Te quedarias sin ensambladores.");
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisney() == true){
            Developer guionistaPLOTTWISTDCNuevo = new Developer(4,16, Global.getMutexDisney(),Global.getDisney()); //creas al nuevo guionista
            Global.getDisney().getGuionistasPlotTwist().insertFinal(guionistaPLOTTWISTDCNuevo);//lo agregas a la lista de guionistas de disney
            PlotTwistDisneyActivos.setText(Integer.toString(Global.getDisney().getGuionistasPlotTwist().getSize()));//actualizamos el label de la interfaz
            guionistaPLOTTWISTDCNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro guionista plot twist. El total de trabajadores es 16(el maximo)");
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisney() == true){
            Developer actorDCNuevo = new Developer(3,16, Global.getMutexDisney(),Global.getDisney()); //creas al nuevo actor
            Global.getDisney().getActoresDoblaje().insertFinal(actorDCNuevo);//lo agregas a la lista de actores de disney
            DoblajesDisneyActivos.setText(Integer.toString(Global.getDisney().getActoresDoblaje().getSize()));//actualizamos el label de la interfaz
            actorDCNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro actor. El total de actores es 16(el maximo)");
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisney() == true){
            Developer animadorDCNuevo = new Developer(0,40, Global.getMutexDisney(),Global.getDisney()); //creas al nuevo animador
            Global.getDisney().getAnimadores().insertFinal(animadorDCNuevo);//lo agregas a la lista de animadores de disney
            AnimadoresDisneyActivos.setText(Integer.toString(Global.getDisney().getAnimadores().getSize()));//actualizamos el label de la interfaz
            animadorDCNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro animador. El total de trabajadores es 16(el maximo)");
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisney() == true){
            Developer diseñadorDCNuevo = new Developer(2,26, Global.getMutexDisney(),Global.getDisney()); //creas al nuevo diseñador
            Global.getDisney().getDisenadoresEscenarios().insertFinal(diseñadorDCNuevo);//lo agregas a la lista de diseñadores de disney
            EscenariosDisneyActivos.setText(Integer.toString(Global.getDisney().getDisenadoresEscenarios().getSize()));//actualizamos el label de la interfaz
            diseñadorDCNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro diseñador de escenario. El total de trabajadores es 16(el maximo)");
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorDisney() == true){
            Developer guionistaDCNuevo = new Developer(1,20, Global.getMutexDisney(),Global.getDisney()); //creas al nuevo guionista
            Global.getDisney().getGuionistas().insertFinal(guionistaDCNuevo);//lo agregas a la lista de guionistas de disney
            GuionistasDisneyActivos.setText(Integer.toString(Global.getDisney().getGuionistas().getSize()));//actualizamos el label de la interfaz
            guionistaDCNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro guionista. El total de trabajadores es 16(el maximo)");
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        if(Global.getDisney().getGuionistasPlotTwist().getSize() > 1){
            Developer trabajadorDespedido = (Developer) Global.getDisney().getGuionistasPlotTwist().getTail().getElement();//agarramos al ultimo guionista
            Global.getDisney().getGuionistasPlotTwist().deleteFinal();//eliminamos al ultimo guionista
            trabajadorDespedido.stop();//detenemos el hilo del ultimo guionista
            PlotTwistDisneyActivos.setText(Integer.toString(Global.getDisney().getGuionistasPlotTwist().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un guionista plot twist.Te quedarias sin guionistas plot twist.");
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if(Global.getDisney().getActoresDoblaje().getSize() > 1){
            Developer trabajadorDespedido = (Developer) Global.getDisney().getActoresDoblaje().getTail().getElement();//agarramos al ultimo actor
            Global.getDisney().getActoresDoblaje().deleteFinal();//eliminamos al ultimo actor
            trabajadorDespedido.stop();//detenemos el hilo del ultimo actor
            DoblajesDisneyActivos.setText(Integer.toString(Global.getDisney().getActoresDoblaje().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un actor de doblaje.Te quedarias sin actores de doblaje.");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if(Global.getDisney().getAnimadores().getSize() > 1){
            Developer trabajadorDespedido = (Developer) Global.getDisney().getAnimadores().getTail().getElement();//agarramos al ultimo animador
            Global.getDisney().getAnimadores().deleteFinal();//eliminamos al ultimo animador
            trabajadorDespedido.stop();//detenemos el hilo del ultimo animador
            AnimadoresDisneyActivos.setText(Integer.toString(Global.getDisney().getAnimadores().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un animador.Te quedarias sin animadores.");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if(Global.getDisney().getDisenadoresEscenarios().getSize() > 1){
            Developer trabajadorDespedido = (Developer) Global.getDisney().getDisenadoresEscenarios().getTail().getElement();//agarramos al ultimo diseñador
            Global.getDisney().getDisenadoresEscenarios().deleteFinal();//eliminamos al ultimo diseñador
            trabajadorDespedido.stop();//detenemos el hilo del ultimo diseñador
            EscenariosDisneyActivos.setText(Integer.toString(Global.getDisney().getDisenadoresEscenarios().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un diseñador de escenario.Te quedarias sin diseñadores de escenario.");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if(Global.getDisney().getGuionistas().getSize() > 1){
            Developer trabajadorDespedido = (Developer) Global.getDisney().getGuionistas().getTail().getElement();//agarramos al ultimo guionista
            Global.getDisney().getGuionistas().deleteFinal();//eliminamos al ultimo guionista
            trabajadorDespedido.stop();//detenemos el hilo del ultimo guionista
            GuionistasDisneyActivos.setText(Integer.toString(Global.getDisney().getGuionistas().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un guionista.Te quedarias sin guionistas.");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCartoonNetwork() == true){
            Assembler ensambladorCNNuevo = new Assembler(5,50, Global.getMutexCartoonNetwork(),Global.getCartoonNetwork()); //creas al nuevo ensambladores
            Global.getCartoonNetwork().getEnsambladores().insertFinal(ensambladorCNNuevo);//lo agregas a la lista de ensambladores de cartoon network
            EnsambladoresCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getEnsambladores().getSize()));//actualizamos el label de la interfaz
            ensambladorCNNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro ensamblador. El total de trabajadores es 18(el maximo)");
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(Global.getCartoonNetwork().getEnsambladores().getSize() > 1){
            Assembler trabajadorDespedido = (Assembler) Global.getCartoonNetwork().getEnsambladores().getTail().getElement();//agarramos al ultimo ensamblador
            Global.getCartoonNetwork().getEnsambladores().deleteFinal();//eliminamos al ultimo ensamblador
            trabajadorDespedido.stop();//detenemos el hilo del ultimo ensamblador
            EnsambladoresCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getEnsambladores().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un ensamblador.Te quedarias sin ensambladores.");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCartoonNetwork() == true){
            Developer guionistaPLOTTWISTCNNuevo = new Developer(4,34, Global.getMutexCartoonNetwork(),Global.getCartoonNetwork()); //creas al nuevo guionista
            Global.getCartoonNetwork().getGuionistasPlotTwist().insertFinal(guionistaPLOTTWISTCNNuevo);//lo agregas a la lista de guionistas de cartoon network
            PlotTwistCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getGuionistasPlotTwist().getSize()));//actualizamos el label de la interfaz
            guionistaPLOTTWISTCNNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro guionista plot twist. El total de trabajadores es 18(el maximo)");
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCartoonNetwork() == true){
            Developer actorCNNuevo = new Developer(3,16, Global.getMutexCartoonNetwork(),Global.getCartoonNetwork()); //creas al nuevo actor
            Global.getCartoonNetwork().getActoresDoblaje().insertFinal(actorCNNuevo);//lo agregas a la lista de actores de cartoon network
            DoblajesCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getActoresDoblaje().getSize()));//actualizamos el label de la interfaz
            actorCNNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro actor. El total de trabajadores es 18(el maximo)");
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCartoonNetwork() == true){
            Developer animadorCNNuevo = new Developer(0,40, Global.getMutexCartoonNetwork(),Global.getCartoonNetwork()); //creas al nuevo animador
            Global.getCartoonNetwork().getAnimadores().insertFinal(animadorCNNuevo);//lo agregas a la lista de animadores de cartoon network
            AnimadoresCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getAnimadores().getSize()));//actualizamos el label de la interfaz
            animadorCNNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro animador. El total de trabajadores es 18(el maximo)");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCartoonNetwork() == true){
            Developer diseñadorCNNuevo = new Developer(2,26, Global.getMutexCartoonNetwork(),Global.getCartoonNetwork()); //creas al nuevo diseñador
            Global.getCartoonNetwork().getDisenadoresEscenarios().insertFinal(diseñadorCNNuevo);//lo agregas a la lista de diseñadores de cartoon network
            EscenariosCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getDisenadoresEscenarios().getSize()));//actualizamos el label de la interfaz
            diseñadorCNNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro diseñador. El total de trabajadores es 18(el maximo)");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        if(this.permisoDeAumentoDeTrabajadorCartoonNetwork() == true){
            Developer guionistaCNNuevo = new Developer(1,20, Global.getMutexCartoonNetwork(),Global.getCartoonNetwork()); //creas al nuevo guionista
            Global.getCartoonNetwork().getGuionistas().insertFinal(guionistaCNNuevo);//lo agregas a la lista de guionistas de cartoon network
            GuionistasCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getGuionistas().getSize()));//actualizamos el label de la interfaz
            guionistaCNNuevo.start();//lo pones a trabajar
        }else{
            JOptionPane.showMessageDialog(null, "No puedes agregar otro guionista. El total de trabajadores es 18(el maximo)");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(Global.getCartoonNetwork().getGuionistasPlotTwist().getSize() > 1){
            Developer trabajadorDespedido = (Developer) Global.getCartoonNetwork().getGuionistasPlotTwist().getTail().getElement();//agarramos al ultimo guionista
            Global.getCartoonNetwork().getGuionistasPlotTwist().deleteFinal();//eliminamos al ultimo guionista
            trabajadorDespedido.stop();//detenemos el hilo del ultimo guionista
            PlotTwistCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getGuionistasPlotTwist().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un guionista plot twist.Te quedarias sin guionistas plot twist.");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(Global.getCartoonNetwork().getActoresDoblaje().getSize() > 1){
            Developer trabajadorDespedido = (Developer) Global.getCartoonNetwork().getActoresDoblaje().getTail().getElement();//agarramos al ultimo actor
            Global.getCartoonNetwork().getActoresDoblaje().deleteFinal();//eliminamos al ultimo actor
            trabajadorDespedido.stop();//detenemos el hilo del ultimo actor
            DoblajesCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getActoresDoblaje().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un actor de doblaje.Te quedarias sin actores de doblaje.");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(Global.getCartoonNetwork().getAnimadores().getSize() > 1){
            Developer trabajadorDespedido = (Developer) Global.getCartoonNetwork().getAnimadores().getTail().getElement();//agarramos al ultimo animador
            Global.getCartoonNetwork().getAnimadores().deleteFinal();//eliminamos al ultimo animador
            trabajadorDespedido.stop();//detenemos el hilo del ultimo animador
            AnimadoresCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getAnimadores().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un animador.Te quedarias sin animadores.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(Global.getCartoonNetwork().getDisenadoresEscenarios().getSize() > 1){
            Developer trabajadorDespedido = (Developer) Global.getCartoonNetwork().getDisenadoresEscenarios().getTail().getElement();//agarramos al ultimo diseñador
            Global.getCartoonNetwork().getDisenadoresEscenarios().deleteFinal();//eliminamos al ultimo diseñador
            trabajadorDespedido.stop();//detenemos el hilo del ultimo diseñador
            EscenariosCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getDisenadoresEscenarios().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un diseñador de escenario.Te quedarias sin diseñador de escenarios.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(Global.getCartoonNetwork().getGuionistas().getSize() > 1){
            Developer trabajadorDespedido = (Developer) Global.getCartoonNetwork().getGuionistas().getTail().getElement();//agarramos al ultimo guionista
            Global.getCartoonNetwork().getGuionistas().deleteFinal();//eliminamos al ultimo guionista
            trabajadorDespedido.stop();//detenemos el hilo del ultimo guionista
            GuionistasCartoonNetworkActivos.setText(Integer.toString(Global.getCartoonNetwork().getGuionistas().getSize()));//actualizamos el label de la interfaz
        }else{
            JOptionPane.showMessageDialog(null, "No puedes despedir un guionista.Te quedarias sin guionistas.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        //Informacion
        Global.getGrafico().getVentana().setVisible(true);
        
    }//GEN-LAST:event_jButton54ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AnimacionesCN;
    private javax.swing.JLabel AnimacionesDisney;
    private javax.swing.JLabel AnimadoresCartoonNetworkActivos;
    private javax.swing.JLabel AnimadoresDisneyActivos;
    private javax.swing.JLabel DoblajesCN;
    private javax.swing.JLabel DoblajesCartoonNetworkActivos;
    private javax.swing.JLabel DoblajesDisney;
    private javax.swing.JLabel DoblajesDisneyActivos;
    private javax.swing.JLabel EnsambladoresCartoonNetworkActivos;
    private javax.swing.JLabel EnsambladoresDisneyActivos;
    private javax.swing.JLabel EscenariosCN;
    private javax.swing.JLabel EscenariosCartoonNetworkActivos;
    private javax.swing.JLabel EscenariosDisney;
    private javax.swing.JLabel EscenariosDisneyActivos;
    private javax.swing.JLabel GuionesCN;
    private javax.swing.JLabel GuionesDisney;
    private javax.swing.JLabel GuionistasCartoonNetworkActivos;
    private javax.swing.JLabel GuionistasDisneyActivos;
    private javax.swing.JLabel PlotTwistCN;
    private javax.swing.JLabel PlotTwistCartoonNetworkActivos;
    private javax.swing.JLabel PlotTwistDisney;
    private javax.swing.JLabel PlotTwistDisneyActivos;
    private javax.swing.JLabel actividadDirectorCN;
    private javax.swing.JLabel actividadDirectorDisney;
    private javax.swing.JLabel animadoresCN;
    private javax.swing.JLabel animadoresDisney;
    private javax.swing.JLabel capitulosListosCN;
    private javax.swing.JLabel capitulosListosDisney;
    private javax.swing.JLabel capitulosPlotTwistListosCN;
    private javax.swing.JLabel capitulosPlotTwistListosDisney;
    private javax.swing.JLabel costosOperativosCN;
    private javax.swing.JLabel costosOperativosDisney;
    private javax.swing.JLabel diasCN;
    private javax.swing.JLabel diasDisney;
    private javax.swing.JLabel diasEntrega;
    private javax.swing.JLabel diasEntregaCartoonNetwork;
    private javax.swing.JLabel diasEntregaDisney;
    private javax.swing.JLabel diasInicio;
    private javax.swing.JLabel doblajeCN;
    private javax.swing.JLabel doblajeDisney;
    private javax.swing.JLabel duracionDias;
    private javax.swing.JLabel ensambladoresCN;
    private javax.swing.JLabel ensambladoresDisney;
    private javax.swing.JLabel escenariosCN;
    private javax.swing.JLabel escenariosDisney;
    private javax.swing.JLabel estandarcninicio;
    private javax.swing.JLabel estandardcinicio;
    private javax.swing.JLabel faltasPMCN;
    private javax.swing.JLabel faltasPMDisney;
    private javax.swing.JLabel gananciasCN;
    private javax.swing.JLabel gananciasDisney;
    private javax.swing.JLabel gananciascninicio;
    private javax.swing.JLabel gananciasdcinicio;
    private javax.swing.JLabel guionistasCN;
    private javax.swing.JLabel guionistasDisney;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel operativoscninicio;
    private javax.swing.JLabel operativosdcinicio;
    private javax.swing.JLabel plottwistCN;
    private javax.swing.JLabel plottwistDisney;
    private javax.swing.JLabel plottwistdcinicio;
    private javax.swing.JLabel plotwistcninicio;
    private javax.swing.JLabel pmActividadDisney;
    private javax.swing.JLabel pmCNActividad;
    private javax.swing.JLabel pmCNActividadd;
    private javax.swing.JPanel pmDisneyActividad2;
    private javax.swing.JPanel salarioDescontadoCN2;
    private javax.swing.JLabel salarioDescontadoDisney;
    private javax.swing.JLabel salarioDescontadocn23;
    private javax.swing.JLabel utilidadesCN;
    private javax.swing.JLabel utilidadesDisney;
    private javax.swing.JLabel utilidadescninicio;
    private javax.swing.JLabel utilidadesdcinicio;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the diasEntregaDisney
     */
    public javax.swing.JLabel getDiasEntregaDisney() {
        return diasEntregaDisney;
    }

    /**
     * @param diasEntregaDisney the diasEntregaDisney to set
     */
    public void setDiasEntregaDisney(javax.swing.JLabel diasEntregaDisney) {
        this.diasEntregaDisney = diasEntregaDisney;
    }

}
