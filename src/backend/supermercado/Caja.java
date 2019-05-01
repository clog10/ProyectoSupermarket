package backend.supermercado;

/**
 *
 * @author Alonso
 */
public class Caja {

    Controlador control; // Padre controlador
    private int recesoRestante = 0; // Turnos necesarios para abrir la caja de nuevo
    protected int colaEsperaMaxima; // Suma de los tiempos de espera de los clientes
    private int tamañoCola = 0; // Tamaño actual de la cola de clientes
    protected int maxArticulosPersona = 0; // Máximo de productos que se atienden en la caja por persona
    private int dinero = 0; // Total de dinero recaudado en la caja
    private int totalProductos = 0; // Total de productos procesados en la caja
    private int totalCompradores = 0; // Total de compradores que han pasado por la caja
    private int turnosRestantes = 0; // Turnos restantes para terminar de atender al cliente
    private boolean abierta = true; // Estado de la caja
    public Comprador primero = null; // Primer comprador en la ila

    public Caja(Controlador controlador) {
        maxArticulosPersona = 50;
        colaEsperaMaxima = 5;
        control = controlador;
    }

    public void agregarComprador(Comprador comprador) {
        if (primero == null) {
            primero = comprador;
            tamañoCola = 1;
        } else {
            Comprador c;
            for (c = primero; c.getSiguiente() != null; c = c.getSiguiente()) {
            }
            c.setSiguiente(comprador);
            tamañoCola++;
        }
    }

    public void atenderComprador() {
        if (primero != null) {
            dinero += primero.getContenedor().getPrecioTotal();
            totalProductos += primero.getContenedor().getTotalProductos();
            totalCompradores++;
            tamañoCola--;

            if (primero.getContenedor().getMaxProductos() == 50) {
                control.totalCarritos++;
            } else {
                control.totalCanastas++;
            }
            //Agregar el tiempo de espera del comprador atendido
            control.agregarTiempoComprador(primero, this);

            primero = primero.getSiguiente();
            if (primero != null) {
                turnosRestantes =
                        primero.getContenedor().getTotalProductos() / 10;
            } else {
                tamañoCola = 0;
                abierta = false;
                recesoRestante = 4; // Esperar 5 turnos para abrir
            }
        }
    }

    public void turno() {
        if (isAbierta()) {
            if (getTurnosRestantes() == 0) {
                atenderComprador();
            } else {
                turnosRestantes--;
            }
        } else {
            // Si el receso termina o la cola es mayor a 5 se abre la caja
            if (recesoRestante == 0 || tamañoCola > colaEsperaMaxima) {
                abierta = true;
            } else if (recesoRestante > 0) {
                recesoRestante--;
            }
        }
        if (primero != null)
            primero.aumentarTiempoEnFila();
    }

    public int getTiempoCerrada() {
        return recesoRestante;
    }

    public int getTamañoCola() {
        return tamañoCola;
    }

    public int getMaxArticulosPersona() {
        return maxArticulosPersona;
    }

    public int getDinero() {
        return dinero;
    }

    public int getTotalProductos() {
        return totalProductos;
    }

    public int getTotalCompradores() {
        return totalCompradores;
    }

    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    public boolean isAbierta() {
        return abierta;
    }
}
