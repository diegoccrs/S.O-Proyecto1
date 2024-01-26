/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Companies.Company;
import Drive.Drive;
import EDD.Nodo;
import Ventanas.Interfaz;
import Worker.Assembler;
import Worker.Developer;
import Worker.Worker;
import java.util.concurrent.Semaphore;
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
        Semaphore mutex = new Semaphore(1);
        Drive drive = new Drive();
        Company compania = new Company(drive,0);
     
        Developer trabajador1 = new Developer(0,240, "perez", mutex,drive,1);
        Developer trabajador2 = new Developer(1,100, "Juan", mutex,drive,1);
        Developer trabajador3 = new Developer(2,300, "Julián", mutex,drive,1);
        Developer trabajador4 = new Developer(3,300, "alvaro", mutex,drive,1);
        Developer trabajador5 = new Developer(4,300, "pepe", mutex,drive,1);
        Assembler ensamblador1 = new Assembler(5,40,"albeto",mutex,drive,1);
        
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
        
    }
    
}
