/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Data.Habitacio;
import Data.Tipus;
import Data.data;
import interfaces.EventEnum;
import interfaces.Notify;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author emanu
 */
public class tablero extends JFrame implements MouseListener, Notify{
    private int xC;
    private int yC;
    private final Dimension sizeFrame = new Dimension(512, 512);
    private data datos;
    private Semaphore espera;
    private int currentX = 0;
    private int currentY = 0;

    public tablero(int n) {
        this.addMouseListener(this);
        this.setLayout(new FlowLayout());
        this.setSize(sizeFrame);
        this.setMinimumSize(sizeFrame);
        this.setMaximumSize(sizeFrame);
        //Background inicio = new Background(sizeFrame);
       // inicio.repaint();
       // this.add(inicio);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public tablero(int n, data datos, Semaphore espera) {
        this.espera = espera;
        this.datos = datos;
        this.addMouseListener(this);
        this.setLayout(new GridLayout(n, n));
        this.setSize(sizeFrame);
        this.setMinimumSize(sizeFrame);
        this.setMaximumSize(sizeFrame);
        xC = sizeFrame.height / n; //xC es el tamaño que tiene cada habitacion
        yC = xC;
        inicializarCasillas();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void inicializarCasillas() {
        int y = 0;
        for (int j = datos.cova.length - 1; j >= 0; j--) {
            int x = 0;
            for (int i = 0; i < datos.cova.length; i++) {  
                datos.cova[i][j] = new Habitacio(Tipus.NO, Tipus.NO, Tipus.NO, Tipus.NO, Tipus.NO, new Rectangle2D.Float(x, y, xC, yC));
                datos.cova[i][j].setHabitacio(xC);
                datos.cova[i][j].setOpaque(true);
                datos.cova[i][j].setSize(new Dimension(xC, yC));
                paintCasillas(datos.cova[i][j], i, j);
                this.add(datos.cova[i][j]);
                x += xC;
            }
            y += xC;
        }
    }

    private void paintCasillas(Habitacio casilla, int x, int y) {
            if ((y + x + 1) % 2 == 0) {
            casilla.setColor(new Color(250, 250, 171));
        } else {
            casilla.setColor(new Color(245, 245, 84));
        }
 
    }


    private int[] centroDe(JLabel c) {
        int xy[] = new int[2];
        xy[0] = c.getX() + (c.getSize().width / 2) + 10;
        xy[1] = c.getY() + (c.getSize().height / 2) + 30;
        return xy;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() - 8;
        int y = e.getY() - 30;
        boolean trobat = false;
        int i,j = 0;
        for (i = 0; i < datos.cova.length && !trobat; i++) {
            for (j = 0; j < datos.cova.length && !trobat; j++) {
                if(datos.cova[i][j].getRec().contains(x,y)){
                    trobat = true;
                }
            }
        }
        i--;
        j--;
        if(trobat){
            if(datos.elegirPrecipicis){
                datos.ponerPrecipicio(i,j);
                datos.numPrecipicis --;
                if(datos.numPrecipicis == 0){
                    espera.release();
                    datos.elegirPrecipicis = false;
                } 
            }else if (datos.elegirMonstre){
                datos.ponerMonstruo(i, j);
                datos.elegirMonstre = false;
                espera.release();
            }else if (datos.elegirTresor){
                datos.ponerTesoro(i, j);
                datos.elegirTresor = false;
                espera.release();
            }
        }
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void getHabitacio(int x, int y) {
        System.out.println("coor: "+x+","+y);
        x -= 9;
        y -= 32;
        x = x/(512/datos.cova.length);
        y = y/(512/datos.cova.length);
        System.out.println("hab: "+x+","+y);
        
    }
    
    @Override
    public void notify(EventEnum event, Habitacio h) {
        switch (event) {
            case MOVER:
                for(int i = 0; i < datos.cova.length; i++){
                    for(int j = 0; j < datos.cova[i].length; j++){
                        if(datos.cova[i][j].isAgente()){
                            datos.cova[i][j].setSprite();
                        }
                    }
                }
                h.setSprite(sprite.AGENT);
                this.repaint();
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
                }
                monstre.covaMonstre.step.release();
                
            }
                break;

            default:
                throw new AssertionError("Unexpected event in GUI: " + event.name());
        }
    }

    
}
