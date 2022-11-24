/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
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

    public Habitacio(Tipus hedor, Tipus brisa, Tipus monstre, Tipus precipici, Tipus resplandor, Rectangle2D.Float rec) {
        this.hedor = hedor;
        this.brisa = brisa;
        this.monstre = monstre;
        this.precipici = precipici;
        this.resplandor = resplandor;
        this.rec = rec;
    }
    
    public Habitacio(Tipus hedor, Tipus brisa, Tipus monstre, Tipus precipici, Tipus resplandor) {
        this.hedor = hedor;
        this.brisa = brisa;
        this.monstre = monstre;
        this.precipici = precipici;
        this.resplandor = resplandor;
        this.rec = null;
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
}
