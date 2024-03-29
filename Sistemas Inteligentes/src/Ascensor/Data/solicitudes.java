
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
    
    public int max (){
        for (int i = solicitudes.length - 1; i >= 0; i--) {
            if(solicitudes[i].isActivado()){
                return i;
            }
        }
        return -1;
    }
    
    public int min (){
        for (int i = 0; i < solicitudes.length; i++) {
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
