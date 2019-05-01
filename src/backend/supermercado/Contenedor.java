package backend.supermercado;

/**
 *
 * @author Alonso
 */
public abstract class Contenedor {

    protected Producto productos[];
    protected int precioTotal = 0;
    int máximoProductos = 0;

    public int getMaxProductos() {
        return máximoProductos;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public int getTotalProductos() {
        return productos.length;
    }
}
