
package Vista;

import Data.data;
import ascensor.Constantes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author tomeu,emanuel
 */
public class Panel extends JPanel implements Constantes {

    private data datos;
    
    public Panel(data datos) {
        this.setPreferredSize(new Dimension(10, 10));
        this.setBounds(400, 240, 100, PISOS * 60 + 10);
        this.datos = datos;
        int aux = 250;
        datos.botonesPanel.setBoton(3,new Button(new Rectangle2D.Float(425, aux, 50, 50), 3,"tres.png","tresR.png"));
        aux += 60;
        datos.botonesPanel.setBoton(2,new Button(new Rectangle2D.Float(425, aux, 50, 50), 2,"dos.png","dosR.png"));
        aux += 60;
        datos.botonesPanel.setBoton(1,new Button(new Rectangle2D.Float(425, aux, 50, 50), 1,"uno.png","unoR.png"));
        aux += 60;
        datos.botonesPanel.setBoton(0,new Button(new Rectangle2D.Float(425, aux, 50, 50), 0,"cero.png","ceroR.png"));
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
       Graphics g2d = (Graphics2D) g;
        g2d.drawImage(redimensionarImagen(new ImageIcon("background.jpg"), this.getWidth(), this.getHeight()), 276, 71, null);
        g.setColor(new Color(52, 52, 50));
        g.fillRect(400, 240, 100, 60 * PISOS + 10);
        for (int i = 0; i < datos.botonesPanel.longitud(); i++) {
            datos.botonesPanel.getBoton(i).paint(g);
        }
    }
        private Image redimensionarImagen(ImageIcon imagen, int ancho, int alto) {
        Image imgEscalada = imagen.getImage().getScaledInstance(ancho,
                alto, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(imgEscalada).getImage();
    }
}
