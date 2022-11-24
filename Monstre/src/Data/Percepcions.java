/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

/**
 *
 * @author tomeu
 */
    public enum Percepcions {
    HEDOR("hedor"),
    BRISA("brisa"),
    RESPLANDOR("resplandor"),
    MONSTRUO("monstruo"),
    PRECIPICIO("precipicio");
    
    private final String type;
    
    Percepcions(String type) {
        this.type = type;
    }
    
    public String getInstType() {
        return this.type;
    }
}
