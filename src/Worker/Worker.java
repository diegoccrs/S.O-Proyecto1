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
public abstract class Worker extends Thread{
    private Company compania;
    private float salario;
    private float salarioAcumulado;
    private Semaphore mutex;
    private int tipo;
    private float CantidadDeTrabajoPorDia; //esta es la cantidad que se ira acumulando a la variable ACC
    
    /*TIPOS DE TRABAJADORES:
        animadores                = 0            $40
        guionistas                = 1            $20
        disenadores de escenarios = 2            $26
        actores de doblaje        = 3            $16
        guionistas de plottwist   = 4            $34
        ensambladores             = 5            $50
        Project manager           = 6            $40
        director                  = 7            $60
    */
    
    
    public Worker(int tipo,float salario, Semaphore mutex,Company compania) {
           this.salarioAcumulado = 0;
           this.salario = salario;
           this.mutex = mutex;
           this.tipo = tipo;
           this.compania = compania;
           this.CantidadDeTrabajoPorDia = obtainCantidadPorDia(); //cantidad de trabajo por dia
           
           
    }
    public float obtainCantidadPorDia(){
        Float numero_retorno = null;
        if(this.compania.getTipoCompania() == 0){//Disney: se tomara el carnet de Kevin, ultimo numero = 4
            if(this.getTipo() == 0){
                numero_retorno = (float) 2.0;
            }else if(this.getTipo() == 1){
                numero_retorno = (float) 0.34;
            }else if(this.getTipo() == 2){
                numero_retorno = (float) 0.34;
            }else if(this.getTipo() == 3){
                numero_retorno = (float) 3.0;
            }else if(this.getTipo() == 4){
                numero_retorno = (float) 0.34;
            }else if(this.getTipo() == 5){
                numero_retorno = (float) 0.5;
            }else if(this.getTipo() == 6){
                numero_retorno = (float) 1.0;
            }else if(this.getTipo() == 7){
                numero_retorno = (float) 1;
            }
        }else if(this.compania.getTipoCompania() == 1){//Cartoon network: se tomara el carnet de Diego, ultimo numero = 6
            if(this.getTipo() == 0){
                numero_retorno = (float) 1.0;
            }else if(this.getTipo() == 1){
                numero_retorno = (float) 0.25;
            }else if(this.getTipo() == 2){
                numero_retorno = (float) 0.25;
            }else if(this.getTipo() == 3){
                numero_retorno = (float) 5.0;
            }else if(this.getTipo() == 4){
                numero_retorno = (float) 0.34;
            }else if(this.getTipo() == 5){
                numero_retorno = (float) 0.5;
            }else if(this.getTipo() == 6){
                numero_retorno = (float) 0.5;
            }else if(this.getTipo() == 7){
                numero_retorno = (float) 1;
            }
        }
        
        return numero_retorno;
    }
    
    @Override
    public abstract void run();

    /**
     * @return the salario
     */
    public float getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

    /**
     * @return the salarioAcumulado
     */
    public float getSalarioAcumulado() {
        return salarioAcumulado;
    }

    /**
     * @param salarioAcumulado the salarioAcumulado to set
     */
    public void setSalarioAcumulado(float salarioAcumulado) {
        this.salarioAcumulado = salarioAcumulado;
    }

    /**
     * @return the mutex
     */
    public Semaphore getMutex() {
        return mutex;
    }

    /**
     * @param mutex the mutex to set
     */
    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the CantidadDeTrabajoPorDia
     */
    public float getCantidadDeTrabajoPorDia() {
        return CantidadDeTrabajoPorDia;
    }

    /**
     * @param CantidadDeTrabajoPorDia the CantidadDeTrabajoPorDia to set
     */
    public void setCantidadDeTrabajoPorDia(float CantidadDeTrabajoPorDia) {
        this.CantidadDeTrabajoPorDia = CantidadDeTrabajoPorDia;
    }

    /**
     * @return the compania
     */
    public Company getCompania() {
        return compania;
    }

    /**
     * @param compania the compania to set
     */
    public void setCompania(Company compania) {
        this.compania = compania;
    }

}