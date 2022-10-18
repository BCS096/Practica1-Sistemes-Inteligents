/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ascensor;

import Data.data.data;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author tomeu, emanuel
 */
public class Board extends JPanel implements Constantes{
    int tamañoPorPiso;
    data datos;
    elevator ascensor;
    ArrayList pet;
    public Board (data datos,elevator asc,ArrayList pet){
        this.pet = pet;
        ascensor = asc;
        ascensor.x1 = 125;
        ascensor.x2 = 125 + 125/2;
        this.tamañoPorPiso = ALTO / PISOS;
        ascensor.y = ALTO - tamañoPorPiso;
        this.datos = datos;
        this.setSize(ANCHO, ALTO);

        //boton de arriba
        datos.botonesBajada.solicitudes[2] = new Button(new Rectangle2D.Float(100,tamañoPorPiso - 20, 20, 20), 3,"boton verde.jpg","boton rojo.jpg");
        datos.botonesBajada.solicitudes[1] = new Button(new Rectangle2D.Float(100,tamañoPorPiso * 2 - 20, 20, 20), 2,"boton verde.jpg","boton rojo.jpg");
        datos.botonesBajada.solicitudes[0] = new Button(new Rectangle2D.Float(100,tamañoPorPiso * 3 - 20, 20, 20), 1,"boton verde.jpg","boton rojo.jpg");
        datos.botonesSubida.solicitudes[2] = new Button(new Rectangle2D.Float(100 + 200,tamañoPorPiso * 2 - 20, 20, 20), 2,"boton verde.jpg","boton rojo.jpg");
        datos.botonesSubida.solicitudes[1] = new Button(new Rectangle2D.Float(100 + 200,tamañoPorPiso * 3 - 20, 20, 20),1,"boton verde.jpg","boton rojo.jpg");
        datos.botonesSubida.solicitudes[0] = new Button(new Rectangle2D.Float(300, ALTO - 20, 20, 20),0,"boton verde.jpg","boton rojo.jpg");
    }    
    @Override
    public void paint(Graphics g) {
        
        g.setColor(Color.yellow);
        g.fillRect(125, 0, 125, ALTO);
        ascensor.paint(g);
        g.setColor(Color.red);
        g.fillRect(ascensor.x1, ascensor.y, ascensor.ANCHO_ASCENSOR/2 - ascensor.restador, tamañoPorPiso);
        g.fillRect(ascensor.x2, ascensor.y, ascensor.ANCHO_ASCENSOR/2 + 1, tamañoPorPiso);
        g.setColor(Color.black);
        g.drawRect(ascensor.x1, ascensor.y, ascensor.ANCHO_ASCENSOR/2 - ascensor.restador, tamañoPorPiso);
        g.drawRect(ascensor.x2, ascensor.y, ascensor.ANCHO_ASCENSOR/2 + 1, tamañoPorPiso);
        g.setColor(Color.green);
        g.fillRect(50, 0, 75, ALTO);
        g.fillRect(250, 0, 75, ALTO);
        g.setColor(Color.black);
        for (int i = 0; i < PISOS - 1; i++) {
            g.drawLine(50, ALTO - (tamañoPorPiso * (i + 1)),50+75 ,  ALTO - (tamañoPorPiso * (i + 1)));
            g.drawLine(250, ALTO - (tamañoPorPiso * (i + 1)),250+75 ,  ALTO - (tamañoPorPiso * (i + 1)));
        }
        for (int i = 0; i < PISOS - 1; i++) {
            datos.botonesBajada.solicitudes[i].paint(g);
            datos.botonesSubida.solicitudes[i].paint(g);
        }
    }
}
