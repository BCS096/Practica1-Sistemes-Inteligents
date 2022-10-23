/*
hacer una cola en que se vayan gestionando las solicitudes de ascensor por FIFO
 */
package ascensor;

import Data.data;
import java.util.ArrayList;
import Vista.Button;
import Vista.View;
import Vista.elevator;

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
        View view = new View(datos, ascensor);
        while (true) {
            //aqui iria el algoritmo de la logica del ascensor
            if (!pensar) {
                moverAscensor(ascensor);
                Thread.sleep(ascensor.getVelocitat());
                view.repintar();
            } else {
                if (abrirPuerta(datos,ascensor)) {
                    simulacioAturada(ascensor, view);
                    datos.botonesPanel.getBoton(ascensor.getPisoActual()).setActivado(false);
                    if(ascensor.getPisoActual() != 0){
                        datos.botonesBajada.getBoton(ascensor.getPisoActual() - 1).setActivado(false);
                    }
                    if(ascensor.getPisoActual() != PISOS - 1){
                        datos.botonesSubida.getBoton(ascensor.getPisoActual()).setActivado(false);
                    }
                }
                if (ascensor.getEstat() == estado.SUBIR) {
                    if (datos.botonesSubida.sig(ascensor.getPisoActual()) != -1 || datos.botonesPanel.sig(ascensor.getPisoActual()) != -1) {
                        int a = datos.botonesSubida.sig(ascensor.getPisoActual());
                        int b = datos.botonesPanel.sig(ascensor.getPisoActual());
                        if(a == -1 && b != -1){
                           ascensor.setPisoActual(b); 
                        }else if (b == -1 && a != -1){
                            ascensor.setPisoActual(a);
                        }else if (a < b){
                            ascensor.setPisoActual(a);
                        }else{
                            ascensor.setPisoActual(b);
                        }                      
                        pensar = false;
                    }else if (datos.botonesBajada.max(ascensor.getPisoActual() - 1) != -1){
                        ascensor.setPisoActual(datos.botonesBajada.max(ascensor.getPisoActual() - 1) + 1);
                        pensar = false;
                    }else{
                        ascensor.setEstat(estado.BAJAR);
                    }
                }else{ // estat == estado.BAJAR
                    if (datos.botonesBajada.pre(ascensor.getPisoActual() - 1) != -1 || datos.botonesPanel.pre(ascensor.getPisoActual()) != -1) {
                        int a = datos.botonesBajada.pre(ascensor.getPisoActual() - 1) + 1;
                        int b = datos.botonesPanel.pre(ascensor.getPisoActual());
                        if(a == -1 && b != -1){
                           ascensor.setPisoActual(b);
                        }else if (b == -1 && a != -1){
                            ascensor.setPisoActual(a);
                        }else if (a > b){
                            ascensor.setPisoActual(a);
                        }else{
                            ascensor.setPisoActual(b);
                        }
                        pensar = false;
                    }else if (datos.botonesSubida.min(ascensor.getPisoActual()) != -1){
                        ascensor.setPisoActual(datos.botonesSubida.min(ascensor.getPisoActual()));
                        pensar = false;
                    }else{
                        ascensor.setEstat(estado.SUBIR);
                    }
                }    
                view.repintar();
            }
        }
    }

    public static boolean abrirPuerta(data datos,elevator ascensor){
        switch (ascensor.getPisoActual()) {
            case 0 -> {
                if(datos.botonesSubida.getBoton(ascensor.getPisoActual()).isActivado() || datos.botonesPanel.getBoton(ascensor.getPisoActual()).isActivado()){
                    return true;
                }
            }
            case PISOS - 1 -> {
                if(datos.botonesBajada.getBoton(ascensor.getPisoActual() - 1).isActivado() || datos.botonesPanel.getBoton(ascensor.getPisoActual()).isActivado()){
                    return true;
                }
            }
            default -> {
                if(datos.botonesSubida.getBoton(ascensor.getPisoActual()).isActivado() || datos.botonesPanel.getBoton(ascensor.getPisoActual()).isActivado() ||
                        datos.botonesBajada.getBoton(ascensor.getPisoActual() - 1).isActivado()){
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
        if (asc.getEstat() == estado.BAJAR) {
            asc.y++;
            asc.getRec().y = asc.y;
            if (asc.y >= ALTO - (ALTO / PISOS * (asc.getPisoActual() + 1))) {
                pensar = true;
            }
        }
        if (asc.getEstat() == estado.SUBIR) {
            asc.y--;
            asc.getRec().y = asc.y;
            if (asc.y <= ALTO - (ALTO / PISOS * (asc.getPisoActual() + 1))) {
                pensar = true;
            }
        }
    }

}
