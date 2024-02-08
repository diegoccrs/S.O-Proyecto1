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
    int capitulosEstandar = 0;
    int capitulosPlotTwist = 0;
    public Assembler(int type,float salary, Semaphore mutex,Company compania) {
        super(type,salary, mutex,compania);
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
        float salario = this.getSalario()*24;
        try {
            this.getCompania().getDrive().getSalarioAccMutex().acquire();
            this.getCompania().getDrive().addSalary(this.getTipo(), this.getSalario()*24, this.getCompania().getTipoCompania());
            this.getCompania().getDrive().getSalarioAccMutex().release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Assembler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Para entender este metodo hay que saber que Cartoon Network=1 y Disney channel=0
    //Ademas hay que saber que si te toca hacer un capitulo PlotTwist y te faltan guiones PlotTwist, no puedes hacer capitulos estandar aunque tengas todas sus partes
    //Se debe esperar para hacer el capitulo PlotTwist
    private boolean verificarPartesNecesarias(){
        boolean respuesta = false;
        //Si es Cartoon Network y el drive tiene suficientes partes para hacer un capitulo estandar entrara en el siguiente if
        if(this.getCompania().getTipoCompania() == 1 && this.getCompania().getDrive().getGuiones() >= 1 && this.getCompania().getDrive().getEscenarios() >= 2 && this.getCompania().getDrive().getAnimaciones() >= 6 && this.getCompania().getDrive().getDoblajes() >= 5){
            //si al assembler le toca hacer un capitulo estandar entrara en el siguiente if
            if((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)%4 != 0){
                respuesta = true;
            //si al assembler le toca hacer un capitulo PlotTwist y el drive tiene los guiones plotTwist necesarios, entrara en el siguiente else if
            }else if(((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)%4 == 0) && this.getCompania().getDrive().getGuionesPlotTwist() >= 1){
                respuesta = true;
            }
            
        //Si es Disney channel y el drive tiene suficientes partes para hacer un capitulo estandar entrara en el siguiente if
        }else if(this.getCompania().getTipoCompania() == 0 && this.getCompania().getDrive().getGuiones() >= 1 && this.getCompania().getDrive().getEscenarios() >= 1 && this.getCompania().getDrive().getAnimaciones() >= 2 && this.getCompania().getDrive().getDoblajes() >= 4){
            //si le toca hacer un capitulo estandar entrara
            if((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)%3 != 0){
                respuesta = true;
            //si al assembler le toca hacer un capitulo PlotTwist y el drive tiene los guiones plotTwist necesarios, entrara en el siguiente else if
            }else if(((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)%3 == 0) && this.getCompania().getDrive().getGuionesPlotTwist() >= 3){
                respuesta = true;
            }
        }
        
        return respuesta;
    }
    
    //devolvera 0 si toca hacer un capitulo estandar y 1 si toca hacer un capitulo plottwist
    private int verificarCapituloQueToca(){
        int respuesta = 0;
        if(this.getCompania().getTipoCompania() == 0){
            //si le toca hacer un capitulo estandar entrara
            if((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)%3 != 0){
                respuesta = 0;
            //si al assembler le toca hacer un capitulo PlotTwist y el drive tiene los guiones plotTwist necesarios, entrara en el siguiente else if
            }else if(((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)%3 == 0)){
                respuesta = 1;
            }
        }else if(this.getCompania().getTipoCompania() == 1){
            //si al assembler le toca hacer un capitulo estandar entrara en el siguiente if
            if((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)%4 != 0){
                respuesta = 0;
            //si al assembler le toca hacer un capitulo PlotTwist y el drive tiene los guiones plotTwist necesarios, entrara en el siguiente else if
            }else if(((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)%4 == 0)){
                respuesta = 1;
            }
        }
        
        return respuesta;
    }

    private void work() {
        //si el ensamblador no esta ensamblando un capitulo, entra aqui
        if(this.capitulosEstandar == 0 && this.capitulosPlotTwist == 0){
            try {
                this.getMutex().acquire(); //wait
                boolean autorizacion = this.verificarPartesNecesarias();
                if(autorizacion == true){
                    this.tomarPartesDeCapitulos();
                }
                this.getMutex().release();// signal     

            } catch (InterruptedException ex) {
                Logger.getLogger(Developer.class.getName()).log(Level.SEVERE, null, ex);
            }
        //entrara aqui si esta ensamblando un capitulo
        }else{
            this.acc = this.acc + this.getCantidadDeTrabajoPorDia();
            if (this.acc >= 1){
                    try {
                        this.getMutex().acquire(); //wait
                        //agregara un capitulo,si y solo si, el capitulo que termino de ensamblar es el que corresponde mandar.
                        if((this.capitulosEstandar == 1 && this.verificarCapituloQueToca() == 0) || (this.capitulosPlotTwist == 1 && this.verificarCapituloQueToca() == 1)){
                            this.agregarCapitulo();//critica
                        }
                        this.getMutex().release();// signal
                        this.acc = 0;

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Developer.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
            }
        }
    }
    
    //ESTE METODO AGREGA CAPITULOS AL DRIVE Y ACTUALIZA EL ESTADO DE CAPITULOS DEL ENSAMBLADOR
    public void agregarCapitulo(){
        if(this.capitulosEstandar == 1){
            this.capitulosEstandar = 0;
            this.getCompania().getDrive().setCapitulosEstandar(this.getCompania().getDrive().getCapitulosEstandar() + 1,this.getCompania().getTipoCompania());
            this.getCompania().getDrive().setCapitulosEstandarAcumulados(this.getCompania().getDrive().getCapitulosEstandarAcumulados()+1);
        }else if(this.capitulosPlotTwist == 1){
            this.capitulosPlotTwist = 0;
            this.getCompania().getDrive().setCapitulosPlotTwist(this.getCompania().getDrive().getCapitulosPlotTwist() + 1,this.getCompania().getTipoCompania());
            this.getCompania().getDrive().setCapitulosPlotTwistAcumulados(this.getCompania().getDrive().getCapitulosPlotTwistAcumulados() + 1);
        
        }
    }
    
    //ESTE METODO TOMA LAS PARTES DE CAPITULO, PUEDE DARSE CUENTA DE SI TOCA UN ESTANDAR O UN PLOTTWIST
    public void tomarPartesDeCapitulos(){
        if(this.getCompania().getTipoCompania() == 1){ //1 cartoon network
            //Tomamos las partes del drive
            this.getCompania().getDrive().setGuiones(this.getCompania().getDrive().getGuiones() - 1,this.getCompania().getTipoCompania());
            this.getCompania().getDrive().setEscenarios(this.getCompania().getDrive().getEscenarios() - 2,this.getCompania().getTipoCompania());
            this.getCompania().getDrive().setAnimaciones(this.getCompania().getDrive().getAnimaciones() - 6,this.getCompania().getTipoCompania());
            this.getCompania().getDrive().setDoblajes(this.getCompania().getDrive().getDoblajes() - 5,this.getCompania().getTipoCompania());
            if(((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)% 4 == 0) && (this.getCompania().getDrive().getGuionesPlotTwist() >= 1)){
                this.getCompania().getDrive().setGuionesPlotTwist(this.getCompania().getDrive().getGuionesPlotTwist() - 1,this.getCompania().getTipoCompania());//quitas el guion plottwist del drive
                this.capitulosPlotTwist = this.capitulosPlotTwist + 1;
            }else if((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)% 4 != 0){
                this.capitulosEstandar = this.capitulosEstandar + 1;
                //System.out.println("Capitulos Estandar disponibles:" + this.getCapitulosEstandar());
            }
        }else if(this.getCompania().getTipoCompania() == 0){ // 0 = disney channel
            this.getCompania().getDrive().setGuiones(this.getCompania().getDrive().getGuiones() - 1,this.getCompania().getTipoCompania());
            this.getCompania().getDrive().setEscenarios(this.getCompania().getDrive().getEscenarios() - 1,this.getCompania().getTipoCompania());
            this.getCompania().getDrive().setAnimaciones(this.getCompania().getDrive().getAnimaciones() - 2,this.getCompania().getTipoCompania());
            this.getCompania().getDrive().setDoblajes(this.getCompania().getDrive().getDoblajes() - 4,this.getCompania().getTipoCompania());
            if(((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)% 3 == 0) && (this.getCompania().getDrive().getGuionesPlotTwist() >= 3)){
                this.getCompania().getDrive().setGuionesPlotTwist(this.getCompania().getDrive().getGuionesPlotTwist() - 3,this.getCompania().getTipoCompania());
                this.capitulosPlotTwist = this.capitulosPlotTwist + 1;
                //System.out.println("Capitulos PlotTwist disponibles:" + this.getCompania().getDrive().getCapitulosPlotTwist());
            }else if((this.getCompania().getDrive().getCapitulosEstandarAcumulados()+this.getCompania().getDrive().getCapitulosPlotTwistAcumulados()+1)% 3 != 0){
                this.capitulosEstandar = this.capitulosEstandar + 1;
                //System.out.println("Capitulos Estandar disponibles:" + this.getCapitulosEstandar());
            }
        }
    }
    
}