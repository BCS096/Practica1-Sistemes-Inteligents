/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author emanu
 */
enum tipus {
    AGENT("Agent"),
    BRISA("Brisa"),
    MONSTRE("Monstre"),
    PRECIPICI("Precipici");

    private final String type;

    tipus(String type) {
        this.type = type;
    }

    public String getInstType() {
        return this.type;
    }
}

public class Element extends JPanel {

    tipus tipo;

    public Element(tipus tipo) {
        this.tipo = tipo;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //TODO: Cada opción del sqitch tiene su propia imagen dependiendo de la casilla que sea
        Image img = null;
        try {
            switch (tipo) {
                case AGENT:
                    img = ImageIO.read(new File("media/agente.png"));
                    break;
                case BRISA:
                    break;
                case MONSTRE:
                    break;
                case PRECIPICI:
                    break;
                default:
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Element.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TODO: Instanciar la imagen a medida, es decir método para agrandar/disminuir y encajar en tamaño
    }
}
