package Principal;

import static java.lang.Thread.sleep;

/**
 * Clase Filósofo.
 * @author Mario Adrián Domínguez González de Eiris (adrian@eiris.es)
 */
public class Filosofo extends Thread{
    
    private Integer idFilosofo;
    private String estado;   
    private Tenedor tenedor_i;
    private Tenedor tenedor_d;
    private Portero portero;
    
    /**
     * Constructor de la clase filósofo.
     * @param id Id del filósofo
     * @param tenedor_i Tenedor asignado a la mano izquierda
     * @param tenedor_d Tenedor asignado a la mano derecha
     * @param portero Portero encargado de dar pases
     */
    public Filosofo(Integer id, Tenedor tenedor_i, Tenedor tenedor_d, Portero portero) {
        this.idFilosofo = id;
        this.estado = "Pensando";
        this.tenedor_i = tenedor_i;
        this.tenedor_d = tenedor_d;
        this.portero = portero;
    }
    
    /**
     * Método de inicialización de los hilos. Realizará en bucle las fases de los filósofos.
     */
    @Override
    public void run()  {
        while(true) {
            try {
                this.sentarse();
                this.comer();
                this.pensar();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Método para sentarse. Pedirá al portero un pase, y cuando se lo dé, el filósofo se sentará en la mesa.
     * @throws InterruptedException 
     */
    private void sentarse() throws InterruptedException {
        // Comprobamos si hay sitio en la mesa. Si lo hay, el portero nos da un pase.
        this.portero.darPase();
        this.estado = "Sentado";
        System.out.println("El filósofo " + idFilosofo + " se ha sentado.");
    }
    
    /**
     * Método para comer. El filósofo sentado cogerá ambos tenedores cuando estén disponibles, comerá, y los soltará de nuevo. Tardará 3 segundos en comer.
     * @throws InterruptedException 
     */
    private void comer() throws InterruptedException {
        // Cogemos los tenedores, si no están libres, esperamos a que lo estén.
        this.tenedor_i.cogerTenedor(idFilosofo);
        this.tenedor_d.cogerTenedor(idFilosofo);
        
        // Una vez tenemos ambos tenedores, comemos. Tarda 3 segundos en comer.
        this.estado = "Comiendo";
        System.out.println("El filósofo " + idFilosofo + " está comiendo.");
        sleep(3000);
        
        // Soltamos los tenedores para que otro filósofo los pueda usar.
        this.tenedor_i.soltarTenedor(idFilosofo);
        this.tenedor_d.soltarTenedor(idFilosofo);
    }
    
    /**
     * Método pensar. El filósofo se levantará de la mesa, liberará un pase que notificará al portero, y se dedicará a pensar.
     * @throws InterruptedException 
     */
    private void pensar() throws InterruptedException {
        // Cuando hemos comido, el portero nos saca de la mesa y libera un pase para que otro filósofo pueda pedirlo y el filósofo se queda pensando.
        this.portero.soltarPase();
        this.estado = "Pensando";
        System.out.println("El filósofo " + idFilosofo + " está pensando.");
    }
}
