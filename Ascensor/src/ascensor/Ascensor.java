/*
hacer una cola en que se vayan gestionando las solicitudes de ascensor por FIFO
 */
package ascensor;

import Data.data;
import java.util.ArrayList;
import Vista.Button;
import Vista.View;

/**
 *
 * @author tomeu, emanuel
 */
public class Ascensor implements Constantes {

    /**
     * @param args the command line arguments
     */
    public enum estado {
        SUBIR, BAJAR
    }

    static boolean pensar = true;

    public static void main(String[] args) throws InterruptedException {
        data datos = new data();
        elevator ascensor = new elevator();
        ArrayList<Button> peticiones = new ArrayList();
        View view = new View(datos, ascensor, peticiones);
        while (true) {
            //aqui iria el algoritmo de la logica del ascensor
            if (!pensar) {
                moverAscensor(ascensor);
                Thread.sleep(ascensor.velocitat);
                view.repintar();
            } else {
                if (abrirPuerta(datos,ascensor)) {
                    simulacioAturada(ascensor, view);
                    datos.botonesPanel.solicitudes[ascensor.pisoActual].activado = false;
                    if(ascensor.pisoActual != 0){
                        datos.botonesBajada.solicitudes[ascensor.pisoActual - 1].activado = false;
                    }
                    if(ascensor.pisoActual != PISOS - 1){
                        datos.botonesSubida.solicitudes[ascensor.pisoActual].activado = false;
                    }
                }
                if (ascensor.estat == estado.SUBIR) {
                    if (datos.botonesSubida.sig(ascensor.pisoActual) != -1 || datos.botonesPanel.sig(ascensor.pisoActual) != -1) {
                        int a = datos.botonesSubida.sig(ascensor.pisoActual);
                        int b = datos.botonesPanel.sig(ascensor.pisoActual);
                        if(a == -1 && b != -1){
                           ascensor.pisoActual = b; 
                        }else if (b == -1 && a != -1){
                            ascensor.pisoActual = a;
                        }else if (a < b){
                            ascensor.pisoActual = a;
                        }else{
                            ascensor.pisoActual = b;
                        }                      
                        pensar = false;
                    }else if (datos.botonesBajada.sig(ascensor.pisoActual - 1) != -1){
                        ascensor.pisoActual = datos.botonesBajada.sig(ascensor.pisoActual - 1) + 1;
                        pensar = false;
                    }else{
                        ascensor.estat = estado.BAJAR;
                    }
                }else{ // estat == estado.BAJAR
                    if (datos.botonesBajada.pre(ascensor.pisoActual - 1) != -1 || datos.botonesPanel.pre(ascensor.pisoActual) != -1) {
                        int a = datos.botonesBajada.pre(ascensor.pisoActual - 1) + 1;
                        int b = datos.botonesPanel.pre(ascensor.pisoActual);
                        if(a == -1 && b != -1){
                           ascensor.pisoActual = b; 
                        }else if (b == -1 && a != -1){
                            ascensor.pisoActual = a;
                        }else if (a > b){
                            ascensor.pisoActual = a;
                        }else{
                            ascensor.pisoActual = b;
                        }
                        pensar = false;
                    }else if (datos.botonesSubida.pre(ascensor.pisoActual) != -1){
                        ascensor.pisoActual = datos.botonesSubida.pre(ascensor.pisoActual);
                        pensar = false;
                    }else{
                        ascensor.estat = estado.SUBIR;
                    }
                }    
                view.repintar();
            }
        }
    }

    public static boolean abrirPuerta(data datos,elevator ascensor){
        switch (ascensor.pisoActual) {
            case 0 -> {
                if(datos.botonesSubida.solicitudes[ascensor.pisoActual].activado || datos.botonesPanel.solicitudes[ascensor.pisoActual].activado){
                    return true;
                }
            }
            case PISOS - 1 -> {
                if(datos.botonesBajada.solicitudes[ascensor.pisoActual - 1].activado || datos.botonesPanel.solicitudes[ascensor.pisoActual].activado){
                    return true;
                }
            }
            default -> {
                if(datos.botonesSubida.solicitudes[ascensor.pisoActual].activado || datos.botonesPanel.solicitudes[ascensor.pisoActual].activado ||
                        datos.botonesBajada.solicitudes[ascensor.pisoActual - 1].activado){
                    return true;
                }
            }
        }
        return false;
    }
    public static void simulacioAturada(elevator asc, View v) throws InterruptedException {
        for (int i = 0; i < asc.ANCHO_ASCENSOR / 2; i++) {
            asc.x2++;
            asc.restador++;
            Thread.sleep(50);
            v.repaint();
        }
        Thread.sleep(1000);
        for (int i = 0; i < asc.ANCHO_ASCENSOR / 2; i++) {
            asc.x2--;
            asc.restador--;
            Thread.sleep(50);
            v.repaint();
        }
    }

    public static void moverAscensor(elevator asc) throws InterruptedException {
        if (asc.estat == estado.BAJAR) {
            asc.y++;
            asc.rec.y = asc.y;
            if (asc.y >= ALTO - (ALTO / PISOS * (asc.pisoActual + 1))) {
                pensar = true;
            }
        }
        if (asc.estat == estado.SUBIR) {
            asc.y--;
            asc.rec.y = asc.y;
            if (asc.y <= ALTO - (ALTO / PISOS * (asc.pisoActual + 1))) {
                pensar = true;
            }
        }
    }

}
