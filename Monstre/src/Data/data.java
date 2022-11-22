
package Data;

import java.awt.Color;

/**
 *
 * @author emanu
 */
public class data {
    
    public Habitacio[][] cova;
    public boolean elegirPrecipicis;
    public boolean elegirMonstre;
    public int numPrecipicis;
    
    public data(int dim){
        cova = new Habitacio[dim][dim];
        elegirPrecipicis = false;
        elegirMonstre = false;
        numPrecipicis = 0;
    }

    public void ponerPrecipicio(int i, int j) {
        cova[i][j].setPrecipici(Tipus.SI);
        cova[i][j].setBackground(Color.red);
        if(i + 1 != cova.length){
            cova[i + 1][j].setBrisa(Tipus.SI);
        }
        if(i - 1 >= 0){
            cova[i - 1][j].setBrisa(Tipus.SI);
        }
        if(j + 1 != cova.length){
            cova[i][j + 1].setBrisa(Tipus.SI);
        }
        if(j - 1 >= 0){
            cova[i][j - 1].setBrisa(Tipus.SI);
        }
    }
    
    public void ponerMonstruo(int i, int j) {
        cova[i][j].setMonstre(Tipus.SI);
        cova[i][j].setBackground(Color.green);
        if(i + 1 != cova.length){
            cova[i + 1][j].setHedor(Tipus.SI);
        }
        if(i - 1 >= 0){
            cova[i - 1][j].setHedor(Tipus.SI);
        }
        if(j + 1 != cova.length){
            cova[i][j + 1].setHedor(Tipus.SI);
        }
        if(j - 1 >= 0){
            cova[i][j - 1].setHedor(Tipus.SI);
        }
    }
    
    public Habitacio percebre(int x, int y){
        if( x >= 0 && x < cova.length && y >= 0 && y < cova.length ){
            return cova[x][y];
        }
        return null;
    }
    
}
