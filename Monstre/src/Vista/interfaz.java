/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Data.data;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Semaphore;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author emanu
 */
public class interfaz extends JFrame {

    private JTextField resSize;
    private JTextField numPrecipici;
    private tablero cova;
    private data datos;
    private Semaphore espera;

    public interfaz(Semaphore espera) {
        this.espera = espera;
        this.setLayout(new GridLayout(4, 1));
        inicializar();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void inicializar() {
        JPanel tablero = new JPanel();
        tablero.setLayout(new GridLayout(1, 2));
        JLabel askSize = new JLabel("Tamaño de la cueva: ");
        resSize = new JTextField();
        tablero.add(askSize);
        tablero.add(resSize);
        JLabel askPrecipici = new JLabel("Número de precipicis: ");
        numPrecipici = new JTextField();
        tablero.add(askPrecipici);
        tablero.add(numPrecipici);
        this.add(tablero);
        JButton soluciones = new JButton("Mostrar cueva");
        soluciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                datos = new data(getSizeTablero());
                datos.numPrecipicis = getNumPrecipicis();
                cova = new tablero(getSizeTablero(),datos, espera);
                espera.release();
                cova.repaint();
            }
        });
        this.add(soluciones);
    }

    public int getSizeTablero() {
        return Integer.parseInt(this.resSize.getText());
    }

    public int getNumPrecipicis() {
        return Integer.parseInt(this.numPrecipici.getText());
    }

    public void repintar() {
        cova.repaint();
    }
    
    public data getData(){
        return datos;
    }
}
