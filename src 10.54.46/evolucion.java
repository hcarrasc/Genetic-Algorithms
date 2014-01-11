
import java.util.Arrays;
import java.util.Random;

public class evolucion {
    
    int sizeCromosoma; 
    int sizePoblacion;
    
    public evolucion(int sizeCromosoma, int sizePoblacion){
        this.sizeCromosoma = sizeCromosoma;
        this.sizePoblacion = sizePoblacion;
    }
    
    public String generarCromosoma(){
        
        Random r = new Random();
        String cromosoma = new String();
        
        // se genera un cromosoma de 10 genes aleatoriamente
        for(int i=0; i<sizeCromosoma; i++){
            cromosoma = cromosoma + String.valueOf(r.nextInt(2));
        }
        
        System.out.print("\t"+cromosoma+" "); System.out.println(obtenerFenotipo(cromosoma));
        return cromosoma;
    }
    
    public String[] generarPoblacion(){
        
        String[] poblacion = new String[sizePoblacion];
        
        System.out.println("\n\tPOBLACION INICIAL");
        for(int i=0; i<sizePoblacion; i++){
            poblacion[i] = generarCromosoma();
        }
        return poblacion;
    }
    
    public int obtenerFenotipo(String cromosoma){
        
        int fenotipo = 0;
        
        try{
             fenotipo = Integer.parseInt(cromosoma,2);   
        }catch(Exception ex){
             System.out.println("El cromosoma "+cromosoma+" contiene genes inválidos");
        }
        
        return fenotipo;
    }
    
    public int obtenerPunto(int largoCromosoma){
        
        Random r = new Random();
        return r.nextInt(largoCromosoma);
    }
    
    public String[] hacerCrucePunto (String cromosoma1, String cromosoma2){
        
        String[] hijos = new String[2]; 
        
        int corte = obtenerPunto(sizeCromosoma+1);
        
        String parte1;
        String parte2;
        String hijo;
        
        parte1 = cromosoma1.substring(0, corte);
        parte2 = cromosoma2.substring(corte, sizeCromosoma);
        hijo   = parte1.concat(parte2);
        hijos[0] = hijo;
        
        parte1 = cromosoma2.substring(0, corte);
        parte2 = cromosoma1.substring(corte, sizeCromosoma);
        hijo   = parte1.concat(parte2);
        hijos[1] = hijo;
        
        return hijos;
    }
    
    public String mutarCromosoma(String cromosoma){
        
        String x;
        int mutar  = obtenerPunto(cromosoma.length());
        char[] aux = cromosoma.toCharArray();
        if(aux[mutar] == '1'){
            aux[mutar] = '0';
        }else{
            aux[mutar] = '1';
        }
        return  x = new String(aux); 
    }
    
    public int fitness(int fenotipo){
        
        // funcion fitness para el maximo de x^2
        return fenotipo*fenotipo;
    }
    
    private int calcularTotalFenotipo(String[] poblacion){
        
        int total=0;
        for(int i=0; i<poblacion.length; i++){
            total+= obtenerFenotipo(poblacion[i]);
        }
        return total;
    }
    
    public String[] NuevaPoblacionRuleta(String[] poblacion){
        
        String[] NuevaPoblacion = new String[sizePoblacion];
        String[] cruce          = new String[2];
        int indiceCruce=0;
        float [] ve = new float[sizePoblacion];
        int   total = calcularTotalFenotipo(poblacion);
        int ruleta = 0;
        int suma   = 0;
        int individuos = 0;
        
        for(int i=0; i<poblacion.length; i++){
            ve[i] = (obtenerFenotipo(poblacion[i])*100)/total;
        }
        
        while(individuos<sizePoblacion){
            
            ruleta = obtenerPunto(100);
            for(int i=0; i<ve.length;i++){
                suma+=ve[i];
                
                if(ruleta<suma){
                    cruce[indiceCruce] = poblacion[i];
                    indiceCruce++;
                    
                    // Si el algoritmo encuentra los 2 cromosomas para
                    // reproducirse, entonces los recombina para generar
                    // un cromosoma hijo
                    if(indiceCruce==2){
                        
                        indiceCruce=0;
                        cruce = hacerCrucePunto(cruce[indiceCruce],cruce[indiceCruce+1]);
                        NuevaPoblacion[individuos] = cruce[0];
                        individuos++;
                        NuevaPoblacion[individuos] = cruce[1];
                        individuos++;
                    }
                    break;
                }
            }
        }
        return NuevaPoblacion;
    }

    public int obtenerMayor(String[] poblacion){
        
        int mayor=0;
        for(int i=0; i<poblacion.length;i++){
            if(mayor<obtenerFenotipo(poblacion[i])){
                mayor = obtenerFenotipo(poblacion[i]);
            }
        }
        return mayor; 
    }


}
