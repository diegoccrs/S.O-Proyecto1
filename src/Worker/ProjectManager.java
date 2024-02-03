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
 * @author Diego
 */
public class ProjectManager extends Worker{
    private float salarioAcumulado = this.getSalario() * 24;
    
    public ProjectManager(int tipo, float salario, Semaphore mutex, Company compania) {
        super(tipo, salario, mutex, compania);
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
                    this.getCompania().getDrive().setPmEstado(1,this.getCompania().getTipoCompania()); //Actualizamos Su estado a Viendo Anime

                    //Si el director lo esta vigilando entonces
                    if (this.getCompania().getDrive().getEstadorDirector() == 0) {
                        //TODO: poner lo que va a hacer 
                        this.getCompania().getDrive().setFaltas(this.getCompania().getDrive().getFaltas() + 1,this.getCompania().getTipoCompania());
                        this.getCompania().getDrive().setSalarioDescontado(this.getCompania().getDrive().getSalarioDescontado() + 100,this.getCompania().getTipoCompania());
                        this.salarioAcumulado = this.salarioAcumulado - 100;
                    }

                    sleep(Math.round(halfHour));
                    this.getCompania().getDrive().setPmEstado(0,this.getCompania().getTipoCompania());
                    sleep(Math.round(halfHour));
                    counter++;
                }

                this.getCompania().getDrive().setPmEstado(0,this.getCompania().getTipoCompania());

                sleep(Math.round(halfHour * 16));
                
                this.getCompania().getDrive().getDiasMutex().acquire();
                if(this.getCompania().getDrive().getEstadoDeadline() != 0){
                    this.getCompania().getDrive().setEstadoDeadline(this.getCompania().getDrive().getEstadoDeadline() - 1,this.getCompania().getTipoCompania()); //LE RESTA 1 AL DEADLINE,el dia 0 sera el director quien reiniciara el deadline
                }
                this.getCompania().getDrive().setContadorPasoDeLosDias(this.getCompania().getDrive().getContadorPasoDeLosDias() + 1);
                this.getCompania().getDrive().getDiasMutex().release();
                

                //Cobrando su dia de trabajo
                this.getCompania().getDrive().getSalarioAccMutex().acquire();
                this.getCompania().getDrive().setPmAcc(this.getCompania().getDrive().getPmAcc() + this.salarioAcumulado);//actualizamos lo acumulado por los PM
                this.getCompania().getDrive().setCostosOperativos(this.getCompania().getDrive().getCostosOperativos()+salarioAcumulado, this.getCompania().getTipoCompania());//actualizamos los costos operativos
                this.getCompania().getDrive().setUtilidades(this.getCompania().getDrive().getUtilidades() - salarioAcumulado, this.getCompania().getTipoCompania());//actualizamos las utilidades
                this.getCompania().getDrive().getSalarioAccMutex().release();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
