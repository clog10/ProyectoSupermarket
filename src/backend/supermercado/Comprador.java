package backend.supermercado;

import backend.main.RandomGenerator;

/**
 *
 * @author Alonso
 */
public class Comprador {

    // Canasta o carrito
    private Contenedor contenedor;
    private int tiempoEnFila = 0;
    private Comprador siguiente = null;

    public Comprador(int Canastas, int Carritos) {
        // Asignar canasta o carrito segun la disponibilidad
        if (RandomGenerator.getRandom(Canastas + Carritos) < Canastas) {
            contenedor = new Canasta();
        } else {
            contenedor = new Carrito();
        }
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public int getTiempoEnFila() {
        return tiempoEnFila;
    }

    public void aumentarTiempoEnFila() {
        // Sumar una unidad al tiempo que lleva en fila
        tiempoEnFila++;
        if (siguiente != null) {
            siguiente.aumentarTiempoEnFila();
        }
    }

    public Comprador getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Comprador siguiente) {
        this.siguiente = siguiente;
    }

    public int getTotalSiguientes() {
        if (this.siguiente == null) {
            return 0;
        } else {
            return 1 + siguiente.getTotalSiguientes();
        }
    }
}
