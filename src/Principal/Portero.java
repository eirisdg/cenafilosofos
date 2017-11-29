package Principal;

/**
 * Clase Portero. Encargada de asignar y quitar pases.
 * @author Mario Adrián Domínguez González de Eiris (adrian@eiris.es)
 */
public class Portero {
    
    private Integer pases;
    
    /**
     * Método constructor.
     * @param pases Número de pases a asignar.
     */
    public Portero(Integer pases){
        this.pases = pases;
    }
    
    /**
     * Método darPase. Dará un pase y restará de los disponibles. Es un método sincronizado, puesto que cuando se quede sin pases que asignar, quedará esperando hasta que haya alguno libre.
     * @throws InterruptedException Excepción que salta si el hilo se interrumpe.
     */
    public synchronized void darPase() throws InterruptedException {
        // Si no hay pases libres esperamos hasta que los haya.
        while (this.pases==0) {
            wait();
        }
        // Cuando hay pases, reducimos el número de pases libres.
        this.pases--;
    }
    
    /**
     * Método soltarPase. Liberará un pase y lo sumará a los disponibles. Es un método sincronizado que notificará a quien esté esperando pases libres.
     * @throws InterruptedException Excepción que salta si el hilo se interrumpe.
     */
    public synchronized void soltarPase() throws InterruptedException {
        // Cuando ya ha comido el filósofo, libera el pase y notifica al portero para que lo ponga libre. 
        this.pases++;
        notify();
    }
}
