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
public class Assembler extends Worker{
    float salarioAcc;
    float acc;
    public Assembler(int type,float salary, String name, Semaphore mutex,Company compania) {
        super(type,salary, name, mutex,compania);
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
                sleep(this.getCompania().getDuracionDia());
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
        if(this.getCompania().getTipoCompania() == 0 && this.getCompania().getDrive().getGuiones() >= 1 && this.getCompania().getDrive().getEscenarios() >= 2 && this.getCompania().getDrive().getAnimaciones() >= 6 && this.getCompania().getDrive().getDoblajes() >= 5){
            //si al assembler le toca hacer un capitulo estandar entrara en el siguiente if
            if((this.getCompania().getDrive().getCapitulosEstandar()+this.getCompania().getDrive().getCapitulosPlotTwist()+1)%4 != 0){
                respuesta = true;
            //si al assembler le toca hacer un capitulo PlotTwist y el drive tiene los guiones plotTwist necesarios, entrara en el siguiente else if
            }else if(((this.getCompania().getDrive().getCapitulosEstandar()+this.getCompania().getDrive().getCapitulosPlotTwist()+1)%4 == 0) && this.getCompania().getDrive().getGuionesPlotTwist() >= 1){
                respuesta = true;
            }
            
        //Si es Disney channel y el drive tiene suficientes partes para hacer un capitulo estandar entrara en el siguiente if
        }else if(this.getCompania().getTipoCompania() == 1 && this.getCompania().getDrive().getGuiones() >= 1 && this.getCompania().getDrive().getEscenarios() >= 1 && this.getCompania().getDrive().getAnimaciones() >= 2 && this.getCompania().getDrive().getDoblajes() >= 4){
            //si le toca hacer un capitulo estandar entrara
            if((this.getCompania().getDrive().getCapitulosEstandar()+this.getCompania().getDrive().getCapitulosPlotTwist()+1)%3 != 0){
                respuesta = true;
            //si al assembler le toca hacer un capitulo PlotTwist y el drive tiene los guiones plotTwist necesarios, entrara en el siguiente else if
            }else if(((this.getCompania().getDrive().getCapitulosEstandar()+this.getCompania().getDrive().getCapitulosPlotTwist()+1)%3 == 0) && this.getCompania().getDrive().getGuionesPlotTwist() >= 3){
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
                        this.getCompania().getDrive().addChapter(this.getCompania().getTipoCompania());//critica
                        this.getMutex().release();// signal
                        this.acc = 0;

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Developer.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
            }
        }
    }
    
}
