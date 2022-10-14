/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ascensor;

import Data.data.data;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author tomeu
 */
public class Board extends JPanel{
    int alto;
    int ancho;
    int tamañoPorPiso;
    final int PISOS = 4;
    data datos;
    elevator ascensor;
    ArrayList pet;
    public Board (int ancho, int alto, data datos,elevator asc,ArrayList pet){
        this.pet = pet;
        ascensor = asc;
        ascensor.x = 125;
        this.tamañoPorPiso = alto / PISOS;
        ascensor.y = alto - tamañoPorPiso;
        this.datos = datos;
        this.setSize(ancho, alto);
        this.alto = alto;
        this.ancho = ancho;

        //boton de arriba
        datos.botonesbajada[2] = new Button(new Rectangle2D.Float(100,tamañoPorPiso - 20, 20, 20), 3);
        datos.botonesbajada[1] = new Button(new Rectangle2D.Float(100,tamañoPorPiso * 2 - 20, 20, 20), 2);
        datos.botonesbajada[0] = new Button(new Rectangle2D.Float(100,tamañoPorPiso * 3 - 20, 20, 20), 1);
        datos.botonessubida[2] = new Button(new Rectangle2D.Float(100 + 200,tamañoPorPiso * 2 - 20, 20, 20), 2);
        datos.botonessubida[1] = new Button(new Rectangle2D.Float(100 + 200,tamañoPorPiso * 3 - 20, 20, 20),1);
        datos.botonessubida[0] = new Button(new Rectangle2D.Float(300, alto - 20, 20, 20),0);
    }    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(50, 0, 75, alto);
        g.fillRect(250, 0, 75, alto);
        g.setColor(Color.black);
        for (int i = 0; i < 3; i++) {
            g.drawLine(50, alto - (tamañoPorPiso * (i + 1)),50+75 ,  alto - (tamañoPorPiso * (i + 1)));
            g.drawLine(250, alto - (tamañoPorPiso * (i + 1)),250+75 ,  alto - (tamañoPorPiso * (i + 1)));
        }
        for (int i = 0; i < 3; i++) {
            datos.botonesbajada[i].paint(g);
            datos.botonessubida[i].paint(g);
        }
        g.setColor(Color.yellow);
        g.fillRect(125, 0, 125, alto);
        g.setColor(Color.red);
        g.fillRect(125, ascensor.y, 125, tamañoPorPiso);
        g.setColor(Color.black);
        g.drawLine(125+125/2, ascensor.y, 125+125/2, ascensor.y + tamañoPorPiso);
    }
}
