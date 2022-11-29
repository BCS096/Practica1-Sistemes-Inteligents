/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monstre;

import Data.Habitacio;
import Data.Percepcions;
import Data.data;
import Data.Tipus;
import Vista.interfaz;
import interfaces.EventEnum;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author emanu
 */
public class covaMonstre {

    static Semaphore espera = new Semaphore(0);
    static data datos;
    static BC bc = new BC();
    static interfaz cova;
    public static Semaphore step = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {
        cova = new interfaz(espera);
        espera.acquire();
        datos = cova.getData();
        datos.elegirPrecipicis = true;
        espera.acquire();
        datos.elegirMonstre = true;
        espera.acquire();
        datos.elegirTresor = true;
        espera.acquire();
        while (!solucion(0, 0)) {
            bc.visitades = new ArrayList();
        }

    }

    public static boolean solucion(int x, int y) throws InterruptedException {
        Habitacio percepcion = datos.percebre(x, y);
        bc.visitades.add(percepcion);
        Moviments mov = new Moviments();
        boolean acabat = false;
        bc.setPosicioActual(x, y);
        if (percepcion == null) { //si m'he donat un cop
            String s = "(" + x + "," + y + ")";
            bc.comprovarORs(Percepcions.MONSTRUO, s);
            bc.comprovarORs(Percepcions.PRECIPICIO, s);
            return false;

        }
        if (percepcion.getResplandor() != Tipus.SI) {
            bc.aprender(percepcion);
            step.acquire();
            cova.getTablero().notify(EventEnum.MOVER, bc.bc1.get("(" + x + "," + y + ")"), bc.visitades);
            bc.mostrarBC();
            for (int i = 0; i < 4 && !acabat; i++) {
                //comunicar a la interfaz que ya no voy a estar en la casilla que estaba, no hace falta graficamente
                int a = x + mov.nouMovX();
                int b = y + mov.nouMovY();
                if (bc.moviment_viable(a, b)) {
                    //comunicar a la interfaz de la nueva casilla a la que voy a estar
                    acabat = solucion(a, b);
                    step.acquire();
                    cova.getTablero().notify(EventEnum.MOVER, bc.bc1.get("(" + x + "," + y + ")"), bc.visitades);
                }
                mov.nouMoviment();
            }
        } else {
            cova.getTablero().notify(EventEnum.FOUND, null, bc.visitades);
            return true;
        }
        return acabat;
    }
}
