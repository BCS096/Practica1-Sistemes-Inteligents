
package Vista;

import Data.data;
import ascensor.Constantes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author tomeu, emanuel
 */
public class View extends JFrame implements MouseListener, KeyListener, Constantes {

    private Board board;
    private Panel panel;
    private data datos;
    private elevator ascensor;
    private infoPanel aux;

    public View(data datos, elevator asc) {
        this.datos = datos;
        ascensor = asc;
        panel = new Panel(datos);
        aux = new infoPanel();
        this.setTitle("ASCENSOR");
        setSize(ANCHO, ALTO + 50);
        this.setPreferredSize(new Dimension(ANCHO, ALTO + 50));
        setResizable(false);
        setLocationRelativeTo(null);
        board = new Board(datos, asc);
        board.addMouseListener(this);
        panel.addMouseListener(this);
        this.addKeyListener(this);
        add(board);
        add(aux);
        add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void repintar() {
        board.repaint();
        panel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            int x = e.getX();
            int y = e.getY();

            boolean trobat = false;
            boolean bajada = false;
            int i;
            for (i = 0; i < PISOS - 1 && !trobat; i++) {
                trobat = datos.botonesBajada.getBoton(i).getRec().contains(x, y);
                if (trobat) {
                    bajada = true;
                    break;
                }
            }
            for (int j = 0; j < PISOS - 1 && !trobat; j++) {
                trobat = datos.botonesSubida.getBoton(j).getRec().contains(x, y);
                if (trobat) {
                    bajada = false;
                    i = j;
                    break;
                }
            }
            if (trobat) {
                if (bajada) {
                    datos.botonesBajada.getBoton(i).setActivado(true);

                } else {
                    datos.botonesSubida.getBoton(i).setActivado(true);

                }
            } else { //para panel
                int j;
                for (j = 0; j < datos.botonesPanel.longitud(); j++) {
                    trobat = datos.botonesPanel.getBoton(j).getRec().contains(x, y);
                    if (trobat) {
                        break;
                    }
                }
                if (trobat) {
                    datos.botonesPanel.getBoton(j).setActivado(true);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 521 -> {
                if (ascensor.getVelocitat() > 1) {
                    ascensor.setVelocitat(ascensor.getVelocitat() - 1);
                }
            }
            case 45 -> ascensor.setVelocitat(ascensor.getVelocitat() + 1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
