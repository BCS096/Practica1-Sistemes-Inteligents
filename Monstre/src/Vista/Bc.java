/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Data.Habitacio;
import Data.Tipus;
import Data.data;
import interfaces.EventEnum;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import monstre.BC;

/**
 *
 * @author tomeu
 */
public class Bc extends JFrame{
    private int xC;
    private int yC;
    private final Dimension sizeFrame = new Dimension(512, 512);
    private data datos;
    private int currentX = 0;
    private int currentY = 0;
    public int timer = 500;
    public Habitacio[][] cova;

    public Bc(int n, data datos) {
        this.datos = datos;
        this.setLayout(new GridLayout(n, n));
        this.setSize(sizeFrame);
        this.setMinimumSize(sizeFrame);
        this.setMaximumSize(sizeFrame);
        xC = sizeFrame.height / n; //xC es el tamaño que tiene cada habitacion
        yC = xC;
        cova = new Habitacio[datos.cova.length][datos.cova.length];
        inicializarCasillas();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }

    private void inicializarCasillas() {
        for (int j = datos.cova.length - 1; j >= 0; j--) {
            for (int i = 0; i < datos.cova.length; i++) {
                cova[i][j] = new Habitacio(Tipus.BUIT, Tipus.BUIT, Tipus.BUIT, Tipus.BUIT, Tipus.BUIT, true);
                cova[i][j].setHabitacio(xC);
                cova[i][j].setOpaque(true);
                cova[i][j].setSize(new Dimension(xC, yC));
                cova[i][j].setVisible(true);
                paintCasillas(cova[i][j], i, j);
                this.add(cova[i][j]);
            }

        }
    }

    private void paintCasillas(Habitacio casilla, int x, int y) {
        if ((y + x + 1) % 2 == 0) {
            casilla.setColor(new Color(165, 138, 138));
        } else {
            casilla.setColor(new Color(158, 158, 158));
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < cova.length; i++) {
            for (int j = 0; j < cova.length; j++) {
                cova[i][j].repaint();
            }
        }
    }

    /*
    * Gets the next available room in the arraylist and deletes it
     */
    private Habitacio nextRoom(ArrayList<Habitacio> camino) {
        int index = camino.size() - 1;
        Habitacio result = null;
        while ((result = camino.get(index--)) == null) {
            //Remove null room
            camino.remove(index + 1);
        }
        //As whe postincrement, we have the wrong index by 1, removing consulted valid room
        camino.remove(++index);
        return result;
    }

    public void pasarATablero(BC bc) {
        for (int i = 0; i < cova.length; i++) {
            for (int j = 0; j < cova.length; j++) {
                String key = "("+i+","+j+")";
                Habitacio hab = bc.bc1.get(key);
                if(hab != null){
                    cova[i][j].setMonstre(hab.getMonstre());
                    cova[i][j].setPrecipici(hab.getPrecipici());
                }  
            }
        }
    }
}
