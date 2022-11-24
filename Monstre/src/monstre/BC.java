/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monstre;

import Data.Habitacio;
import java.util.ArrayList;

/*
    private Tipus hedor;
    private Tipus brisa;
    private Tipus monstre;
    private Tipus precipici;
    private Tipus resplandor;
 */
public class BC {

    ArrayList visitades = new ArrayList<Habitacio>();

    void aprender(Habitacio percepcion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean moviment_viable(Habitacio percepcion) {
        if (!visitades.contains(percepcion)) { //La habitació no ha estat visitada encara
            //EL MOVIMIENTO ES VIABLE SIEMPRE QUE EN LA CASIILA PROPORCIONADA NO HAY NI MONSTRU NI PRECIPICIO Y NO HA SIDO VISITADA
            if (percepcion.getMonstre() == Data.Tipus.NO && percepcion.getPrecipici() == Data.Tipus.NO) {
                return true;
            }
        } //SI YA HA SIDO VISITADA PERO RECULAS? NO IMPLEMENTADO POR TANTO DE MOMENTO FALSE
        return false;
    }

}
