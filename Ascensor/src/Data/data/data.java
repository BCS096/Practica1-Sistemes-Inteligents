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
public class data implements Constantes {
    public solicitudes botonesSubida = new solicitudes(PISOS - 1);
    public solicitudes botonesBajada = new solicitudes(PISOS - 1);
    public solicitudes botonesPanel = new solicitudes(PISOS);
}
