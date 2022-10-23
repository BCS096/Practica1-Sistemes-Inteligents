/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Vista.Button;
import ascensor.Constantes;

/**
 *
 * @author tomeu, emanuel
 */
public class solicitudes implements Constantes{
    private Button [] solicitudes;
    
    public solicitudes (int tamaño){
        solicitudes = new Button[tamaño];
    }
    
    public int sig(int pisoActual){
        for (int i = pisoActual + 1; i < solicitudes.length; i++) {
            if(solicitudes[i].isActivado()){
                return i;
            }
        }
        return -1; 
    }
    
    public int pre(int pisoActual){
        for (int i = pisoActual - 1; i >= 0; i--) {
            if(solicitudes[i].isActivado()){
                return i;
            }
        }
        return -1;
    }
    
    public int max (int pisoActual){
        for (int i = solicitudes.length - 1; i > pisoActual; i--) {
            if(solicitudes[i].isActivado()){
                return i;
            }
        }
        return -1;
    }
    
    public int min (int pisoActual){
        for (int i = 0; i < pisoActual; i++) {
            if(solicitudes[i].isActivado()){
                return i;
            }
        }
        return -1;
    }
    
    public Button getBoton (int pos){
        return solicitudes[pos];
    }
    
    public void setBoton (int pos,Button boton){
        solicitudes[pos] = boton;
    }
    
    public int longitud (){
        return solicitudes.length;
    }
}
