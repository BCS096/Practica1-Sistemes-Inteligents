/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

/**
 *
 * @author tomeu
 */

    public enum Tipus {
    SI("SI"),
    NO("NO"),
    POTSER("POTSER"),
    BUIT("BUIT");
    
    private final String type;
    
    Tipus(String type) {
        this.type = type;
    }
    
    public String getInstType() {
        return this.type;
    }
}
