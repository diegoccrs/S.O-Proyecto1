/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Drive;

import Main.ManejadorInterfaz;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Kevin
 */
public class Drive {
    // Partes de capitulos
    private int animaciones = 0; // Animaciones
    private int guiones = 0; //Guiones
    private int escenarios = 0; //Escenarios
    private int doblajes = 0; //Doblajes
    private int guionesPlotTwist = 0; // Guiones de PlotTwist
    
    //Capitulos
    private int capitulosPlotTwist = 0; // Capitulos PlotTwist
    private int capitulosEstandar = 0; //Capitulos Estandar
    
    //Semaforos
    private Semaphore salarioAccMutex = new Semaphore(1); //Este es para entrar al drive a gestionar los salarios
    private Semaphore diasMutex = new Semaphore(1); //Este es para entrar al drive a gestionar los dias
    
    private int deadline;//cantidad de dias que se tienen para entregar capitulos
    private int estadoDeadline;//ira disminuyendo de 1 en 1 hasta llegar a 0, luego se reiniciara.
    
    //Salario acumulado
    private float animadoresAcc = 0;
    private float guionistasAcc = 0;
    private float escenariosAcc = 0;
    private float doblajesAcc = 0;
    private float disenadoresGuionesPlotTwistAcc = 0;
    private float ensambladorAcc = 0;
    private float pmAcc = 0;
    private float directorAcc =0;
    
    //
    private int contadorPasoDeLosDias = 0;
    
    
    //Maximo de partes en el drive
    private float animacionesMax = 55;
    private float guionesMax = 25;
    private float escenariosMax = 20;
    private float doblajesMax = 35;
    private float guionesPlotTwistMax = 10;
    
    //Estatus del director: 0 = vigilando, 1 = trabajando
    private int estadorDirector = 1;
    
    //Estatus del PM: 0  = trabajando, 1 = viendo anime
    private int pmEstado = 0;
    private int faltas = 0;
    private int salarioDescontado = 0;
    
    
    public Drive(){
    }
    
    //ESTE METODO AGREGA EL SALARIO, AL ACUMULADO DE CADA TIPO DE TRABAJADOR
    public void addSalary(int type,float salary){
        if (type == 0){
            this.setAnimadoresAcc(this.getAnimadoresAcc() + salary*24);
        }else if(type == 1){
            this.setGuionistasAcc(this.getGuionistasAcc() + salary*24);
        }else if(type == 2){
            this.setEscenariosAcc(this.getEscenariosAcc() + salary*24);
        }else if(type == 3){
            this.setDoblajesAcc(this.getDoblajesAcc() + salary*24);
        }else if(type == 4){
            this.setDisenadoresGuionesPlotTwistAcc(this.getDisenadoresGuionesPlotTwistAcc() + salary*24);
        }else if(type == 5){
            this.setEnsambladorAcc(this.getEnsambladorAcc() + salary*24);
        }else if(type == 6){
            this.setPmAcc(this.getPmAcc() + salary*24);
        }else if(type == 7){
            this.setDirectorAcc(this.getDirectorAcc() + salary*24);
        }
    }
    
    //ESTE METODO AGREGA LAS PARTES QUE HA HECHO CADA TRABAJADOR. ESTE METODO APLICA IGUAL PARA TODAS LAS COMPANIAS
    public void addPart(int type){
        if (type == 0 && this.getAnimaciones() < this.getAnimacionesMax()){
            if(this.getAnimaciones() < 54){
                this.setAnimaciones(this.getAnimaciones() + 2);
                System.out.println("animaciones disponibles:" + this.getAnimaciones());
            }else{
                this.setAnimaciones(this.getAnimaciones() + 1);
                System.out.println("animaciones disponibles:" + this.getAnimaciones());
            }
        }else if(type == 1 && this.getGuiones() < this.getGuionesMax()){
            this.setGuiones(this.getGuiones() + 1);
            System.out.println("guiones disponibles:" + this.getGuiones());
        }else if(type == 2 && this.getEscenarios() < this.getEscenariosMax()){
            this.setEscenarios(this.getEscenarios() + 1);
            System.out.println("escenarios disponibles:" + this.getEscenarios());
        }else if(type == 3 && this.getDoblajes() < this.getDoblajesMax()){
            this.setDoblajes(this.getDoblajes() + 3);
            System.out.println("doblajes disponibles:" + this.getDoblajes());
        }else if(type == 4 && this.getGuionesPlotTwist() < this.getGuionesPlotTwistMax()){
            this.setGuionesPlotTwist(this.getGuionesPlotTwist() + 1);
            System.out.println("Guiones de PlotTwist disponibles:" + this.getGuionesPlotTwist());
        }
    }
    
