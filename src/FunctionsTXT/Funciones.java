/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FunctionsTXT;

import EDD.ListaDoble;
import Main.ManejadorInterfaz;
import Ventanas.Global;
import Worker.Assembler;
import Worker.Developer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class Funciones {
    public void escribir_txt(){
        System.out.println("");
        String datos_iniciales = "";
        //COLOCAMOS LOS DATOS DE LOS TRABAJDORES DE CARTOON NETWORK separados por coma
        datos_iniciales = ManejadorInterfaz.getInterfaz().getAnimadoresCN()+","+
                          ManejadorInterfaz.getInterfaz().getGuionistasCN() +","+
                          ManejadorInterfaz.getInterfaz().getEscenariosCN()+","+
                          ManejadorInterfaz.getInterfaz().getdoblajeCN()+","+
                          ManejadorInterfaz.getInterfaz().getPlottwistCN()+","+
                          ManejadorInterfaz.getInterfaz().getEnsambladoresCN();
        //COLOCAMOS UN SALTO DE LINEA Y LUEGO LOS DATOS DE LOS TRABAJDORES DE Disney separados por coma
        datos_iniciales = datos_iniciales + "\n"+ManejadorInterfaz.getInterfaz().getAnimadoresDisney()+","+
                                                ManejadorInterfaz.getInterfaz().getGuionistasDisney() +","+
                                                ManejadorInterfaz.getInterfaz().getEscenariosDisney()+","+
                                                ManejadorInterfaz.getInterfaz().getdoblajeDisney()+","+
                                                ManejadorInterfaz.getInterfaz().getPlottwistDisney()+","+
                                                ManejadorInterfaz.getInterfaz().getEnsambladoresDisney();
        //COLOCAMOS LA DURACION DE LOS DIAS
        datos_iniciales = datos_iniciales + "\n"+ManejadorInterfaz.getInterfaz().getDuracionDias();
        //COLOCAMOS EL DEADLINE
        datos_iniciales = datos_iniciales + "\n"+ManejadorInterfaz.getInterfaz().getDiasEntrega();
        try{
            PrintWriter pw = new PrintWriter("test\\datos_iniciales.txt");
            pw.println(datos_iniciales);
            pw.close();
            JOptionPane.showMessageDialog(null,"Guardado exitoso");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERROR al guardar");
        }
    }
    
    public void leer_txt(){
        String line;
        String txt ="";
        String path = "test\\datos_iniciales.txt";
        File file = new File(path);
        try{
            if (!file.exists()){
                file.createNewFile();
            }else{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while((line = br.readLine()) != null){
                    if(! line.isEmpty()){
                        txt += line + "\n";
                    }
                }
                
                if(! "".equals(txt)){
                    String[] datos_split = txt.split("\n");
                    String[] cn = datos_split[0].split(",");
                    String[] disney = datos_split[1].split(",");
                    //Cargamos trabajadores de Cartoon Network
                    for (int i = 0; i < 6; i++) {
                        if(i == 0){ 
                            for (int j = 0; j < Integer.parseInt(cn[i]); j++) {
                                Developer animadorNuevo = new Developer(0,40,Global.getMutexCartoonNetwork(),Global.getCartoonNetwork());
                                animadorNuevo.start();
                                Global.getCartoonNetwork().getAnimadores().insertFinal(animadorNuevo);
                            }
                        }else if(i == 1){
                            for (int j = 0; j < Integer.parseInt(cn[i]); j++) {
                                Developer guionistaNuevo = new Developer(1,25,Global.getMutexCartoonNetwork(),Global.getCartoonNetwork());
                                guionistaNuevo.start();
                                Global.getCartoonNetwork().getGuionistas().insertFinal(guionistaNuevo);
                            }
                        }else if(i == 2){
                            for (int j = 0; j < Integer.parseInt(cn[i]); j++) {
                                Developer DisEscenariosNuevo = new Developer(2,26,Global.getMutexCartoonNetwork(),Global.getCartoonNetwork());
                                DisEscenariosNuevo.start();
                                Global.getCartoonNetwork().getDisenadoresEscenarios().insertFinal(DisEscenariosNuevo);
                            }
                        }else if(i == 3){
                            for (int j = 0; j < Integer.parseInt(cn[i]); j++) {
                                Developer ActorDoblajeNuevo = new Developer(3,16,Global.getMutexCartoonNetwork(),Global.getCartoonNetwork());
                                ActorDoblajeNuevo.start();
                                Global.getCartoonNetwork().getActoresDoblaje().insertFinal(ActorDoblajeNuevo);
                            }
                        }else if(i == 4){
                            for (int j = 0; j < Integer.parseInt(cn[i]); j++) {
                                Developer pttNuevo = new Developer(4,34,Global.getMutexCartoonNetwork(),Global.getCartoonNetwork());
                                pttNuevo.start();
                                Global.getCartoonNetwork().getGuionistasPlotTwist().insertFinal(pttNuevo);
                            }
                        }else if(i == 5){
                            for (int j = 0; j < Integer.parseInt(cn[i]); j++) {
                                Assembler ensambladorNuevo = new Assembler(5,50,Global.getMutexCartoonNetwork(),Global.getCartoonNetwork());
                                ensambladorNuevo.start();
                                Global.getCartoonNetwork().getEnsambladores().insertFinal(ensambladorNuevo);
                            }
                        }
                    }
                    this.ActualizarPestanaCartoonNetwork(cn);
                    
                    //Cargamos trabajadores de Disney
                    for (int i = 0; i < 6; i++) {
                        if(i == 0){ 
                            for (int j = 0; j < Integer.parseInt(disney[i]); j++) {
                                Developer animadorNuevo = new Developer(0,40,Global.getMutexDisney(),Global.getDisney());
                                animadorNuevo.start();
                                Global.getDisney().getAnimadores().insertFinal(animadorNuevo);
                            }
                        }else if(i == 1){
                            for (int j = 0; j < Integer.parseInt(disney[i]); j++) {
                                Developer guionistaNuevo = new Developer(1,25,Global.getMutexDisney(),Global.getDisney());
                                guionistaNuevo.start();
                                Global.getDisney().getGuionistas().insertFinal(guionistaNuevo);
                            }
                        }else if(i == 2){
                            for (int j = 0; j < Integer.parseInt(disney[i]); j++) {
                                Developer disEscenariosNuevo = new Developer(2,26,Global.getMutexDisney(),Global.getDisney());
                                disEscenariosNuevo.start();
                                Global.getDisney().getDisenadoresEscenarios().insertFinal(disEscenariosNuevo);
                            }
                        }else if(i == 3){
                            for (int j = 0; j < Integer.parseInt(disney[i]); j++) {
                                Developer actorDoblajeNuevo = new Developer(3,16,Global.getMutexDisney(),Global.getDisney());
                                actorDoblajeNuevo.start();
                                Global.getDisney().getActoresDoblaje().insertFinal(actorDoblajeNuevo);
                            }
                        }else if(i == 4){
                            for (int j = 0; j < Integer.parseInt(disney[i]); j++) {
                                Developer pttNuevo = new Developer(4,34,Global.getMutexDisney(),Global.getDisney());
                                pttNuevo.start();
                                Global.getDisney().getGuionistasPlotTwist().insertFinal(pttNuevo);
                            }
                        }else if(i == 5){
                            for (int j = 0; j < Integer.parseInt(disney[i]); j++) {
                                Assembler ensambladorNuevo = new Assembler(5,50,Global.getMutexDisney(),Global.getDisney());
                                ensambladorNuevo.start();
                                Global.getDisney().getEnsambladores().insertFinal(ensambladorNuevo);
                            }
                        }
                    }
                    this.ActualizarPestanaDisney(disney);
                    
                    //Cargamos duracion dias
                    Global.getCartoonNetwork().setDuracionDia(Integer.parseInt(datos_split[2])*1000);
                    Global.getDisney().setDuracionDia(Integer.parseInt(datos_split[2])*1000);
                    
                    //Cargamos deadline
                    Global.getDriveCartoonNetwork().setDeadline(Integer.parseInt(datos_split[3]));
                    Global.getDriveDisney().setDeadline(Integer.parseInt(datos_split[3]));
                    
                    //Cargamos el estadoDeadline
                    Global.getDriveDisney().setEstadoDeadline(Integer.parseInt(datos_split[3]),Global.getDisney().getTipoCompania());
                    Global.getDriveCartoonNetwork().setEstadoDeadline(Integer.parseInt(datos_split[3]), Global.getCartoonNetwork().getTipoCompania());
                    
                }
                br.close();
                //JOptionPane.showMessageDialog(null,"Lectura exitosa");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERROR al leer");
        }
    }
    
    public void ActualizarPestanaCartoonNetwork(String[] cn){
        for (int i = 0; i < 6; i++) {
            if(i == 0){
                ManejadorInterfaz.getInterfaz().setAnimadoresCartoonNetwork(Integer.parseInt(cn[i]));
            }else if(i == 1){
                ManejadorInterfaz.getInterfaz().setGuionistasCartoonNetwork(Integer.parseInt(cn[i]));
            }else if(i == 2){
                ManejadorInterfaz.getInterfaz().setEscenariosCartoonNetwork(Integer.parseInt(cn[i]));
            }else if(i == 3){
                ManejadorInterfaz.getInterfaz().setDoblajesCartoonNetwork(Integer.parseInt(cn[i]));
            }else if(i == 4){
                ManejadorInterfaz.getInterfaz().setGuionesPlowtTistCartoonNetwork(Integer.parseInt(cn[i]));
            }else if(i == 5){
                ManejadorInterfaz.getInterfaz().setEnsambladoresCartoonNetwork(Integer.parseInt(cn[i]));
            }
        }
    }
    
    public void ActualizarPestanaDisney(String[] disney){
        for (int i = 0; i < 6; i++) {
            if(i == 0){
                ManejadorInterfaz.getInterfaz().setAnimadoresDisney(Integer.parseInt(disney[i]));
            }else if(i == 1){
                ManejadorInterfaz.getInterfaz().setGuionistasDisney(Integer.parseInt(disney[i]));
            }else if(i == 2){
                ManejadorInterfaz.getInterfaz().setEscenariosDisney(Integer.parseInt(disney[i]));
            }else if(i == 3){
                ManejadorInterfaz.getInterfaz().setDoblajesDisney(Integer.parseInt(disney[i]));
            }else if(i == 4){
                ManejadorInterfaz.getInterfaz().setGuionistasPlowtTistDisney(Integer.parseInt(disney[i]));
            }else if(i == 5){
                ManejadorInterfaz.getInterfaz().setEnsambladoresDisney(Integer.parseInt(disney[i]));
            }
        }
    }
    
}
