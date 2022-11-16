
package ascensor;

import Data.data;
import Vista.View;
import Vista.elevator;

/**
 *
 * @author tomeu, emanuel
 */
public class Ascensor implements Constantes {

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
                    if (datos.botonesSubida.sig(ascensor.getPisoActual()) != -1 || datos.botonesPanel.sig(ascensor.getPisoActual()) != -1 || 
                            (datos.botonesBajada.max() + 1 > ascensor.getPisoActual() )) {
                        ascensor.setPisoActual(ascensor.getPisoActual() + 1);
                        pensar = false;
                    }else{
                        ascensor.setEstat(estado.BAJAR);
                    }
                }else{ // estat == estado.BAJAR
                    if (datos.botonesBajada.pre(ascensor.getPisoActual() - 1) != -1 || datos.botonesPanel.pre(ascensor.getPisoActual()) != -1 || 
                            (datos.botonesSubida.min() < ascensor.getPisoActual() && datos.botonesSubida.min() != -1)) {
                        ascensor.setPisoActual(ascensor.getPisoActual() - 1);
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
                if(ascensor.getEstat() == estado.SUBIR){
                    if(datos.botonesSubida.getBoton(ascensor.getPisoActual()).isActivado() || datos.botonesPanel.getBoton(ascensor.getPisoActual()).isActivado()){
                        return true;
                    }else if(datos.botonesBajada.max() + 1 == ascensor.getPisoActual() && datos.botonesSubida.max() < ascensor.getPisoActual() && 
                            datos.botonesPanel.max() < ascensor.getPisoActual()){
                        return true;
                    }
                }else{
                    if(datos.botonesBajada.getBoton(ascensor.getPisoActual() - 1).isActivado() || datos.botonesPanel.getBoton(ascensor.getPisoActual()).isActivado()){
                        return true;
                    }else if(datos.botonesSubida.min() == ascensor.getPisoActual()&& datos.botonesSubida.min() > ascensor.getPisoActual() && 
                            datos.botonesPanel.min() > ascensor.getPisoActual()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void simulacioAturada(elevator asc, View v) throws InterruptedException {
        for (int i = 0; i < asc.ANCHO_ASCENSOR / 2 - 1; i++) {
            asc.x2++;
            asc.restador++;
            Thread.sleep(50);
            v.repaint();
        }
        Thread.sleep(1000);
        for (int i = 0; i < asc.ANCHO_ASCENSOR / 2 - 1; i++) {
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
