/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Vista.sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tomeu
 */
public class Habitacio extends JPanel {

    private Tipus hedor;
    private Tipus brisa;
    private Tipus monstre;
    private Tipus precipici;
    private Tipus resplandor;
    private Rectangle2D.Float rec;
    private Color color;
    private sprite sprite;
    boolean bc = false;
    private int i;
    private int j;

    private int size = 0;
    private Image img;

    public Habitacio(Tipus hedor, Tipus brisa, Tipus monstre, Tipus precipici, Tipus resplandor, Rectangle2D.Float rec) {
        this.hedor = hedor;
        this.brisa = brisa;
        this.monstre = monstre;
        this.precipici = precipici;
        this.resplandor = resplandor;
        this.rec = rec;
        this.sprite = null;
    }

    public Habitacio(Tipus hedor, Tipus brisa, Tipus monstre, Tipus precipici, Tipus resplandor, boolean bc) {
        this.hedor = hedor;
        this.brisa = brisa;
        this.monstre = monstre;
        this.precipici = precipici;
        this.resplandor = resplandor;
        this.rec = null;
        this.sprite = null;
        this.bc = bc;
//        if (bc) {
//            info1 = new JLabel();
//            info1.setForeground(Color.BLUE);
//            info2 = new JLabel();
//            info2.setForeground(Color.BLUE);
//            this.add(info1);
//            this.add(info2);
//        }
    }

    public void setIJ(int[] ij) {
        this.i = ij[0];
        this.j = ij[1];
    }

    public int getI() {
        return this.i;
    }

    public int getJ() {
        return this.j;
    }

    public void setSprite(sprite sprite) {
        this.sprite = sprite;
    }

    public void setSprite() {
        this.sprite = null;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rectangle2D.Float getRec() {
        return rec;
    }

    public void setRec(Rectangle2D.Float rec) {
        this.rec = rec;
    }

    public Habitacio() {
    }

    public Tipus getHedor() {
        return hedor;
    }

    public void setHedor(Tipus hedor) {
        this.hedor = hedor;
    }

    public Tipus getBrisa() {
        return brisa;
    }

    public void setBrisa(Tipus brisa) {
        this.brisa = brisa;
    }

    public Tipus getMonstre() {
        return monstre;
    }

    public void setMonstre(Tipus monstre) {
        this.monstre = monstre;
    }

    public Tipus getPrecipici() {
        return precipici;
    }

    public void setPrecipici(Tipus precipici) {
        this.precipici = precipici;
    }

    public Tipus getResplandor() {
        return resplandor;
    }

    public void setResplandor(Tipus resplandor) {
        this.resplandor = resplandor;
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        img = null;
        g.setColor(color);
        g.fillRect(0, 0, size, size);
        try {
            if (!this.isAgente()) {
                if (this.hedor == Tipus.SI) {
                    if (this.sprite == sprite.BRISA) {
                        this.sprite = sprite.COMBINE;
                    } else if (this.sprite == null) {
                        this.sprite = sprite.HEDOR;
                    }
                } else {
                    if (this.brisa == Tipus.SI) {
                        if (this.sprite == sprite.HEDOR) {
                            this.sprite = sprite.COMBINE;
                        } else if (this.sprite == null) {
                            this.sprite = sprite.BRISA;
                        }
                    }
                }
            }
            if (sprite != null) {
                switch (sprite) {
                    case AGENT:
                        img = ImageIO.read(new File("media/agente.png"));
                        break;
                    case MONSTRE:
                        img = ImageIO.read(new File("media/monstruo.png"));
                        break;
                    case PRECIPICI:
                        img = ImageIO.read(new File("media/precipicio.png"));
                        break;
                    case TRESOR:
                        img = ImageIO.read(new File("media/tesoro.png"));
                        break;
                    case ONEUP:
                        img = ImageIO.read(new File("media/1up.png"));
                        break;
                    case BRISA:
                        img = ImageIO.read(new File("media/brisa.png"));
                        break;
                    case HEDOR:
                        img = ImageIO.read(new File("media/toxic.png"));
                        break;
                    case COMBINE:
                        img = ImageIO.read(new File("media/toxicWind.png"));
                        break;
                    default:
                        break;
                }
                img = img.getScaledInstance(calculateSize(), calculateSize(), Image.SCALE_SMOOTH);
                g.drawImage(img, calculatePos(), calculatePos(), null);
            } else {
                if (bc) {
                    if (monstre == Tipus.BUIT && precipici == Tipus.BUIT) {
                        this.setBackground(Color.BLACK);
                    } else {
                        this.setBackground(color);
                    }
                    if (monstre != Tipus.BUIT) {
                        g.setColor(Color.BLACK);
                        String aux = "M -> " + monstre;
//                    info1.setText(aux);
                        g.drawChars(aux.toCharArray(), 0, aux.length(), 5, 30);
                    }
                    if (precipici != Tipus.BUIT) {
                        String aux = "P -> " + precipici;
                        g.drawChars(aux.toCharArray(), 0, aux.length(), 5, 50);
//                    info2.setText(aux);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("No existe la imagen!");
        }
    }

    private int calculateSize() {
        double temp = this.size * 0.25;
        return (int) Math.ceil(size - temp);
    }

    private int calculatePos() {
        return (int) Math.ceil((this.size - calculateSize()) / 2);
    }

    public String getInfo() {
        return "    hedor =" + hedor + "\n    brisa = " + brisa + "\n    monstre = " + monstre + "\n    precipici = " + precipici + "    resplandor = " + resplandor + "\n";
    }

    public String getInfoBc() {
        return "H->" + hedor + "\n" + "M->" + monstre;
    }

    public void setHabitacio(int size) {
        this.size = size;
    }

    public boolean isAgente() {
        return this.sprite == sprite.AGENT ? true : false;
    }

    public boolean isTesoro() {
        return this.sprite == sprite.TRESOR ? true : false;
    }
}
