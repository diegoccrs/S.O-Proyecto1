/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Worker;

import Companies.Company;
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
    float salarioAcc;
    float acc;
    public Developer(int tipo,float salario, Semaphore mutex, Company compania) {
        super(tipo,salario, mutex,compania);
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
                sleep(this.getCompania().getDuracionDia());
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
                    this.getCompania().getDrive().addPart(this.getTipo(),this.getCompania().getTipoCompania());//critica
                    this.getMutex().release();// signal
                    this.acc = 0;
                }
                
                this.getCompania().getDrive().getSalarioAccMutex().acquire(); //wait
                this.getCompania().getDrive().addSalary(this.getTipo(), this.getSalario());
                this.getCompania().getDrive().getSalarioAccMutex().release();// signal
            } catch (InterruptedException ex) {
                Logger.getLogger(Developer.class.getName()).log(Level.SEVERE, null, ex);
            } 
        
    }
}
