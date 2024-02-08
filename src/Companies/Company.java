/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Companies;

import Drive.Drive;
import EDD.ListaDoble;
import EDD.Nodo;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Kevin
 */
public class Company {
    private int duracionDia;
    private int tipoCompania;
    private Drive drive;
    private float utilidades; //Utilidad
    private ListaDoble ensambladores; //ensambladores
    private ListaDoble animadores; //animadores
    private ListaDoble disenadoresEscenarios; //disenadores de escenarios
    private ListaDoble guionistas; //guionistas
    private ListaDoble actoresDoblaje; //Actores de doblaje
    private ListaDoble guionistasPlotTwist; //guionistas de plotTwist

    public Company() {
    }
    
    public Company(Drive drive,int companyType) {
        this.tipoCompania = companyType;
        this.drive = drive;
        this.utilidades = 0;
        this.animadores = new ListaDoble();
        this.ensambladores = new ListaDoble();
        this.disenadoresEscenarios = new ListaDoble();
        this.guionistas = new ListaDoble();
        this.actoresDoblaje = new ListaDoble();
        this.guionistasPlotTwist = new ListaDoble();
    }
    
    public int cantidadTrabajadores(){
        int respuesta = ensambladores.getSize()+
                        animadores.getSize()+
                        disenadoresEscenarios.getSize()+
                        guionistas.getSize()+
                        actoresDoblaje.getSize()+
                        guionistasPlotTwist.getSize();
        return respuesta;
    }

    /**
     * @return the tipoCompania
     */
    public int getTipoCompania() {
        return tipoCompania;
    }

    /**
     * @param tipoCompania the tipoCompania to set
     */
    public void setTipoCompania(int tipoCompania) {
        this.tipoCompania = tipoCompania;
    }

    /**
     * @return the drive
     */
    public Drive getDrive() {
        return drive;
    }

    /**
     * @param drive the drive to set
     */
    public void setDrive(Drive drive) {
        this.drive = drive;
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
    public void setUtilidades(float utilidades) {
        this.utilidades = utilidades;
    }

    /**
     * @return the ensambladores
     */
    public ListaDoble getEnsambladores() {
        return ensambladores;
    }

    /**
     * @param ensambladores the ensambladores to set
     */
    public void setEnsambladores(ListaDoble ensambladores) {
        this.ensambladores = ensambladores;
    }

    /**
     * @return the animadores
     */
    public ListaDoble getAnimadores() {
        return animadores;
    }

    /**
     * @param animadores the animadores to set
     */
    public void setAnimadores(ListaDoble animadores) {
        this.animadores = animadores;
    }

    /**
     * @return the disenadoresEscenarios
     */
    public ListaDoble getDisenadoresEscenarios() {
        return disenadoresEscenarios;
    }

    /**
     * @param disenadoresEscenarios the disenadoresEscenarios to set
     */
    public void setDisenadoresEscenarios(ListaDoble disenadoresEscenarios) {
        this.disenadoresEscenarios = disenadoresEscenarios;
    }

    /**
     * @return the guionistas
     */
    public ListaDoble getGuionistas() {
        return guionistas;
    }

    /**
     * @param guionistas the guionistas to set
     */
    public void setGuionistas(ListaDoble guionistas) {
        this.guionistas = guionistas;
    }

    /**
     * @return the actoresDoblaje
     */
    public ListaDoble getActoresDoblaje() {
        return actoresDoblaje;
    }

    /**
     * @param actoresDoblaje the actoresDoblaje to set
     */
    public void setActoresDoblaje(ListaDoble actoresDoblaje) {
        this.actoresDoblaje = actoresDoblaje;
    }

    /**
     * @return the guionistasPlotTwist
     */
    public ListaDoble getGuionistasPlotTwist() {
        return guionistasPlotTwist;
    }

    /**
     * @param guionistasPlotTwist the guionistasPlotTwist to set
     */
    public void setGuionistasPlotTwist(ListaDoble guionistasPlotTwist) {
        this.guionistasPlotTwist = guionistasPlotTwist;
    }

    /**
     * @return the duracionDia
     */
    public int getDuracionDia() {
        return duracionDia;
    }

    /**
     * @param duracionDia the duracionDia to set
     */
    public void setDuracionDia(int duracionDia) {
        this.duracionDia = duracionDia;
    }
    
}