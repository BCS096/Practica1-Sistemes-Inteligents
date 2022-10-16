/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ascensor;

import Data.data.data;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author tomeu
 */
public class View  extends JFrame implements MouseListener{
    Board board;
    data datos;
    elevator ascensor;
    ArrayList pet;
    public View (int width, int height,data datos,elevator asc,ArrayList pet){
        this.pet = pet;
        this.datos = datos;
        ascensor = asc;
        this.setTitle("ASCENSOR");
        setSize(width, height + 50);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        board = new Board(width, height,datos,asc,pet);
        board.addMouseListener(this);
        add(board);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void repintar(){
        board.repaint();
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
            for (i = 0; i < 3 && !trobat; i++) {
                trobat = datos.botonesbajada[i].rec.contains(x, y);
                if(trobat){
                    bajada = true;
                    break;
                }
            }
            for (int j = 0; j < 3 && !trobat; j++) {
                trobat = datos.botonessubida[j].rec.contains(x, y);
                if(trobat){
                    bajada = false;
                    i = j;
                    break;
                }
            }
            if(trobat){
                if(bajada){
                    if(!pet.contains(datos.botonesbajada[i])){
                        System.out.println("lo meto");
                        pet.add(datos.botonesbajada[i]);
                        datos.botonesbajada[i].activado = true;
                    }
                    
                }else{
                    if(!pet.contains(datos.botonessubida[i])){
                        System.out.println("lo meto");
                        pet.add(datos.botonessubida[i]);
                        datos.botonessubida[i].activado = true;
                    } 
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
