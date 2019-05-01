package backend.main;

/** Clase para generar números aleatorios
 *
 * @author Alonso
 */
public class RandomGenerator {

    /** Retorna un valor aleatorio
     * @param max El valor máximo
     * @return Un valor aleatorio entre 0 y max
     */
    public static int getRandom(int max) {
        return (int) (Math.random() * max);
    }

    /** Retorna un valor aleatorio
     * @param max El valor máximo
     * @param min El valor mínimo
     * @return Un valor aleatorio entre min y max
     */
    public static int getRandom(int min, int max) {
        return (int) (min + Math.random() * (max - min));
    }
}
