package backend.supermercado;

import backend.main.RandomGenerator;

public class Controlador {

    private Caja cajas[];
    private int tiemposDeEspera[];
    private int generadorComprador = 2;
    protected int totalCarritos = 0;
    protected int totalCanastas = 0;

    public Controlador(int compradoresPorTurno,
            int Carritos, int Canastas) {
        cajas = new Caja[10];
        tiemposDeEspera = new int[10];
        // Asignar las cajas y las cajas express
        for (int i = 0; i < cajas.length; i++) {
            if (i > 7) {
                cajas[i] = new CajaExpress(this);
            } else {
                cajas[i] = new Caja(this);
            }
        }
        generadorComprador = compradoresPorTurno;
        totalCanastas = Canastas;
        totalCarritos = Carritos;
    }

    // Ejecutar el turno
    public void siguienteTurno() {
        // Se selecciona una cantidad de nuevos compradores para este turno
        int generarEsteTurno = Math.min(totalCanastas + totalCarritos,
                RandomGenerator.getRandom(generadorComprador - 2,
                generadorComprador + 2));
        // Para cada comprador nuevo, se elige una caja
        for (int i = 0; i < generarEsteTurno; i++) {
            Comprador c = new Comprador(getTotalCanastas(), getTotalCarritos());
            if (c.getContenedor().getMaxProductos() == 30) {
                totalCanastas--;
            } else {
                totalCarritos--;
            }
            Caja mejorCaja = getCajas()[0];

            for (int j = 1; j < getCajas().length; j++) {
                if (c.getContenedor().getTotalProductos()
                        < getCajas()[j].getMaxArticulosPersona()
                        && getCajas()[j].getTamañoCola() <= mejorCaja.getTamañoCola()) {
                    mejorCaja = getCajas()[j];
                }
            }
            mejorCaja.agregarComprador(c);
        }
        // Se ejecuta el turno de cada caja individual
        for (int i = 0; i < getCajas().length; i++) {
            getCajas()[i].turno();
        }
    }

    // Agregar el tiempo de espera de un comprador al registro
    public void agregarTiempoComprador(Comprador comprador, Caja caja) {
        int indice = 0;
        for (indice = 0; indice < getCajas().length; indice++) {
            if (getCajas()[indice] == caja) {
                break;
            }
        }
        tiemposDeEspera[indice] += comprador.getTiempoEnFila();
    }

    public double getTiempoPromedio(int indiceCaja) {
        double te = (double) tiemposDeEspera[indiceCaja];
        double tc = (double) cajas[indiceCaja].getTotalCompradores();
        double r = te / tc;
        return r;
    }

    public int getDinero() {
        int total = 0;
        for (int i = 0; i < getCajas().length; i++) {
            total += getCajas()[i].getDinero();
        }
        return total;
    }

    public int getCompradores() {
        int total = 0;
        for (int i = 0; i < getCajas().length; i++) {
            total += getCajas()[i].getTotalCompradores();
        }
        return total;
    }

    public int getProductos() {
        int total = 0;
        for (int i = 0; i < getCajas().length; i++) {
            total += getCajas()[i].getTotalProductos();
        }
        return total;
    }

    public Caja[] getCajas() {
        return cajas;
    }

    public int getTotalCarritos() {
        return totalCarritos;
    }

    public int getTotalCanastas() {
        return totalCanastas;
    }

    public void setGeneradorComprador(int generadorComprador) {
        this.generadorComprador = generadorComprador;
    }

}
