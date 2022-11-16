
package Vista;

import Data.data;
import ascensor.Constantes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author tomeu, emanuel
 */
public class Board extends JPanel implements Constantes{
    private int tamañoPorPiso;
    private data datos;
    private elevator ascensor;
    public Board (data datos,elevator asc){
        ascensor = asc;
        ascensor.x1 = 75;
        ascensor.x2 = 75 + 125/2;
        this.tamañoPorPiso = ALTO / PISOS;
        ascensor.y = ALTO - tamañoPorPiso;
        this.datos = datos;
        this.setSize(ANCHO, ALTO);
        datos.botonesBajada.setBoton(2,new Button(new Rectangle2D.Float(50,tamañoPorPiso - 20, 20, 20), 3,"abajoVerde.png","abajoRojo.png"));
        datos.botonesBajada.setBoton(1,new Button(new Rectangle2D.Float(50,tamañoPorPiso * 2 - 20, 20, 20), 2,"abajoVerde.png","abajoRojo.png"));
        datos.botonesBajada.setBoton(0,new Button(new Rectangle2D.Float(50,tamañoPorPiso * 3 - 20, 20, 20), 1,"abajoVerde.png","abajoRojo.png"));
        datos.botonesSubida.setBoton(2,new Button(new Rectangle2D.Float(250,tamañoPorPiso * 2 - 20, 20, 20), 2,"arribaVerde.png","arribaRojo.png"));
        datos.botonesSubida.setBoton(1,new Button(new Rectangle2D.Float(250,tamañoPorPiso * 3 - 20, 20, 20),1,"arribaVerde.png","arribaRojo.png"));
        datos.botonesSubida.setBoton(0,new Button(new Rectangle2D.Float(250, ALTO - 20, 20, 20),0,"arribaVerde.png","arribaRojo.png"));
    }    
    @Override
    public void paint(Graphics g) {
        Graphics g2d = (Graphics2D) g;
        g2d.drawImage(redimensionarImagen(new ImageIcon("fondo.png"),125,ALTO), 75, 0, null);
        g.setFont(new Font("Monospaced", Font.BOLD, 36));
        int num = 0;
        for (int i = tamañoPorPiso/2; num < PISOS; i+= tamañoPorPiso) {
            g.drawString(String.valueOf(num), 70 + 125/2,ALTO - i);
            num++;
        }
        ascensor.paint(g);
        g2d.drawImage(redimensionarImagen(new ImageIcon("puerta.jpg"),ascensor.ANCHO_ASCENSOR/2 - ascensor.restador,tamañoPorPiso), ascensor.x1, ascensor.y, null);
        g2d.drawImage(redimensionarImagen(new ImageIcon("puerta.jpg"),ascensor.ANCHO_ASCENSOR/2 + 1, tamañoPorPiso), ascensor.x2, ascensor.y, null);
        g.setColor(Color.black);
        g.drawRect(ascensor.x1, ascensor.y, ascensor.ANCHO_ASCENSOR/2 - ascensor.restador, tamañoPorPiso);
        g.drawRect(ascensor.x2, ascensor.y, ascensor.ANCHO_ASCENSOR/2 + 1, tamañoPorPiso);
        g2d.drawImage(redimensionarImagen(new ImageIcon("pared.jpg"), 75, ALTO), 0, 0, null);
        g2d.drawImage(redimensionarImagen(new ImageIcon("pared.jpg"), 75, ALTO), 200, 0, null);
        g.drawLine(0, 0, 0, ALTO);
        g.drawLine(200, 0, 200, ALTO);
        g.drawLine(75, 0, 75, ALTO);
        g.drawLine(275, 0, 275, ALTO);
        for (int i = 0; i < PISOS - 1; i++) {
            g.drawLine(0, ALTO - (tamañoPorPiso * (i + 1)),75 ,  ALTO - (tamañoPorPiso * (i + 1)));
            g.drawLine(200, ALTO - (tamañoPorPiso * (i + 1)),200+75 ,  ALTO - (tamañoPorPiso * (i + 1)));
        }
        for (int i = 0; i < PISOS - 1; i++) {
            datos.botonesBajada.getBoton(i).paint(g);
            datos.botonesSubida.getBoton(i).paint(g);
        }
    }
    
    private Image redimensionarImagen(ImageIcon imagen,int ancho,int alto) {
        Image imgEscalada = imagen.getImage().getScaledInstance(ancho,
            alto, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(imgEscalada).getImage(); 
    }
}
