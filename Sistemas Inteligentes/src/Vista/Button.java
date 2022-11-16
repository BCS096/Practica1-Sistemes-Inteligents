
package Vista;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

/**
 *
 * @author tomeu, emanuel
 */
public class Button implements java.io.Serializable{
    private Image botonVerde;
    private Image botonRojo;
    private boolean activado;
    private Rectangle2D.Float rec;
    private int numPiso;
    
    
    public Button (Rectangle2D.Float rec,int num, String ima1, String ima2){
        numPiso = num;
        this.rec = rec;
        ImageIcon img = redimensionarImagen(new ImageIcon(ima1));
        botonVerde = img.getImage();
        img = redimensionarImagen(new ImageIcon(ima2));
        botonRojo = img.getImage();
        activado = false;
    }
    
    private ImageIcon redimensionarImagen(ImageIcon imagen) {
        Image imgEscalada = imagen.getImage().getScaledInstance((int)this.rec.height,
            (int)this.rec.width, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(imgEscalada); 
    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if(activado){
            g2d.drawImage(botonRojo, (int) this.rec.x, (int) this.rec.y, null);
        }else{
            g2d.drawImage(botonVerde, (int) this.rec.x, (int) this.rec.y, null);
        }        
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public Rectangle2D.Float getRec() {
        return rec;
    }

    public void setRec(Rectangle2D.Float rec) {
        this.rec = rec;
    }

    public int getNumPiso() {
        return numPiso;
    }

    public void setNumPiso(int numPiso) {
        this.numPiso = numPiso;
    }
    
}
