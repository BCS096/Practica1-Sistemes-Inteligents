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
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.Semaphore;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author emanu
 */
public class interfaz extends JFrame {

    private JTextField resSize;
    private JTextField numPrecipici;
    private JTextField numMonstres;
    private tablero cova;
    public Bc mapa = null;
    private data datos;
    private JPanel contenedor;
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

    public tablero getTablero() {
        return this.cova;
    }

    private void inicializar() {
        this.setName("Cova del monstre");
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        contenedor = new JPanel();
        contenedor.setSize(new Dimension(600, 600));
        contenedor.setPreferredSize(new Dimension(600, 600));
        contenedor.setLayout(new GridLayout(0, 1));
        Background background = new Background(new Dimension(600, 600));
        this.setLayout(new BorderLayout());
        JPanel interaccion = new JPanel();
        interaccion.setSize(new Dimension(200, 600));
        interaccion.setPreferredSize(new Dimension(200, 600));
        interaccion.setLayout(new FlowLayout(FlowLayout.CENTER));
        contenedor.add(background);
        this.add(contenedor, BorderLayout.WEST);
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
        JPanel v = new JPanel();
        v.setLayout(new FlowLayout());
        JSlider velocitat = new JSlider(JSlider.VERTICAL, 0, 1000, 750);
        velocitat.setMinorTickSpacing(125);
        velocitat.setMajorTickSpacing(250);
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(1000, new JLabel("-"));
        labels.put(750, new JLabel("Lent"));
        labels.put(500, new JLabel("Normal"));
        labels.put(250, new JLabel("Ràpid"));
        labels.put(0, new JLabel("+"));
        velocitat.setInverted(true);
        velocitat.setLabelTable(labels);
        velocitat.setPaintTicks(true);
        velocitat.setPaintLabels(true);
        velocitat.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                cova.timer = velocitat.getValue();
            }
        });
        v.add(velocitat);
        interaccion.add(v);
        JButton soluciones = new JButton("Mostrar cueva");
        JButton paso = new JButton("Un paso");
        JButton auto = new JButton("Automático");
        paso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monstre.covaMonstre.automatic = false;
                monstre.covaMonstre.pasito.release();
            }
        });
        auto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!monstre.covaMonstre.automatic) {
                    monstre.covaMonstre.automatic = true;
                    monstre.covaMonstre.pasito.release();
                }
            }
        });
        soluciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                nuevoTablero();
                espera.release();
            }
        });
        JButton mostraBC = new JButton("Mostra mapa");
        JPanel modo = new JPanel();
        modo.setPreferredSize(new Dimension(150, 110));
        modo.setLayout(new GridLayout(3, 1, 0, 15));
        modo.add(new JPanel());
        modo.add(paso);
        modo.add(auto);
        interaccion.add(soluciones);
        interaccion.add(modo);
        interaccion.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0)));
        mostraBC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean s = mapa.isVisible() ? false : true;
                mapa.setVisible(s);
            }
        });
        interaccion.add(mostraBC);
        this.add(interaccion, BorderLayout.EAST);
    }

    private void nuevoTablero() {
        datos = new data(getSizeTablero());
        datos.numPrecipicis = getNumPrecipicis();
        datos.numMonstres = getNumMonstres();
        contenedor.removeAll();
        cova = new tablero(getSizeTablero(), datos, espera, new Dimension(600, 600));
        contenedor.add(cova);
        this.repaint();
        this.pack();
        mapa = new Bc(getSizeTablero(), datos);
        mapa.setVisible(false);
        mapa.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

    public data getData() {
        return datos;
    }
}
