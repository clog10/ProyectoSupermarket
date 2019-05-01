package backend.supermercado;

import backend.main.RandomGenerator;

/**
 *
 * @author Alonso
 */
public final class Carrito extends Contenedor {
    
    public Carrito()
    {
        máximoProductos = 50;
        int cant = RandomGenerator.getRandom(30, máximoProductos) + 1;
        this.productos = new Producto[cant];
        for (int i = 0; i < cant; i++)
        {
            this.productos[i] = new Producto();
            precioTotal += this.productos[i].getPrecio();
        }
    }
}