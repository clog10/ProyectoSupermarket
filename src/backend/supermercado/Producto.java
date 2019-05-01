/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.supermercado;
import backend.main.RandomGenerator;
/**
 *
 * @author Alonso
 */
public class Producto {
    
    private int precio;
    public Producto()
    {
        // Generar un precio aleatorio entre 1500 y 2000
        precio = RandomGenerator.getRandom(50, 200) * 10;
    }

    public int getPrecio() {
        return precio;
    }
    
}