    //ESTE METODO AGREGA LOS CAPITULOS AL DRIVE Y ELIMINA LAS PARTES QUE SE USARON
    //ES USADO POR LOS ENSAMBLADORES Y ES DIFERENTE PARA CADA COMPANIA
    public void addChapter(int tipoCompania){
        if(tipoCompania == 0){ //0 cartoon network
            this.setGuiones(this.getGuiones() - 1);
            this.setEscenarios(this.getEscenarios() - 2);
            this.setAnimaciones(this.getAnimaciones() - 6);
            this.setDoblajes(this.getDoblajes() - 5);
            if(((this.getCapitulosEstandar()+this.getCapitulosPlotTwist()+1)% 4 == 0) && (this.getGuionesPlotTwist() >= 1)){
                this.setGuionesPlotTwist(this.getGuionesPlotTwist() - 1);
                this.setCapitulosPlotTwist(this.getCapitulosPlotTwist() + 1);
                System.out.println("Capitulos PlotTwist disponibles:" + this.getCapitulosPlotTwist());
            }else{
                this.setCapitulosEstandar(this.getCapitulosEstandar() + 1);
                System.out.println("Capitulos Estandar disponibles:" + this.getCapitulosEstandar());
            }
        }else if(tipoCompania == 1){ // 1 = disney channel
            this.setGuiones(this.getGuiones() - 1);
            this.setEscenarios(this.getEscenarios() - 1);
            this.setAnimaciones(this.getAnimaciones() - 2);
            this.setDoblajes(this.getDoblajes() - 4);
            if(((this.getCapitulosEstandar()+this.getCapitulosPlotTwist()+1)% 3 == 0) && (this.getGuionesPlotTwist() >= 3)){
                this.setGuionesPlotTwist(this.getGuionesPlotTwist() - 3);
                this.setCapitulosPlotTwist(this.getCapitulosPlotTwist() + 1);
                System.out.println("Capitulos PlotTwist disponibles:" + this.getCapitulosPlotTwist());
            }else{
                this.setCapitulosEstandar(this.getCapitulosEstandar() + 1);
                System.out.println("Capitulos Estandar disponibles:" + this.getCapitulosEstandar());
            }
        }
    }

    /**
     * @return the animaciones
     */
    public int getAnimaciones() {
        return animaciones;
    }

    /**
     * @param animaciones the animaciones to set
     */
    public void setAnimaciones(int animaciones) {
        this.animaciones = animaciones;
    }

    /**
     * @return the guiones
     */
    public int getGuiones() {
        return guiones;
    }

    /**
     * @param guiones the guiones to set
     */
    public void setGuiones(int guiones) {
        this.guiones = guiones;
    }

    /**
     * @return the escenarios
     */
    public int getEscenarios() {
        return escenarios;
    }

    /**
     * @param escenarios the escenarios to set
     */
    public void setEscenarios(int escenarios) {
        this.escenarios = escenarios;
    }

    /**
     * @return the doblajes
     */
    public int getDoblajes() {
        return doblajes;
    }

    /**
     * @param doblajes the doblajes to set
     */
    public void setDoblajes(int doblajes) {
        this.doblajes = doblajes;
    }

    /**
     * @return the guionesPlotTwist
     */
    public int getGuionesPlotTwist() {
        return guionesPlotTwist;
    }

