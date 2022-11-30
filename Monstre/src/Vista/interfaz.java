/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Data.data;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
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
    private JTextField numMonstres;
    private tablero cova;
    public Bc bc;
    private data datos;
    private Semaphore espera;
    private Dimension size = new Dimension(800, 600);
    private Font fuente = new Font("Courier", Font.BOLD, 24);   

    public interfaz(Semaphore espera) {
        this.espera = espera;
        this.setLayout(new GridLayout(4, 1));
        inicializar();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public tablero getTablero(){
        return this.cova;
    }

    private void inicializar() {
        this.setName("iniciCova");
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        Background background = new Background(new Dimension(600, 600));
        this.setLayout(new BorderLayout());
        JPanel interaccion = new JPanel();
        interaccion.setSize(new Dimension(200, 600));
        interaccion.setPreferredSize(new Dimension(200, 600));
        interaccion.setLayout(new FlowLayout());
        this.add(background, BorderLayout.WEST);
        JLabel askSize = new JLabel("Tamaño de la cueva:    ");
        resSize = new JTextField();
        resSize.setFont(fuente);
        resSize.setPreferredSize(new Dimension(30, 30));
        interaccion.add(askSize);
        interaccion.add(resSize);
        JLabel askPrecipici = new JLabel("Número de precipicis: ");
        numPrecipici = new JTextField();
        numPrecipici.setFont(fuente);
        numPrecipici.setPreferredSize(new Dimension(30, 30));
        interaccion.add(askPrecipici);
        interaccion.add(numPrecipici);
        JLabel askMonstre = new JLabel("Número de monstres: ");
        numMonstres = new JTextField();
        numMonstres.setFont(fuente);
        numMonstres.setPreferredSize(new Dimension(30, 30));
        interaccion.add(askMonstre);
        interaccion.add(numMonstres);
        JButton soluciones = new JButton("Mostrar cueva");
        soluciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                datos = new data(getSizeTablero());
                datos.numPrecipicis = getNumPrecipicis();
                datos.numMonstres = getNumMonstres();
                setVisible(false);
                dispose();
                cova = new tablero(getSizeTablero(),datos, espera);
                bc = new Bc(getSizeTablero(),datos);
                espera.release();
                cova.repaint();
            }
        });
        interaccion.add(soluciones);
        this.add(interaccion, BorderLayout.EAST);
    }

    public int getSizeTablero() {
        return Integer.parseInt(this.resSize.getText());
    }

    public int getNumPrecipicis() {
        return Integer.parseInt(this.numPrecipici.getText());
    }

    public int getNumMonstres() {
        return Integer.parseInt(this.numMonstres.getText());
    }
    public void repintar() {
        cova.repaint();
    }
    
    public data getData(){
        return datos;
    }
}
