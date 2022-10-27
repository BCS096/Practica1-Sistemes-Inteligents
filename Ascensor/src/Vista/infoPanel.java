/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import static ascensor.Constantes.ALTO;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import Data.data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JLabel;

/**
 *
 * @author tomeu
 */
public class infoPanel extends JPanel {
    
    JLabel text1;
    JLabel text2;
    
    public infoPanel() {
        text1 = new JLabel();
        text1.setText("Pitji la tecla '+' per aumentar la velocitat de l'ascensor");
        this.add(text1);
        text2 = new JLabel();
        text2.setText("Pitji la tecla '-' per disminuir la velocitat de l'ascensor");
        this.add(text2);
        this.setBounds(400, 20, 325, 50);
        this.setBackground(Color.red);
        
    }
}
