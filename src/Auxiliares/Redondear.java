
package Auxiliares;

/**
 *
 * @author WildoJ
 */
public class Redondear {
    
    public Redondear(){
    
    }
    public double rD(double valorInicial) {
        double parteEntera, resultado;
        double res=0.0;
        double aux=0.0;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        aux=resultado-parteEntera;
        if(aux>=0.50){
            res=parteEntera+1;
        }
        else{
            res=valorInicial;
        }
        return res;
    }
}