    /**
     * @param guionesPlotTwist the guionesPlotTwist to set
     */
    public void setGuionesPlotTwist(int guionesPlotTwist) {
        this.guionesPlotTwist = guionesPlotTwist;
    }

    /**
     * @return the capitulosPlotTwist
     */
    public int getCapitulosPlotTwist() {
        return capitulosPlotTwist;
    }

    /**
     * @param capitulosPlotTwist the capitulosPlotTwist to set
     */
    public void setCapitulosPlotTwist(int capitulosPlotTwist) {
        this.capitulosPlotTwist = capitulosPlotTwist;
    }

    /**
     * @return the capitulosEstandar
     */
    public int getCapitulosEstandar() {
        return capitulosEstandar;
    }

    /**
     * @param capitulosEstandar the capitulosEstandar to set
     */
    public void setCapitulosEstandar(int capitulosEstandar) {
        this.capitulosEstandar = capitulosEstandar;
    }

    /**
     * @return the salarioAccMutex
     */
    public Semaphore getSalarioAccMutex() {
        return salarioAccMutex;
    }

    /**
     * @param salarioAccMutex the salarioAccMutex to set
     */
    public void setSalarioAccMutex(Semaphore salarioAccMutex) {
        this.salarioAccMutex = salarioAccMutex;
    }

    /**
     * @return the animadoresAcc
     */
    public float getAnimadoresAcc() {
        return animadoresAcc;
    }

    /**
     * @param animadoresAcc the animadoresAcc to set
     */
    public void setAnimadoresAcc(float animadoresAcc) {
        this.animadoresAcc = animadoresAcc;
    }

    /**
     * @return the guionistasAcc
     */
    public float getGuionistasAcc() {
        return guionistasAcc;
    }

    /**
     * @param guionistasAcc the guionistasAcc to set
     */
    public void setGuionistasAcc(float guionistasAcc) {
        this.guionistasAcc = guionistasAcc;
    }

    /**
     * @return the escenariosAcc
     */
    public float getEscenariosAcc() {
        return escenariosAcc;
    }

    /**
     * @param escenariosAcc the escenariosAcc to set
     */
    public void setEscenariosAcc(float escenariosAcc) {
        this.escenariosAcc = escenariosAcc;
    }

    /**
     * @return the doblajesAcc
     */
    public float getDoblajesAcc() {
        return doblajesAcc;
    }

    /**
     * @param doblajesAcc the doblajesAcc to set
     */
    public void setDoblajesAcc(float doblajesAcc) {
        this.doblajesAcc = doblajesAcc;
    }

    /**
     * @return the disenadoresGuionesPlotTwistAcc
     */
    public float getDisenadoresGuionesPlotTwistAcc() {
        return disenadoresGuionesPlotTwistAcc;
    }

    /**
     * @param disenadoresGuionesPlotTwistAcc the disenadoresGuionesPlotTwistAcc to set
     */
    public void setDisenadoresGuionesPlotTwistAcc(float disenadoresGuionesPlotTwistAcc) {
        this.disenadoresGuionesPlotTwistAcc = disenadoresGuionesPlotTwistAcc;
    }


    /**
     * @return the animacionesMax
     */
    public float getAnimacionesMax() {
        return animacionesMax;
    }

    /**
     * @param animacionesMax the animacionesMax to set
     */
    public void setAnimacionesMax(float animacionesMax) {
        this.animacionesMax = animacionesMax;
    }

    /**
     * @return the guionesMax
     */
    public float getGuionesMax() {
        return guionesMax;
    }

    /**
     * @param guionesMax the guionesMax to set
     */
    public void setGuionesMax(float guionesMax) {
        this.guionesMax = guionesMax;
    }

    /**
     * @return the escenariosMax
     */
    public float getEscenariosMax() {
        return escenariosMax;
    }

    /**
     * @param escenariosMax the escenariosMax to set
     */
    public void setEscenariosMax(float escenariosMax) {
        this.escenariosMax = escenariosMax;
    }

    /**
     * @return the doblajesMax
     */
    public float getDoblajesMax() {
        return doblajesMax;
    }

