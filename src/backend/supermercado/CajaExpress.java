/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.supermercado;

/**
 *
 * @author Alonso
 */
public class CajaExpress extends Caja {

    public CajaExpress(Controlador control) {
        super(control);
        maxArticulosPersona = 10;
        colaEsperaMaxima = 2;
    }
}
