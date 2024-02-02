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
/**
 *
 * @author Diego
 */
public class Director extends Worker{
    
    private Drive drive;
    
    public Director(int tipo,float salario,Semaphore mutex,Company compañia){
    super(tipo,salario,mutex,compañia);
    this.drive = drive;
    }
    
    @Override
    public void run(){
        while(true){
            double timePassed = 0;
            try{
                drive.getDiasMutex().release();
                //El director escoge una hora del dia aleatoriamente, 
                //si llega a esa hora el director cambiara su estado a revisando y revisara durante 35 minutos
                Random r = new Random();
                
                 double oneHour = this.getCompania().getDuracionDia() / 24;
                    double checkingHour = r.nextInt(24) * oneHour;
                    timePassed = (checkingHour + 1) * oneHour;

                    double contador = 0;
                    while (contador <  this.getCompania().getDuracionDia()) {
                        System.out.println("");
                        System.out.println(contador);
                        System.out.println(checkingHour);
                        System.out.println("");
                        if (contador == checkingHour) {
                            drive.setEstadorDirector(0);



                            //Ya aqui empiezan a pasar los minutos 
                            double oneMinute = oneHour / 60;
                            sleep(Math.round(25 * oneMinute));
                            drive.setEstadorDirector(1);
                            sleep(Math.round(35 * oneMinute));
                        }
                        sleep(Math.round(oneHour));

                        contador += oneHour;
                    }

                //Cobrando su dia de trabajo
                this.getCompania().getDrive().getSalarioAccMutex().acquire();
                this.getCompania().getDrive().setDirectorAcc(this.getCompania().getDrive().getDirectorAcc() + this.getSalario() * 24);
                this.getCompania().getDrive().getSalarioAccMutex().release();
                
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }
    
    
}