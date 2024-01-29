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
/**
 *
 * @author User
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Interfaz inicio = new Interfaz();
        //inicio.setLocationRelativeTo(null);
        //inicio.show();
        ManejadorInterfaz.getInterfaz().show();
        Semaphore mutex = new Semaphore(1);
        Global.getDriveDisney().setDeadline(7);
        Global.getDriveDisney().setEstadoDeadline(7);
        Company compania = new Company(Global.getDriveDisney(),0,1000);
        Global.setDisney(compania);
        Global.setMutexDisney(mutex);
        Developer trabajador1 = new Developer(0,240, "perez", mutex,compania);
        Developer trabajador2 = new Developer(1,100, "Juan", mutex,compania);
        Developer trabajador3 = new Developer(2,300, "Julián", mutex,compania);
        Developer trabajador4 = new Developer(3,300, "alvaro", mutex,compania);
        Developer trabajador5 = new Developer(4,300, "pepe", mutex,compania);
        Assembler ensamblador1 = new Assembler(5,40,"albeto",mutex,compania);
        ProjectManager pm = new ProjectManager(6,240,"pedro",mutex,compania);
        System.out.println(Global.getDisney().cantidadTrabajadores());
        //compania..insertBegin(trabajador1);
        //compania.getDevelopers().insertBegin(ensamblador1);
        //System.out.println(compania.getDevelopers().getHead().getNext().getElement().getType());
        //System.out.println(compania.getDevelopers().getHead().getElement().getType());
        
        trabajador1.start();
        trabajador2.start();
        trabajador3.start();
        trabajador4.start();
        trabajador5.start();
        ensamblador1.start();
        pm.start();
        
        //Creacion de archivo TXT 
        try {
            File file = new File("datos.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("Duración en segundos del dia: \nDeadline de entrega: \nCantidad inicial  de trabajadores: \nCantidad inicial de ensambladores: ");
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
    
}
