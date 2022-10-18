/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ascensor;

import Data.data.data;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author tomeu
 */
public class Panel extends JPanel implements Constantes {

    data datos;

    public Panel(data datos) {
        this.setSize(10, 10);
        this.datos = datos;
        int aux = 250;
        datos.botonesPanel.solicitudes[3] = new Button(new Rectangle2D.Float(425, aux, 50, 50), 3,"tres.png","tresR.png");
        aux += 60;
        datos.botonesPanel.solicitudes[2] = new Button(new Rectangle2D.Float(425, aux, 50, 50), 2,"dos.png","dosR.png");
        aux += 60;
        datos.botonesPanel.solicitudes[1] = new Button(new Rectangle2D.Float(425, aux, 50, 50), 1,"uno.png","unoR.png");
        aux += 60;
        datos.botonesPanel.solicitudes[0] = new Button(new Rectangle2D.Float(425, aux, 50, 50), 0,"cero.png","ceroR.png");
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(400, 240, 100, 60 * PISOS + 10);
        for (int i = 0; i < datos.botonesPanel.solicitudes.length; i++) {
            datos.botonesPanel.solicitudes[i].paint(g);
        }
    }
}
