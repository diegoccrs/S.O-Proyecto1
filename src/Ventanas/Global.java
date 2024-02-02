/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

import Companies.Company;
import Drive.Drive;
import FunctionsTXT.Funciones;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Kevin
 */
public class Global {
   //Estas son instancias de clases estaticas. Te permiten acceder a los objetos desde cualquier clase sin inicializarlos
    private static Drive driveDisney = new Drive();
    private static Drive driveCartoonNetwork = new Drive();
    private static Company Disney = new Company();
    private static Company CartoonNetwork = new Company();
    private static Semaphore mutexDisney = new Semaphore(1);
    private static Semaphore mutexCartoonNetwork = new Semaphore(1);
    private static Funciones  funciones = new Funciones();

    
    public static Drive getDriveDisney(){
        return driveDisney;
    }
    
    public static void setDriveDisney(Drive aDriveDisney) {
        driveDisney = aDriveDisney;
    }
    
    public static Drive getDriveCartoonNetwork(){
        return driveCartoonNetwork;
    }
    
    public static void setDriveCartoonNetwork(Drive adriveCartoonNetwork) {
        driveCartoonNetwork = adriveCartoonNetwork;
    }
    
    public static Company getDisney(){
        return Disney;
    }
    
    public static void setDisney(Company aDisney) {
        Disney = aDisney;
    }
    
    public static Company getCartoonNetwork(){
        return CartoonNetwork;
    }
    
    public static void setCartoonNetwork(Company aCartoonNetwork) {
        CartoonNetwork = aCartoonNetwork;
    }

    /**
     * @return the mutexDisney
     */
    public static Semaphore getMutexDisney() {
        return mutexDisney;
    }

    /**
     * @param aMutexDisney the mutexDisney to set
     */
    public static void setMutexDisney(Semaphore aMutexDisney) {
        mutexDisney = aMutexDisney;
    }

    /**
     * @return the mutexCartoonNetwork
     */
    public static Semaphore getMutexCartoonNetwork() {
        return mutexCartoonNetwork;
    }

    /**
     * @param aMutexCartoonNetwork the mutexCartoonNetwork to set
     */
    public static void setMutexCartoonNetwork(Semaphore aMutexCartoonNetwork) {
        mutexCartoonNetwork = aMutexCartoonNetwork;
    }

    /**
     * @return the funciones
     */
    public static Funciones getFunciones() {
        return funciones;
    }

    /**
     * @param aFunciones the funciones to set
     */
    public static void setFunciones(Funciones aFunciones) {
        funciones = aFunciones;
    }
    
    

}
