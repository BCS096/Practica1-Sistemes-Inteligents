/*
hacer una cola en que se vayan gestionando las solicitudes de ascensor por FIFO
 */
package ascensor;

import Data.data.data;
import java.util.ArrayList;

/**
 *
 * @author tomeu, emanuel
 */
public class Ascensor implements Constantes{

    /**
     * @param args the command line arguments
     */
    public enum estado {
        SUBIR, BAJAR, STOP
    }
    
    static boolean pensar = true;

    public static void main(String[] args) throws InterruptedException {
        data datos = new data();
        elevator ascensor = new elevator();
        ArrayList<Button> peticiones = new ArrayList();
        View view = new View(datos, ascensor, peticiones);

        Button aux = null;
        while (true) {
            //aqui iria el algoritmo de la logica del ascensor
            if (!peticiones.isEmpty() || aux != null) {
                if (pensar) {
                    aux = peticiones.get(0);//primero de la lista
                    
                    if (aux.numPiso > ascensor.pisoActual) {
                        ascensor.estat = estado.SUBIR;
                    } else if (aux.numPiso < ascensor.pisoActual) {
                        ascensor.estat = estado.BAJAR;
                    }
                    pensar = false;
                } else if (aux != null) { // l'ascensor es mou
                    moverAscensor(ascensor, aux);
                    Thread.sleep(20);
                    if(pensar){
                        aux.activado = false;
                        aux = null;
                        peticiones.remove(0); //lo borramos
                        simulacioAturada(ascensor,view);
                    }
                }

            }
            view.repintar();
        }
    }

    public static void simulacioAturada(elevator asc,View v) throws InterruptedException{
        for (int i = 0; i < asc.ANCHO_ASCENSOR/2; i++) {
          asc.x2++;
          asc.restador++;  
          Thread.sleep(50);
          v.repaint();
        }   
        Thread.sleep(1000);
        for (int i = 0; i < asc.ANCHO_ASCENSOR/2; i++) {
          asc.x2--;
          asc.restador--;  
          Thread.sleep(50);
          v.repaint();
        }  
    }
    public static void moverAscensor(elevator asc, Button piso) {
        if (asc.estat == estado.BAJAR) {
            asc.y++;
            asc.rec.y = asc.y;
            if (asc.y >= ALTO - (ALTO / PISOS * (piso.numPiso + 1))) {
                pensar = true;
                asc.pisoActual = piso.numPiso;
            }
        }
        if (asc.estat == estado.SUBIR) {
            asc.y--;
            asc.rec.y = asc.y;
            if (asc.y <= ALTO - (ALTO / PISOS * (piso.numPiso + 1))) {
                pensar = true;
                asc.pisoActual = piso.numPiso;
            }
        }
    }

}
