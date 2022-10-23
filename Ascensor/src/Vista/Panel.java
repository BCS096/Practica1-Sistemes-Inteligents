/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Data.data;
import ascensor.Constantes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author tomeu,emanuel
 */
public class Panel extends JPanel implements Constantes {

    private data datos;

    public Panel(data datos) {
        this.setSize(10, 10);
        this.datos = datos;
        int aux = 250;
        datos.botonesPanel.setBoton(3,new Button(new Rectangle2D.Float(425, aux, 50, 50), 3,"tres.png","tresR.png"));
        aux += 60;
        datos.botonesPanel.setBoton(2,new Button(new Rectangle2D.Float(425, aux, 50, 50), 2,"dos.png","dosR.png"));
        aux += 60;
        datos.botonesPanel.setBoton(1,new Button(new Rectangle2D.Float(425, aux, 50, 50), 1,"uno.png","unoR.png"));
        aux += 60;
        datos.botonesPanel.setBoton(0,new Button(new Rectangle2D.Float(425, aux, 50, 50), 0,"cero.png","ceroR.png"));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(52, 52, 50));
        g.fillRect(400, 240, 100, 60 * PISOS + 10);
        for (int i = 0; i < datos.botonesPanel.longitud(); i++) {
            datos.botonesPanel.getBoton(i).paint(g);
        }
    }
}
