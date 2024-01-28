/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Ventanas.Interfaz;

/**
 *
 * @author Kevin
 */
public class ManejadorInterfaz {
    private static Interfaz interfaz = new Interfaz();

    /**
     * @return the interfaz
     */
    public static Interfaz getInterfaz() {
        return interfaz;
    }

    /**
     * @param aInterfaz the interfaz to set
     */
    public static void setInterfaz(Interfaz aInterfaz) {
        interfaz = aInterfaz;
    }
}
