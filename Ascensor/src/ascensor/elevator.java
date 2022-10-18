/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ascensor;

import ascensor.Ascensor.estado;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

/**
 *
 * @author tomeu, emanuel
 */
public class elevator implements java.io.Serializable,Constantes{
    public int x1;
    public int x2;
    public int y;
    public int restador = 0;
    public estado estat = estado.SUBIR;
    public int pisoActual = 0;
    public final int ANCHO_ASCENSOR = 125;
    public Rectangle2D.Float rec = new Rectangle2D.Float(ANCHO_ASCENSOR, ALTO - ALTO/PISOS, ANCHO_ASCENSOR, ALTO/PISOS);
    Image image;
    
    
    public elevator(){
        ImageIcon img = redimensionarImagen(new ImageIcon("ascensor.jpg"));
        image = img.getImage();
    }
    
    private ImageIcon redimensionarImagen(ImageIcon imagen) {
        Image imgEscalada = imagen.getImage().getScaledInstance(ANCHO_ASCENSOR,
            ALTO/PISOS, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(imgEscalada); 
    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, (int) this.rec.x, (int) this.rec.y, null);
    }
}
