
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String Args[]){
        
        int t=0;
        int agenteMutador = 0;
        int sizeCromosoma = 4;
        int sizePoblacion = 10;
        evolucion p = new evolucion(sizeCromosoma, sizePoblacion);
        String poblacion[] = new String[sizePoblacion];
        
        // Se genera la poblacion inicial de manera aleatoria   
        poblacion = p.generarPoblacion();
        
        while(t<=500){
            
            System.out.println("Fitness "+t+"="+p.fitness(p.obtenerMayor(poblacion)));
            poblacion = p.NuevaPoblacionRuleta(poblacion);
            agenteMutador = p.obtenerPunto(poblacion.length);
            p.mutarCromosoma(poblacion[agenteMutador]);
            agenteMutador = p.obtenerPunto(poblacion.length);
            p.mutarCromosoma(poblacion[agenteMutador]);
            t++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("La evolucion eligio :"+p.fitness(p.obtenerMayor(poblacion)));
    
        try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
