/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.data;

import ascensor.Button;
import ascensor.Constantes;

/**
 *
 * @author tomeu, emanuel
 */
public class solicitudes implements Constantes{
    //aqui se implementarán los métodos sig(),pre() y como atributo serà un array de objetos
    //basicamente lo que habra en la clase datos, en vez de los array de buttons serán objetos 
    //solicitudes
    public Button [] solicitudes;
    
    public solicitudes (int tamaño){
        solicitudes = new Button[tamaño];
    }
    
    public int sig(int pisoActual){
        for (int i = pisoActual + 1; i < solicitudes.length; i++) {
            if(solicitudes[i].activado){
                return i;
            }
        }
        return -1; 
    }
    
    public int pre(int pisoActual){
        for (int i = pisoActual - 1; i <= 0; i--) {
            if(solicitudes[i].activado){
                return i;
            }
        }
        return -1;
    }
}
