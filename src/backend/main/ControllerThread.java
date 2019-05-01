package backend.main;
import backend.supermercado.Comprador;
import backend.supermercado.Controlador;
import frontend.*;

public final class ControllerThread extends Thread {

    //int pasos = 10;
    FrameView view;
    Controlador control;
    private int compradores;
    private int sleepTime = 120;
    private int carritos;
    private int canastas;
    private volatile boolean active;
    
    public ControllerThread(FrameView frame, int Compradores,
            int Carritos, int Canastas) {
        view = frame;
        compradores = Compradores;
        carritos = Carritos;
        canastas = Canastas;
        control = new Controlador(compradores, Carritos, Canastas);
        view.setControl(control);
    }

    @Override
    public void run() {
        active = true;
        try {
            do {
                sleep(sleepTime);
                control.siguienteTurno();
                view.showStatistics(control);
            } while (isActive());
        } catch (Exception ie) {
            System.out.println(ie.getMessage());
        }
    }

    public void setCompradores(int compradores) {
        this.compradores = compradores;
    }

    public void setCarritos(int carritos) {
        this.carritos = carritos;
    }

    public void setCanastas(int canastas) {
        this.canastas = canastas;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }
}
