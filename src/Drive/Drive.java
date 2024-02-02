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
    
    //Capitulos que no se han enviado a las empresas
    private int capitulosPlotTwist = 0; // Capitulos PlotTwist
    private int capitulosEstandar = 0; //Capitulos Estandar
    
    //capitulos acumulados,es decir, contador de todos los capitulos creados
    private int capitulosPlotTwistAcumulados = 0; // Capitulos PlotTwist
    private int capitulosEstandarAcumulados = 0; //Capitulos Estandar
    
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
    
    //DATOS IMPORTANTES DE CADA EMPRESA
    private float ganancias = 0; 
    private float costosOperativos = 0;
    private float utilidades = 0;
    
    
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
    public void addSalary(int type,float salary,int tipoCompania){
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
        this.setCostosOperativos(this.getCostosOperativos() + salary,tipoCompania);
        this.setUtilidades(this.getUtilidades() - salary,tipoCompania);
    }
    
    //ESTE METODO AGREGA LAS PARTES QUE HA HECHO CADA TRABAJADOR DEPENDIENDO DE LA COMPANIA
    public void addPart(int tipoTrabajador,int tipoCompania){
        if(tipoCompania == 0){//Disney: ultimo numero del carnet de Kevin= 4
            if (tipoTrabajador == 0 && this.getAnimaciones() < this.getAnimacionesMax()){
                if(this.getAnimaciones() < 54){
                    this.setAnimaciones(this.getAnimaciones() + 2,tipoCompania);
                    //System.out.println("animaciones disponibles:" + this.getAnimaciones());
                }else{
                    this.setAnimaciones(this.getAnimaciones() + 1,tipoCompania);
                    //System.out.println("animaciones disponibles:" + this.getAnimaciones());
                }
            }else if(tipoTrabajador == 1 && this.getGuiones() < this.getGuionesMax()){
                this.setGuiones(this.getGuiones() + 1,tipoCompania);
                //System.out.println("guiones disponibles:" + this.getGuiones());
            }else if(tipoTrabajador == 2 && this.getEscenarios() < this.getEscenariosMax()){
                this.setEscenarios(this.getEscenarios() + 1,tipoCompania);
                //System.out.println("escenarios disponibles:" + this.getEscenarios());
            }else if(tipoTrabajador == 3 && this.getDoblajes() < this.getDoblajesMax()){
                if(this.getDoblajes() < 33){
                    this.setDoblajes(this.getDoblajes() + 3,tipoCompania);
                }else{
                    this.setDoblajes(this.getDoblajes() + 2,tipoCompania);
                }
                //System.out.println("doblajes disponibles:" + this.getDoblajes());
            }else if(tipoTrabajador == 4 && this.getGuionesPlotTwist() < this.getGuionesPlotTwistMax()){
                this.setGuionesPlotTwist(this.getGuionesPlotTwist() + 1,tipoCompania);
                //System.out.println("Guiones de PlotTwist disponibles:" + this.getGuionesPlotTwist());
            }
        }else if(tipoCompania == 1){//Cartoon Network: ultimo numero del carnet de Diego= 6
            if (tipoTrabajador == 0 && this.getAnimaciones() < this.getAnimacionesMax()){
                this.setAnimaciones(this.getAnimaciones() + 1,tipoCompania);
                //System.out.println("animaciones disponibles:" + this.getAnimaciones());
            }else if(tipoTrabajador == 1 && this.getGuiones() < this.getGuionesMax()){
                this.setGuiones(this.getGuiones() + 1,tipoCompania);
                //System.out.println("guiones disponibles:" + this.getGuiones());
            }else if(tipoTrabajador == 2 && this.getEscenarios() < this.getEscenariosMax()){
                this.setEscenarios(this.getEscenarios() + 2,tipoCompania);
                //System.out.println("escenarios disponibles:" + this.getEscenarios());
            }else if(tipoTrabajador == 3 && this.getDoblajes() < this.getDoblajesMax()){
                this.setDoblajes(this.getDoblajes() + 5,tipoCompania);
                //System.out.println("doblajes disponibles:" + this.getDoblajes());
            }else if(tipoTrabajador == 4 && this.getGuionesPlotTwist() < this.getGuionesPlotTwistMax()){
                this.setGuionesPlotTwist(this.getGuionesPlotTwist() + 1,tipoCompania);
                //System.out.println("Guiones de PlotTwist disponibles:" + this.getGuionesPlotTwist());
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
    public void setAnimaciones(int animaciones,int tipoCompania) {
        this.animaciones = animaciones;
        if(tipoCompania == 0){
            ManejadorInterfaz.getInterfaz().cambiarAnimacionesDisney(animaciones);
        }else if(tipoCompania == 1){
            ManejadorInterfaz.getInterfaz().cambiarAnimacionesCartoonNetwork(animaciones);
        }
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
    public void setGuiones(int guiones,int tipoCompania) {
        this.guiones = guiones;
        if(tipoCompania == 0){
            ManejadorInterfaz.getInterfaz().cambiarGuionesDisney(guiones);
        }else if(tipoCompania == 1){
            ManejadorInterfaz.getInterfaz().cambiarGuionesCartoonNetwork(guiones);
        }
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
    public void setEscenarios(int escenarios,int tipoCompania) {
        this.escenarios = escenarios;
        if(tipoCompania == 0){
            ManejadorInterfaz.getInterfaz().cambiarEscenariosDisney(escenarios);
        }else if(tipoCompania == 1){
            ManejadorInterfaz.getInterfaz().cambiarEscenariosCartoonNetwork(escenarios);
        }
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
    public void setDoblajes(int doblajes,int tipoCompania) {
        this.doblajes = doblajes;
        if(tipoCompania == 0){
            ManejadorInterfaz.getInterfaz().cambiarDoblajesDisney(doblajes);
        }else if(tipoCompania == 1){
            ManejadorInterfaz.getInterfaz().cambiarDoblajesCartoonNetwork(doblajes);
        }
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
    public void setGuionesPlotTwist(int guionesPlotTwist,int tipoCompania) {
        this.guionesPlotTwist = guionesPlotTwist;
        if(tipoCompania == 0){
            ManejadorInterfaz.getInterfaz().cambiarGuionesPlowtTistDisney(guionesPlotTwist);
        }else if(tipoCompania == 1){
            ManejadorInterfaz.getInterfaz().cambiarGuionesPlowtTistCartoonNetwork(guionesPlotTwist);
        }
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
    public void setCapitulosPlotTwist(int capitulosPlotTwist,int tipoCompania) {
        this.capitulosPlotTwist = capitulosPlotTwist;
        ManejadorInterfaz.getInterfaz().cambiarCapitulosPlotTwistListos(capitulosPlotTwist, tipoCompania);
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
    public void setCapitulosEstandar(int capitulosEstandar,int tipoCompania) {
        this.capitulosEstandar = capitulosEstandar;
        ManejadorInterfaz.getInterfaz().cambiarCapitulosListos(capitulosEstandar, tipoCompania);
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
    public void setEstadorDirector(int estadorDirector,int tipoCompania) {
        this.estadorDirector = estadorDirector;
        if(estadorDirector == 0){
            ManejadorInterfaz.getInterfaz().cambiarActividadDirector("VIGILANDO", tipoCompania);
        }else{
            ManejadorInterfaz.getInterfaz().cambiarActividadDirector("TRABAJANDO", tipoCompania);
        }
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
    public void setPmEstado(int pmEstado,int tipoCompania) {
        this.pmEstado = pmEstado;
        if(pmEstado == 0){
            ManejadorInterfaz.getInterfaz().cambiarPMActividad("TRABAJANDO", tipoCompania);
        }else{
            ManejadorInterfaz.getInterfaz().cambiarPMActividad("VIENDO ANIME", tipoCompania);
        }
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
    public void setFaltas(int faltas,int tipoCompania) {
        this.faltas = faltas;
        ManejadorInterfaz.getInterfaz().cambiarFaltasPM(faltas, tipoCompania );
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
    public void setSalarioDescontado(int salarioDescontado, int tipoCompania) {
        this.salarioDescontado = salarioDescontado;
        ManejadorInterfaz.getInterfaz().cambiarSalarioDescontadoPM(salarioDescontado, tipoCompania);
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
    public void setEstadoDeadline(int estadoDeadlineNuevo,int tipoCompania) {
        System.out.println(estadoDeadlineNuevo);
        this.estadoDeadline = estadoDeadlineNuevo;
        ManejadorInterfaz.getInterfaz().cambiarDiasLanzamiento(estadoDeadline,tipoCompania);
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
        ManejadorInterfaz.getInterfaz().cambiarPasoDeDias(contadorPasoDeLosDias);
    }

    /**
     * @return the ganancias
     */
    public float getGanancias() {
        return ganancias;
    }

    /**
     * @param ganancias the ganancias to set
     */
    public void setGanancias(float ganancias,int tipoCompania) {
        this.ganancias = ganancias;
        ManejadorInterfaz.getInterfaz().cambiarGanancias(ganancias, tipoCompania);
    }

    /**
     * @return the costosOperativos
     */
    public float getCostosOperativos() {
        return costosOperativos;
    }

    /**
     * @param costosOperativos the costosOperativos to set
     */
    public void setCostosOperativos(float costosOperativos,int tipoCompania) {
        this.costosOperativos = costosOperativos;
        ManejadorInterfaz.getInterfaz().cambiarCostosOperativos(costosOperativos, tipoCompania);
    }

    /**
     * @return the utilidades
     */
    public float getUtilidades() {
        return utilidades;
    }

    /**
     * @param utilidades the utilidades to set
     */
    public void setUtilidades(float utilidades,int tipoCompania) {
        this.utilidades = utilidades;
        ManejadorInterfaz.getInterfaz().cambiarUtilidades(utilidades, tipoCompania);
    }

    /**
     * @return the capitulosPlotTwistAcumulados
     */
    public int getCapitulosPlotTwistAcumulados() {
        return capitulosPlotTwistAcumulados;
    }

    /**
     * @param capitulosPlotTwistAcumulados the capitulosPlotTwistAcumulados to set
     */
    public void setCapitulosPlotTwistAcumulados(int capitulosPlotTwistAcumulados) {
        this.capitulosPlotTwistAcumulados = capitulosPlotTwistAcumulados;
    }

    /**
     * @return the capitulosEstandarAcumulados
     */
    public int getCapitulosEstandarAcumulados() {
        return capitulosEstandarAcumulados;
    }

    /**
     * @param capitulosEstandarAcumulados the capitulosEstandarAcumulados to set
     */
    public void setCapitulosEstandarAcumulados(int capitulosEstandarAcumulados) {
        this.capitulosEstandarAcumulados = capitulosEstandarAcumulados;
    }

}