    /**
     * @param doblajesMax the doblajesMax to set
     */
    public void setDoblajesMax(float doblajesMax) {
        this.doblajesMax = doblajesMax;
    }

    /**
     * @return the guionesPlotTwistMax
     */
    public float getGuionesPlotTwistMax() {
        return guionesPlotTwistMax;
    }

    /**
     * @param guionesPlotTwistMax the guionesPlotTwistMax to set
     */
    public void setGuionesPlotTwistMax(float guionesPlotTwistMax) {
        this.guionesPlotTwistMax = guionesPlotTwistMax;
    }

    /**
     * @return the estadorDirector
     */
    public int getEstadorDirector() {
        return estadorDirector;
    }

    /**
     * @param estadorDirector the estadorDirector to set
     */
    public void setEstadorDirector(int estadorDirector) {
        this.estadorDirector = estadorDirector;
    }

    /**
     * @return the pmEstado
     */
    public int getPmEstado() {
        return pmEstado;
    }

    /**
     * @param pmEstado the pmEstado to set
     */
    public void setPmEstado(int pmEstado) {
        this.pmEstado = pmEstado;
    }

    /**
     * @return the faltas
     */
    public int getFaltas() {
        return faltas;
    }

    /**
     * @param faltas the faltas to set
     */
    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    /**
     * @return the salarioDescontado
     */
    public int getSalarioDescontado() {
        return salarioDescontado;
    }

    /**
     * @param salarioDescontado the salarioDescontado to set
     */
    public void setSalarioDescontado(int salarioDescontado) {
        this.salarioDescontado = salarioDescontado;
    }

    /**
     * @return the ensambladorAcc
     */
    public float getEnsambladorAcc() {
        return ensambladorAcc;
    }

    /**
     * @param ensambladorAcc the ensambladorAcc to set
     */
    public void setEnsambladorAcc(float ensambladorAcc) {
        this.ensambladorAcc = ensambladorAcc;
    }

    /**
     * @return the pmAcc
     */
    public float getPmAcc() {
        return pmAcc;
    }

    /**
     * @param pmAcc the pmAcc to set
     */
    public void setPmAcc(float pmAcc) {
        this.pmAcc = pmAcc;
    }

    /**
     * @return the directorAcc
     */
    public float getDirectorAcc() {
        return directorAcc;
    }

    /**
     * @param directorAcc the directorAcc to set
     */
    public void setDirectorAcc(float directorAcc) {
        this.directorAcc = directorAcc;
    }

    /**
     * @return the diasMutex
     */
    public Semaphore getDiasMutex() {
        return diasMutex;
    }

    /**
     * @param diasMutex the diasMutex to set
     */
    public void setDiasMutex(Semaphore diasMutex) {
        this.diasMutex = diasMutex;
    }

    /**
     * @return the deadline
     */
    public int getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    /**
     * @return the estadoDeadline
     */
    public int getEstadoDeadline() {
        return estadoDeadline;
    }

    /**
     * @param estadoDeadline the estadoDeadline to set
     */
    public void setEstadoDeadline(int estadoDeadlineNuevo) {
        if(estadoDeadlineNuevo == -1){ //SI ES IGUAL A -1, REINICIA EL CONTADOR
            this.estadoDeadline = deadline;
            ManejadorInterfaz.getInterfaz().cambiarDiasLanzamientoDisney(estadoDeadline);
        }else{ // SINO ES IGUAL A 0, SIGUE DISMINUYENDO DE 1 EN 1
            this.estadoDeadline = estadoDeadlineNuevo;
            ManejadorInterfaz.getInterfaz().cambiarDiasLanzamientoDisney(estadoDeadline);
        }
                
        
    }

    /**
     * @return the contadorPasoDeLosDias
     */
    public int getContadorPasoDeLosDias() {
        return contadorPasoDeLosDias;
    }

    /**
     * @param contadorPasoDeLosDias the contadorPasoDeLosDias to set
     */
    public void setContadorPasoDeLosDias(int contadorPasoDeLosDias) {
        this.contadorPasoDeLosDias = contadorPasoDeLosDias;
    }

}
