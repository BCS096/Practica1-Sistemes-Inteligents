/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monstre;

import Data.Habitacio;
import Data.data;
import Data.Tipus;
import Vista.interfaz;
import java.util.concurrent.Semaphore;

/**
 *
 * @author emanu
 */
public class covaMonstre {
    
    static Semaphore espera = new Semaphore(0);
    static data datos;
    static BC bc = new BC();
    
        public static void main(String[] args) throws InterruptedException {
            interfaz cova = new interfaz(espera);
            espera.acquire();
            datos = cova.getData();
            datos.elegirPrecipicis = true;   
            espera.acquire();
            datos.elegirMonstre = true;
            espera.acquire();
            System.out.println("A partir de aqui se genera el codigo para que el agente se mueva");
            solucion(0,0);
    }
    
        
        public static boolean solucion (int x, int y){
            Habitacio percepcion = datos.percebre(x, y);          
            Moviments mov = new Moviments();
            boolean acabat = false;
            if(percepcion == null){ //si m'he donat un cop 
                return false;
            }
            if(percepcion.getResplandor() != Tipus.SI){
                bc.aprender(percepcion);
                for (int i = 0; i < 4 && !acabat; i++) {
                    //comunicar a la interfaz que ya no voy a estar en la casilla que estaba
                    int a = x + mov.nouMovX();
                    int b = y + mov.nouMovY();
                    if(bc.moviment_viable(percepcion)){
                        //comunicar a la interfaz de la nueva casilla a la que voy a estar
                        acabat = solucion(a,b);
                    }
                    mov.nouMoviment();
                }      
            }else{
                return true;
            }
            return false;
        }
}
