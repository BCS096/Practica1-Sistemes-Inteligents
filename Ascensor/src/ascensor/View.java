/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ascensor;

import Data.data.data;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author tomeu, emanuel
 */
public class View  extends JFrame implements MouseListener,Constantes{
    Board board;
    Panel panel;
    data datos;
    elevator ascensor;
    ArrayList pet;
    public View (data datos,elevator asc,ArrayList pet){
        this.pet = pet;
        this.datos = datos;
        ascensor = asc;
        panel = new Panel(datos);
        this.setTitle("ASCENSOR");
        setSize(ANCHO, ALTO + 50);
        this.setPreferredSize(new Dimension(ANCHO, ALTO + 50));
        setResizable(false);
        setLocationRelativeTo(null);
        //setLayout(null);
        board = new Board(datos,asc,pet);
        board.addMouseListener(this);
        panel.addMouseListener(this);
        add(board);
        add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void repintar(){
        board.repaint();
        panel.repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            int x = e.getX();
            int y = e.getY();
            
            boolean trobat = false;
            boolean bajada = false;
            int i;
            for (i = 0; i < PISOS - 1 && !trobat; i++) {
                trobat = datos.botonesBajada.solicitudes[i].rec.contains(x, y);
                if(trobat){
                    bajada = true;
                    break;
                }
            }
            for (int j = 0; j < PISOS - 1 && !trobat; j++) {
                trobat = datos.botonesSubida.solicitudes[j].rec.contains(x, y);
                if(trobat){
                    bajada = false;
                    i = j;
                    break;
                }
            }
            if(trobat){
                if(bajada){
                    if(!pet.contains(datos.botonesBajada.solicitudes[i])){
                        datos.botonesBajada.solicitudes[i].activado = true;
                    }
                    
                }else{
                    if(!pet.contains(datos.botonesSubida.solicitudes[i])){
                        datos.botonesSubida.solicitudes[i].activado = true;
                    } 
                }
            }
            else{ //para panel
                int j;
                for (j = 0; j < datos.botonesPanel.solicitudes.length; j++) {
                    trobat = datos.botonesPanel.solicitudes[j].rec.contains(x, y);
                    if(trobat){
                        break;
                    }
                }
                if(trobat){
                    datos.botonesPanel.solicitudes[j].activado = true;
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
}
