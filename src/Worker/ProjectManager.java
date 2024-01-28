/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Worker;

import Companies.Company;
import Main.ManejadorInterfaz;
import java.util.concurrent.Semaphore;
import Ventanas.Interfaz;

/**
 *
 * @author Kevin
 */
public class ProjectManager extends Worker{
    
    public ProjectManager(int tipo, float salario, String nombre, Semaphore mutex, Company compania) {
        super(tipo, salario, nombre, mutex, compania);
    }

    @Override
    public void run() {
        while (true) {
            try {
                //NECESITAMOS OBTENER EL EQUIVALENTE A MEDIA HORA
                // Media hora = un dia entre 48
                double halfHour = this.getCompania().getDuracionDia() / 48;
                int counter = 0;

                // 16 horas al dia
                while (counter < 16) {

                    this.getCompania().getDrive().setPmEstado(0); //Actualizamos Su estado a Viendo Anime

                    //Si el director lo esta vigilando entonces
                    if (this.getCompania().getDrive().getEstadorDirector() == 0) {
                        //TODO: poner lo que va a hacer 
                        this.getCompania().getDrive().setFaltas(this.getCompania().getDrive().getFaltas() + 1);
                        this.getCompania().getDrive().setSalarioDescontado(this.getCompania().getDrive().getSalarioDescontado() + 100);
                        
                        //Le quitan su plata
                        this.getCompania().getDrive().getSalarioAccMutex().acquire();
                        this.getCompania().getDrive().setPmAcc(this.getCompania().getDrive().getPmAcc() - 100);
                        this.getCompania().getDrive().getSalarioAccMutex();

                    }

                    sleep(Math.round(halfHour));
                    this.getCompania().getDrive().setPmEstado(1);
                    sleep(Math.round(halfHour));
                    counter++;
                }

                this.getCompania().getDrive().setPmEstado(0);

                sleep(Math.round(halfHour * 16));
                
                this.getCompania().getDrive().getDiasMutex().acquire();
                this.getCompania().getDrive().setEstadoDeadline(this.getCompania().getDrive().getEstadoDeadline() - 1); //LE RESTA 1 AL DEADLINE
                //System.out.println("HOLA SOY EL PM");
                this.getCompania().getDrive().getDiasMutex().release();

                //Cobrando su dia de trabajo
                this.getCompania().getDrive().getSalarioAccMutex().acquire();
                this.getCompania().getDrive().setPmAcc(this.getCompania().getDrive().getPmAcc() + this.getSalario() * 24);
                this.getCompania().getDrive().getSalarioAccMutex().release();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
}
