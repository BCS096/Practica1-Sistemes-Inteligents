package monstre;

import Data.Habitacio;
import Data.Percepcions;
import Data.Tipus;
import Vista.Bc;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author tomeu
 */
public class BC {

    public ArrayList<Habitacio> visitades;
    public HashMap<Point, Habitacio> bc1;
    ArrayList<OR> bc2;
    //coordenadas X e Y relativas del agente
    int coordX;
    int coordY;

    public BC() {
        this.bc1 = new HashMap<>();
        this.bc2 = new ArrayList<>();
        this.visitades = new ArrayList();
        this.coordX = 0;
        this.coordY = 0;
    }

    public void aprender(Habitacio casilla) {
        Point[] mov = {new Point(coordX, (coordY - 1)), new Point(coordX, (coordY + 1)),
            new Point((coordX + 1), coordY), new Point((coordX - 1), coordY)};
        if (casilla.getHedor() == Tipus.NO) {
            for (Point mov1 : mov) {
                comprovarORs(Percepcions.MONSTRUO, mov1);
                poner(Percepcions.MONSTRUO, Tipus.NO, mov1);
            }
        } else {  // hedor == Tipus.SI
            ArrayList<Point> aux = new ArrayList<>();
            aux.addAll(Arrays.asList(mov));
            aux = comprovarPossiblesEliminacions(aux, Percepcions.MONSTRUO);
            bc2.add(new OR(Percepcions.MONSTRUO, aux));
            for (int i = 0; i < aux.size(); i++) {
                poner(Percepcions.MONSTRUO, Tipus.POTSER, aux.get(i));
            }

        }
        if (casilla.getBrisa() == Tipus.NO) {
            for (Point mov1 : mov) {
                comprovarORs(Percepcions.PRECIPICIO, mov1);
                poner(Percepcions.PRECIPICIO, Tipus.NO, mov1);
            }
        } else {  // brisa == Tipus.SI
            ArrayList<Point> aux = new ArrayList<>();
            aux.addAll(Arrays.asList(mov));
            aux = comprovarPossiblesEliminacions(aux, Percepcions.PRECIPICIO);
            bc2.add(new OR(Percepcions.PRECIPICIO, aux));
            for (int i = 0; i < aux.size(); i++) {
                poner(Percepcions.PRECIPICIO, Tipus.POTSER, aux.get(i));
            }
        }
        bc1.put(new Point(coordX, coordY), casilla);
    }

    private void poner(Percepcions per, Tipus type, Point coords) {
        Habitacio valor;
        valor = bc1.get(coords);
        if (valor == null) {
            valor = new Habitacio(Tipus.BUIT, Tipus.BUIT, Tipus.BUIT, Tipus.BUIT, Tipus.BUIT, false);
            bc1.put(coords, valor);
        }
        switch (per) {
            case MONSTRUO:
                if (!(type == Tipus.POTSER && (valor.getMonstre() == Tipus.NO || valor.getMonstre() == Tipus.SI))) {
                    valor.setMonstre(type);
                }
                break;
            case PRECIPICIO:
                if (!(type == Tipus.POTSER && (valor.getPrecipici() == Tipus.NO || valor.getPrecipici() == Tipus.SI))) {
                    valor.setPrecipici(type);
                }
                break;
        }
    }

    private ArrayList<Point> comprovarPossiblesEliminacions(ArrayList<Point> aux, Percepcions per) {
        //TO DO : aux.size puede cambiar durante el bucle, antes del bucle guardarla en una variable
        //mirar si en otro bucle pasa eso
        for (int i = 0; i < aux.size() && i >= 0; i++) {
            Habitacio hab = bc1.get(aux.get(i));
            switch (per) {
                case MONSTRUO:
                    if (hab != null && (hab.getMonstre() == Tipus.NO)) {
                        aux.remove(i);
                        i--;
                    }
                    break;
                case PRECIPICIO:
                    if (hab != null && (hab.getPrecipici() == Tipus.NO)) {
                        aux.remove(i);
                        i--;
                    }
                    break;
            }
        }
        return aux;
    }

    void setPosicioActual(int x, int y) {
        this.coordX = x;
        this.coordY = y;
    }

    public void comprovarORs(Percepcions per, Point coords) {
        Habitacio aux = bc1.get(coords);
        switch (per) {
            case MONSTRUO:
                if (aux != null && aux.getMonstre() == Tipus.POTSER) {
                    for (int i = 0; i < bc2.size(); i++) {
                        OR or = bc2.get(i);
                        if (or.tipus == per) {
                            for (int j = 0; j < or.habitacions.size(); j++) {
                                if (coords.equals(or.habitacions.get(j))) {
                                    or.habitacions.remove(j);
                                    if (or.habitacions.size() == 1) {
                                        Point newCoords = or.habitacions.get(0);
                                        Habitacio hab = bc1.get(newCoords);
                                        hab.setMonstre(Tipus.SI);
                                        hab.setPrecipici(Tipus.NO);
                                        comprovarORs(Percepcions.PRECIPICIO,newCoords);
                                        bc2.remove(i);
                                        break;
                                    }
                                }
                            }
                        }

                    }
                }
                break;
            case PRECIPICIO:
                if (aux != null && aux.getPrecipici() == Tipus.POTSER) {
                    for (int i = 0; i < bc2.size(); i++) {
                        OR or = bc2.get(i);
                        if (or.tipus == per) {
                            for (int j = 0; j < or.habitacions.size(); j++) {
                                if (coords.equals(or.habitacions.get(j))) {
                                    or.habitacions.remove(j);
                                    if (or.habitacions.size() == 1) {
                                        Point newCoords = or.habitacions.get(0);
                                        Habitacio hab = bc1.get(newCoords);
                                        hab.setPrecipici(Tipus.SI);
                                        hab.setMonstre(Tipus.NO);
                                        comprovarORs(Percepcions.MONSTRUO, newCoords);
                                        bc2.remove(i);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    public boolean moviment_viable(int x, int y) {
        Habitacio revisar = this.bc1.get(new Point(x, y));
        if (!visitades.contains(revisar)) { //La habitació no ha estat visitada encara
            //EL MOVIMIENTO ES VIABLE SIEMPRE QUE EN LA CASIILA PROPORCIONADA NO HAY NI MONSTRU NI PRECIPICIO Y NO HA SIDO VISITADA
            if (revisar.getMonstre() == Data.Tipus.NO && revisar.getPrecipici() == Data.Tipus.NO) {
                return true;
            }
        } //SI YA HA SIDO VISITADA PERO RECULAS? NO IMPLEMENTADO POR TANTO DE MOMENTO FALSE
        return false;
    }

    void mostrarBC() {
        System.out.println("******************* BC 1 ****************************\n");
        bc1.forEach((k,v) -> System.out.println("Habitacion "+k+" con la información\n\n"+v.getInfo()));
        System.out.println("******************* BC 2 ****************************\n");
        for (int i = 0; i < bc2.size(); i++) {
            System.out.println("         OR "+ (i + 1));
            for (int j = 0; j < bc2.get(i).habitacions.size(); j++) {
                System.out.println("    Habitació  "+bc2.get(i).habitacions.get(j));
            }
        }
    }

}
