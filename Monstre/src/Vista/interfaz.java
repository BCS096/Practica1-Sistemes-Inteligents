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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author emanu
 */
public final class interfaz extends JFrame implements Runnable {

    private JTextField resSize;
    private tablero cova;
    public Bc mapa = null;
    private data datos;
    private JPanel contenedor;
    private Semaphore espera;
    public static boolean firstTresor = true;
    private Dimension size = new Dimension(800, 600);
    private Font fuente = new Font("Courier", Font.BOLD, 24);
    JPanel interaccion = new JPanel();

    public interfaz(Semaphore espera) {
        this.setName("Cueva del monstre");
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
        this.setName("Cueva del monstre");
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        contenedor = new JPanel();
        contenedor.setSize(new Dimension(600, 600));
        contenedor.setPreferredSize(new Dimension(600, 600));
        contenedor.setLayout(new GridLayout(0, 1));
        Background background = new Background(new Dimension(600, 600));
        this.setLayout(new BorderLayout());
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
        JButton soluciones = new JButton("Mostrar cueva");
        soluciones.addActionListener((ActionEvent ae) -> {
            try {
                nuevoTablero();
                interaccion.removeAll();
                inicializarCova();
            } catch (NumberFormatException e) {
                System.err.println("No es pot traduïr a número!");
            }
        });
        interaccion.add(soluciones);
        this.add(interaccion, BorderLayout.EAST);
    }

    private void inicializarCova() {
        JRadioButton precipicis = new JRadioButton("Precipicio");
        JRadioButton monstres = new JRadioButton("Monstruo");
        JRadioButton tresor = new JRadioButton("Tesoro");
        ButtonGroup opcions = new ButtonGroup();
        precipicis.addActionListener((ActionEvent e) -> {
            if (precipicis.isSelected()) {
                datos.elegirPrecipicis = true;
                datos.elegirMonstre = false;
                datos.elegirTresor = false;
            }
        });
        monstres.addActionListener((ActionEvent e) -> {
            if (monstres.isSelected()) {
                datos.elegirPrecipicis = false;
                datos.elegirMonstre = true;
                datos.elegirTresor = false;
            }
        });
        tresor.addActionListener((ActionEvent e) -> {
            if (tresor.isSelected() && firstTresor) {
                datos.elegirPrecipicis = false;
                datos.elegirMonstre = false;
                datos.elegirTresor = true;
            } else {
                opcions.clearSelection();
                tresor.setEnabled(false);
            }
        });
        JPanel radiosLabel = new JPanel();
        radiosLabel.setPreferredSize(new Dimension(150, 120));
        JPanel radios = new JPanel();

        radios.setPreferredSize(new Dimension(150, 100));
        radios.setLayout(new GridLayout(3, 0));
        radios.add(precipicis);
        radios.add(monstres);
        radios.add(tresor);

        opcions.add(precipicis);
        opcions.add(monstres);
        opcions.add(tresor);

        radiosLabel.add(new JLabel("Objeto:"));
        radiosLabel.add(radios);
        interaccion.add(radiosLabel);
        
        JPanel v = new JPanel();
        v.setPreferredSize(new Dimension(150, 250));
        v.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JSlider velocitat = new JSlider(JSlider.VERTICAL, 0, 1250, 750);
        velocitat.setSize(new Dimension(150, 200));
        velocitat.setMinorTickSpacing(125);
        velocitat.setMajorTickSpacing(250);
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(1250, new JLabel("-"));
        labels.put(1000, new JLabel("Lento"));
        labels.put(750, new JLabel("Normal"));
        labels.put(250, new JLabel("Rápido"));
        labels.put(0, new JLabel("+"));
        velocitat.setInverted(true);
        velocitat.setLabelTable(labels);

        velocitat.setPaintTicks(true);
        velocitat.setPaintLabels(true);
        velocitat.addChangeListener((ChangeEvent e) -> {
            cova.timer = velocitat.getValue();
        });
        
        v.add(new JLabel("Velocidad:"));
        v.add(velocitat);

        interaccion.add(v);

        JButton paso = new JButton("Un paso");
        paso.addActionListener((ActionEvent e) -> {
            try {
                tablero.mutex.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            monstre.covaMonstre.automatic = false;
            monstre.covaMonstre.pasito.release();
            tablero.mutex.release();
        });

        JButton iniciar = new JButton("Iniciar");
        iniciar.addActionListener((ActionEvent e) -> {
            monstre.covaMonstre.automatic = !monstre.covaMonstre.automatic;
            if (monstre.covaMonstre.automatic) {
                monstre.covaMonstre.pasito.release();
                iniciar.setText("Pausar");
            } else {
                iniciar.setText("Iniciar");
            }
        });
        
        JButton reset = new JButton("Reset");
        reset.addActionListener((ActionEvent e) -> {
            mapa.setVisible(false);
            mapa = null;
            nuevoTablero();
            interaccion.removeAll();
            inicializarCova();
            firstTresor = true;
            monstre.covaMonstre.automatic = false;
            // TODO: Esto bloquea el programa, quizá instanciar en su propio thread
            // me da la sensación de que el while(!solucion) del main
            // la computa constantemente hasta que el problema es resoluble
            // monstre.covaMonstre.reset();
        });

        JButton mostraBC = new JButton("Mostra mapa");
        mostraBC.addActionListener((ActionEvent e) -> {
            if (mapa != null) {
                mapa.setVisible(!mapa.isVisible());
            }
        });
        
        JPanel modo = new JPanel();
        modo.setPreferredSize(new Dimension(150, 150));
        modo.setLayout(new GridLayout(4, 0, 0, 10));
        modo.add(iniciar);
        modo.add(paso);
        modo.add(mostraBC);
        modo.add(reset);

        interaccion.add(modo);

        this.repaint();

        this.pack();
    }

    private void nuevoTablero() {
        datos = new data(getSizeTablero());
        datos.numPrecipicis = 0;
        datos.numMonstres = 0;
        contenedor.removeAll();
        cova = new tablero(getSizeTablero(), datos, espera, new Dimension(600, 600));
        contenedor.add(cova);
        this.repaint();
        this.pack();
        mapa = new Bc(getSizeTablero(), datos);
        mapa.setVisible(false);
        mapa.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        espera.release();
    }

    public int getSizeTablero() {
        return Integer.parseInt(this.resSize.getText());
    }

    public void repintar() {
        cova.repaint();
    }

    public data getData() {
        return datos;
    }

    public data getDatos() {
        return this.datos;
    }

    @Override
    public void run() {

    }
}
