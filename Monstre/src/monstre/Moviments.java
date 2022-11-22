/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monstre;

/**
 *
 * @author tomeu
 */
public class Moviments {
    int numMoviment;
    public Moviments(){
        numMoviment = 0;
    }
    
    public int nouMovX (){
        switch(numMoviment){
            case 0 -> {
                return 1;
            }
            case 1 -> {
                return 0;
            }
            case 2 -> {
                return -1;
            }
            case 3 -> {
                return 0;
            }
        }
        return -2;
    }
    
    public int nouMovY (){
        switch(numMoviment){
            case 0 -> {
                return 0;
            }
            case 1 -> {
                return 1;
            }
            case 2 -> {
                return 0;
            }
            case 3 -> {
                return -1;
            }
        }
        return -2;
    }
    
    public void nouMoviment(){
        numMoviment ++;
    }
}
