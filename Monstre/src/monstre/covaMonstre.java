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
import Vista.Bc;
import Vista.interfaz;
import interfaces.EventEnum;
import java.awt.Point;
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
    static Bc repBc;
    public static Semaphore pasito = new Semaphore(0);
    public static boolean automatic = false;
    private static final boolean DEBUG = false;

    public static void main(String[] args) throws InterruptedException {
        cova = new interfaz(espera);
        Thread pintar = new Thread(cova);
        pintar.start();
        espera.acquire();
        repBc = cova.mapa;
        datos = cova.getDatos();
        while (!solucion(0, 0)) {
            bc.visitades = new ArrayList();
        }
        pintar.join();
    }
    
    public static void reset() throws InterruptedException {
        // Reiniciamos los semáforos
        espera = new Semaphore(0);
        step = new Semaphore(1);
        pasito = new Semaphore(0);
        
        repBc = cova.mapa;
        datos = cova.getDatos();
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
            bc.comprovarORs(Percepcions.MONSTRUO, new Point(x, y));
            bc.comprovarORs(Percepcions.PRECIPICIO, new Point(x, y));
            step.acquire();
            if (!automatic) {
                pasito.acquire();
            }
            cova.getTablero().notify(EventEnum.COP, bc.bc1.get(new Point(x, y)), bc.visitades);
            pasito.release();
            return false;

        }
        if (percepcion.getResplandor() != Tipus.SI) {
            bc.aprender(percepcion);
            step.acquire();
            if (!automatic) {
                pasito.acquire();
            }
            cova.getTablero().notify(EventEnum.MOVER, bc.bc1.get(new Point(x, y)), bc.visitades);
            repBc.pasarATablero(bc);
            repBc.repaint();
            if (DEBUG) {
                bc.mostrarBC();
            }
            for (int i = 0; i < 4 && !acabat; i++) {
                //comunicar a la interfaz que ya no voy a estar en la casilla que estaba, no hace falta graficamente
                int a = x + mov.nouMovX();
                int b = y + mov.nouMovY();
                if (bc.moviment_viable(a, b)) {
                    //comunicar a la interfaz de la nueva casilla a la que voy a estar
                    acabat = solucion(a, b);
                    step.acquire();
                    if (!automatic) {
                        pasito.acquire();
                    }
                    cova.getTablero().notify(EventEnum.MOVER, bc.bc1.get(new Point(x, y)), bc.visitades);
                }
                mov.nouMoviment();
            }
        } else {
            step.acquire();
            if (!automatic) {
                pasito.acquire();
            }

            cova.getTablero().notify(EventEnum.MOVER, bc.bc1.get(new Point(x, y)), bc.visitades);
            cova.getTablero().notify(EventEnum.FOUND, null, bc.visitades);
            return true;
        }
        step.acquire();
        if (!automatic) {
            //pasito.acquire();
        }

        cova.getTablero().notify(EventEnum.MOVER, bc.bc1.get(new Point(x, y)), bc.visitades);
        return acabat;
    }
}
