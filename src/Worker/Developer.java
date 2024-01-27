/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Worker;

import Drive.Drive;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class Developer extends Worker{
    int duracionDia;
    float salarioAcc;
    float acc;
    public Developer(int tipo,float salario, String nombre, Semaphore mutex,Drive drive, int tipoCompania) {
        super(tipo,salario, nombre, mutex,drive,tipoCompania);
        this.duracionDia = 1000;
        this.salarioAcc = 0;
        this.acc = 0;
    }
    @Override
    public void run(){
       while(true) {
            
            try {
                obtainSalary();
                work();
                //System.out.println("Trabajador: "+ this.name + " gana: "+this.salaryAcc+"$");
                sleep(this.duracionDia);
            } catch (InterruptedException ex) {
                Logger.getLogger(Developer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void obtainSalary(){
        this.salarioAcc += this.getSalario()*24;
    }

    
    public void work(){
        this.acc += this.getCantidadDeTrabajoPorDia();
            try {
                if (this.acc >= 1){
                    this.getMutex().acquire(); //wait
                    this.getDrive().addPart(this.getTipo());//critica
                    this.getMutex().release();// signal
                    this.acc = 0;
                }
                
                this.getDrive().getSalarioAccMutex().acquire(); //wait
                this.getDrive().addSalary(this.getTipo(), this.getSalario());
                this.getDrive().getSalarioAccMutex().release();// signal
            } catch (InterruptedException ex) {
                Logger.getLogger(Developer.class.getName()).log(Level.SEVERE, null, ex);
            } 
        
    }
}
