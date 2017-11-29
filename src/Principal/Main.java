package Principal;

/**
 * Clase principal.
 * @author Mario Adrián Domínguez González de Eiris (adrian@eiris.es)
 */
public class Main {
    
    /**
     * Método principal de la clase.
     * 
     * El bucle de los filósofos será:
     * - Pensar
     * - Sentarse
     * - Comer
     * - Levantarse
     * - Pensar
     * 
     * @param args Sin uso
     */
    public static void main(String[] args){
        
        // El problema se define con 5 filósofos, pero pueden ser más.
        Integer nFilosofos = 5;
        
        // Creamos un portero, que se encargará de asignar pases para comer. Siempre tendrá un pase menos que el número de filósofos.
        Portero portero = new Portero(nFilosofos-1);
        
        // Creamos los tenedores, que serán igual al número de filósofos, y los incializamos a disponible.
        Tenedor[] tenedores = new Tenedor[nFilosofos];
        for (Integer i = 0; i < tenedores.length; i++) {
            tenedores[i] = new Tenedor(i);
        }
        
        // Creamos los filósofos, le asignamos su tenedor izquierdo y derecho. También le pasamos el objeto portero para saber si hay sitio en la mesa.
        Thread[] filosofos = new Filosofo[nFilosofos];
        for (Integer i = 0; i < filosofos.length; i++) {
            Integer tenedor_i = i;
            Integer tenedor_d = i+1;
            if (i == filosofos.length-1) tenedor_d=0;
            filosofos[i] = new Filosofo(i, tenedores[tenedor_i], tenedores[tenedor_d], portero);
        }
        
        // Inicializamos los hilos de los filósofos para que comiencen el bucle.
        for (Integer i = 0; i < filosofos.length; i++) {
            filosofos[i].start();
        }
    }
}
