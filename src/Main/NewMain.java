/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Companies.Company;
import Drive.Drive;
import EDD.Nodo;
import Worker.Assembler;
import Worker.Developer;
import Worker.ProjectManager;
import Worker.Worker;
import java.util.concurrent.Semaphore;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Ventanas.Global;
import Ventanas.Grafico;
import Worker.Director;
/**
 *
 * @author Diego
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ManejadorInterfaz.getInterfaz().show();
        
        //Creamos la compania Disney y la cargamos a Global
        Company disney = new Company(Global.getDriveDisney(),0);
        Global.setDisney(disney);
        //Creamos la compania Cartoon Network y la cargamos a Global
        Company cartoonNetwork = new Company(Global.getDriveCartoonNetwork(),1);
        Global.setCartoonNetwork(cartoonNetwork);
        
        //Creamos los semaforos y los cargamos  a Global
        Semaphore mutexDisney = new Semaphore(1);
        Semaphore mutexCN = new Semaphore(1);
        Global.setMutexCartoonNetwork(mutexCN);
        Global.setMutexDisney(mutexDisney);
        
        Global.getFunciones().leer_txt();
        
        
        //Creamos y ponemos a trabjar los PM de ambas empresas
        ProjectManager pm = new ProjectManager(6,40,mutexDisney,       Global.getDisney());
        ProjectManager pm2 = new ProjectManager(6,40,mutexDisney,Global.getCartoonNetwork());
        pm.start();
        pm2.start();
        
        //Creamos y ponemos a trabjar los DIRECTORES de ambas empresas
        Director directorDisney = new Director(7,60,mutexDisney,Global.getDisney());
        directorDisney.start();
        Director directorCN = new Director(7,60,mutexCN,Global.getCartoonNetwork());
        directorCN.start();
        
        Global.getGrafico().start();
        
    }
    
}
