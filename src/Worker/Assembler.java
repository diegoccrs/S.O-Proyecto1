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
public class Assembler extends Worker{
    int duracionDia;
    float salarioAcc;
    float acc;
    public Assembler(int type,float salary, String name, Semaphore mutex,Drive drive,int CompanyType) {
        super(type,salary, name, mutex,drive,CompanyType);
        this.duracionDia = 1000;
        this.salarioAcc = 0;
        this.acc = 0;
    }
    @Override
    public void run() {
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

    private void obtainSalary() {
        this.salarioAcc = this.salarioAcc + this.getSalario()*24;
    }
    
    //Para entender este metodo hay que saber que Cartoon Network=0 y Disney channel=1
    //Ademas hay que saber que si te toca hacer un capitulo PlotTwist y te faltan guiones PlotTwist, no puedes hacer capitulos estandar aunque tengas todas sus partes
    //Se debe esperar para hacer el capitulo PlotTwist
    private boolean verificarPartesNecesarias(){
        boolean respuesta = false;
        //Si es Cartoon Network y el drive tiene suficientes partes para hacer un capitulo estandar entrara en el siguiente if
        if(this.getTipoCompania() == 0 && this.getDrive().getGuiones() >= 1 && this.getDrive().getEscenarios() >= 2 && this.getDrive().getAnimaciones() >= 6 && this.getDrive().getDoblajes() >= 5){
            //si al assembler le toca hacer un capitulo estandar entrara en el siguiente if
            if((this.getDrive().getCapitulosEstandar()+this.getDrive().getCapitulosPlotTwist()+1)%4 != 0){
                respuesta = true;
            //si al assembler le toca hacer un capitulo PlotTwist y el drive tiene los guiones plotTwist necesarios, entrara en el siguiente else if
            }else if(((this.getDrive().getCapitulosEstandar()+this.getDrive().getCapitulosPlotTwist()+1)%4 == 0) && this.getDrive().getGuionesPlotTwist() >= 1){
                respuesta = true;
            }
            
        //Si es Disney channel y el drive tiene suficientes partes para hacer un capitulo estandar entrara en el siguiente if
        }else if(this.getTipoCompania() == 1 && this.getDrive().getGuiones() >= 1 && this.getDrive().getEscenarios() >= 1 && this.getDrive().getAnimaciones() >= 2 && this.getDrive().getDoblajes() >= 4){
            //si le toca hacer un capitulo estandar entrara
            if((this.getDrive().getCapitulosEstandar()+this.getDrive().getCapitulosPlotTwist()+1)%3 != 0){
                respuesta = true;
            //si al assembler le toca hacer un capitulo PlotTwist y el drive tiene los guiones plotTwist necesarios, entrara en el siguiente else if
            }else if(((this.getDrive().getCapitulosEstandar()+this.getDrive().getCapitulosPlotTwist()+1)%3 == 0) && this.getDrive().getGuionesPlotTwist() >= 3){
                respuesta = true;
            }
        }
        
        return respuesta;
    }

    private void work() {
        boolean autorizacion = this.verificarPartesNecesarias();
        if(autorizacion == true){
            this.acc = this.acc + this.getCantidadDeTrabajoPorDia();
            if (this.acc >= 1){
                    try {
                        this.getMutex().acquire(); //wait
                        this.getDrive().addChapter(this.getTipoCompania());//critica
                        this.getMutex().release();// signal
                        this.acc = 0;

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Developer.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
            }
        }
    }
    
}
