/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Worker;

import Companies.Company;
import Main.ManejadorInterfaz;
import java.util.concurrent.Semaphore;
import Ventanas.Interfaz;
import java.util.Random;
import Drive.Drive;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Diego
 */
public class Director extends Worker{
    
    public Director(int tipo,float salario,Semaphore mutex,Company compañia){
    super(tipo,salario,mutex,compañia);
    }
    
    @Override
    public void run(){
        while(true){
            try{
                this.cobrarSueldo();
                this.work();
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }
    
    public void work(){
        if(this.getCompania().getDrive().getEstadoDeadline() == 0){
            try {
                this.enviarCapitulos();
                this.getCompania().getDrive().getDiasMutex().acquire();
                this.getCompania().getDrive().setEstadoDeadline(this.getCompania().getDrive().getDeadline(), this.getCompania().getTipoCompania());
                this.getCompania().getDrive().getDiasMutex().release();
                sleep(this.getCompania().getDuracionDia());
            } catch (InterruptedException ex) {
                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try{
                //El director escoge una hora del dia aleatoriamente, 
                //si llega a esa hora el director cambiara su estado a revisando y revisara durante 35 minutos
                Random r = new Random();
                
                double oneHour = this.getCompania().getDuracionDia() / 24;
                double checkingHour = r.nextInt(24) * oneHour; //obtiene un numero aleatorio entre 0-23.  Y lo multiplica por el equivalente a 1 hora
                //int horas = 0;
                double contador = 0;
                while (contador <  this.getCompania().getDuracionDia()) {
                        if (contador == checkingHour) {
                            this.getCompania().getDrive().setEstadorDirector(0,this.getCompania().getTipoCompania()); // cambiamos su estado a vigilando



                            //Ya aqui empiezan a pasar los minutos 
                            double oneMinute = oneHour / 60;
                            sleep(Math.round(35 * oneMinute)); //vigila durante 35 minutos
                            this.getCompania().getDrive().setEstadorDirector(1,this.getCompania().getTipoCompania());//cambias su estado a trabajando
                            sleep(Math.round(25 * oneMinute));//trabaja 25 minutos
                            contador += oneHour;
                            //horas +=1;
                        }else{
                            sleep(Math.round(oneHour));
                            contador += oneHour;
                            //horas +=1;
                        }
                }              
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }
    
    public void cobrarSueldo(){
        try {
            //Cobrando su dia de trabajo
            this.getCompania().getDrive().getSalarioAccMutex().acquire();
            this.getCompania().getDrive().addSalary(this.getTipo(), this.getSalario()*24, this.getCompania().getTipoCompania());
            this.getCompania().getDrive().getSalarioAccMutex().release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarCapitulos(){
        try {
            //Cobrando su dia de trabajo
            this.getMutex().acquire();
            this.getCompania().getDrive().getSalarioAccMutex().acquire();
            float ganancias = 0;
            if(this.getCompania().getTipoCompania() == 0){
                ganancias = (this.getCompania().getDrive().getCapitulosEstandar()*250000) + (this.getCompania().getDrive().getCapitulosPlotTwist() * 600000);
                this.getCompania().getDrive().setGanancias(this.getCompania().getDrive().getGanancias()+ ganancias, this.getCompania().getTipoCompania());
                this.getCompania().getDrive().setUtilidades(this.getCompania().getDrive().getUtilidades() + ganancias, this.getCompania().getTipoCompania());
            }else if(this.getCompania().getTipoCompania() == 1){
                ganancias = (this.getCompania().getDrive().getCapitulosEstandar()*300000) + (this.getCompania().getDrive().getCapitulosPlotTwist() * 650000);
                this.getCompania().getDrive().setGanancias(this.getCompania().getDrive().getGanancias()+ ganancias, this.getCompania().getTipoCompania());
                this.getCompania().getDrive().setUtilidades(this.getCompania().getDrive().getUtilidades() + ganancias, this.getCompania().getTipoCompania());
            }
            
            //reiniciamos los capitulos acumulados
            this.getCompania().getDrive().setCapitulosEstandar(0, this.getCompania().getTipoCompania());
            this.getCompania().getDrive().setCapitulosPlotTwist(0, this.getCompania().getTipoCompania());
            
            this.getMutex().release();
            this.getCompania().getDrive().getSalarioAccMutex().release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}