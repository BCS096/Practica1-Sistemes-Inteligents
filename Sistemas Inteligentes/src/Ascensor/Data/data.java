
package Data;

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
