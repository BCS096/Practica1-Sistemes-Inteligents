/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monstre;

import Data.data;
import Vista.interfaz;
import java.util.concurrent.Semaphore;

/**
 *
 * @author emanu
 */
public class covaMonstre {
    
    static Semaphore espera = new Semaphore(0);
    
        public static void main(String[] args) throws InterruptedException {
            interfaz cova = new interfaz(espera);
            espera.acquire();
            data datos = cova.getData();
            datos.elegirPrecipicis = true;   
            espera.acquire();
            datos.elegirMonstre = true;
            espera.acquire();
            System.out.println("A partir de aqui se genera el codigo para que el agente se mueva");        
    }
    
}
