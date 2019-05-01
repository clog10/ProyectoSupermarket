package backend.supermercado;

import backend.main.RandomGenerator;

/**
 *
 * @author Alonso
 */
public final class Canasta extends Contenedor {

    public Canasta() {
        máximoProductos = 30;
        int cant = RandomGenerator.getRandom(máximoProductos) + 1;
        this.productos = new Producto[cant];
        for (int i = 0; i < cant; i++) {
            this.productos[i] = new Producto();
            precioTotal += this.productos[i].getPrecio();
        }
    }
}
