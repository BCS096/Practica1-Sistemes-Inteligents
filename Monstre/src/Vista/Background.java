/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Dimension;
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
class Background extends JPanel{
    
    private int width;
    private int height;
    private Image image;
    public Background(Dimension size){
        this.setSize(size);
        this.setPreferredSize(size);
        this.image = null;
        try {
            this.image = ImageIO.read((new File("media/inici.jpg")));
        } catch (IOException ex) {
            Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.width = (int) size.getWidth();
        this.height = (int) size.getHeight();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image, 0, 0, this.width, this.height, null);
    }
}
