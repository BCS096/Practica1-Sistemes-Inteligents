/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.Color;
import javax.swing.JPanel;
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
        this.setBounds(276, 0, 520, 70);
        this.setBackground(new Color(148, 148, 142));
        
    }
}
