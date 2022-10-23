/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import ascensor.Ascensor.estado;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import ascensor.Constantes;

/**
 *
 * @author tomeu, emanuel
 */
public class elevator implements java.io.Serializable,Constantes{
    public int x1;
    public int x2;
    public int y;
    public int restador = 0;
    private estado estat = estado.SUBIR;
    private int pisoActual = 0;
    public final int ANCHO_ASCENSOR = 125;
    private int velocitat = 10;
    private Rectangle2D.Float rec = new Rectangle2D.Float(ANCHO_ASCENSOR, ALTO - ALTO/PISOS, ANCHO_ASCENSOR, ALTO/PISOS);
    private Image image;

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRestador() {
        return restador;
    }

    public void setRestador(int restador) {
        this.restador = restador;
    }

    public estado getEstat() {
        return estat;
    }

    public void setEstat(estado estat) {
        this.estat = estat;
    }

    public int getPisoActual() {
        return pisoActual;
    }

    public void setPisoActual(int pisoActual) {
        this.pisoActual = pisoActual;
    }

    public int getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(int velocitat) {
        this.velocitat = velocitat;
    }

    public Rectangle2D.Float getRec() {
        return rec;
    }

    public void setRec(Rectangle2D.Float rec) {
        this.rec = rec;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    
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
